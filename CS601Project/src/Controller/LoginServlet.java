package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import Service.ViewPageProcesser;

/*
 * Allows a user to log in
 */
public class LoginServlet extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//if user is logged in, redirect
		if(session.getAttribute(USERNAME) != null) {
			response.sendRedirect(response.encodeRedirectURL("/dashboard"));
			return;
		}
				
		String status = getParameterValue(request, STATUS);
				
		boolean statusok = status != null && status.equals(ERROR)?false:true;
		boolean redirected = status != null && status.equals(NOT_LOGGED_IN)?true:false;
		boolean registrationInvalidData = status != null && status.equals(REGISTRATION_ERROR_INVALID_DATA)?true:false;
		boolean isRegistrationError = status != null && status.equals(IS_REGISTRATION_ERROR)?true:false;
		
		STGroup stGroup = new STGroupDir("webContent/template", '$', '$');
		ST view = stGroup.getInstanceOf("login");
        view.add("statusok", statusok);
        view.add("redirected", redirected);
        view.add("isRegistrationError", isRegistrationError);
        view.add("registrationInvalidData", registrationInvalidData);
        
		//output text box requesting user name
		PrintWriter out = prepareResponse(response);
		out.print(view.render());

	}

}
