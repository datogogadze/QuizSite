package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDao;
import User.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(name="RegistrationServlet", value="/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		doGet(request, response);
		UserDao dao = new UserDao();
		if(dao.searchUser(request.getParameter("username")) 
		|| dao.searchMail(request.getParameter("email"))
		|| !request.getParameter("password").equals(request.getParameter("comfirm_password")) ) {
			RequestDispatcher disp = request.getRequestDispatcher("registrationFailed.jsp");
			disp.forward(request, response);			
		}else {
			User user = new User(
					request.getParameter("first_name"),
					request.getParameter("last_name"),
					request.getParameter("username"), 
					request.getParameter("password"),
					request.getParameter("email"));
			dao.addUser(user);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("NotFound", "found");
			RequestDispatcher disp = request.getRequestDispatcher("mainPage.jsp");

			disp.forward(request, response);
		}
	}
}
