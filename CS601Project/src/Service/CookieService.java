package Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieService {
	public static HttpServletResponse setCookieExpiredTime(HttpServletRequest request, HttpServletResponse response) {
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
		
		return response;
	}
}
