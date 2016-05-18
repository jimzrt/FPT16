package fpt.com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.openjpa.persistence.OpenJPAPersistence;

public class OpenJPAConnector {

	EntityManagerFactory fac;
	EntityManager e;

	public void insert(Product product) {
		if (product.getId() == -1) {
			product.setId(0);
			if (fac == null)
				fac = getWithoutConfig();
			// fac = Persistence.createEntityManagerFactory(
			// "openjpa", System.getProperties());

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
			fac = getWithoutConfig();
		// fac = Persistence.createEntityManagerFactory(
		// "openjpa");
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

	public static EntityManagerFactory getWithoutConfig() {

		Map<String, String> map = new HashMap<String, String>();

		map.put("openjpa.ConnectionURL",
				"jdbc:postgresql://java.is.uni-due.de/ws1011");
		map.put("openjpa.ConnectionDriverName", "org.postgresql.Driver");
		map.put("openjpa.ConnectionUserName", "ws1011");
		map.put("openjpa.ConnectionPassword", "ftpw10");
		map.put("openjpa.RuntimeUnenhancedClasses", "supported");
		map.put("openjpa.jdbc.SynchronizeMappings", "false");

		map.put("openjpa.MetaDataFactory",
				"jpa(Types=" + Product.class.getName() + ")");

		return OpenJPAPersistence.getEntityManagerFactory(map);

	}

}
