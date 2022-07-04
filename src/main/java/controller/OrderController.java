package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import model.Account;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.Product;
import services.CartService;


/**
 * Servlet implementation class OrderController
 */
@WebServlet(name = "order", urlPatterns = { "/order" })
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("acc");
		if (account!=null) {
			String address = request.getParameter("address");
			Cart cart = (Cart) session.getAttribute("cart");
			Order order =null;
			OrderDAO orderDAO = new OrderDAO();
			if (cart!=null) {
				
				order = new Order(account.getId(), address, cart.getTotalMoney());// id = 0;
				//them order vao csdl de tao id
				orderDAO.addOrder(order);
				
				// lay order sau khi them vao bang - da co id;
				order = orderDAO.getOrderByaccountID(String.valueOf(account.getId()));
				System.out.println("OrderID: "+order.getId());
			
				for(Integer key : cart.getProductCart().keySet()) {
					Product product = cart.getProductCart().get(key).getProduct();
					int quantity = cart.getProductCart().get(key).getQuantity();
					OrderDetail orderDetail = new OrderDetail(order.getId(), quantity, product);
					// them OrderDetail 
					orderDAO.addOrderDetail(orderDetail);
				}
				
				Map<Integer, OrderDetail> listItems = orderDAO.getListOrderDetailByOID(order.getId());
				if(listItems!=null)
					session.setAttribute("listItems", listItems);
				
				
				session.setAttribute("order", order);
				session.removeAttribute("cart");
				
				new CartService().deleteCart(account.getId());
				request.getRequestDispatcher("/view/Order.jsp").forward(request, response);
			}else {
//				request.setAttribute("messC", "Khong co san pham nao trong Cart !");
//				request.getRequestDispatcher("cart").forward(request, response);
				response.sendRedirect("cart?messC=-1");
			}
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		HttpSession session = request.getSession();
//		Cart cart = (Cart) session.getAttribute("cart");
//		if(cart==null) {
//			response.sendRedirect("cart?messC=Khong co san pham trong Cart!");
//		}
	}

}
