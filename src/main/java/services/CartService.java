package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import dao.DBConnect;
import dao.ProductDAO;
import impl_services.ICartService;
import model.Cart;
import model.Product;
import model.ProductCart;

public class CartService extends DBConnect implements ICartService {
	

	@Override
	public void add2Cart(HashMap<Integer, ProductCart> cart, String accID) {
		String cartID = new CartService().getCartID(accID);
		System.out.println("Add to Cart:");
		try {
			connect = getConnection();

			if (cartID == null) {
				// tao moi cart
				System.out.println("Create new Cart");
				String sql_insert_cart = "insert into Cart(AccountID) values(?)";
				pres = connect.prepareStatement(sql_insert_cart);
				pres.setString(1, accID);
				pres.executeUpdate();
			}
			// them san pham vao cart-detail
			/**
			 * Cart{ Product Cart : { quantity int ,product func : incrementQuantity } }
			 * 
			 * getKey = id-Cart-Detail*
			 */
			System.out.println("Add product into Cart");
			cartID = new CartService().getCartID(accID);
			String sql_product_in_cartDetail = "select * from CartDetail where cartID = ? and productID = ?";
			for (Map.Entry<Integer, ProductCart> entry : cart.entrySet()) {
				// Kiem tra san pham da ton tai trong DB chua?

				ProductCart productCart = entry.getValue();
				int productID = productCart.getProduct().getId();
				pres = connect.prepareStatement(sql_product_in_cartDetail);
				pres.setString(1, cartID);
				pres.setInt(2, productID);
				res = pres.executeQuery();
				if (res.next()) {
					System.out.println("San pham da ton tai ==> chi cap nhat so luong");
					// neu ton tai thi cap nhat so luong

					String sql_update_quantity = "update CartDetail \r\n" + "	set quantity = ? \r\n"
							+ "	where cartID =? and productID=?";

					pres = connect.prepareStatement(sql_update_quantity);
					pres.setInt(1, productCart.getQuantity());
					pres.setString(2, cartID);
					pres.setInt(3, productID);

					pres.executeUpdate();
				} else {
					// neu chua ton tai thi insert san pham vao bang CartDetail
					System.out.println("San pham chua ton tai ==> Them moi");
					String sql_insert_product_to_CartDetail = "insert into CartDetail(productID,quantity,cartID) values(?,?,?)";
					pres = connect.prepareStatement(sql_insert_product_to_CartDetail);
					pres.setInt(1, productID);
					pres.setInt(2, productCart.getQuantity());
					pres.setString(3, cartID);
					pres.executeUpdate();
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String getCartID(String accountID) {
		String sql = "select * from Cart where AccountID = ?";

		try {
			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, accountID);
			res = pres.executeQuery();
			if (res.next()) {
				return res.getString(2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	

	@Override
	public Cart getCart(String accountID) {

		Cart cart = new Cart();
		int cartID = 0;
		Map<Integer, ProductCart> productCartFinnal = new HashMap<Integer, ProductCart>();
		String sql = "select * from Cart where AccountID = ?";
		try {
			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setString(1, accountID);
			res = pres.executeQuery();

			while (res.next()) {
				cartID = res.getInt(4);
				int quantity = res.getInt(2);
				int productID = res.getInt(3);
//				System.out.println(productID);
				Product product = new ProductDAO().getProductById(String.valueOf(productID));
//				System.out.println(product);
				ProductCart productCart = new ProductCart(quantity, product);

				productCartFinnal.put(Integer.valueOf(productID), productCart);
			}
			if (cartID != 0) {
				cart.setCartID(cartID);
				cart.setProductCart(productCartFinnal);
				return cart;
			}

		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void deleteProductCart(int cartID, int productID) {
		String sql = "delete from Cart where id=? and ProductID =?";
		try {
			connect = new DBConnect().getConnection();
			pres = connect.prepareStatement(sql);
			pres.setInt(1, cartID);
			pres.setInt(2, productID);
			pres.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	

	@Override
	public void deleteCart(int acccountID) {
		String sql="delete from Cart where AccountID=?";
		try {
			connect = new DBConnect().getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, acccountID);
			
			pres.executeUpdate();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void addToCart(Cart cart) {
			
			System.out.println("CartService");
			
			Set<Integer> keySet = cart.getProductCart().keySet();
			for(Integer key : keySet) {
				System.out.println(key);
	//			Product product = new DAO().getProductById(String.valueOf(key));
				try {
					connect = new DBConnect().getConnection();
					if(new CartService().checkProductCart(key, cart.getCartID())==false) {
						// insert product cart vao CArt
						String sql="insert into Cart (AccountID,Amount,ProductID,id) values(?,?,?,?)";
						pres = connect.prepareStatement(sql);
						pres.setInt(1, cart.getCartID());
						pres.setInt(2, cart.getProductCart().get(key).getQuantity());
						pres.setInt(3, key);
						pres.setInt(4, cart.getCartID());
						pres.executeUpdate();
						
					}else {
						// update quantity
						String sql = "update Cart set Amount=? where id=? and ProductID=?";
						pres = connect.prepareStatement(sql);
						pres.setInt(1, cart.getProductCart().get(key).getQuantity());
						pres.setInt(2, cart.getCartID());
						pres.setInt(3, key);
						pres.executeUpdate();
					
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	@Override
	public boolean checkProductCart(int proID, int cartID) {
		String sql="select * from Cart where ProductID=? and id=?";
		try {
			connect = getConnection();
			pres = connect.prepareStatement(sql);
			pres.setInt(1, proID);
			pres.setInt(2, cartID);
			res = pres.executeQuery();
			if(res.next()) {
				return true;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return false;
	}

	

}
