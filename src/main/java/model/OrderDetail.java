package model;

import java.io.Serializable;

public class OrderDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5910597013925139645L;
	/**
	 * 
	 */

	private int orderID,quantity;
	private double price;
	private Product product;
	
	public OrderDetail() {
		
	}
	public OrderDetail(int orderID, int quantity, Product product) {
		super();
		this.orderID = orderID;
		this.quantity = quantity;
		this.product = product;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	public double getPrice() {
		return this.product.getPrice();
	}
	public void setPrice(double price) {
		this.price = this.product.getPrice();
	}
	
	
}
