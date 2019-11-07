package com.asu.ser515.model;

import java.util.List;

public class Quiz {

	private List<String> questions;
	private List<String> answers;
	
	public List<String> getQuestions() {
		return questions;
	}
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
