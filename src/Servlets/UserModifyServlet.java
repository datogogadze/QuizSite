package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDao;
import User.User;

/**
 * Servlet implementation class UserModifyServlet
 */
@WebServlet("/UserModifyServlet")
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("delete") != null) {
			int id = Integer.parseInt(request.getParameter("delete"));
			UserDao ud = new UserDao();
			ud.deleteUser(id);
			ud.closeCon();
		}
		if(request.getParameter("changeStatus") != null) {
			int id = Integer.parseInt(request.getParameter("changeStatus"));
			UserDao ud = new UserDao();
			if(ud.getUserByID(id).getType() == 1) {
				ud.updateAdminStatus(2, id);
			}else{
				ud.updateAdminStatus(1, id);
			}
			ud.closeCon();
		}
		RequestDispatcher rd = request.getRequestDispatcher("manageUsers.jsp");
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
