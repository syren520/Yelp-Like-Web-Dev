package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Database;

/*
 * Servlet invoked at login.
 * Creates cookie and redirects to main ListServlet.
 */
public class VerifyUserServlet extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// VerifyUser does not accept GET requests. Just redirect to login with
		// error status.
		response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter(USERNAME);
		String passWord = request.getParameter(PASSWORD);
		//Check if name and password is valid
		if (name == null || name.trim().equals("") || passWord == null || passWord.trim().equals("")) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
			return;
		}
		try {
			Connection db = Database.getDBInstance();
			Statement stmt = db.createStatement();
			// execute a query, which returns a ResultSet object
			ResultSet result = stmt
					.executeQuery("SELECT password FROM user WHERE username = \"" + name + "\";");
			
			if (!result.next()) {
				// ResultSet is empty
				response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
			} else {
				String storedPassword = result.getString("password");
				if (storedPassword.equals(passWord)) {
					HttpSession session = request.getSession();
					session.setAttribute(USERNAME, name);
					//Get cookies from request
					Cookie[] cookies = request.getCookies();
					if (cookies != null && cookies.length > 0) {
						//If cookies is not null then go through all cookies to find JSESSIONID,
						//Set the cookie expired time to be 10 days
						//Then add the cookie to response and set back to browser
						for(int i = 0; i<cookies.length; i++) {
							if(cookies[i].getName().compareTo("JSESSIONID") == 0) {
								cookies[i].setMaxAge(3600*24*10);
								response.addCookie(cookies[i]);
							}
						}
					}
					
					response.sendRedirect(response.encodeRedirectURL("/dashboard"));
				} else {
					response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
				}
			}
			db.close();
		} catch (SQLException e) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
