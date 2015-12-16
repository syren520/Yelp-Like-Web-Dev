package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Database;

/*
 * Servlet invoked at addBusiness.
 * Support both get and post method
 */
public class AddBusinessProcessorServlet extends BaseServlet {

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
		String businessName = request.getParameter("businessName");
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String type = request.getParameter("type");
		if ( businessName== null || businessName.trim().equals("") || address == null || address.trim().equals("")|| state == null || state.trim().equals("")|| city == null || city.trim().equals("")|| type == null || type.trim().equals("")) {
			response.sendRedirect(response.encodeRedirectURL("/addBusiness?" + STATUS + "=" + ERROR));
			return;
		}
		Database db=new Database();
		int result = db.addBusiness(businessName,address,state,city,type);
		response.sendRedirect(response.encodeRedirectURL("/viewBusinessList"));
		db.closeDB();
	}

}