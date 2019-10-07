package com.asu.ser515.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ser515.model.User;
import com.asu.ser515.services.helper.DBConnServiceHelper;
import com.asu.ser515.services.impl.DBConnServiceImpl;

/**
 * Controller class to handle the login functionality of the application. It will authenticate the
 * user and redirect to their respective web pages. 
 * 
 * @author amanjotsingh
 * @date 09/25/2019
 * 
 * @author kushagrjolly
 * @date 09/29/2019
 * 
 * */

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private String errorPage = "error.html";
	private String exceptionPage = "exception.html";
	private String adminPage = "admin.html";
	private String teacherPage = "teacher.html";
	private String studentGrade_1Page = "student1.html";
	private String studentGrade_6Page = "student2.html";
	
	public void init(ServletConfig config) throws ServletException { 
    	super.init(config);
   	}
	
	// doPost method to handle form submit coming from web page
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String password = req.getParameter("password");
        String userName = req.getParameter("username");
		User oldUser = new User(userName,password);
        DBConnServiceImpl serviceImpl = new DBConnServiceImpl();
        int dbResult = serviceImpl.authenticateUser(oldUser);
        if(dbResult == 0) {
        	//User not present
        	try {
				req.getRequestDispatcher(errorPage).forward(req, res);
			} catch (IOException ioExc) {
				ioExc.printStackTrace();
			} catch (ServletException servletExc) {
				servletExc.printStackTrace();
			}
        }
        if(dbResult == -1) {
        	//Exception in database
        	try {
				req.getRequestDispatcher(exceptionPage).forward(req, res);
			} catch (IOException ioExc) {
				ioExc.printStackTrace();
			} catch (ServletException servletExc) {
				servletExc.printStackTrace();
			}
        }
        else {
        	DBConnServiceHelper dbHelper = new DBConnServiceHelper();
    		String role=dbHelper.mapDBtoUsertype(dbResult);
    		if (role.equalsIgnoreCase("admin")){
    			try {
    				req.getRequestDispatcher(adminPage).forward(req, res);
    			} catch (IOException ioExc) {
    				ioExc.printStackTrace();
    			} catch (ServletException servletExc) {
    				servletExc.printStackTrace();
    			}
    		}
    		if (role.equalsIgnoreCase("teacher")){
    			try {
    				req.getRequestDispatcher(teacherPage).forward(req, res);
    			} catch (IOException ioExc) {
    				ioExc.printStackTrace();
    			} catch (ServletException servletExc) {
    				servletExc.printStackTrace();
    			}
    		}
    		if (role.equalsIgnoreCase("studentGrade_1")){
    			try {
    				req.getRequestDispatcher(studentGrade_1Page).forward(req, res);
    			} catch (IOException ioExc) {
    				ioExc.printStackTrace();
    			} catch (ServletException servletExc) {
    				servletExc.printStackTrace();
    			}
    		}
    		if (role.equalsIgnoreCase("studentGrade_6")){
    			try {
    				req.getRequestDispatcher(studentGrade_6Page).forward(req, res);
    			} catch (IOException ioExc) {
    				ioExc.printStackTrace();
    			} catch (ServletException servletExc) {
    				servletExc.printStackTrace();
    			}
    		}
        }        
	}
}
