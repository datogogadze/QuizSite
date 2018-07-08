package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBupdate.DBupdate;
import Message.Challenge;
import Model.MyDBInfo;

public class ChallengeDao extends TableNames {

	private Connection con;
	private DBupdate upd;

	public ChallengeDao() {
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

	public void addChallangeInDB(int senderID, int recieverID, int quizId) {
		if(senderID == recieverID) {
			return;
		}
		String query = "INSERT INTO " + CHALLENGE_TABLE
				+ " (SENDER_ID, RECIEVER_ID, CHALLENGE_STATUS, SEND_DATE, QUIZ_ID) VALUES (" + senderID + ","
				+ recieverID + ", 'PENDING', now()," + quizId + ")";
			System.out.println(query);
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void completeChallange(int recieverId, int senderId, int quizid) {
		String query = "UPDATE " + CHALLENGE_TABLE
				+ " SET CHALLENGE_STATUS = 'CONFIRMED', CONFIRM_DATE = now() WHERE RECIEVER_ID = " + recieverId
				+ " AND SENDER_ID = " + senderId + " AND QUIZ_ID = " + quizid;
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Challenge> getChallenges(int reciever) {
		ArrayList<Challenge> challenges = new ArrayList<Challenge>();
		String query = "SELECT * FROM " + CHALLENGE_TABLE + " WHERE RECIEVER_ID = "
				+ reciever + " AND CHALLENGE_STATUS = 'PENDING'";
		try {
			ResultSet rs = upd.executeQuery(query);
			while (rs.next()) {
				challenges.add(new Challenge(rs.getInt("SENDER_ID"), reciever, rs.getInt("QUIZ_ID")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return challenges;
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