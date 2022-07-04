package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import impl_services.IOrder;
import model.Order;
import model.OrderDetail;
import model.Product;

public class OrderDAO extends DBConnect implements IOrder {

	@Override
	public Order getOrderByaccountID(String accountID) {
		String sql = "select * from [Order] where AccountID = ? order by id desc";
		try {
			connect = getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setString(1, accountID);
			
			res = pres.executeQuery();
			if(res.next()) {
				return new Order(res.getInt("id"),res.getInt("accountID"), res.getString("address"), res.getDouble("totalMoney"));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderDetail> getListOrderDetail() {

		return null;
	}

	@Override
	public void addOrder(Order order) {

		String sql = "insert into [Order](accountID,[address],totalMoney) values(?,?,?)";
		try {
			connect = getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, order.getAccountID());
			
			pres.setString(2, order.getAddress());
			
			pres.setDouble(3, order.getTotalMoney());
			
			pres.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

	@Override
	public void addOrderDetail(OrderDetail orderDetail) {
		String sql ="insert into OrderDetail(orderID,productID,price,quantity) values(?,?,?,?) ";
		
		try {
			connect = new DBConnect().getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, orderDetail.getOrderID());
			
			pres.setInt(2, orderDetail.getProduct().getId());
			
			pres.setDouble(3, orderDetail.getPrice());
			
			pres.setInt(4, orderDetail.getQuantity());
			
			pres.executeUpdate();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public HashMap<Integer, OrderDetail> getListOrderDetailByOID(int orderID) {
		Map<Integer, OrderDetail> listItems = new HashMap<Integer, OrderDetail>();
		String sql="select * from OrderDetail where orderID =?";
		try {
			connect = new DBConnect().getConnection();
			
			pres = connect.prepareStatement(sql);
			
			pres.setInt(1, orderID);
			
			res = pres.executeQuery();
			
			while(res.next()) {
				//productID,quantity,price
				
				String productID = res.getString("productID");
				
				int quantity = res.getInt("quantity");
				
//				double price = res.getDouble("price");
				
				Product product = new ProductDAO().getProductById(productID);
				
				OrderDetail orderDetail = new OrderDetail(orderID, quantity, product);
				
				listItems.put(product.getId(), orderDetail);
				
				
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(listItems.isEmpty()) {
			return null;
		}
		return (HashMap<Integer, OrderDetail>) listItems;
	}

}
