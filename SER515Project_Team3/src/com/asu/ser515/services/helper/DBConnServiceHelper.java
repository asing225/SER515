package com.asu.ser515.services.helper;

/**
 * Helper Class to handle the any conversions or logic before storing  
 * data into the database
 * 
 * @author amanjotsingh
 * date 09/28/2019
 * 
 * */

public class DBConnServiceHelper {
	
	public int mapUserTypeToDB(String usertype) {
		if(usertype.equalsIgnoreCase("admin")) {
			return 1;
		}
		else if(usertype.equalsIgnoreCase("teacher")) {
			return 2;
		}
		else if(usertype.equalsIgnoreCase("studentGrade_1")) {
			return 3;
		}
		else if(usertype.equalsIgnoreCase("studentGrade_6")) {
			return 4;
		}
		return 0;
	}
}
