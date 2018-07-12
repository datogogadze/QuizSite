package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * CS108 Student: This file will be replaced when we test your code. So, do not add any of your
 * assignment code to this file. Also, do not modify the public interface of this file.
 * Only change the public MyDBInfo constants so that it works with the database login credentials 
 * that we emailed to you.
 */
public class MyDBInfo {
	public static final String MYSQL_USERNAME = "root";
	public static final String MYSQL_PASSWORD = "";
	public static final String MYSQL_DATABASE_SERVER = "localhost";
	public static final int MYSQL_DATABASE_PORT = 3306;
	public static final String MYSQL_DATABASE_NAME = "quizbase";
	public static final String JDBC_DATABASE_URL = "jdbc:mysql://" + MYSQL_DATABASE_SERVER + ":" + MYSQL_DATABASE_PORT;
	private static Connection conn;
	public MyDBInfo() {
		try {
			Class.forName("com.mysql.jdbc.Driver");		// Called to just initialize JDBC driver
			conn = DriverManager.getConnection(JDBC_DATABASE_URL, // Connect to database
					MYSQL_USERNAME, MYSQL_PASSWORD);
			
			Statement stmt = conn.createStatement();
			stmt.execute("USE " + MYSQL_DATABASE_NAME);			
			System.out.println("==============");
			
			conn.close();
		} catch (ClassNotFoundException e) {
			// No com.mysql.jdbc.Driver class found in classpath
			e.printStackTrace();
		} catch (SQLException e) {
			// There is a driver but something went wrong while astablishing connection,
			//   e.g. username or password is wrong, or server url is not correct.
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		return conn;
	}


	public static void main(String[] args) {
		new MyDBInfo();
	}
}
