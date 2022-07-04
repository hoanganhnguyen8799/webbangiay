package controller;


import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.AccountDAO;
import model.Account;
import model.User;

/**
 * Servlet implementation class ProfileController
 */
@WebServlet(name = "profile", urlPatterns = { "/profile" })

public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("acc");
		if(account!=null) {
			int id = account.getId();
			
			User user = new AccountDAO().getUserById(String.valueOf(id));
			
			request.setAttribute("user", user);
		}
		
		request.getRequestDispatcher("/view/Profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("acc");
		int id =0;
		if(account!=null) {
			id=account.getId();
		}
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		User user = new User(id, name, phone, email);
		
		String action = request.getParameter("action");
		System.out.println("Action profile: "+action);
		String mess=null;
		switch (action) {
		case "update":
			new AccountDAO().updateUser(user);
			mess="Update thanh cong!";
			break;

		case "insert":
			new AccountDAO().insertUser(user);
			mess="Insert thanh cong!";
			break;
		}
		user =  new AccountDAO().getUserById(String.valueOf(id)) ;
		request.setAttribute("user", user);
		request.setAttribute("mess", mess);
		request.getRequestDispatcher("/view/Profile.jsp").forward(request, response);
		
	}
	

}
