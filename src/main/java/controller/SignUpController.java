package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountDAO;
import model.Account;

@WebServlet(urlPatterns = {"/signup"})
public class SignUpController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String username =req.getParameter("user");
		String pass = req.getParameter("pass");
		String repass = req.getParameter("repass");		
		
		if(!pass.equals(repass)) {

				resp.sendRedirect("login");
			
			
		}else {
			
			AccountDAO dao = new AccountDAO();
			Account acc = dao.checkExist(username);
			if(acc==null) {
				dao.signUp(username, repass);
				
				req.setAttribute("acc",	new AccountDAO().login(username, repass));
				resp.sendRedirect("home");

			}
			else
				resp.sendRedirect("login");
			
		}
			
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher disp = req.getRequestDispatcher("/view/LoginLogout.jsp");
		disp.forward(req, resp);
	}
}
