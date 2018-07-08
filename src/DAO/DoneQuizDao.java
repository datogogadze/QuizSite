package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBupdate.DBupdate;
import Model.MyDBInfo;
import Quiz.DoneQuiz;

public class DoneQuizDao extends TableNames {
	private Connection con;
	private DBupdate upd;

	public DoneQuizDao() {
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

	public ArrayList<DoneQuiz> getTop10(int userID, int quizID) {
		ArrayList<DoneQuiz> compQuizes = new ArrayList<DoneQuiz>();
		String query = "SELECT * FROM " + COMPLETE_QUIZ_ID + " WHERE QUIZ_ID  = " + quizID + " AND USER_ID = " + userID
				+ " ORDER BY USER_SCORE DESC, SPENT_TIME_SECONDS LIMIT 10";	
		try {
			ResultSet rs = upd.executeQuery(query);
			while (rs.next()) {
				String completeQuizID = rs.getString("COMPLETE_QUIZ_ID");
				int spentTime = rs.getInt("SPENT_TIME_SECONDS");
				int userScore = rs.getInt("USER_SCORE");
				String finishDate = rs.getString("FINISH_DATE");
				DoneQuiz q = new DoneQuiz(userID, quizID, userScore, finishDate,  spentTime);
				
				compQuizes.add(q);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return compQuizes;
	}
	
	public ArrayList<DoneQuiz> getTop10Leaderboard(int quizID) {
		ArrayList<DoneQuiz> compQuizes = new ArrayList<DoneQuiz>();
		String query = "SELECT * FROM " + COMPLETE_QUIZ_ID + " WHERE QUIZ_ID  = " + quizID 
				+ " ORDER BY USER_SCORE DESC, SPENT_TIME_SECONDS LIMIT 10";
		try {
			ResultSet rs = upd.executeQuery(query);
			while (rs.next()) {
				String completeQuizID = rs.getString("COMPLETE_QUIZ_ID");
				int userID = rs.getInt("USER_ID");
				int spentTime = rs.getInt("SPENT_TIME_SECONDS");
				int userScore = rs.getInt("USER_SCORE");
				String finishDate = rs.getString("FINISH_DATE");
				DoneQuiz q = new DoneQuiz(userID, quizID, userScore, finishDate, spentTime);
				
				compQuizes.add(q);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return compQuizes;
	}
	
	public ArrayList<DoneQuiz> getLast(int userID) {
		ArrayList<DoneQuiz> compQuizes = new ArrayList<DoneQuiz>();
		String query = "SELECT * FROM " + COMPLETE_QUIZ_ID + " WHERE USER_ID = " + userID
				+ " ORDER BY FINISH_DATE DESC";		
		try {
			ResultSet rs = upd.executeQuery(query);
			while (rs.next()) {
		//		String completeQuizID = rs.getString("COMPLETE_QUIZ_ID");
				int spentTime = rs.getInt("SPENT_TIME_SECONDS");
				int userScore = rs.getInt("USER_SCORE");
				String finishDate = rs.getString("FINISH_DATE");
				DoneQuiz q = new DoneQuiz(userID, getNextIndex(), userScore, finishDate, spentTime);
				
				compQuizes.add(q);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return compQuizes;
	}
	
	public int getNextIndex() {
		String query = "SELECT * FROM "; 
		query+= COMPLETE_QUIZ_ID + " WHERE QUIZ_ID = (SELECT MAX(QUIZ_ID) FROM  " + COMPLETE_QUIZ_ID + ")";	
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next()) return rs.getInt(2);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return 1;
	}
	
	public String getQuizName(int id){
		String query = "Select QUIZ_NAME from " + QUIZES_TABLE + " where QUIZ_ID = " + id;
		try {
			ResultSet bla = upd.executeQuery(query);
			bla.next();
			return bla.getString("QUIZ_NAME");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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