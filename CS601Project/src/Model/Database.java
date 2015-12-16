package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Some functions related to Database
 */
public class Database {
	// Set configuration of database
	private static final String USERNAME  = "user55";
	private static final String PASSWORD  = "user55";
	private static final String DB  = "user55";
	private static final String domain = "jdbc:mysql://sql.cs.usfca.edu:3306/";
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

	// Register user to database
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

	// Web-reference:
	// http://stackoverflow.com/questions/9191105/can-i-find-out-the-next-auto-increment-to-be-used
	// Get auto increment review id from database
	public String getAutoReviewId() {
		String autoReviewId = null;
		try {
			Statement stmt = this.con.createStatement();
			ResultSet autoReviewid = stmt
					.executeQuery("select auto_increment from information_schema.TABLES where TABLE_NAME ='review';");
			if (autoReviewid.next()) {
				autoReviewId = autoReviewid.getString("auto_increment");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return autoReviewId;
	}

	// Add new review to database
	public int addReviewsProcessor(String name, String reviewBusinessId, String reviewRate, String reviewDescription) {
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("insert into review (username,businessid,rating,description) value (\""
					+ name + "\",\"" + reviewBusinessId + "\"," + reviewRate + ",\"" + reviewDescription + "\")");
			return result;
		} catch (Exception e) {
		}
		return 0;
	}

	// Add image path information to image table in database
	public int addImage(String path, String reviewid) {
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt
					.executeUpdate("insert into image (reviewid,path) value (\"" + reviewid + "\",\"" + path + "\")");
			return result;
		} catch (Exception e) {
		}
		return 0;
	}

	// Get path information of image based on review id from table image
	public ResultSet getPath(String reviewid) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select path from image where reviewid=" + reviewid);
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// Delete a review from table review
	public int deleteReviews(String reviewId) {
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("delete from review where reviewid = " + reviewId);
			return result;
		} catch (Exception e) {
		}
		return 0;
	}

	// get a review from table review
	public ResultSet getReview(String reviewId) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select * from review where reviewid =" + reviewId);
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// get reviews and related business info for specific user
	public ResultSet getSpecificUserReviews(String name) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select * from business right outer join review on business.businessid = review.businessid where review.username=\""
							+ name + "\";");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// Get all reviews and related business info
	public ResultSet getAllReviews() {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select * from business right outer join review on business.businessid = review.businessid ;");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// Search reviews and related business info by keywords
	public ResultSet searchReviews(String keywords) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select * from business right outer join review on business.businessid = review.businessid where review.description like \"%"
							+ keywords + "%\";");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// Update review in table review
	public int updateReviews(String rate, String description, String reviewId) {
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate("update review set rating =" + rate + ",description =\"" + description
					+ "\"where reviewid = " + reviewId);
			return result;
		} catch (Exception e) {
		}
		return 0;
	}

	// select password from table user in order to verify user
	public ResultSet verifyUser(String name) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("SELECT password FROM user WHERE username = \"" + name + "\";");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// get all business and related review info
	public ResultSet viewBusinessList() {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select * from business left outer join review on business.businessid = review.businessid;");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// get one specific business and related reviews info
	public ResultSet viewSpecialBusinessList(String paramBusinessid) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select * from business left outer join review on business.businessid = review.businessid where business.businessid=\""
							+ paramBusinessid + "\";");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// get all reviewIds of one specific business
	public ResultSet getSpecificReviewIds(String paramBusinessid) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select reviewid from business left outer join review on business.businessid = review.businessid where business.businessid =\""
							+ paramBusinessid + "\";");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// get all reviewIds from table review
	public ResultSet getAllReviewIds() {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet allReviewId = stmt.executeQuery("select reviewid from review;");
			return allReviewId;
		} catch (Exception e) {
		}
		return null;
	}

	// get reviewIds of reviews from specific user
	public ResultSet getSpecificUserReviewIds(String name) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet myReviewId = stmt.executeQuery("select reviewid from review  where username =\"" + name + "\";");
			return myReviewId;
		} catch (Exception e) {
		}
		return null;
	}

	// search business
	public ResultSet searchBusiness(String searchName, String selection) {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery(
					"select * from business left outer join review on business.businessid = review.businessid where business."
							+ selection + " like \"%" + searchName + "%\";");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// add new business info into table business
	public int addBusiness(String businessName, String address, String state, String city, String type) {
		try {
			Statement stmt = this.con.createStatement();
			int result = stmt.executeUpdate(
					"insert into business (businessid, businessname,addresss,state,city,type) value (uuid(), \""
							+ businessName + "\",\"" + address + "\",\"" + state + "\",\"" + city + "\",\"" + type
							+ "\")");
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// select all username
	public ResultSet showUser() {
		try {
			Statement stmt = this.con.createStatement();
			ResultSet result = stmt.executeQuery("select username from user");
			return result;
		} catch (Exception e) {
		}
		return null;
	}

	// close the database connection
	public void closeDB() {
		try {
			this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
