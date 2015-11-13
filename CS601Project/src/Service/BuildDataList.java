package Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class BuildDataList {
	public static HashMap<String, Object> buildDataList(ResultSet result) throws SQLException {
		// Data structure to store nested busniesses objects with review list
		HashMap<String, HashMap<String, Object>> businessesList = new HashMap<String, HashMap<String, Object>>();
		HashMap<String, Object> businessInfo;
		TreeSet<String> reviewIdList;
		HashMap<String, HashMap<String, String>> reviewsList = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> reviewInfo;
		while (result.next()) {
			// Get business and review info
			String businessId = result.getString("businessid");
			String businessName = result.getString("businessname");
			String businessCity = result.getString("state");
			String businessState = result.getString("city");
			String businessAddress = result.getString("addresss");
			String reviewId = result.getString("reviewid");
			String reviewUser = result.getString("username");
			String reviewContent = result.getString("description");
			String reviewRating = result.getString("rating");

			if (!businessesList.containsKey(businessId)) {
				// If business doesn't exist in businesslist, then add business
				// info in
				// the list
				businessInfo = new HashMap<String, Object>();
				businessInfo.put("businessName", businessName);
				businessInfo.put("businessCity", businessCity);
				businessInfo.put("businessState", businessState);
				businessInfo.put("businessAddress", businessAddress);
				// Create review list
				reviewIdList = new TreeSet<String>();
				if (reviewId != null) {
					reviewIdList.add(reviewId);
				}
				businessInfo.put("reviewIdList", reviewIdList);
				businessesList.put(businessId, businessInfo);
			} else {
				// If business exists in businesslist, then get reviewIdList of
				// the business and update it
				if (reviewId != null) {
					((TreeSet<String>) businessesList.get(businessId).get("reviewIdList")).add(reviewId);
				}
			}
			if (!reviewsList.containsKey(reviewId)) {
				// Update review info in the reviewList
				reviewInfo = new HashMap<String, String>();
				reviewInfo.put("reviewUser", reviewUser);
				reviewInfo.put("reviewContent", reviewContent);
				reviewInfo.put("reviewRating", reviewRating);

				reviewsList.put(reviewId, reviewInfo);
			}
		}
		// Calculate average rating of business
		Iterator businessIterator = businessesList.entrySet().iterator();
		while (businessIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) businessIterator.next();
			TreeSet<String> reviews = (TreeSet<String>) ((HashMap) pair.getValue()).get("reviewIdList");
			String businessId = (String) pair.getKey();
			String resultAveRate = "0";
			if (reviews.size() != 0) {
				float aveRating = 0;
				Iterator<String> reviewIterator = reviews.iterator();
				while (reviewIterator.hasNext()) {
					String reviewId = reviewIterator.next();
					aveRating += Float.parseFloat(reviewsList.get(reviewId).get("reviewRating"));
				}
				// Format to result to only keep one decimal
				DecimalFormat df = new DecimalFormat("#.#");
				resultAveRate = df.format(aveRating / reviews.size());
			}
			// Add average rating to businesslist
			((HashMap) businessesList.get(businessId)).put("businessAveRating", resultAveRate);
		}
		// put result into hashmap
		HashMap<String, Object> list = new HashMap<String, Object>();
		list.put("businessesList", businessesList);
		list.put("reviewsList", reviewsList);

		return list;
	}

}