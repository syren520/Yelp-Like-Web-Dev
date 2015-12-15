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

/*
 * Servlet invoked at show all users of Yap.
 * Generate view users list page.
 * Support both get and post method
 */
public class ShowUserServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// Check if user exist in session
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(USERNAME);
		if (name == null) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + NOT_LOGGED_IN));
			return;
		}

		Database db = new Database();
		ResultSet result = db.showUser();
		HashMap<Integer, String > users = new HashMap<Integer, String>();
		int i=1;
		while (result.next()) {
			String userName=result.getString("username");
			users.put(i,userName);
			i++;
		}
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("showUser");
		view.add("userName", name);
		view.add("userList", users);
		PrintWriter out = prepareResponse(response);
		out.print(view.render());
		db.closeDB();
	}
}