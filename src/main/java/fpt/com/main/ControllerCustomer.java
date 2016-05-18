package fpt.com.main;

import fpt.com.main.Product;

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
