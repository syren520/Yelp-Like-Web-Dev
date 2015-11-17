package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

/*
 * Base class for all Servlets in this application.
 * Provides general helper methods.
 */
public class BaseServlet extends HttpServlet {

	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String CONFIRMPASS = "confirmPassword";
	public static final String EMAIL = "email";
	public static final String STATUS = "status";
	public static final String ERROR = "error";
	public static final String NOT_LOGGED_IN = "not_logged_in";
	public static final String IS_REGISTRATION_ERROR = "is_registration_error";
	public static final String REGISTRATION_ERROR_INVALID_DATA = "registration_error_invalid_data";
	public static final String NOTFOUND = "not_found";
	public static final String NOREVIEWS = "noreviews";

	/*
	 * Prepare a response of HTML 200 - OK. Set the content type and status.
	 * Return the PrintWriter.
	 */
	protected PrintWriter prepareResponse(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		return response.getWriter();
	}
}