package com.asu.ser515.services;

import java.util.List;

import java.util.ArrayList;

import com.asu.ser515.model.Quiz;
import com.asu.ser515.model.User;

/**
 * Interface to handle DB connectivity
 * 
 * @author anurag933103
 * @date 09/28/2019
 * 
 * @author kushagrjolly
 * @date 09/29/2019
 * 
 * @author anurag933103
 * @date 11/17/2019
 */

public interface DBConnService {
	public User authenticateUser(String username, String password);
	public int quizCreation(int U_ID, String quizname, String instructions);
	public int questionaireCreation(int U_ID, Quiz quiz);
	public List<String>[] teacherQuizJsonExtraction();
	public List<String>[] quizQuestionJsonExtraction(int quizId);
	public ArrayList<Quiz> getQuiz(int usertype);
	public Quiz getQuestion(int quiz_id);
	public List<String>[] getUserList();
	public int updateUserStatus(String userId);
}
