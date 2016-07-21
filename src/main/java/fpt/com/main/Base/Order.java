package fpt.com.main.Base;

import fpt.com.Product;

public class Order extends java.util.ArrayList<Product> implements
		fpt.com.Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4733781991177978714L;
	private double sum;
	private int quantity;

	@Override
	public boolean add(Product p) {
		sum += p.getPrice() * p.getQuantity();
		quantity += p.getQuantity();
		return super.add(p);
	}

	@Override
	public boolean delete(Product p) {
		sum -= p.getPrice() * p.getQuantity();
		quantity -= p.getQuantity();
		return super.remove(p);
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

	@Override
	public double getSum() {
		return sum;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}
	
	public void print(){
		for(Product product : this){
			System.out.println(product);
		}
	}

}
