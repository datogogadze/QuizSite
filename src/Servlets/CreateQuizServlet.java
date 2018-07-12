package Servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import Achievement.Achievement;
import Answer.Answer;
import DAO.AchievementDao;
import DAO.AnswerDao;
import DAO.QuestionDao;
import DAO.QuizDao;
import DAO.UserDao;
import JsonParser.JsonParser;
import Questions.Question;
import Quiz.Quiz;

/**
 * Servlet implementation class CreareQuizServlet
 */
@WebServlet(name="CreateQuizServlet", value="/CreateQuizServlet")
public class CreateQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQuizServlet() {
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
		// TODO Auto-generated method stub
		JsonParser jp = new JsonParser();
		Quiz q = jp.getQuizObject(request.getParameter("quiz"));		
		q.setAuthor(Integer.parseInt(request.getParameter("user")));
		AnswerDao ad = new AnswerDao();
		QuestionDao qud = new QuestionDao(ad);
		UserDao ud = new UserDao();
		QuizDao qd = new QuizDao(qud, ud);
		AchievementDao ach = new AchievementDao();
		int id = Integer.parseInt(request.getParameter("user"));
		qd.addQuiz(q); 
		int numquiz = qd.getAllQuizes(id).size();
		if (numquiz == 1) ach.addAchievement(new Achievement("Amateur author", id));
		if (numquiz == 5) ach.addAchievement(new Achievement("Prolific author", id));
		if (numquiz == 10) ach.addAchievement(new Achievement("Prodigious author", id));

		qd.closeCon();
		qud.closeCon();
		ad.closeCon();
		ud.closeCon();
		ach.closeCon();
	}

}
