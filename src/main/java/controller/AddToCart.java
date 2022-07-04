package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.CategoryDAO;
import dao.ProductDAO;
import model.Account;
import model.Cart;
import model.Category;
import model.Product;
import model.ProductCart;
import services.CartService;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet(name = "add-2-cart", urlPatterns = { "/add-2-cart" })
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("pid"));
		Product product = new ProductDAO().getProductById(String.valueOf(id));
		HttpSession session = request.getSession();
		String action =request.getServletPath();
		System.out.println("Action at add-to-cart: "+action);
		Account account = (Account) session.getAttribute("acc");
		
		if(account!=null)
		{
			System.out.println("Account id: "+account.getId());
			Cart cart = (Cart) session.getAttribute("cart");
			ProductCart proCart;
			Map<Integer, ProductCart> cartDetail;
			if (cart == null) {
				// tao moi cart
				int cartID = account.getId();
				int quantity = 1;
				proCart = new ProductCart(quantity, product);
				cartDetail = new HashMap<Integer, ProductCart>();
				cartDetail.put(id, proCart);
				cart = new Cart(cartID, (HashMap<Integer, ProductCart>)cartDetail);
				
				

			}else {
				//kiem tra san pham co trong cart ?
				if(cart.getProductCart().containsKey(id)) {
					// neu co thi chi tang so luong
					cart.getProductCart().get(id).incrementQuantity();
					
					
					
				}else {
					// chua co thi them moi san pham
					ProductCart pCart = new ProductCart(1, product);
					cart.getProductCart().put(id, pCart);
				
					
					
				}
			}
			new CartService().addToCart(cart);
			session.setAttribute("cart", cart);
			List<Product> listP = new ProductDAO().getAllProducts();

			List<Category> listC = new CategoryDAO().getAllCategory();
			Product lastP = new ProductDAO().getLastProduct();
			
//			
			if(listP!=null) {
				request.setAttribute("listP", listP);
			}
			
			request.setAttribute("listC", listC);
			request.setAttribute("lastP", lastP);
			RequestDispatcher disp = request.getRequestDispatcher("/view/index.jsp");
			disp.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
