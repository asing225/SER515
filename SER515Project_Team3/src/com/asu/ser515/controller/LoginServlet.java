package com.asu.ser515.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author amanjotsingh
 * date 09/25/2019
 * 
 * Controller class to handle the login functionality of the application. It will authenticate the
 * user and redirect to their respective web pages. 
 * */

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	
	private String adminPage = "admin.html";
	
	public void init(ServletConfig config) throws ServletException { 
    	super.init(config);
   	}
	
	// doPost method to handle form submit coming from web page
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String firstname = req.getParameter("firstName");
        try {
			req.getRequestDispatcher(adminPage).forward(req, res);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
