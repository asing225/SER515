package com.asu.ser515.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ser515.model.User;
import com.asu.ser515.services.impl.DBConnServiceImpl;

/**
 * Controller class to handle the new user registration functionality of the application. 
 * It will send a request to administrator to accept or reject new user
 * 
 * @author amanjotsingh
 * date 09/26/2019
 * 
 * */

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	private String adminPage = "admin.html";
	
	public void init(ServletConfig config) throws ServletException { 
    	super.init(config);
   	}
	
	// doPost method to handle form submit coming from web page
		public void doPost(HttpServletRequest req, HttpServletResponse res) {
	        String firstName = req.getParameter("firstName");
	        String lastName = req.getParameter("lastName");
	        String userType = req.getParameter("userType");
	        String password = req.getParameter("password");
	        String userName = req.getParameter("userName");
	        System.out.println("test"+firstName + " " + lastName + " " + userType + " "+password + " " + userName);
	        User newUser = new User(firstName, lastName, userName, password, userType);
	        DBConnServiceImpl serviceImpl = new DBConnServiceImpl();
	        int dbResult = serviceImpl.registerUser(newUser);
	        if(dbResult == 1) {
	        	//User created
	        }
	        if(dbResult == -1) {
	        	//Exception in database
	        }
	        try {
				req.getRequestDispatcher(adminPage).forward(req, res);
			} catch (IOException ioExc) {
				ioExc.printStackTrace();
			} catch (ServletException servletExc) {
				servletExc.printStackTrace();
			}
		}
}
