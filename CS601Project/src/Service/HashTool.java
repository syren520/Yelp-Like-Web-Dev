package Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTool {
	/*
	 * User SHA-256 to hash to string in order to generate
	 * safe password
	 */
    public static String hashString (String str)
    {
    	String password = str;
        StringBuffer hashedStr = new StringBuffer();
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
	        md.update(password.getBytes());
	        byte byteData[] = md.digest();
	        for (int i = 0; i < byteData.length; i++) {
	        	hashedStr.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
             	
    	return hashedStr.toString();
    }
}