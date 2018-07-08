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
	Connection con;
	private DBupdate upd;
	public FriendDao() {
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

	public void addFriend(int senderID, int recieverID) throws SQLException {
		if (senderID == recieverID)
			return;
		String q = "SELECT * FROM " + FRIENDS_TABLE + " WHERE SENDER_ID = " + senderID;
		ResultSet rs = upd.executeQuery(q);
		while (rs.next()) {
			if (rs.getInt("RECIEVER_ID") == recieverID)
				return;
		}
		q = "SELECT * FROM " + FRIENDS_TABLE + " WHERE RECIEVER_ID = " + senderID;
		rs = upd.executeQuery(q);
		while (rs.next()) {
			if (rs.getInt("SENDER_ID") == recieverID) {
				if (rs.getString("REQUEST_STATUS").equals("PENDING")) {
					acceptRequest(recieverID, senderID);
					return;
				} else
					return;
			}
		}
		String query = "INSERT INTO " + FRIENDS_TABLE + " (SENDER_ID, RECIEVER_ID, REQUEST_STATUS, SEND_DATE) values"
				+ " (" + senderID + "," + recieverID + "," + "'PENDING', now())";
		upd.executeUpdate(query);
	}

	public void acceptRequest(int sender, int receiver) {
		String query = "UPDATE " + FRIENDS_TABLE
				+ " SET REQUEST_STATUS = 'CONFIRMED', CONFIRM_DATE = now() WHERE SENDER_ID = " + sender
				+ " AND RECIEVER_ID = " + receiver;

		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void acceptRequest(int friend_request_id) {
		String query = "UPDATE " + FRIENDS_TABLE
				+ " SET REQUEST_STATUS = 'CONFIRMED', CONFIRM_DATE = now() WHERE FRIEND_REQUEST_ID = "
				+ friend_request_id;
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteFriendship(int id1, int id2) {
		String query = "DELETE FROM " + FRIENDS_TABLE + " SENDER_ID = " + id1 + "AND RECEIVER_ID = " + id2;
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query = "DELETE FROM " + FRIENDS_TABLE + " SENDER_ID = " + id2 + "AND RECEIVER_ID = " + id1;
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeCon() throws SQLException {
		con.close();
	}

}