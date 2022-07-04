package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private int cartID;
	private Map<Integer, ProductCart> productCart;
	public Cart() {
		productCart = new HashMap<Integer, ProductCart>()
				;
	}
	public Cart(int cartID, HashMap<Integer, ProductCart> productCart) {
		super();
		this.setCartID(cartID);
		this.setProductCart(productCart);
	}
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public HashMap<Integer, ProductCart> getProductCart() {
		return (HashMap<Integer, ProductCart>) productCart;
	}
	public void setProductCart(Map<Integer, ProductCart> productCartFinnal) {
		this.productCart = (HashMap<Integer, ProductCart>) productCartFinnal;
	}
	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", productCart=" + productCart + "]";
	}
	public double getTotalMoney() {
		double total =0.0;
		for(Integer p : this.productCart.keySet()) {
			total+=this.productCart.get(p).getQuantity()*(this.productCart.get(p).getProduct().getPrice());
		}
		
		
		return total;
	}
	
}
