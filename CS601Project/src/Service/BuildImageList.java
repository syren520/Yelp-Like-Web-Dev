package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Model.Database;

/*
 * build a iamgeList that contains reviewId and image path
 */
public class BuildImageList {
	public static HashMap<String, Object> buildImageList(ResultSet reviewIds, Database db) {
		HashMap<String, Object> imagelist = new HashMap<String, Object>();
		try {
			while (reviewIds.next()) {
				String reviewid = reviewIds.getString("reviewid");
				ResultSet paths = db.getPath(reviewid);
				int i = 1;
				HashMap<Integer, String> pathlist = new HashMap<Integer, String>();
				while (paths.next()) {
					String path = paths.getString("path");
					pathlist.put(i, path);
					i++;
				}
				imagelist.put(reviewid, pathlist);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return imagelist;
	}
}