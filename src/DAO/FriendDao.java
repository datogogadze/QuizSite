package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBupdate.DBupdate;
import Model.MyDBInfo;

public class FriendDao extends TableNames {
	private Connection con;
	private DBupdate upd;
	public FriendDao() {
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

	public void addFriend(int senderID, int recieverID) throws SQLException {
		if (senderID == recieverID) {
			return;
		}
		String query1 = "SELECT * FROM " + FRIENDS_TABLE + " WHERE SENDER_ID = " + senderID;
		ResultSet rs = upd.executeQuery(query1);
		while (rs.next()) {
			if (rs.getInt("RECIEVER_ID") == recieverID) {
				return;
			}
		}
		String query2 = "SELECT * FROM " + FRIENDS_TABLE + " WHERE RECIEVER_ID = " + senderID;
		rs = upd.executeQuery(query2);
		while (rs.next()) {
			if (rs.getInt("SENDER_ID") == recieverID) {
				if (rs.getString("REQUEST_STATUS").equals("PENDING")) {
					acceptRequest(recieverID, senderID);
					return;
				} else {
					return;
				}
			}
		}
		String query3 = "INSERT INTO " + FRIENDS_TABLE + " (SENDER_ID, RECIEVER_ID, REQUEST_STATUS, SEND_DATE) values"
				+ " (" + senderID + "," + recieverID + "," + "'PENDING', now())";
		upd.executeUpdate(query3);

	}

	public void acceptRequest(int recieverId, int senderId) {
		String query = "UPDATE " + FRIENDS_TABLE
				+ " SET REQUEST_STATUS = 'CONFIRMED', CONFIRM_DATE = now() WHERE RECIEVER_ID = " + recieverId
				+ " AND SENDER_ID = " + senderId;
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteFriendship(int id1, int id2) {
		String query = "DELETE FROM " + FRIENDS_TABLE + " WHERE SENDER_ID = " + id1 + "AND RECIEVER_ID = " + id2;
		try {
			upd.executeUpdate(query);
			query = "DELETE FROM " + FRIENDS_TABLE + " WHERE SENDER_ID = " + id1 + "AND RECIEVER_ID = " + id2;
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getRequests(int useriD) {
		String query = "SELECT 	SENDER_ID FROM FRIENDS WHERE RECIEVER_ID = " + useriD
				+ " AND REQUEST_STATUS = 'PENDING'";	
		ArrayList<Integer> senders = new ArrayList<Integer>();
		try {
			ResultSet rs = upd.executeQuery(query);
			while (rs.next()) {
				senders.add(rs.getInt("SENDER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return senders;
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