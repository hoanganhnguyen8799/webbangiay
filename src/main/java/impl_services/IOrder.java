package impl_services;

import java.util.HashMap;
import java.util.List;

import model.Order;
import model.OrderDetail;

public interface IOrder {
	public Order getOrderByaccountID(String accountID);
	public List<OrderDetail> getListOrderDetail();
	public void addOrder(Order order);
	public void addOrderDetail(OrderDetail orderDetail);
	public HashMap<Integer, OrderDetail> getListOrderDetailByOID(int orderID);
}
