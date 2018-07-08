package DAO;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DBupdate.DBupdate;
import Message.Challenge;
import Model.MyDBInfo;
 
public class ChallengeDao extends TableNames{
   
    private Connection con;
    private DBupdate upd;
    public ChallengeDao() {
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
   
    public void addChallangeInDB(int senderID, int recieverID, String quizid) {
        String query = "INSERT INTO " + MESSAGE_TABLE + " (SENDER_USER_ID, RECIEVER_USER_ID, MESSAGE_TYPE_ID, MESSAGE_TEXT, SEND_DATE) VALUES ("
                + senderID + "," + recieverID + ", 2, "+quizid+", now())";
        try {
			upd.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    public void completeChallange(int messageID) {
        String query = "UPDATE " + MESSAGE_TABLE + " SET SEEN_DATE  = now() WHERE MAIL_MESSAGE_ID = " + messageID ;
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