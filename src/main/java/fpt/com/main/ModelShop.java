package fpt.com.main;

import fpt.com.Product;
import javafx.collections.*;

public class ModelShop extends
		ModifiableObservableListBase<fpt.com.main.Product> {

	public ProductList productlist;

	public ModelShop() {

		productlist = new ProductList();
	}

	@Override
	protected void doAdd(int arg0, fpt.com.main.Product arg1) {
		productlist.add(arg0, arg1);
	}

	@Override
	protected fpt.com.main.Product doRemove(int arg0) {
		return (fpt.com.main.Product) productlist.remove(arg0);

	}

	@Override
	protected fpt.com.main.Product doSet(int arg0, fpt.com.main.Product arg1) {
		return (fpt.com.main.Product) productlist.set(arg0, arg1);

	}

	@Override
	public fpt.com.main.Product get(int arg0) {
		return (fpt.com.main.Product) productlist.get(arg0);

	}

	@Override
	public int size() {
		return productlist.size();

	}

	public boolean delete(Product product) {
		return productlist.delete(product);

	}

	public Product findProductById(long id) {
		return productlist.findProductById(id);

	}

	public Product findProductByName(String name) {
		return productlist.findProductByName(name);

	}

	public boolean add(Product e) {
		return productlist.add(e);
	}

}
