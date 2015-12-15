package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import Model.Database;

/*
 * Servlet invoked at editReviews.
 * Generate edit view page.
 * Support both get and post method
 */
public class EditReviewsServlet extends BaseServlet {

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
		String reviewId = request.getParameter("reviewId");
		String status = request.getParameter(STATUS);
		String reviewContent = "";
		Database db = new Database();
		ResultSet result = db.getReview(reviewId);
		try {
			if (result.next()) {
				reviewContent = result.getString("description");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// user is not logged in(which means user not in session), redirect to
		// login page
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("editReviews");
		view.add("userName", name);
		view.add("reviewId", reviewId);
		view.add("description", reviewContent);
		view.add("status", status);
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
		db.closeDB();
	}

}