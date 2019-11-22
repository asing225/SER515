package com.asu.ser515.model;

/**
 * Model class for the Question&Answer. This class has all the properties associated
 * with the quiz creation.
 * 
 * @author kushagrjolly
 * @date 10/29/2019
 * 
 */

public class Question {
	private String question;
	private String answer;

	// default constructor
	public Question() {
	}
	
	// class constructors
		public Question(String question, String answer) {
			this.question = question;
			this.answer = answer;
		}

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
