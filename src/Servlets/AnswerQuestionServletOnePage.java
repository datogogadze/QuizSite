package Servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import Achievement.Achievement;
import DAO.AchievementDao;
import DAO.AnswerDao;
import DAO.QuestionDao;
import DAO.QuizDao;
import DAO.UserDao;
import Questions.Question;
import Quiz.DoneQuiz;
import Quiz.Quiz;
import User.User;

/**
 * Servlet implementation class AnswerQuestionServletOnePage
 */
@WebServlet("/AnswerQuestionServletOnePage")
public class AnswerQuestionServletOnePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnswerQuestionServletOnePage() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int score = 0;
		int quizid = Integer.parseInt(request.getParameter("quizid"));
		UserDao us = new UserDao();
		AnswerDao an = new AnswerDao();
		QuestionDao qp = new QuestionDao(an);
		QuizDao complete = new QuizDao(qp, us);
		Quiz quiz = complete.getQuiz(quizid);
		for (int i = 0; i < quiz.getQuizQuestions().size(); i++) {
			String ans = "";
			ans = request.getParameter("response" + i);
			Question quest = quiz.getQuizQuestions().get(i);
			String correctAns = (quest.getCorrectAnswer().getPossibleAnswer());
			if (ans.equals(correctAns)) {
				score++;
			}
		}

		RequestDispatcher disp;
		Date startdate = (Date) request.getSession().getAttribute("startdate" + quizid);
		User usr = (User) request.getSession().getAttribute("user");
		Date dat = new Date();
		DoneQuiz don = new DoneQuiz(usr.getId(), quizid, score, dat.toString(),
				(int) ((dat.getTime() - startdate.getTime()) / 1000));
		complete.insertCompletedQuiz(don);
		if (complete.getCompleteQuizes(usr.getId()).size() == 10) {
			usr.addAchievement(new Achievement("Quiz Machine", usr.getId()));
		}
		if(score >= complete.getMaxScore(quizid)) {
			usr.addAchievement(new Achievement("I am the Greatest", usr.getId()));
		}
		request.getSession().setAttribute("score", score);
		an.closeCon();
		qp.closeCon();
		complete.closeCon();
		disp = request.getRequestDispatcher("doneQuiz.jsp?" + quizid);
		disp.forward(request, response);
	}

}
