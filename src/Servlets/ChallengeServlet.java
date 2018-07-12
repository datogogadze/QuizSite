package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ChallengeDao;
import DAO.UserDao;
import User.User;

/**
 * Servlet implementation class ChallengeServlet
 */
@WebServlet("/ChallengeServlet")
public class ChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChallengeDao cd = new ChallengeDao();
		UserDao ud = new UserDao();
		User us = (User) request.getSession().getAttribute("user");
		int id1 = us.getId();
		String friend = request.getParameter("select");
		int id2 = ud.getUserId(friend);
		String quizid = request.getParameter("quizid");
		cd.addChallangeInDB(id1, id2, Integer.parseInt(quizid));
		RequestDispatcher disp = request.getRequestDispatcher("quizDescription.jsp?quizId="+quizid);
		disp.forward(request, response);
		doGet(request, response);
	}

}
