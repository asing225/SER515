package com.asu.ser515.model;

/**
 * Model class for the Question&Answer. This class has all the properties associated
 * with the quiz creation.
 * 
 * @author kushagrjolly
 * @date 10/21/2019
 * 
 */

public class QuestionAnswer {
	
	private String question;
	private String answer;

	// default constructor
	public QuestionAnswer() {
	}

	// class constructors
	public QuestionAnswer(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	// getters and setters
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}


}
