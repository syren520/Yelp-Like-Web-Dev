package Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewPageProcesser {
	/*
	 * Read html page template to generate the html string
	 */
	public static String readFile(String html) {
        String codes = "";
        try {
        	//Read html template
            codes = new String(Files.readAllBytes(Paths.get(html)));
        } catch (IOException e) {
        }
        return codes;
    }
}
