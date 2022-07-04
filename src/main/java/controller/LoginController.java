package controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;

import model.Account;
import model.Cart;

import services.CartService;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookies[] = req.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals("userC")) {
				req.setAttribute("username", cookie.getValue());
			}
			if(cookie.getName().equals("passC")) {
				req.setAttribute("password", cookie.getValue());
			}
		}
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view/LoginLogout.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset = UTF-8");

		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		String remember = req.getParameter("remember");
		Account acc = new AccountDAO().login(user, pass);

		if (acc != null) {

			Cart cart = new CartService().getCart(String.valueOf(acc.getId()));

			HttpSession session = req.getSession();
			session.setAttribute("acc", acc);
			if (cart != null)
				session.setAttribute("cart", cart);
			else
				System.out.println("cart null");
			Cookie userC = new Cookie("userC", user);
			Cookie passC = new Cookie("passC", pass);
			
			userC.setMaxAge(60*20);
			if(remember!=null) {
				passC.setMaxAge(60*20);
			}else
				passC.setMaxAge(0);
			
			
			resp.addCookie(userC);
			resp.addCookie(passC);
//			req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
			
			resp.sendRedirect("home");

		} else
			resp.sendRedirect("login?err=1");

	}

//		

}
