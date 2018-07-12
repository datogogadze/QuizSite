package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDao;
import User.User;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		HttpSession session = request.getSession(false);
		UserDao ud = new UserDao();
		int id = ud.getUserId(username);
		User us = ud.getUserByID(id);
		ud.closeCon();
		session.setAttribute("foundUser", us);
		response.sendRedirect("foundUser.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		HttpSession session = request.getSession(false);
		UserDao ud = new UserDao();
		boolean userInBase = ud.searchUser(username);
		if (userInBase) {
			int id = ud.getUserId(username);
			User us = ud.getUserByID(id);
			ud.closeCon();
			session.setAttribute("foundUser", us);
			response.sendRedirect("foundUser.jsp");
		} else {
			ud.closeCon();
			session.setAttribute("NotFound", "NotFound");
			response.sendRedirect("mainPage.jsp");
		}
	}

}
