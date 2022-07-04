package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Account;
import model.Product;

/**
 * Servlet implementation class EditProductController
 */
@WebServlet(name = "edit-product", urlPatterns = { "/edit-product" })
public class EditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String price = request.getParameter("price");
		String image = request.getParameter("image");
		String description = request.getParameter("description");
		String cateID = request.getParameter("cateID");
		
//		String cateID = request.getParameter("categoryID");
		System.out.println("Name: "+name);
		System.out.println(title);
		System.out.println(price);
		System.out.println(image);
		System.out.println(description);
		System.out.println("categoryID: "+cateID);
		
		int sellID = 0;
		Account account = (Account) request.getSession().getAttribute("acc");
		if(account!=null)
			sellID=account.getId();
		new ProductDAO().updateProductSell(new Product(Integer.valueOf(id), name, image, Double.parseDouble(price), title, description, Integer.parseInt(cateID), sellID));
		
		response.sendRedirect("manage-product");
		
	}

}
