package com.asu.ser515.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ser515.model.User;
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
 */

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public static String userName;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	// doPost method to handle form submit coming from web page
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String password = req.getParameter("password");
		userName = req.getParameter("username");
		User oldUser = new User(userName, password);
		DBConnServiceImpl serviceImpl = new DBConnServiceImpl();
		int dbResult = serviceImpl.authenticateUser(oldUser);
		LoginServletHelper loginServletHelper = new LoginServletHelper();
		String userPage = loginServletHelper.mapUserToPage(dbResult);
		try {
			req.getRequestDispatcher(userPage).forward(req, res);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (ServletException servletExc) {
			servletExc.printStackTrace();
		}
	}
}
	
