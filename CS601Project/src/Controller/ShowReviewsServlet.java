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
 * Servlet invoked at showReviews.
 * Generate reviews that users selected in the serachReviews page.
 * Support both get and post method
 */
public class ShowReviewsServlet extends BaseServlet {

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
		String keywords = request.getParameter("keywords").trim();
		Database db = new Database();
		// user is not logged in(which means user not in session), redirect to
		// login page
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		HashMap<String, Object> searchreviewlist = new HashMap<String, Object>();
		try {
			ResultSet result = db.searchReviews(keywords);
			searchreviewlist = BuildDataList.buildDataList(result);
			if (searchreviewlist == null) {
				response.sendRedirect(response.encodeRedirectURL("/searchReviews?" + STATUS + "=" + NOTFOUND));
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, Object> imageList = new HashMap<String, Object>();
		ResultSet allReviewId = db.getAllReviewIds();
		imageList = BuildImageList.buildImageList(allReviewId, db);
		String status = request.getParameter(STATUS);
		boolean search = status != null && status.equals(NOTFOUND) ? false : true;
		// Use string template to generate the html page
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("showReviews");
		view.add("reviewsList", searchreviewlist.get("reviewsList"));
		view.add("userName", name);
		view.add("businessesList", searchreviewlist.get("businessesList"));
		view.add("imageList", imageList);
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
		db.closeDB();
	}
}