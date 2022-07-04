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

/**
 * Servlet implementation class PagingController
 */
@WebServlet(name = "paging", urlPatterns = { "/paging" })
public class PagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String index = request.getParameter("index");
		int index1;
		if(index==null) {
			index1 =0;
		}else
			index1 =(Integer.parseInt(index)-1)*9;
		
		List<Product> listP = new ProductDAO().getProductPaging(index1);

		List<Category> listC = new CategoryDAO().getAllCategory();
		Product lastP = new ProductDAO().getLastProduct();
		
//		
		if(listP!=null) {
			request.setAttribute("listP", listP);
		}
		int pageNumber = new ProductDAO().pageNumber();
		request.setAttribute("listC", listC);
		request.setAttribute("lastP", lastP);
		request.setAttribute("pageNumber", pageNumber);
		RequestDispatcher disp = request.getRequestDispatcher("/view/index.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
