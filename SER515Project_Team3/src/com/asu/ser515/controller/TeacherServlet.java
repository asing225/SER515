package com.asu.ser515.controller;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asu.ser515.model.QuestionAnswer;
import com.asu.ser515.services.impl.DBConnServiceImpl;

/**
 * Controller class to handle the HTTP calls from teacher quiz creation and
 * store quiz into database. The teacher will be redirected to next page from
 * this controller.
 * 
 * @author amanjotsingh
 * @date 10/06/2019
 * 
 * @author kushagrjolly
 * @date 10/21/2019
 * 
 */

@SuppressWarnings("serial")
public class TeacherServlet extends HttpServlet{

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	// doPost method to handle form submit coming from web page
		public void doPost(HttpServletRequest req, HttpServletResponse res) {		
			for( int i = 1; i <= 10; i++) {
				if(req.getParameter("Question"+i)!=null)  {
					if(req.getParameter("Solution"+i)!=null) {
						System.out.println("Total Question present:"+i);
						String question = req.getParameter("Question"+i);
						String solution = req.getParameter("Solution"+i);
						QuestionAnswer questionaire = new QuestionAnswer(question, solution);
						DBConnServiceImpl serviceImpl = new DBConnServiceImpl();
						int dbResult = serviceImpl.questionairecreation(questionaire);
						System.out.println(dbResult);
					}
				}
			}
			
		}
	
	

}
