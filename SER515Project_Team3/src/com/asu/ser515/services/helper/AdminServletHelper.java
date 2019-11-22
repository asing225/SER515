package com.asu.ser515.services.helper;

/**
 * @author anurag933103
 * @date 11/17/2019
 * 
 * Helper class to determine which page to route to as per the user.
 * */

public class AdminServletHelper {
	private static String errorPage = "error.html";
	private static String exceptionPage = "exception.html";
	private static String adminPage = "/admin.html";
	
	// method to map the user to it's page
	public String mapAdminToPage(int userType) {
		if (userType == 0) {
			return errorPage;
		}
		if (userType == -1) {
			return exceptionPage;
		}
		else {
			DBConnServiceHelper dbHelper = new DBConnServiceHelper();
			String role = dbHelper.mapDBtoUsertype(userType);
			if (role.equalsIgnoreCase("admin")) {
				return adminPage;
			}
	    }
		return null;
    }
}