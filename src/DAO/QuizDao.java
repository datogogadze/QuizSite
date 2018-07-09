package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBupdate.DBupdate;
import Model.MyDBInfo;
import Questions.Question;
import Quiz.DoneQuiz;
import Quiz.Quiz;

public class QuizDao extends TableNames{
	QuestionDao qp;
	UserDao up;
	private Connection con;
	private DBupdate upd;
	public QuizDao(QuestionDao qp, UserDao up) {
		this.qp = qp;
		this.up = up;
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

	public void addQuiz(Quiz q) {
		int userId = q.getQuizAuthor();
		List<Question> questions = q.getQuizQuestions();
		String quizName = q.getQuizName();
		String quizDesc = q.getQuizDescription();
		boolean randomize = q.isRandomizedQuiz();
		boolean multipage = q.isMultiPageQuiz();
		String query = "insert into " + QUIZES_TABLE + "(QUIZ_NAME,QUIZ_DESCRIPTION,USER_ID,RANDOMIZE,MULTIPAGE) values "
				+ "('" + quizName + "','" + quizDesc + "'," + userId + "," + randomize + "," + multipage + ")";
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int quizId = getNextIndex();
		for(Question qst : questions){
			qp.addQuestion(qst,quizId); 
		}
	}

	public Quiz getQuiz(int quizId) {
		String query = "SELECT * FROM " + QUIZES_TABLE + " WHERE QUIZ_ID = " + quizId;
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next()) {
				String quizName = rs.getString("QUIZ_NAME");
				
				int username =(rs.getInt("USER_ID"));
				String quizDescription = rs.getString("QUIZ_DESCRIPTION");
				ArrayList<Question> questions = qp.getAllQuestions(quizId);
				Quiz temp = new Quiz(quizName, quizDescription,  questions);
				temp.setAuthor(username);
				temp.setQuizId(quizId);
				return temp;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getNextIndex() {
		String query = "SELECT * FROM "; 
		query+= QUIZES_TABLE + " WHERE QUIZ_ID = (SELECT MAX(QUIZ_ID) FROM  " + QUIZES_TABLE + ")";	
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next()) return rs.getInt(1);
		}catch (SQLException e){
			e.printStackTrace();
		}
		return 1;
	}
	
	public void insertCompletedQuiz(DoneQuiz temp){
		int quizId = temp.getQuizId();
		String complete = temp.getDate();
		int spentTime = temp.getTimeSpent();
		int userID = temp.getUserId();
		int score = temp.getScore();
		String query = "insert into " + COMPLETE_QUIZ_ID + " (QUIZ_ID,USER_ID,SPENT_TIME_SECONDS,USER_SCORE,FINISH_DATE) values "
				+ "(" + quizId + "," + userID + "," + spentTime + "," + score + ", now())";
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<DoneQuiz> getCompleteQuizes(int userID){
		String query = "Select * from " + COMPLETE_QUIZ_ID + " where USER_ID = " + userID;
		ArrayList<DoneQuiz> temp = new ArrayList<DoneQuiz>();
		try {
			ResultSet rs = upd.executeQuery(query);
			while(rs.next()){
				int quizID = rs.getInt(2);
				int user = rs.getInt(3);
				int time = rs.getInt(4);
				int score = rs.getInt(5);
				String date = rs.getString(6);
				DoneQuiz q = new DoneQuiz(user, quizID, score, date,  time);
				temp.add(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public ArrayList<Quiz> getAllQuizes(int userId){
		ArrayList<Quiz> arr = new ArrayList<Quiz>();
		String query = "Select QUIZ_ID from " + QUIZES_TABLE + " where USER_ID = " + userId;
		try {
			ResultSet rs = upd.executeQuery(query);
			while(rs.next()){
				int quizid = rs.getInt("QUIZ_ID");
				Quiz bla = getQuiz(quizid);
				arr.add(bla);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	
	 public ArrayList<Quiz> getAllQuizes(){
	        ArrayList<Quiz> arr = new ArrayList<Quiz>();
	        String query = "Select * FROM " + QUIZES_TABLE;
	        ResultSet rs;
			try {
				rs = upd.executeQuery(query);
				while(rs.next()){
	                String quizName = rs.getString("QUIZ_NAME");
	                int author = (rs.getInt("USER_ID"));
	                //ArrayList<Question> questions = qp.getAllQuestions(rs.getInt("QUIZ_ID"));
	                String desc = rs.getString("QUIZ_DESCRIPTION");
	                Quiz bla = new Quiz(quizName, desc, new ArrayList<Question>());
	                bla.setQuizId(rs.getInt("QUIZ_ID"));
	                bla.setAuthor(author);
	                arr.add(bla);
	            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        return arr;
	    }
	
	public void closeCon() throws SQLException {
		con.close();
	}

}