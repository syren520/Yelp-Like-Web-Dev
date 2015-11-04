package Controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class BootStrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {	
		//Set port number
		Server server = new Server(11050);
		// create a ServletHander to attach servlets
		ServletContextHandler servhandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		//Make server could serve static files
		servhandler.setContextPath("/");
		DefaultServlet defaultServlet = new DefaultServlet();
		ServletHolder holderPwd = new ServletHolder("default", defaultServlet);
		holderPwd.setInitParameter("resourceBase", "./webContent/");
		servhandler.addServlet(holderPwd, "/*");
		
		//Map server url
		servhandler.addServlet(LoginServlet.class, "/login");
		servhandler.addServlet(VerifyUserServlet.class, "/verifyuser");
		servhandler.addServlet(DashboardServlet.class, "/dashboard");
		servhandler.addServlet(LogoutServlet.class, "/logout");
		servhandler.addServlet(RegisterUserServlet.class, "/registration");

		// set the list of handlers for the server
		server.setHandler(servhandler);

		// start the server
		server.start();
		server.join();

	}

}
