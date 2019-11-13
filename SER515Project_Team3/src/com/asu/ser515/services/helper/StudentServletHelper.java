package com.asu.ser515.services.helper;


/**
 * @author Kushagr Jolly
 * Nov 8, 2019
 */

public class StudentServletHelper {

	private static String errorPage = "/error.html";
	private static String exceptionPage = "/exception.html";
	private String student1Page = "/student1.jsp";
	private String student6Page = "/student6.jsp";	

	/**
	 * @param userType
	 * @return
	 */
	public String mapUserToPage(int userType) {
		if (userType == 0) {
			return errorPage;
		}
		if (userType == -1) {
			return exceptionPage;
		}
		else {
			DBConnServiceHelper dbHelper = new DBConnServiceHelper();
			String role = dbHelper.mapDBtoUsertype(userType);
			if (role.equalsIgnoreCase("studentGrade_1")) {
				return student1Page;
			}
			if (role.equalsIgnoreCase("studentGrade_6")) {
				return student6Page;
			}
		}
		return null;
	}
}
