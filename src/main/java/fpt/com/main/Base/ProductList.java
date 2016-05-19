package fpt.com.main.Base;

import fpt.com.Product;

public class ProductList extends java.util.ArrayList<Product> implements
		fpt.com.ProductList {

	/**
	 * 
	 */
	private static final long serialVersionUID = -318470093051559450L;

	@Override
	public boolean delete(Product product) {
		return super.remove(product);
	}

	@Override
	public Product findProductById(long id) {
		for (Product prod : this)
			if (prod.getId() == id)
				return prod;
		return null;
	}

	@Override
	public Product findProductByName(String name) {
		for (Product prod : this)
			if (prod.getName() == name)
				return prod;
		return null;
	}

}
