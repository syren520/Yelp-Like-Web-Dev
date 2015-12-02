package Controller;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class BootStrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {	
		//Set port number
		Server server = new Server(11050);
		// create a ServletHander to attach servlets
		ServletContextHandler servhandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		
		//Map server url
		servhandler.addServlet(LoginServlet.class, "/login");
		servhandler.addServlet(VerifyUserServlet.class, "/verifyuser");
		servhandler.addServlet(DashboardServlet.class, "/dashboard");
		servhandler.addServlet(LogoutServlet.class, "/logout");
		servhandler.addServlet(RegisterUserServlet.class, "/registration");
		servhandler.addServlet(ViewBusinessListServlet.class, "/viewBusinessList");
		servhandler.addServlet(ViewSpecialBusinessListServlet.class, "/viewSpecialBusinessList");
		servhandler.addServlet(SearchReviewsServlet.class, "/searchReviews");
		servhandler.addServlet(ShowReviewsServlet.class, "/showReviews");
		servhandler.addServlet(MyReviewsServlet.class, "/myReviews");
		servhandler.addServlet(DeleteReviewsServlet.class, "/delete");
		servhandler.addServlet(UpdateReviewsServlet.class, "/updateReviews");
		servhandler.addServlet(EditReviewsServlet.class, "/editReviews");
		servhandler.addServlet(AddReviewsServlet.class, "/addReviews");
		servhandler.addServlet(AddReviewsProcessorServlet.class, "/addReviewsProcessor");
		servhandler.addServlet(AddBusinessServlet.class, "/addBusiness");
		servhandler.addServlet(AddBusinessProcessorServlet.class, "/addBusinessProcessor");
		servhandler.addServlet(ShowUserServlet .class, "/showUser");
		//Make server could serve static files
		ResourceHandler resource_handler = new ResourceHandler();
		//Set the directory to true
        resource_handler.setDirectoriesListed(true);
        //Set static resources root is folder webContent
        resource_handler.setResourceBase("webContent");
        //Create a list to hold all handlers
        HandlerList handlers = new HandlerList();
        //Add handlers to this handler list
        handlers.setHandlers(new Handler[] { resource_handler, servhandler });
		// set the list of handlers for the server
        //server.setHandler(servhandler);
        //server.setHandler(resource_handler);
        server.setHandler(handlers);
		// start the server
		server.start();
		server.join();

	}

}