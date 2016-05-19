package fpt.com.main.Serialization;

import java.io.IOException;
import java.util.ArrayList;

import fpt.com.Product;
import fpt.com.db.AbstractDatabaseStrategy;
import fpt.com.main.Database.JDBCConnector;

public class DatabaseStrategy extends AbstractDatabaseStrategy {

	JDBCConnector connection = null;
	int count = 0;
	ArrayList<fpt.com.main.Base.Product> products = null;

	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method stub
		if (connection == null) {
			connection = new JDBCConnector();
			connection.connect();
			products = connection.getProducts();
		}


		if (count < 100)
			return products.get(99 - count++);
		else {
			count = 0;
			return null;

		}

	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub
		if (connection == null) {
			connection = new JDBCConnector();
			connection.connect();
		}

		connection.insert((fpt.com.main.Base.Product) obj);

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}

	@Override
	public void open() throws IOException {
		// TODO Auto-generated method stub

		// connection.getInfo();
		// count = connection.getCount();

	}

}
