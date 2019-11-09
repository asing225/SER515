package com.asu.ser515.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	// doPost method to handle form submit coming from web page
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		String password = req.getParameter("password");
		String userName = req.getParameter("username");
		DBConnServiceImpl serviceImpl = new DBConnServiceImpl();
		User user = serviceImpl.authenticateUser(userName, password);
		
		LoginServletHelper loginServletHelper = new LoginServletHelper();
		String userPage = loginServletHelper.mapUserToPage(user.getUserType());
		try {
			HttpSession session = req.getSession(true);
			session.setAttribute("firstname", user.getFirstName());
			session.setAttribute("lastname", user.getLastName());
			session.setAttribute("usertype", user.getUserType());
			session.setAttribute("u_id", user.getUser_Id());
			session.setAttribute("username", user.getUserName());
			if(user.getUserType()==3 || user.getUserType()==4) {
				session.setAttribute("ListQuiz", serviceImpl.getQuiz());
				System.out.println(serviceImpl.getQuiz());
			}
//			req.getRequestDispatcher(userPage).forward(req, res);
			getServletContext().getRequestDispatcher(userPage).forward(req,res);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (ServletException servletExc) {
			servletExc.printStackTrace();
		}
	}
}
	
