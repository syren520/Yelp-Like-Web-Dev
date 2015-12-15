package Controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Model.Database;

/*
 * Servlet invoked at addReviews.
 * Support both get and post method
 */
public class AddReviewsProcessorServlet extends BaseServlet {

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
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute(USERNAME);
		// Web resource reference - fileupload:
		// http://www.programcreek.com/java-api-examples/org.apache.commons.fileupload.FileItemFactory

		// DiskFileItemFactory creates FileItem instances which keep their
		// content either in memory,
		// for smaller items, or in a temporary file on disk,
		// for larger items ( the size threshold is configurable)
		DiskFileItemFactory fileItem = new DiskFileItemFactory();
		// handles multiple files from html form, sent using multipart/mixed
		// encoding type
		ServletFileUpload upload = new ServletFileUpload(fileItem);
		HashMap<String, String> newReview = new HashMap<String, String>();
		try {
			List<Object> items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			int i = 1;
			String reviewBusinessId = null;
			String reviewRate = null;
			String reviewDescription = null;
			Database db = new Database();
			String requiredReviewid = db.getAutoReviewId();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					if (fieldName.equals("businessId")) {
						reviewBusinessId = fieldValue;
					} else if (fieldName.equals("rate")) {
						reviewRate = fieldValue;
					} else {
						reviewDescription = fieldValue;
					}
					if (fieldValue == null || fieldValue.trim().equals("")) {
						response.sendRedirect(response.encodeRedirectURL("/addReviews?businessId=" + reviewBusinessId + "&" + STATUS + "=" + INVALIDINPUT));
						db.closeDB();
						return;
					}
				} else if (item.getSize() != 0) {
					new File("./webContent/image/" + requiredReviewid).mkdir();
					// define a file with path
					File image = new File("./webContent/image/" + requiredReviewid + "/" + i + ".jpg");
					db.addImage("./image/" + requiredReviewid + "/" + i + ".jpg", requiredReviewid);
					item.write(image);
					i++;
				}
			}
			db.addReviewsProcessor(name, reviewBusinessId, reviewRate, reviewDescription);
			response.sendRedirect(response.encodeRedirectURL("/viewSpecialBusinessList?businessid=" + reviewBusinessId));
			db.closeDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}