package com.asu.ser515.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asu.ser515.model.Admin;
import com.asu.ser515.model.Quiz;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;
import com.asu.ser515.services.impl.DBConnServiceImpl;

/**
 * Controller class to handle the admin functionality of the application. It
 * will authenticate the user and redirect to their respective web pages.

 * @author anurag933103
 * @date 11/16/2019
 */

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
      
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		int userId = Integer.parseInt(req.getParameter("id"));
		DBConnService serviceImpl = new DBConnServiceImpl();
		HttpSession session = req.getSession(false);
		User admin = new Admin();
		admin.setFirstName((String) session.getAttribute("firstname"));
		admin.setLastName((String) session.getAttribute("lastname"));
		admin.setUserName((String) session.getAttribute("username"));
		admin.setUser_Id((int) session.getAttribute("u_id"));
		admin.setUserType((int) session.getAttribute("usertype"));
	}
}
