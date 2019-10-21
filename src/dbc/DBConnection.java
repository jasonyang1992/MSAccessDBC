package dbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private Connection conn = null;
	private Statement state = null;
	private ResultSet rs = null;
	
	DBConnection(){
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException cnfex) {

			System.out.println("Problem in loading or registering MS Access JDBC driver");
			cnfex.printStackTrace();
		}
		
		try {
			File dbFile = new File("Test.accdb");
			String msAccDB = dbFile.getAbsolutePath();
			String dbURL = "jdbc:ucanaccess://" + msAccDB;
	
			conn = DriverManager.getConnection(dbURL);
	
			state = conn.createStatement();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (conn != null) {
				rs.close();
				state.close();
				conn.close();
			}
		} catch (SQLException sqlex) {
			System.out.println("There was an error in closing the database!");
		}
	}
}
