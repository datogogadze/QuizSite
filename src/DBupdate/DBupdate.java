package DBupdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBupdate {
	
	private Connection con;
	
	public DBupdate (Connection con) {
		this.con = con;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		ResultSet tmp = null;
		PreparedStatement prep = con.prepareStatement(query);
		tmp = prep.executeQuery();
		return tmp;
	}

	public boolean executeUpdate(String query) throws SQLException {
		PreparedStatement prep = con.prepareStatement(query);
		prep.executeUpdate();
		return true;
	}
	
}
