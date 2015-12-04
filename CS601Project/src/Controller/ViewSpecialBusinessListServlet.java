package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SelectionKey;
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
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}
		HashMap<String, Object> speciallist = new HashMap<String, Object>();
		try {
			Database db = new Database();
			ResultSet result = db.viewSpecialBusinessList(paramBusinessid );
			speciallist = BuildDataList.buildDataList(result);
			if (speciallist == null) {
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
		ST view = stGroup.getInstanceOf("viewSpecialBusinessList");
		view.add("userName", name);
		view.add("businessesList", speciallist.get("businessesList"));
		view.add("reviewsList", speciallist.get("reviewsList"));
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
	}
}