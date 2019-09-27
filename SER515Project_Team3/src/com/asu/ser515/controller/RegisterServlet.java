package com.asu.ser515.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author amanjotsingh
 * date 09/26/2019
 * 
 * Controller class to handle the new user registration functionality of the application. 
 * It will send a request to administrator to accept or reject new user
 * */

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException { 
    	super.init(config);
   	}
	
}
