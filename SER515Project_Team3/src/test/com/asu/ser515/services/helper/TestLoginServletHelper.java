package test.com.asu.ser515.services.helper;

import static org.junit.Assert.assertEquals;
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
	void testMapTeacherToPage() {
		dbResultTestValue = 2;
		String teacherPage = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("teacherHomePage.html", teacherPage);
	}
	
	@Test
	void testMapAdminPage() {
		dbResultTestValue = 1;
		String adminPage = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("admin.html", adminPage);
	}

	@Test
	void testMapStudentGrade1Page() {
		dbResultTestValue = 3;
		String student1Page = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("student1.html", student1Page);
	}
	
	@Test
	void testMapStudentGrade6Page() {
		dbResultTestValue = 4;
		String student2Page = loginServletHelper.mapUserToPage(dbResultTestValue);
		assertEquals("student2.html", student2Page);
	}
}
