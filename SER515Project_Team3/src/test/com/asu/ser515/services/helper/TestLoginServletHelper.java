package test.com.asu.ser515.services.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.asu.ser515.services.helper.LoginServletHelper;

/**
 * @author amanjotsingh
 * @date 10/24/2019
 * 
 * JUnit test class to test LoginServletHelper class.
 * */

class TestLoginServletHelper {

	private LoginServletHelper loginServletHelper = new LoginServletHelper();
	private int dbResultTestValue = 0;
	
	@Test
	void testMapTeacherToPage_success() {
		dbResultTestValue = 2;
		String teacherPage = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("teacherHomePage.html", teacherPage);
	}
	
	@Test
	void testMapAdminPage_success() {
		dbResultTestValue = 1;
		String adminPage = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("admin.html", adminPage);
	}

	@Test
	void testMapStudentGrade1Page_success() {
		dbResultTestValue = 3;
		String student1Page = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("student1.html", student1Page);
	}
	
	@Test
	void testMapStudentGrade6Page_success() {
		dbResultTestValue = 4;
		String student2Page = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("student2.html", student2Page);
	}
}
