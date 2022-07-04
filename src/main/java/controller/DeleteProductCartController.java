package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Cart;
import services.CartService;

/**
 * Servlet implementation class DeleteProductCartController
 */
@WebServlet(name = "delete-pro-cart", urlPatterns = { "/delete-pro-cart" })
public class DeleteProductCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductCartController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession sesion = request.getSession();
		Account account = (Account) sesion.getAttribute("acc");
		Cart cart = (Cart) sesion.getAttribute("cart");
		if(cart!=null) 
		{
			String productID = request.getParameter("pid");
			
			new CartService().deleteProductCart(cart.getCartID(), Integer.parseInt(productID));
			
			sesion.removeAttribute("cart");
			
			Cart newCart = new CartService().getCart(String.valueOf(account.getId()));
			
			sesion.setAttribute("cart", newCart);
			
			request.getRequestDispatcher("/view/Cart.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
