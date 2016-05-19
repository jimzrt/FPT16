package fpt.com.main.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fpt.com.main.Base.Product;

public class JDBCConnector {

	Connection connection;

	public JDBCConnector() {

	}

	public void connect() {
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
			return;

		}

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://java.is.uni-due.de/ws1011", "ws1011",
					"ftpw10");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("Connected");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	public void getInfo() {
		DatabaseMetaData databaseMetaData = null;
		try {
			databaseMetaData = connection.getMetaData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			System.out.println("URL: " + databaseMetaData.getURL());
			System.out.println("username: " + databaseMetaData.getUserName());
			ResultSet rs = databaseMetaData.getTables(null, null, "%", null);
			while (rs.next()) {
				System.out.println("Table: " + rs.getString(3));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public ArrayList<Product> getProducts() {
		Product product = null;
		ArrayList<Product> products = new ArrayList<Product>();
		try (PreparedStatement pstmt = connection
				.prepareStatement("SELECT id,name,price,quantity FROM products ORDER BY id DESC");) {
			pstmt.setMaxRows(100);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			while (rs != null && rs.next()) {
				product = new Product();
				product.setName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setQuantity(rs.getInt(4));
				product.setId(rs.getLong(1));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return products;
	}

	public void close() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public long insert(String name, double price, int quantity) {

		long key = 0;
		try (PreparedStatement pstmt = connection.prepareStatement(
				"INSERT INTO products(name,price,quantity) VALUES (?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);) {
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			pstmt.setInt(3, quantity);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getLong(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return key;

	}

	public void insert(Product product) {
		if (product.getId() == -1) {
			long key = insert(product.getName(), product.getPrice(),
					product.getQuantity());

			product.setId(key);
		} else {
			// update(product);
		}

	}

	public Product read(long productId) {
		Product product = null;
		try (PreparedStatement pstmt = connection
				.prepareStatement("SELECT id,name,price,quantity FROM products WHERE id=?");) {
			pstmt.setLong(1, productId);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			if (rs != null && rs.next()) {
				product = new Product();
				product.setName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setQuantity(rs.getInt(4));
				product.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return product;
	}
}
