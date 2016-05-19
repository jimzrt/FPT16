package fpt.com.main.Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.openjpa.persistence.OpenJPAPersistence;

import fpt.com.main.Base.Product;

public class OpenJPAConnector {

	EntityManagerFactory fac;
	EntityManager e;

	public void insert(Product product) {
		if (product.getId() == -1) {
			product.setId(0);
			if (fac == null)
				fac = Persistence.createEntityManagerFactory("openjpa", System.getProperties());

			if (e == null)
				e = fac.createEntityManager();

			EntityTransaction t = e.getTransaction();
			t.begin();
			e.persist(product);
			t.commit();

		}
	}

	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();

		if (fac == null)
			fac = Persistence.createEntityManagerFactory("openjpa");
		if (e == null)
			e = fac.createEntityManager();

		EntityTransaction t = e.getTransaction();

		t.begin();
		Query q = e.createQuery("SELECT p FROM Product p ORDER BY p.id DESC");
		q.setMaxResults(100);
		for (Object o : q.getResultList()) {
			products.add((Product) o);
		}
		t.commit();

		e.close();
		fac.close();

		return products;
	}

	public void close() {

		if (e != null)
			e.close();
		if (fac != null)
			fac.close();

	}

}
