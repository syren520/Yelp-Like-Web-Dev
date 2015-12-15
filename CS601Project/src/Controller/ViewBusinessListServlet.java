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
 * Servlet invoked at viewBusinessList.
 * Generate view business list page.
 * Support both get and post method
 */
public class ViewBusinessListServlet extends BaseServlet {
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
		Database db = new Database();
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		HashMap<String, Object> formattedData = new HashMap<String, Object>();
		try {
			ResultSet result = db.viewBusinessList();
			formattedData = BuildDataList.buildDataList(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<String, Object> imageList = new HashMap<String, Object>();
		ResultSet allReviewId = db.getAllReviewIds();
		imageList = BuildImageList.buildImageList(allReviewId, db);
		// Get status to check the searching result
		String status = request.getParameter(STATUS);
		boolean search = status != null && status.equals(NOTFOUND) ? false : true;
		boolean invalidData = status != null && status.equals(INVALIDINPUT) ? true : false;
		// Use string template to generate the html page
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("viewBusinessList");
		view.add("userName", name);
		if (formattedData == null) {
			// If no business exist, then set businesseslist and reviewslist to
			// null
			view.add("businessesList", null);
			view.add("reviewsList", null);
		} else {
			// Otherwise put busiesslist and reviewslist in template
			view.add("businessesList", formattedData.get("businessesList"));
			view.add("reviewsList", formattedData.get("reviewsList"));
		}
		view.add("search", search);
		view.add("imageList", imageList);
		view.add("invalidData", invalidData);
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
		db.closeDB();
	}
}