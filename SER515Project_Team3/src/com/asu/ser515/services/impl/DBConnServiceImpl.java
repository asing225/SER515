package com.asu.ser515.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.asu.ser515.services.DBConnService;

/**
 * Implementation to handle DB connectivity 
 * 
 * @author anurag mishra
 * date 09/28/2019 
 * 
 * */

public class DBConnServiceImpl implements DBConnService{

	private static String username = "43k4cWX5sz"; 
	private static String password = "Qk9tuwqLPr";
	private static String connectionString ="jdbc:mysql://remotemysql.com:3306/43k4cWX5sz?useSSL=false"; 
	private static Connection connection; 
	private static Statement command; 
	private static ResultSet data;
	
	@Override
	public void dbPing() {
		try {
			connection = DriverManager.getConnection(connectionString,username,password);
			command = connection.createStatement();
			data =command.executeQuery("SELECT * from userdetails");
		}  catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if (data.first()) {
					while (data.next()) {
						System.out.println("name = " + data.getString("firstname"));
					} 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	   
		
	}

}
