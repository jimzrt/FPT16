package fpt.com.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import fpt.com.Product;

public class BinaryStrategy implements fpt.com.SerializableStrategy {
	
	FileOutputStream fo;
	ObjectOutputStream os;
	FileInputStream fi;
	ObjectInputStream is;

	@Override
	public Product readObject() throws IOException {
		// TODO Auto-generated method stub
		if (fi == null) {
			try {
				fi = new FileInputStream("products.ser");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is = new ObjectInputStream(fi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			return (Product) is.readObject();
		} catch (ClassNotFoundException | IOException e) {

			return null;
		}

	}

	@Override
	public void writeObject(Product obj) throws IOException {
		// TODO Auto-generated method stub
		if (fo == null) {
			try {
				fo = new FileOutputStream("products.ser");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				os = new ObjectOutputStream(fo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			os.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if (fi != null) {
			try {
				fi.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fi = null;
			try {
				is.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			is = null;
		}
		if (os != null) {

			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			os = null;
			try {
				fo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fo = null;
		}

	}
		
	

	@Override
	public void open(InputStream input, OutputStream output) throws IOException {
		// TODO Auto-generated method stub
		
		if(output == null){
			fo = new FileOutputStream("products.ser");
			os = new ObjectOutputStream(fo);
		}
		else
			fo = (FileOutputStream) output;
		
		
		if(input == null){
		fi = new FileInputStream("products.ser");
		is = new ObjectInputStream(fi);
		}
		else
			fi = (FileInputStream) input;
		
		

		
	}

}
