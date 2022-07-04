package impl_services;

import java.util.HashMap;

import model.Cart;
import model.ProductCart;

public interface ICartService {
	public void add2Cart(HashMap<Integer,ProductCart> cart, String accID);
	public String getCartID(String accountID);	
	public Cart getCart(String accountID);
	public void deleteCart(int acccountID);
	public void addToCart(Cart cart);
	public boolean checkProductCart(int proID, int cartID);
}
