package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Answer.Answer;
import DBupdate.DBupdate;
import Model.MyDBInfo;

public class AnswerDao extends TableNames {

	private Connection con;
	private DBupdate upd;
	public AnswerDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Called to just initialize JDBC driver
			con = DriverManager.getConnection(MyDBInfo.JDBC_DATABASE_URL, // Connect to database
					MyDBInfo.MYSQL_USERNAME, MyDBInfo.MYSQL_PASSWORD);
			upd = new DBupdate(con);
			Statement stmt = con.createStatement();
			stmt.execute("USE " + MyDBInfo.MYSQL_DATABASE_NAME);

		} catch (ClassNotFoundException e) {
			// No com.mysql.jdbc.Driver class found in classpath
			e.printStackTrace();
		} catch (SQLException e) {
			// There is a driver but something went wrong while astablishing connection,
			// e.g. username or password is wrong, or server url is not correct.
			e.printStackTrace();
		}
	}

	public void insertAnswer(Answer an, int questionID) {
		boolean correct = an.isAnswerCorrect();
		String answer = an.getPossibleAnswer();
		String query = "insert into " + ANSWERS_TABLE + " (POSSIBLE_ANSWER,QUESTION_ID,RIGHT_ANSWER) values " + "('"
				+ answer + "'," + questionID + "," + correct + ")";
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Answer> getAllAnswers(int questionId) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		String query = "select * from " + ANSWERS_TABLE + " where QUESTION_ID = " + questionId;
		ResultSet temp;
		try {
			temp = upd.executeQuery(query);
			while (temp.next()) {
				int id = temp.getInt("ANSWER_ID");
				int question = temp.getInt("QUESTION_ID");
				boolean isCorrect = temp.getBoolean("RIGHT_ANSWER");
				String pAnswer = temp.getString("POSSIBLE_ANSWER");
				Answer tmpAnswer = new Answer(isCorrect, pAnswer);
				tmpAnswer.setAnswerId(id);
				tmpAnswer.setQuestionId(question);
				answers.add(tmpAnswer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answers;
	}


	public void closeCon() throws SQLException {
		con.close();
	}
}