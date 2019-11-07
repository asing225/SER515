package com.asu.ser515.services.helper;

/**
 * @author amanjotsingh
 * @date 10/24/2019
 * 
 * Helper class to determine which page to route to for the teacher.
 * */

public class TeacherServletHelper {

	private static String errorPage = "error.html";
	private static String exceptionPage = "exception.html";
	private String teacherPage = "teacherHomePage.html";
	
	// method to map the user to it's page
	public String mapTeacherToPage(int quizCreated, int questionsCreated) {
		if (quizCreated == 1 && questionsCreated == 1) {
			return teacherPage;
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
