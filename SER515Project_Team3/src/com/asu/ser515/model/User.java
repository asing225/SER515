package com.asu.ser515.model;

/**
 * Model class for generic user with all the properties associated with the user
 * 
 * @author amanjotsingh
 * @date - 09/27/2019
 * 
 */

public class User {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int userType;
	private int U_ID;

	// default constructor
	public User() {
	}

	// class constructor
	public User(String firstName, String lastName, String userName, String password, int userType, int U_ID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.U_ID = U_ID;
	}

	// class constructor
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getU_ID() {
		return U_ID;
	}

	public void setU_ID(int U_ID) {
		this.U_ID = U_ID;
	}
}
