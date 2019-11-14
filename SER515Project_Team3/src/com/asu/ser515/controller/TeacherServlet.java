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

import com.asu.ser515.model.Question;
import com.asu.ser515.model.Quiz;
import com.asu.ser515.model.Teacher;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;
import com.asu.ser515.services.helper.TeacherServletHelper;
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
 * @author anurag933103
 * @date 11/10/2019
 * 
 */

@SuppressWarnings("serial")
public class TeacherServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	// doPost method to handle form submit coming from web page
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String teacherRouter = null;
		List<Question> listofquestions= new ArrayList<Question>();
		String quizname = req.getParameter("quizname");
		String instructions = req.getParameter("instructions");
		HttpSession session = req.getSession(false);
		User teacher = new Teacher();
		teacher.setFirstName((String) session.getAttribute("firstname"));
		teacher.setLastName((String) session.getAttribute("lastname"));
		teacher.setUserName((String) session.getAttribute("username"));
		teacher.setUser_Id((int) session.getAttribute("u_id"));
		teacher.setUserType((int) session.getAttribute("usertype"));
		DBConnService serviceImpl = new DBConnServiceImpl();
		int quizCreated = serviceImpl.quizCreation(teacher.getUser_Id(), quizname, instructions);
		List<String> quizNames = (List<String>) session.getAttribute("quizNames");
		List<String> quizIDs = (List<String>) session.getAttribute("quizIds");
		quizIDs.add(Integer.toString(quizCreated));
		quizNames.add(quizname);
		session.setAttribute("quizNames", quizNames);
		session.setAttribute("quizIds", quizIDs);
		// This loop needs to be changed so there is only 1 DB connection
		for (int i = 1; i <= 10; i++) {
			if (req.getParameter("Question" + i) != null) {
				if (req.getParameter("Solution" + i) != null) {
					String question = req.getParameter("Question" + i);
					String solution = req.getParameter("Solution" + i);
					Question questionaire = new Question(question, solution);
					listofquestions.add(questionaire);
				}
			}
		}
		Quiz quiz= new Quiz();
		quiz.setQuestions(listofquestions);
		if(quizCreated!=-1) {
			int questionsCreated = serviceImpl.questionaireCreation(teacher.getUser_Id(), quiz);
			TeacherServletHelper teacherHelper = new TeacherServletHelper();
			teacherRouter = teacherHelper.mapTeacherToPage(quizCreated, questionsCreated);	
		}
		try {
			req.getRequestDispatcher(teacherRouter).forward(req, res);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (ServletException servletExc) {
			servletExc.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		int quizId = Integer.parseInt(req.getParameter("id"));
		DBConnService serviceImpl = new DBConnServiceImpl();
		List<String>[] questionEntry = serviceImpl.quizQuestionJsonExtraction(quizId);
		List<String> question = questionEntry[0];
		List<String> solution = questionEntry[1];
		Question questionSet = new Question();
		questionSet.setQuestion(question.get(0));
		req.setAttribute("Questions", question);
		req.setAttribute("Answers", solution);
		try {
			req.getRequestDispatcher("/quizPageTeacher.jsp").forward(req, res);
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (ServletException servletExc) {
			servletExc.printStackTrace();
		}
	}
}
