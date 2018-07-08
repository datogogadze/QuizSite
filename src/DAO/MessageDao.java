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
import Message.TextMessage;

public class MessageDao extends TableNames {
	private Connection con;
	private DBupdate upd;
	public MessageDao() {
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

	public void addNote(int senderID, int recieverID, String text) {
		String query = "INSERT INTO " + MESSAGE_TABLE + " (SENDER_USER_ID, RECIEVER_USER_ID, MESSAGE_TYPE_ID, MESSAGE_TEXT, SEND_DATE) " 
				+ " values (" + senderID + ", " + recieverID + ", 3, " + "'" + text + "'" + ", now())";
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<TextMessage> getNotesByReciever(int recieverID){
		String query = "SELECT * FROM " + MESSAGE_TABLE + " WHERE RECIEVER_USER_ID = " + recieverID;
		ArrayList<TextMessage> notes = new ArrayList<TextMessage>();
		try {
			ResultSet rs = upd.executeQuery(query);
			while(rs.next()) {
				int sender = rs.getInt("SENDER_USER_ID");
				String txt = rs.getString("MESSAGE_TEXT");
				String sendDate = rs.getString("SEND_DATE");
				TextMessage curr = new TextMessage(sender, recieverID, txt, sendDate);
				notes.add(curr);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notes;
	}
	public ArrayList<TextMessage> getNotesBySender(int senderID){
		String query = "SELECT * FROM " + MESSAGE_TABLE + "WHERE SENDER_USER_ID = " + senderID;
		ArrayList<TextMessage> notes = new ArrayList<TextMessage>();
		try {
			ResultSet rs = upd.executeQuery(query);
			while(rs.next()) {
				int reciever = rs.getInt("RECIEVER_USER_ID");
				String txt = rs.getString("MESSAGE_TEXT");
				String sendDate = rs.getString("SEND_DATE");
				TextMessage curr = new TextMessage(senderID, reciever, txt, sendDate);
				notes.add(curr);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notes;
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