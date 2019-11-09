package com.asu.ser515.services;

import java.util.ArrayList;

import com.asu.ser515.model.QuestionAnswer;
import com.asu.ser515.model.Quiz;
import com.asu.ser515.model.User;

/**
 * Interface to handle DB connectivity
 * 
 * @author anurag mishra
 * @date 09/28/2019
 * 
 * @author kushagrjolly
 * @date 09/29/2019
 * 
 */

public interface DBConnService {
	public User authenticateUser(String username, String password);
	public int quizCreation(int U_ID, String quizname, String instructions);
	public int questionaireCreation(int U_ID, QuestionAnswer questionaire);
	public ArrayList<Quiz> getQuiz();
	public Quiz getQuestion(String quiz_id);

}
