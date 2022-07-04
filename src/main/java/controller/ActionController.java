package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;

/**
 * Servlet implementation class ActionController
 */
@WebServlet(name = "action", urlPatterns = { "/action" })
public class ActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action at do get: " + action);
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart != null) {
			
			session.removeAttribute("cart");
			String id = request.getParameter("id");
			
			switch (action) {
			case "increase":
				cart.getProductCart().get(Integer.valueOf(id)).incrementQuantity();
				
				break;

			case "reduce":
				cart.getProductCart().get(Integer.valueOf(id)).reduceQuantity();
				break;

			}
			session.setAttribute("cart", cart);
//			request.getRequestDispatcher("/view/Cart.jsp").forward(request, response);
			response.sendRedirect("cart");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	

}
