package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Database;
import Service.CookieService;

public class RegisterUserServlet extends BaseServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// register does not accept GET requests. Just redirect to login with
		// error status.
		response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter(USERNAME);
		String passWord = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		//Check if passed in data is valid
		if (name == null || name.trim().equals("") 
				|| email == null || email.trim().equals("") 
				|| passWord == null || passWord.trim().equals("")) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + REGISTRATION_ERROR_INVALID_DATA));
			return;
		}
		try {
			Connection db = Database.getDBInstance();

			Statement stmt = db.createStatement();
			int result = stmt.executeUpdate("INSERT INTO user (username, password, email) "
					+ "VALUES ('" + name + "', '" + passWord + "', '" + email + "');");
			if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute(USERNAME, name);
				response = CookieService.setCookieExpiredTime(request, response);
				response.sendRedirect(response.encodeRedirectURL("/dashboard"));
			} else {
				response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + IS_REGISTRATION_ERROR));
			}
			//Close database
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + IS_REGISTRATION_ERROR));
		}
	}
}
