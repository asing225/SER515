package com.asu.ser515.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asu.ser515.services.DBConnService;
import com.asu.ser515.services.impl.DBConnServiceImpl;
/**
 * @author anurag933103
 * 19/11/2019
 */
@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse res) {
		String user_id = req.getParameter("status");
		DBConnService dbConn = new DBConnServiceImpl();
		int dbRes = 0;
		dbRes = dbConn.updateUserStatus(user_id);
		HttpSession session = req.getSession(false);
		List<String> status = (ArrayList<String>) session.getAttribute("status");
		List<String> userId = (ArrayList<String>) session.getAttribute("userId");
		int index = 0;
		for(int i=0; i < userId.size(); i++) {
			if(user_id.equalsIgnoreCase(userId.get(i))) {
				index = i;
			}
		}
		if("Y".equals(status.get(index))) {
			status.set(index, "N");
		}
		else {
			status.set(index, "Y");
		}
		try {
			getServletContext().getRequestDispatcher("/admin.jsp").forward(req, res);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (ServletException servletExc) {
			servletExc.printStackTrace();
		}
	}
}