package com.asu.ser515.controller;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	private String teacherPage = "teacherHomePage.html";
	private String errorPage = "error.html";
	private String exceptionPage = "exception.html";
	private int U_ID;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	// doPost method to handle form submit coming from web page
		public void doPost(HttpServletRequest req, HttpServletResponse res) {
			String quizname=req.getParameter("quizname");
			String instructions=req.getParameter("instructions");
			DBConnServiceImpl serviceImpl = new DBConnServiceImpl();
			System.out.println("Teacher Servelet"+U_ID);
			int dbResult = serviceImpl.quizCreation(U_ID, quizname, instructions);
			for( int i = 1; i <= 10; i++) {
				if(req.getParameter("Question"+i)!=null)  {
					if(req.getParameter("Solution"+i)!=null) {
						String question = req.getParameter("Question"+i);
						String solution = req.getParameter("Solution"+i);
						QuestionAnswer questionaire = new QuestionAnswer(question, solution);
						int dbResult1 = serviceImpl.questionaireCreation(U_ID, questionaire);
						if (dbResult == 1 && dbResult1 == 1) {
							try {
								req.getRequestDispatcher(teacherPage).forward(req, res);
							} catch (IOException ioExc) {
								ioExc.printStackTrace();
							} catch (ServletException servletExc) {
								servletExc.printStackTrace();
							}
						}
						else if(dbResult == 0) {
							// Error in database
							try {
								req.getRequestDispatcher(errorPage).forward(req, res);
							} catch (IOException ioExc) {
								ioExc.printStackTrace();
							} catch (ServletException servletExc) {
								servletExc.printStackTrace();
							}
						}
						else {
							// Exception in database
							try {
								req.getRequestDispatcher(exceptionPage).forward(req, res);
							} catch (IOException ioExc) {
								ioExc.printStackTrace();
							} catch (ServletException servletExc) {
								servletExc.printStackTrace();
							}
						}
					}
				}
			}
		}
		// doGet method to handle form opening in web page
		public void doGet(HttpServletRequest req, HttpServletResponse res) {
			HttpSession session=req.getSession(true);
		    U_ID=(int)session.getAttribute("u_id");
		    
		}
}
