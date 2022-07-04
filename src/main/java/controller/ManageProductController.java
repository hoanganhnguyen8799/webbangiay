package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dao.CategoryDAO;
import dao.ProductDAO;
import model.Account;
import model.Category;
import model.Product;

/**
 * Servlet implementation class ManageProductController
 */
@WebServlet(name = "manage-product", urlPatterns = { "/manage-product" })
public class ManageProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Account account = (Account) request.getSession().getAttribute("acc");

		List<Product> list = null;

		if (account != null) {

			list = new ProductDAO().getSellProduct(account.getId());

		}
		request.getSession().setAttribute("listProduct", list);
		
	
		
		String action = request.getParameter("action");
		System.out.println("Action: "+action);
		if (action != null && action.equals("new") &&account!=null) {
			insertProduct(request, response,account);
			return;
		} else if (action != null && action.equals("edit")&&account!=null) {
			updateProduct(request, response, account);
			return;
		} else if (action != null && action.equals("delete")&&account!=null) {
			deleteProduct(request, response, account);
			return;
		}
		
		request.getRequestDispatcher("/view/ManageProduct.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	private void insertProduct(HttpServletRequest request, HttpServletResponse response, Account account)
			throws ServletException, IOException {
		List<Category> listC = new CategoryDAO().getAllCategory();
		request.setAttribute("listC", listC);
		request.getRequestDispatcher("/view/EditProduct.jsp").forward(request, response);
	
	}
	private void updateProduct(HttpServletRequest request, HttpServletResponse response, Account account)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Product product = new ProductDAO().getProductById(id);
		List<Category> listC = new CategoryDAO().getAllCategory();
		request.setAttribute("listC", listC);
		request.setAttribute("product", product);
		request.setAttribute("category", new CategoryDAO().getCategory(product.getCateID()));
		request.getRequestDispatcher("/view/EditProduct.jsp").forward(request, response);
	
	}
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response, Account account)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		new ProductDAO().deleteProductSell(Integer.valueOf(id), account.getId());
		response.sendRedirect("manage-product");
	
	}
	
}
