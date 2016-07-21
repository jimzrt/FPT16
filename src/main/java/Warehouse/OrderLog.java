package Warehouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fpt.com.Product;

public class OrderLog {
	
	List<Order> orders = new ArrayList<Order>();
	
	Order mainOrder = new Order();
	
	public void addOrder(Order order){
		
		System.out.println("Order eingegangen:");
		Iterator<Product> iterator = order.iterator();
		while(iterator.hasNext()){
			Product product = iterator.next();
			System.out.println(product.getName() + "   " + product.getQuantity() + "   " + product.getQuantity() * product.getPrice());
			
			mainOrder.add(product);
		}
		
		
		orders.add(order);
		
		print();
	}
	
	public void print(){
		System.out.println("");
		System.out.println("Orders gesamt:");
		System.out.println("=================================");
		Iterator<Product> iterator = mainOrder.iterator();

		while(iterator.hasNext()){
			Product product = iterator.next();
			System.out.println(product.getName() + "   " + product.getQuantity() + "   " + product.getQuantity() * product.getPrice());
			
		}
		System.out.println("=================================");
		System.out.println("Gesamtanzahl: " + mainOrder.getQuantity());
		System.out.println("Gesamtwert: " + mainOrder.getSum());
		

		
	}

}
