package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Achievement.Achievement;
import DAO.AchievementDao;
import DAO.AnswerDao;
import DAO.QuestionDao;
import DAO.QuizDao;
import DAO.UserDao;
import Questions.Question;
import Quiz.DoneQuiz;
import User.User;

/**
 * Servlet implementation class answerQuestionServlet
 */
@WebServlet("/answerQuestionServlet")
public class answerQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public answerQuestionServlet() {
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
		String ans = " ";
		ans = request.getParameter("response");
		if (ans == null) ans = " ";
		int quizid = Integer.parseInt(request.getParameter("quizId"));
		int questionNum = Integer.parseInt(request.getParameter("question"));
		Integer c;
		if ((Integer) request.getSession().getAttribute("score") == null) c = 0;
		else c = (Integer) request.getSession().getAttribute("score") ;
		Integer score =c;
		UserDao us = new UserDao();
		AnswerDao an = new AnswerDao();
		QuestionDao qp = new QuestionDao(an);
		QuizDao complete = new QuizDao(qp, us);
		Question quest = qp.getAllQuestions(quizid).get(questionNum-1); 
		String correctAns = (quest.getCorrectAnswer().getPossibleAnswer());
		String answered;
		String ll = "quiz"+quizid+"question"+questionNum;
		if  (request.getSession().getAttribute(ll) != null)  answered = "answered";
		else answered = null;
		if (ans.equals(correctAns) && answered ==null) score++;
		System.out.println(score);
		request.getSession().setAttribute("score", score);
		RequestDispatcher disp ;
		request.getSession().setAttribute(ll, "alreadyAnswered");
		if (questionNum == qp.getAllQuestions(quizid).size()) {
			AchievementDao ach = new AchievementDao();
			Date startdate = (Date) request.getSession().getAttribute("startdate"+quizid);
			User usr = (User) request.getSession().getAttribute("user");
			Date dat =  new Date();
			DoneQuiz don = new DoneQuiz(usr.getId(), quizid, score, dat.toString(), (int)((dat.getTime() - startdate.getTime())/1000) );
			complete.insertCompletedQuiz(don);
			if (complete.getCompleteQuizes(usr.getId()).size() >=2) {
				usr.addAchievement(new Achievement("Quiz Machine", usr.getId(), 4, dat.toString()));
			}
			disp = request.getRequestDispatcher("doneQuiz.jsp?"+quizid);
			disp.forward(request, response);
		}else {
			String link = "writeQuiz.jsp?quizId=" + complete.getQuiz(quizid).getQuizId() + "&question=" + (questionNum);
			disp = request.getRequestDispatcher(link);
			disp.forward(request, response);
		}
		doGet(request, response);
	}

}
