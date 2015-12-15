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
 * Servlet invoked at viewSpecialBusinessList.
 * Generate view special business list page.
 * Support both get and post method
 */
public class ViewSpecialBusinessListServlet extends BaseServlet {
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
		String paramBusinessid = request.getParameter("businessid");
		HashMap<String, Object> imageList = new HashMap<String, Object>();
		Database db = new Database();
		ResultSet reviewIds = db.getSpecificReviewIds(paramBusinessid);
		imageList = BuildImageList.buildImageList(reviewIds, db);
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		HashMap<String, Object> speciallist = new HashMap<String, Object>();
		try {
			ResultSet result = db.viewSpecialBusinessList(paramBusinessid);
			speciallist = BuildDataList.buildDataList(result);
			if (speciallist == null) {
				response.sendRedirect(response.encodeRedirectURL("/viewBusinessList?" + STATUS + "=" + NOTFOUND));
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.closeDB();
		// Use string template to generate the html page
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("viewSpecialBusinessList");
		view.add("userName", name);
		view.add("businessesList", speciallist.get("businessesList"));
		view.add("reviewsList", speciallist.get("reviewsList"));
		view.add("imageList", imageList);
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
	}
}