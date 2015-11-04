package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	//Set configuration of database
	private static final String USERNAME  = "user55";
	private static final String PASSWORD  = "user55";
	private static final String DB  = "user55";
	private static final String domain = "jdbc:mysql://sql.cs.usfca.edu:3306/";
	
	public static Connection getDBInstance() throws SQLException{
		try {
			// load driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch (Exception e) {
			System.err.println("Can't find driver");
			System.exit(1);
			return null;
		}
		String urlString = domain + DB;
		Connection con = DriverManager.getConnection(urlString,
				USERNAME,
				PASSWORD);
		return con;
	}
}
