package fpt.com.main.Serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import fpt.com.Product;

public class BinaryStrategy implements fpt.com.SerializableStrategy {

	OutputStream fo;
	ObjectOutputStream os;
	InputStream fi;
	ObjectInputStream is;

	private String fileName = "products.ser";

	@Override
	public Product readObject() throws IOException {
		if (fi == null) {
			if (!Files.exists(Paths.get("", fileName)))
				return null;
			try {
				fi = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				is = new ObjectInputStream(fi);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			return (Product) is.readObject();
		} catch (ClassNotFoundException | IOException e) {
			//test
			return null;
		}

	}

	@Override
	public void writeObject(Product obj) throws IOException {
		if (fo == null) {
			try {
				fo = new FileOutputStream(fileName);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				os = new ObjectOutputStream(fo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			os.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void close() throws IOException {
		if (fi != null) {
			try {
				fi.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			fi = null;
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			is = null;
		}
		if (os != null) {

			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			os = null;
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fo = null;
		}

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {

	}

}
