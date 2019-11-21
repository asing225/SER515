package com.asu.ser515.services.helper;

/**
 * @author amanjotsingh
 * @date 10/24/2019
 * 
 * Helper class to determine which page to route to for the teacher.
 * */

public class TeacherServletHelper {

//	private static String errorPage = "error.html";
	private static String exceptionPage = "exception.html";
	private String teacherPage = "/teacherLandingPage.jsp";
	
	// method to map the user to it's page
	public String mapTeacherToPage(int quizCreated, int questionsCreated) {
		if (questionsCreated == 1) {
			return teacherPage;
		}
		else {
			return exceptionPage;
		}
	}
}
