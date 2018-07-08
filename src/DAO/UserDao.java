package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBupdate.DBupdate;
import Model.MyDBInfo;
import User.User;

public class UserDao extends TableNames {
	public static final String CONTEXT_ATTRIBUTE_NAME = "userAccess";
	private Connection con;
	private DBupdate upd;

	public UserDao() {
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

	// searches for username in table USERINFO,returns true or false accordingly
	public boolean searchUser(String userName) {
		String query = "Select USER_NAME from " + USER_INFO_TABLE + " where USER_NAME = '" + userName + "'";
		try {
			ResultSet set;
			set = upd.executeQuery(query);
			if (getRowNumbers(set) == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public ArrayList<User> getAllUsers(){
		ArrayList<User> all = new ArrayList<User>();
		String query = "SELECT * FROM " + USER_INFO_TABLE;
		ResultSet set;
		try {
			set = upd.executeQuery(query);
			while (set.next()) {
				int id = set.getInt(1);
				String fname = set.getString(3);
				String lname = set.getString(4);
				String uname = set.getString(2);
				String mail = set.getString(5);
				String pass = set.getString(6);
				User user = new User(fname, lname, uname,pass, mail );
				user.setType(set.getInt(7));
				user.setId(id);
				all.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return all;
	}
	public boolean searchMail(String mail) {
		String query = "Select USER_NAME from " + USER_INFO_TABLE + " where E_MAIL = '" + mail + "'";
		try {
			ResultSet set = upd.executeQuery(query);
			int result;
			result = getRowNumbers(set);
			if (result == 0)
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	// adds user in table USERINFO using registration data provided. returns
	// false if username already in database
	public boolean addUser(User regData) {
		if (searchUser(regData.getUserName()))
			return false;
		String query = "insert into " + USER_INFO_TABLE
				+ " (USER_NAME,PASSWORD_HASH,FIRST_NAME,LAST_NAME,E_MAIL,USER_TYPE) " + "values " + "('"
				+ regData.getUserName() + "','" + regData.getPass() + "','" + regData.getName() + "','"
				+ regData.getLastName() + "','" + regData.getMail() + "','" + regData.getType() + "')";
		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// returns username associated password,returns null if user name does not exist
	public String getPassword(String userName) {
		String password = "";
		String query = "Select PASSWORD_HASH from " + USER_INFO_TABLE + " where USER_NAME = '" + userName + "'";
		ResultSet set;
		try {
			set = upd.executeQuery(query);
			if (set.next())
				password = set.getString(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return password;
	}

	public int getUserId(String username) {
		int userid = -1;
		String query = "Select USER_ID from " + USER_INFO_TABLE + " where USER_NAME = '" + username + "'";
		ResultSet rs;
		try {
			rs = upd.executeQuery(query);
			if (rs.next()) {
				userid = rs.getInt("USER_ID");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return userid;

	}

	public ArrayList<String> getFriends(int userID) {
		ArrayList<String> friends = new ArrayList<String>();
		String query = "Select * from " + FRIENDS_TABLE + " where SENDER_ID = " + userID
				+ " and REQUEST_STATUS = 'CONFIRMED'";
		ResultSet rs;
		try {
			rs = upd.executeQuery(query);
			while (rs.next()) {
				int recieverID = rs.getInt("RECIEVER_ID");
				friends.add(getUsernameByID(recieverID));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query2 = "Select * from " + FRIENDS_TABLE + " where RECIEVER_ID = " + userID
				+ " and REQUEST_STATUS = 'CONFIRMED'";
		ResultSet rs2;
		try {
			rs2 = upd.executeQuery(query2);
			while (rs2.next()) {
				int sender = rs2.getInt("SENDER_ID");
				friends.add(getUsernameByID(sender));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return friends;
	}

	public User getUserByID(int userID) {
		String query = "SELECT * FROM " + USER_INFO_TABLE + " WHERE USER_ID = " + userID;
		ResultSet rs;
		try {
			rs = upd.executeQuery(query);
			if (rs.next()) {
				String username = rs.getString("USER_NAME");
				String firstname = rs.getString("FIRST_NAME");
				String lastname = rs.getString("LAST_NAME");
				String email = rs.getString("E_MAIL");
				String pass = rs.getString("PASSWORD_HASH");
				int type = rs.getInt("USER_TYPE");
				User user = new User(firstname, lastname, username, pass, email, type);
				user.setId(rs.getInt("USER_ID"));
				return user;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public String getUsernameByID(int userID) {
		String query = "SELECT USER_NAME FROM WEB_USERS WHERE USER_ID = " + userID;
		ResultSet rs;
		try {
			rs = upd.executeQuery(query);
			if (rs.next()) {
				return rs.getString("USER_NAME");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

//	public boolean isAdmin(int userID) {
//		String query = "SELECT USER_TYPE FROM WEB_USERS WHERE USER_ID = " + userID;
//		ResultSet rs;
//		try {
//			rs = upd.executeQuery(query);
//			if (rs.next()) {
//				int type = rs.getInt("USER_TYPE");
//				return type == 1;
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		return false;
//	}


	public void closeCon() throws SQLException {
		con.close();
	}
}
