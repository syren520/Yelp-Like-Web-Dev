package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import Service.BuildImageList;

/*
 * Servlet invoked at myReviews.
 * Generate user's own reviews page.
 * Support both get and post method
 */
public class MyReviewsServlet extends BaseServlet {

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
		// user is not logged in(which means user not in session), redirect to
		// login page
		Database db = new Database();
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		HashMap<String, Object> myreviewlist = new HashMap<String, Object>();
		String status = request.getParameter(STATUS);
		boolean search = status != null && status.equals(NOREVIEWS) ? false : true;
		if (search) {
			try {
				ResultSet result = db.getSpecificUserReviews(name);
				myreviewlist = BuildDataList.buildDataList(result);
				if (myreviewlist == null) {
					response.sendRedirect(response.encodeRedirectURL("/myReviews?" + STATUS + "=" + NOREVIEWS));
					return;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HashMap<String, Object> imageList = new HashMap<String, Object>();
		ResultSet myReviewId = db.getSpecificUserReviewIds(name);
		imageList = BuildImageList.buildImageList(myReviewId, db);
		db.closeDB();
		// Use string template to generate the html page
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("myReviews");
		view.add("reviewsList", myreviewlist.get("reviewsList"));
		view.add("userName", name);
		view.add("businessesList", myreviewlist.get("businessesList"));
		view.add("search", search);
		view.add("imageList", imageList);
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
	}
}