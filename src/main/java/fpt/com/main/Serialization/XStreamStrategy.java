package fpt.com.main.Serialization;

import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public class XStreamStrategy implements SerializableStrategy {

	XStream xstream = new XStream(new DomDriver());

	FileWriter fw;
	FileReader fr;
	ObjectOutputStream out;
	ObjectInputStream in;

	private String fileName = "XStreamSer.xml";

	@Override
	public Product readObject() throws IOException {
		xstream.alias("ware", fpt.com.main.Base.Product.class);
		xstream.registerConverter(new ProductConverter());

		if (fr == null) {
			if (!Files.exists(Paths.get("", fileName)))
				return null;
			fr = new FileReader(fileName);
			in = xstream.createObjectInputStream(fr);
		}
		try {
			return (Product) in.readObject();
		} catch (ClassNotFoundException | EOFException e) {
			return null;
		}

	}

	@Override
	public void writeObject(Product obj) throws IOException {
		xstream.alias("ware", fpt.com.main.Base.Product.class);
		xstream.registerConverter(new ProductConverter());
		if (fw == null) {
			fw = new FileWriter(fileName);
			out = xstream.createObjectOutputStream(fw, "waren");
		}

		out.writeObject(obj);

	}

	@Override
	public void close() throws IOException {
		if (fw != null) {
			out.close();
			out = null;
			fw.close();
			fw = null;
		}
		if (fr != null) {
			in.close();
			in = null;
			fr.close();
			fr = null;
		}

	}

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {

	}

}
