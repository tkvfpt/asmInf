package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {
	@Id
	@Column(name="code")
	String code;
	@Column(name="name")
	String name;
	@Column(name="price")
	float price;
	@Column(name="image")
	byte[] image;
	@Column(name="quantity")
	int quantity;
	public Products(String code, String name, float price, byte[] image, int quantity) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.image = image;
		this.quantity = quantity;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Products() {
		super();
	}
	
}
