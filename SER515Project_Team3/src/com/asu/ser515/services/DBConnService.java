package com.asu.ser515.services;

import com.asu.ser515.model.User;

/**
 * Interface to handle DB connectivity 
 * 
 * @author anurag mishra
 * date 09/28/2019 
 * 
 * @author kushagrjolly
 * date 09/29/2019 
 * 
 * */

public interface DBConnService {
	//public void dbPing();
	public int registerUser(User newUser);
	public int authenticateUser(User oldUser);
}
