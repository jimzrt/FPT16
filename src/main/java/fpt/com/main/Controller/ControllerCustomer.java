package fpt.com.main.Controller;

import fpt.com.main.ModelShop;
import fpt.com.main.Base.Product;
import fpt.com.main.Views.ViewCustomer;

public class ControllerCustomer {

	public void link(ModelShop model, ViewCustomer view) {

		view.setList(model);

		view.addEventHandler(e -> {

			int selectedIndex = e.getTableView().getSelectionModel()
					.getSelectedIndex();
			Product p = e.getRowValue();
			p.setName(e.getNewValue());
			model.set(selectedIndex, p);

		});

	}

}
