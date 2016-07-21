package Warehouse;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import fpt.com.Product;

public class Order implements fpt.com.Order{
	


	public List<fpt.com.Product> products = new ArrayList<fpt.com.Product>();
	private double sum;
	private int quantity;
	
	


	@Override
	public Iterator<fpt.com.Product> iterator() {

		return products.iterator();
	}


	@Override
	public boolean add(fpt.com.Product e) {
		quantity += e.getQuantity();
		sum += e.getPrice()*e.getQuantity();
		for(Product product : products){
			if(product.getName() == e.getName() && product.getPrice()== e.getPrice()){
				products.get(products.indexOf(product)).setQuantity(products.get(products.indexOf(product)).getQuantity() + e.getQuantity());
				return true;
			}
		}
		return products.add(e);
	}


	@Override
	public boolean delete(fpt.com.Product p) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public fpt.com.Product findProductById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public fpt.com.Product findProductByName(String name) {
		// TODO Auto-generated method stub
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


	
}
