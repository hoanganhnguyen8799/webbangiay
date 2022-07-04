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
import model.Category;
import model.Product;
@WebServlet(urlPatterns = {"/product-detail"})
public class ProductDetailController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String id = req.getParameter("pid");
		Product p = new ProductDAO().getProductById(id);
		List<Category> listC = new CategoryDAO().getAllCategory();
		Product lastP = new ProductDAO().getLastProduct();
		req.setAttribute("listC", listC);
		req.setAttribute("lastP", lastP);
		req.setAttribute("p", p);
		req.getRequestDispatcher("/view/ProductDetail.jsp").forward(req, resp);
	}
}
