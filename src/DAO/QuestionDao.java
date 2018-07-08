package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Answer.Answer;
import DBupdate.DBupdate;
import Model.MyDBInfo;
import Questions.FillInTheBlank;
import Questions.MultipleChoice;
import Questions.PictureResponse;
import Questions.Question;
import Questions.QuestionResponse;

public class QuestionDao extends TableNames {
	AnswerDao apr;
	private Connection con;
	private DBupdate upd;
	public QuestionDao(AnswerDao apr) {
		this.apr = apr;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Called to just initialize JDBC driver
			con = DriverManager.getConnection(MyDBInfo.JDBC_DATABASE_URL, // Connect to database
					MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);

			Statement stmt = con.createStatement();
			stmt.execute("USE " + MyDBInfo.MYSQL_DATABASE_NAME);
			upd = new DBupdate(con);
		} catch (ClassNotFoundException e) {
			// No com.mysql.jdbc.Driver class found in classpath
			e.printStackTrace();
		} catch (SQLException e) {
			// There is a driver but something went wrong while astablishing connection,
			// e.g. username or password is wrong, or server url is not correct.
			e.printStackTrace();
		}
	}

	public void addQuestion(Question quest, int quizId) {
		int questionId = this.getNextIndex();
		String question = quest.getQuestion();
		int type = quest.getQuestionType();
		ArrayList<Answer> answers = quest.getAnswers();
		Answer correctAnswer = quest.getCorrectAnswer();
		int questionN = quest.getQuestionN();
		String url = quizId == 4 ? correctAnswer.getPossibleAnswer() : null;
		String query = "insert into " + QUESTION_TABLE
				+ "(QUIZ_ID,QUESTION_TEXT,QUESTION_TYPE_ID,URL,QUESTION_N) values " + "(" + quizId + ",'" + question
				+ "'," + type + ",'" + url + "'," + questionN + ")";
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (quest.getQuestionType() == 3 || quest.getQuestionType() == 1)
			addAnswers(answers, questionId);
		else
			apr.insertAnswer(correctAnswer, questionId);
	}

	public ArrayList<Question> getAllQuestions(int quizId) {
		ArrayList<Question> quest = new ArrayList<Question>();
		;
		String select = "select * from " + QUESTION_TABLE + " where QUIZ_ID = " + quizId;
		try {
			ResultSet rs = upd.executeQuery(select);
			while (rs.next()) {
				String questionText = rs.getString("QUESTION_TEXT");
				int questionType = rs.getInt("QUESTION_TYPE_ID");
				int questionN = rs.getInt("QUESTION_N");
				int questionID = rs.getInt("QUESTION_ID");
				String url = rs.getString("URL");
				Question temp = sortAndAdd(questionType, questionN, questionID, quizId, questionText, url);
				quest.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return quest;
	}

	private void addAnswers(ArrayList<Answer> answers, int questionId) {
		for (Answer temp : answers) {
			apr.insertAnswer(temp, questionId);
		}
	}

	private Question sortAndAdd(int type, int num, int questId, int quizId, String questionText, String url) {
		ArrayList<Answer> answers = apr.getAllAnswers(questId);
		if (type == 1) {
			QuestionResponse q = new QuestionResponse(questionText, answers, num);
			q.setQuizId(quizId);
			q.setQuestionId(this.getNextIndex());
			return q;
		}
		if (type == 2) {
			FillInTheBlank b = new FillInTheBlank(questionText, answers.get(0), num);
			b.setQuizId(quizId);
			b.setQuestionId(this.getNextIndex());
			return b;
		}
		if (type == 3) {
			Answer correct = getCorrect(answers);
			MultipleChoice m = new MultipleChoice(questionText, answers, num);
			m.setQuizId(quizId);
			m.setQuestionId(this.getNextIndex());
			return m;
		} else {
			PictureResponse p = new PictureResponse(questionText, answers.get(0), num);
			p.setQuizId(quizId);
			p.setQuestionId(this.getNextIndex());
			return p;
		}
	}

	private Answer getCorrect(ArrayList<Answer> ans) {
		Answer correct = null;
		for (Answer a : ans) {
			if (a.isAnswerCorrect()) {
				correct = a;
			}
		}
		return correct;
	}

	public int getNextIndex() {
		String query = "SELECT * FROM ";
		query += QUESTION_TABLE + " WHERE QUESTION_ID = (SELECT MAX(QUESTION_ID) FROM  " + QUESTION_TABLE + ")";		
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next())
				return rs.getInt(1) + 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public void closeCon() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}