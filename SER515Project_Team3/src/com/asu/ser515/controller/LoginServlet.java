package com.asu.ser515.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;
import com.asu.ser515.services.helper.LoginServletHelper;
import com.asu.ser515.services.impl.DBConnServiceImpl;

/**
 * Controller class to handle the login functionality of the application. It
 * will authenticate the user and redirect to their respective web pages.
 * 
 * @author amanjotsingh
 * @date 09/25/2019
 * 
 * @author kushagrjolly
 * @date 09/29/2019
 * 
 * @author anurag933103
 * @date 11/07/2019
 */

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	// doPost method to handle form submit coming from web page
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(true);
		String password = req.getParameter("password");
		String userName = req.getParameter("username");
		DBConnService serviceImpl = new DBConnServiceImpl();
		User user = serviceImpl.authenticateUser(userName, password);
		LoginServletHelper loginServletHelper = new LoginServletHelper();
		String userPage = loginServletHelper.mapUserToPage(user.getUserType());
		List<String>[] data = serviceImpl.teacherQuizJsonExtraction();
		session.setAttribute("quizNames", data[0]);
		session.setAttribute("quizIds", data[1]);
		try {
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("lastname", user.getLastName());
			session.setAttribute("usertype", user.getUserType());
			session.setAttribute("u_id", user.getUser_Id());
			session.setAttribute("username", user.getUserName());
			if (user.getUserType() == 3 || user.getUserType() == 4) {
				session.setAttribute("ListQuiz", serviceImpl.getQuiz(user.getUserType()));
			}
			getServletContext().getRequestDispatcher(userPage).forward(req,res);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (ServletException servletExc) {
			servletExc.printStackTrace();
		}
	}
}
