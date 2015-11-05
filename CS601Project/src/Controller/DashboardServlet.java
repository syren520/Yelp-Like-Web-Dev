package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import Model.Database;

/*
 * Servlet invoked at dashboard.
 * Generate dashboard page.
 * Support both get and post method
 */
public class DashboardServlet extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Check if user exist in session
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(USERNAME);

		// user is not logged in(which means user not in session), redirect to login page
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		
		//Data structure to store nested busniesses objects with review list
		HashMap<String, HashMap<String, Object>> businessesList = new HashMap<String, HashMap<String, Object>>();
		HashMap<String, Object> businessInfo;
		TreeSet<String> reviewIdList;
		HashMap<String, HashMap<String, String>> reviewsList = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> reviewInfo;
		
		try {
			//Create database instance to build database connection
			Connection db = Database.getDBInstance();
			//Create query statement 
			Statement stmt = db.createStatement();
			//Execute a query, which returns a ResultSet object
			ResultSet result = stmt.executeQuery("select * from business left outer join review on business.businessid = review.businessid;");
			//Go through result from db query
			while (result.next()) {
				//Get business and review info
				String businessId = result.getString("businessid");
				String businessName = result.getString("businessname");
				String businessCity = result.getString("state");
				String businessState = result.getString("city");
				String businessAddress = result.getString("addresss");
				String reviewId = result.getString("reviewid");
				String reviewUser = result.getString("username");
				String reviewContent = result.getString("description");
				String reviewRating = result.getString("rating");

				if (!businessesList.containsKey(businessId)) {
					//If business doesn't exist in businesslist, then add business info in
					// the list
					businessInfo = new HashMap<String, Object>();
					businessInfo.put("businessName", businessName);
					businessInfo.put("businessCity", businessCity);
					businessInfo.put("businessState", businessState);
					businessInfo.put("businessAddress", businessAddress);
					//Create review list
					reviewIdList = new TreeSet<String>();
					if (reviewId!=null) {
						reviewIdList.add(reviewId);
					}
					businessInfo.put("reviewList", reviewIdList);
					businessesList.put(businessId, businessInfo);
				} else {
					//If business exists in businesslist, then get reviewlist of the business and update it
					if (reviewId!=null) {
						((TreeSet<String>)businessesList.get(businessId).get("reviewList")).add(reviewId);

					}
				}				
				if (!reviewsList.containsKey(reviewId)) {
					//Update review info in the reviewList
					reviewInfo = new HashMap<String, String>();
					reviewInfo.put("reviewUser", reviewUser);
					reviewInfo.put("reviewContent", reviewContent);
					reviewInfo.put("reviewRating", reviewRating);

					reviewsList.put(reviewId, reviewInfo);
				}
			}
			//Iterator business to calculate the average rating of each business
			Iterator businessIterator = businessesList.entrySet().iterator();
			while (businessIterator.hasNext()) {
				Map.Entry pair = (Map.Entry) businessIterator.next();
				TreeSet<String> reviews = (TreeSet<String>) ((HashMap) pair.getValue()).get("reviewList");
				String businessId = (String) pair.getKey();
				String resultAveRate = "0";
				if (reviews.size() != 0) {
					float aveRating = 0;
					Iterator<String> reviewIterator = reviews.iterator();
					while (reviewIterator.hasNext()) {
						String reviewId = reviewIterator.next();
						aveRating += Float.parseFloat(reviewsList.get(reviewId).get("reviewRating"));
					}
					// Format to result to only kepp one decimal
					DecimalFormat df = new DecimalFormat("#.#");
					resultAveRate = df.format(aveRating / reviews.size());
				}
				// Add average rating to businesslist
				((HashMap) businessesList.get(businessId)).put("businessAveRating", resultAveRate);
			}
		    //close database
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Use string template to generate the html page
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("dashboardContent");
        view.add("userName", name);
        view.add("businessesList", businessesList);
        view.add("reviewsList", reviewsList);
        
		PrintWriter out = prepareResponse(response);
		out.print(view.render());

	}
}
