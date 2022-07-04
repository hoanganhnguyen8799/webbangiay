package controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String action =req.getServletPath();
		System.out.println("Action: "+action);
		List<Product> listP = new ProductDAO().getProductPaging(0);

		List<Category> listC = new CategoryDAO().getAllCategory();
		Product lastP = new ProductDAO().getLastProduct();
		
//		
		if(listP!=null) {
			req.setAttribute("listP", listP);
		}
		int pageNumber = new ProductDAO().pageNumber();
		req.setAttribute("listC", listC);
		req.setAttribute("lastP", lastP);
		req.setAttribute("pageNumber", pageNumber);
		RequestDispatcher disp = req.getRequestDispatcher("/view/index.jsp");
		disp.forward(req, resp);
				
	
	}

}
