package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	// Set configuration of database
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DB = "user55";
	private static final String domain = "jdbc:mysql://127.0.0.1:3306/";
	private Connection con;

	public Database() {
		try {
			// load driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Can't find driver");
			System.exit(1);
		}
		String urlString = domain + DB;
		try {
			this.con = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int registerUser(String name, String passWord, String email) {
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("INSERT INTO user (username, password, email) " + "VALUES ('" + name
					+ "', '" + passWord + "', '" + email + "');");
			return result;
		} catch (Exception e) {
		}
		return 0;
	}
	public int addReviewsProcessor(String name, String businessId,String reviewRating,String reviewContent)
	{
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("insert into review (username,businessid,rating,description) value (\""+name+"\",\""+businessId+"\","+reviewRating+",\""+reviewContent+"\")");
			return result;
		} catch (Exception e) {
		}
		return 0;
	}
	public int deleteReviews(String reviewId)
	{
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("delete from review where reviewid = " + reviewId);
			return result;
		} catch (Exception e) {
		}
		return 0;	
	}
	public ResultSet editReviews(String reviewId)
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from review where reviewid =" + reviewId);
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public ResultSet myReviews(String name)
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from business right outer join review on business.businessid = review.businessid where review.username=\""
					+ name + "\";");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public ResultSet searchReviews()
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from business right outer join review on business.businessid = review.businessid ;");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public ResultSet showReviews(String keywords)
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from business right outer join review on business.businessid = review.businessid where review.description like \"%"
					+ keywords + "%\";");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public int updateReviews(String rate,String description,String reviewId)
	{
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("update review set rating =" + rate + ",description =\"" + description
					+ "\"where reviewid = " + reviewId);
			return result;
		} catch (Exception e) {
		}
		return 0;	
	}
	public ResultSet verifyUser(String name)
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT password FROM user WHERE username = \"" + name + "\";");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public ResultSet viewBUsinessLIst()
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from business left outer join review on business.businessid = review.businessid;");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public ResultSet viewSpecialBUsinessLIst(String paramBusinessid,String searchName)
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from business left outer join review on business.businessid = review.businessid where business.businessid=\""
					+ paramBusinessid + "\" or business.businessname=\"" + searchName + "\";");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public int addBusinessProcessor( String businessName,String address,String state,String city,String type){
			try {
				Statement stmt = this.con.createStatement();
				System.out.println("insert into business (businessname,addresss,state,city,type) value (\""+businessName+"\",\""+address+"\","+state+",\""+city+"\",\""+type+"\")");
			int result = stmt.executeUpdate("insert into business (businessid, businessname,addresss,state,city,type) value (uuid(), \""+businessName+"\",\""+address+"\",\""+state+"\",\""+city+"\",\""+type+"\")");
			return result;
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
	}
	public ResultSet showUser()
	{
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select username from user");
			return result;
		} catch (Exception e) {
		}
		return null ;	
	}
	public void closeDB () {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
