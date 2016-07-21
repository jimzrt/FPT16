package fpt.com.main.Base;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.openjpa.persistence.Persistent;
import org.apache.openjpa.persistence.jdbc.Strategy;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAliasType;

import javafx.beans.value.ObservableValue;
import javafx.beans.property.*;

@XStreamAliasType("ware")
@XStreamAlias("ware")
@Entity()
@Table(name = "products")
public class Product implements fpt.com.Product, Externalizable {
	
	public Product(String name, double price, int quantity){
		this.name.set(name);
		this.price.set(price);
		this.quantity.set(quantity);
	}

	public Product() {
	}

	@Persistent
	@Strategy("fpt.com.db.StringPropertyValueHandler")
	private SimpleStringProperty name = new SimpleStringProperty();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "products_SEQ")
	private long id;
	
	@Persistent
	@Strategy("fpt.com.db.DoublePropertyValueHandler")
	private SimpleDoubleProperty price = new SimpleDoubleProperty();
	
	@Persistent
	@Strategy("fpt.com.db.IntegerPropertyValueHandler")
	private SimpleIntegerProperty quantity = new SimpleIntegerProperty();
	

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	@Override
	public double getPrice() {
		return price.get();
	}

	@Override
	public void setPrice(double price) {
		this.price.set(price);

	}

	@Override
	public int getQuantity() {
		return quantity.get();
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity.set(quantity);

	}

	@Override
	public String getName() {
		return name.get();
	}

	@Override
	public void setName(String name) {
		this.name.set(name);

	}

	public String toString() {
		return name.get() + " " + price.get() + " " + quantity.get() + " " + id;

	}

	@Override
	public ObservableValue<String> nameProperty() {
		return name;
	}

	@Override
	public ObservableValue<Number> priceProperty() {
		return price;
	}

	@Override
	public ObservableValue<Number> quantityProperty() {
		return quantity;
	}
	

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {

		out.writeLong(getId());
		out.writeObject(getName());
		out.writeDouble(getPrice());
		out.writeInt(getQuantity());

	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {

		setId(in.readLong());
		setName((String) in.readObject());
		setPrice(in.readDouble());
		setQuantity(in.readInt());

	}

	
	

}
