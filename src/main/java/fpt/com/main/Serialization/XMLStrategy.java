package fpt.com.main.Serialization;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class XMLStrategy implements SerializableStrategy {

	OutputStream fo;
	XMLEncoder encoder;
	InputStream fi;
	XMLDecoder decoder;

	private String fileName = "products.xml";

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
			decoder = new XMLDecoder(fi);
		}
		try {
			return (Product) decoder.readObject();

		} catch (ArrayIndexOutOfBoundsException e) {
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
			encoder = new XMLEncoder(fo);
		}
		encoder.writeObject(obj);
		encoder.flush();

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

		if (fo != null) {
			encoder.close();
			fo.close();

			fo = null;
			encoder = null;
		}
		if (fi != null) {
			fi.close();

			decoder.close();
			fi = null;
			decoder = null;
		}

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {

	}

}
