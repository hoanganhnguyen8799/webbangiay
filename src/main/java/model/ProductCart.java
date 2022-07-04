package model;

public class ProductCart {
	
	private int quantity;
	private Product product;
	public ProductCart(int quantity, Product product) {
		this.setQuantity(quantity);
		this.setProduct(product);
	}
	
	public void incrementQuantity() {
		this.setQuantity(this.getQuantity() + 1);
	}
	public void reduceQuantity() {
		if(this.getQuantity()==0) {
			this.setQuantity(0);
		}else
		this.setQuantity(this.getQuantity() - 1);
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
	@Override
	public String toString() {
		return "ProductCart [quantity=" + quantity + ", product=" + product + "]";
	}

}
