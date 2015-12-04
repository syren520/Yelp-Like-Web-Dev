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

/*
 * Servlet invoked at searchBusiness.
 * Allow user to search business by some keywords.
 * Support both get and post method
 */
public class SearchBusinessServlet extends BaseServlet {
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
		String searchName = request.getParameter("searchname");
		String selection=request.getParameter("selection");
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		HashMap<String, Object> selectlist = new HashMap<String, Object>();
		try {
			Database db = new Database();
			ResultSet result = db.searchBusiness( searchName,selection );
			System.out.println(result.toString());
			selectlist = BuildDataList.buildDataList(result);
			if (selectlist == null) {
				response.sendRedirect(response.encodeRedirectURL("/viewBusinessList?" + STATUS + "=" + NOTFOUND));
				return;
			}
			db.closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Use string template to generate the html page
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("searchBusiness");
		view.add("userName", name);
		view.add("businessesList", selectlist.get("businessesList"));
		view.add("reviewsList", selectlist.get("reviewsList"));
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
	}
}