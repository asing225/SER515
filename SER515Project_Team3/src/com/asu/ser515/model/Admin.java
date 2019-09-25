package com.asu.ser515.model;

/**
 * @author amanjotsingh
 * @date 09/25/2019
 * 
 * Model class for the Administrator. This class has all the properties associated with the 
 * admin. 
 * */

public class Admin {
	
	private String firstName;
	private String lastName;
	
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
