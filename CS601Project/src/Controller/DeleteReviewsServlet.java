package Controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

import Model.Database;

/*
 * Servlet invoked at delete.
 * Delete a review.
 * Support both get and post method
 */
public class DeleteReviewsServlet extends BaseServlet {

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
		String reviewId = request.getParameter("reviewId");
		Database db=new Database();
		db.deleteReviews(reviewId);
		//define a file folder with path to delete
		File reviewImageFolder = new File("/webContent/image/"+reviewId+"/");
		FileUtils.deleteDirectory(reviewImageFolder);
		response.sendRedirect(response.encodeRedirectURL("/myReviews"));
		db.closeDB();
	}
}