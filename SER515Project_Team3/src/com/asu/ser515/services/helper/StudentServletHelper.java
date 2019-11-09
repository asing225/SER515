package com.asu.ser515.services.helper;


/**
 * @author Kushagr Jolly
 * Nov 8, 2019
 */

public class StudentServletHelper {

	private static String errorPage = "error.html";
	private static String exceptionPage = "exception.html";
//	private String teacherPage = "teacherHomePage.html";
	private String studentPage = "studentLandingPage.jsp";
	
	// method to map the user to it's page
	public String mapTeacherToPage(int quizCreated, int questionsCreated) {
		if (quizCreated == 1 && questionsCreated == 1) {
			return studentPage;
		}
		else if(quizCreated == 0) {
			// Error in database
			return errorPage;
		}
		else {
			return exceptionPage;
		}
	}
}
