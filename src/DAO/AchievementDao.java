package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Achievement.Achievement;
import DBupdate.DBupdate;
import Model.MyDBInfo;

public class AchievementDao extends TableNames {

	private Connection con;
	private DBupdate upd;

	public AchievementDao() {
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

	public ArrayList<Achievement> getAchievementsByID(int ID) {
		ArrayList<Achievement> achieves = new ArrayList<Achievement>();
		String query = "SELECT * FROM " + ACHIEVMENTS_TABLE + " WHERE USER_ID = " + ID;
		try {
			ResultSet rs = upd.executeQuery(query);
			while (rs.next()) {
				int userID = ID;
				int userAchievID = rs.getInt("USER_ACHIEV_ID");
				int achievTypeID = rs.getInt("ACHIEV_TYPE_ID");
				String achieveDate = rs.getString("FINISH_DATE");
				String achievementName = getAchieveNameByType(achievTypeID);
				Achievement achiev = new Achievement(achievementName, userID);
				achiev.setAchievementId(userAchievID);
				achiev.setFinishTime(achieveDate);
				achieves.add(achiev);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return achieves;
	}

	public String getAchieveNameByType(int achievType) {
		String query = "SELECT ACHIEVMENT_TYPE_DESC FROM " + ACHIEVMENT_TYPE_TABLE + " WHERE ACHIEVMENT_TYPE_ID = "
				+ achievType;
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next()) {
				return rs.getString("ACHIEVMENT_TYPE_DESC");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Unkown Achieve Type";
	}

	public int getAchievementIDbyName(String name) {
		String query = "SELECT ACHIEVMENT_TYPE_ID FROM " + ACHIEVMENT_TYPE_TABLE + " WHERE ACHIEVMENT_TYPE_DESC = '"
				+ name + "'";
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	public Achievement getAchievByTypeAndID(int UserID, int typeID) {
		String query = "SELECT * FROM " + ACHIEVMENTS_TABLE + " WHERE ACHIEV_TYPE_ID = " + typeID + " and USER_ID = "
				+ UserID;
		try {
			ResultSet rs = upd.executeQuery(query);
			if (rs.next()) {
				String achievName = getAchieveNameByType(typeID);
				int achievID = rs.getInt("USER_ACHIEV_ID");
				String finishDate = rs.getString("FINISH_DATE");
				Achievement ach = new Achievement(achievName, UserID);
				ach.setAchievementId(achievID);
				ach.setFinishTime(finishDate);
				return (ach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void addAchievement(Achievement achiev) {
		String querySelect = "SELECT * FROM " + ACHIEVMENTS_TABLE + " WHERE USER_ID =" + achiev.getUserID()
				+ " AND ACHIEV_TYPE_ID = " + getAchievementIDbyName(achiev.getAchievementName());
		try {
			ResultSet rs = upd.executeQuery(querySelect);
			if (rs.next()) {
				return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "INSERT INTO " + ACHIEVMENTS_TABLE + " (USER_ID,ACHIEV_TYPE_ID,FINISH_DATE) VALUES ("
				+ achiev.getUserID() + "," + getAchievementIDbyName(achiev.getAchievementName()) + "," + "now()" + ")";

		try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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