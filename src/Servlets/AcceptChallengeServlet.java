package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ChallengeDao;
import DAO.FriendDao;
import User.User;

/**
 * Servlet implementation class AcceptChallengeServlet
 */
@WebServlet("/AcceptChallengeServlet")
public class AcceptChallengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptChallengeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User reciever = (User) request.getSession().getAttribute("user");
		int senderId = Integer.parseInt(request.getParameter("senderId"));
		int quizid = Integer.parseInt(request.getParameter("quizid"));
		ChallengeDao fd = new ChallengeDao();
		fd.completeChallange(reciever.getId(), senderId, quizid);
		fd.closeCon();		
		RequestDispatcher rd = request.getRequestDispatcher("quizDescription.jsp?quizid="+quizid);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
