package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Database;

/*
 * Allow user change their reviews (rating and description).
 * Support both get and post method
 */
public class UpdateReviewsServlet extends BaseServlet {

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
		// Check if user exist in session
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(USERNAME);
		String rate = request.getParameter("rate");
		String description = request.getParameter("description");
		String reviewId = request.getParameter("reviewId");
		// user is not logged in(which means user not in session), redirect to
		// login page
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		try {

			// Create database instance to build database connection
			Connection db = Database.getDBInstance();
			// Create query statement
			Statement stmt = db.createStatement();
			// Execute a query, which returns a ResultSet object
			stmt.executeUpdate("update review set rating =" + rate + ",description =\"" + description
					+ "\"where reviewid = " + reviewId);
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect(response.encodeRedirectURL("/myReviews"));
	}
}