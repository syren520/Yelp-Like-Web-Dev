package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import Model.Database;
import Service.BuildDataList;

/*
 * Servlet invoked at editReviews.
 * Generate edit view page.
 * Support both get and post method
 */
public class AddReviewsProcessorServlet extends BaseServlet {

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
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(USERNAME);
		String businessId = request.getParameter("businessId");
		String reviewContent = request.getParameter("description");
		String reviewRating = request.getParameter("rate");
		Database db=new Database();
		int result = db.AddReviews2(name, businessId, reviewRating, reviewContent);
		response.sendRedirect(response.encodeRedirectURL("/viewBusinessList"));
		db.closeDB();
	}

}