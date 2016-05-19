package fpt.com.main.Controller;

import java.io.IOException;
import java.util.Iterator;

import fpt.com.SerializableStrategy;
import fpt.com.main.ModelShop;
import fpt.com.main.Base.Product;
import fpt.com.main.Serialization.BinaryStrategy;
import fpt.com.main.Serialization.DatabaseStrategy;
import fpt.com.main.Serialization.OpenJPAStrategy;
import fpt.com.main.Serialization.XMLStrategy;
import fpt.com.main.Serialization.XStreamStrategy;
import fpt.com.main.Utils.IDGenerator;
import fpt.com.main.Views.ViewShop;

public class ControllerShop {

	ModelShop model;
	IDGenerator ids;
	SerializableStrategy strat;

	public void link(ModelShop model, ViewShop view) {

		strat = new BinaryStrategy();

		ids = new IDGenerator();
		this.model = model;

		view.setList(model);

		view.addEventHandler(e -> {
			if (e.toString().contains("Add")) {
				if (!view.getNameInput().isEmpty() && !view.getPriceInput().isEmpty()
						&& !view.getCountInput().isEmpty()) {

					Product p = new Product();
					p.setName(view.getNameInput());
					String regex = "[0-9]+";
					if (view.getPriceInput().matches(regex) && view.getCountInput().matches(regex)) {
						p.setPrice(Double.parseDouble(view.getPriceInput()));
						p.setQuantity(Integer.parseInt(view.getCountInput()));
						// p.setId(-1);
						try {
							p.setId(ids.giveId());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						model.add(p);
					}
				}

			}
			if (e.toString().contains("Delete")) {
				if (view.getSelectedIndex() != -1)
					model.remove(view.getSelectedIndex());

			}
			if (e.toString().contains("Load")) {
				load();
			}

			if (e.toString().contains("Save")) {
				try {
					save();
				} catch (Exception e1) {
					e1.printStackTrace();
					return;
				}
				load();
			}

			if (e.toString().contains("ChoiceBox")) {
				int choice = view.getChoice();
				switch (choice) {
				case 0:
					strat = new BinaryStrategy();
					break;
				case 1:
					strat = new XMLStrategy();
					break;
				case 2:
					strat = new XStreamStrategy();
					break;
				case 3:
					strat = new DatabaseStrategy();
					break;
				case 4:
					strat = new OpenJPAStrategy();
					break;
				}
				;
			}
		});

	}

	public void load() {

		model.clear();
		ids.clear();

		Product product;
		try {
			while ((product = (Product) strat.readObject()) != null) {
				ids.addId(product.getId());
				model.add((Product) product);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			strat.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void save() throws Exception {

		Iterator<Product> itp = model.iterator();

		if (model.size() < 5) {
			throw new Exception("Die Warenliste sollte mindestens 5 Objekte enthalten");

		}

		while (itp.hasNext()) {
			try {
				strat.writeObject(itp.next());

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		try {
			strat.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
