package com.asu.ser515.model;

/**
 * Model class for the Teacher. This class has all the properties associated
 * with the teacher.
 * 
 * @author amanjotsingh
 * @date 09/25/2019
 * 
 */

public class Teacher extends User{

	private String firstName;
	private String lastName;

	// default constructor
	public Teacher() {
	}

	// class constructors
	public Teacher(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
