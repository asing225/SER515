package com.asu.ser515.services.impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.asu.ser515.model.QuestionAnswer;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;

/**
 * Implementation to handle DB connectivity
 * 
 * @author anurag mishra
 * @date 09/28/2019
 * 
 * Edit @author amanjotsingh
 * @date 09/28/2019
 * 
 * Edit @author kushagrjolly
 * @date 09/29/2019
 * 
 */

public class DBConnServiceImpl implements DBConnService {
	
	public static int U_ID;
	private static String __jdbcUrl;
	private static String __jdbcUser;
	private static String __jdbcPasswd;
	private static String __jdbcDriver;
	private static String __getUser;
	private static String __insertQuiz;
	private static String __insertQuestion;
	private static String __getQ_ID;	


	// static block to be executed when class loads to read DB configs from
	// properties file.
	static {
		try {
			Properties dbProperties = new Properties();
			dbProperties.load(DBConnServiceImpl.class.getClassLoader().getResourceAsStream("rdbm.properties"));
			__jdbcUrl = dbProperties.getProperty("jdbcUrl");
			__jdbcUser = dbProperties.getProperty("jdbcUser");
			__jdbcPasswd = dbProperties.getProperty("jdbcPasswd");
			__jdbcDriver = dbProperties.getProperty("jdbcDriver");
			__getUser = dbProperties.getProperty("getUser");
			__insertQuiz=dbProperties.getProperty("insertQuiz");
			__getQ_ID=dbProperties.getProperty("getQ_ID");
			__insertQuestion=dbProperties.getProperty("insertQuestion");

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}

	// Authenticate User in the database
	@Override
	public int authenticateUser(User oldUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__getUser);
			ps.setString(1, oldUser.getUserName());
			ps.setString(2, oldUser.getPassword());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int res = rs.getInt(2);
				U_ID=rs.getInt(1);
				return res;
			}
			return 0;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return -1;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}
	}

	@Override
	public int questionairecreation(QuestionAnswer questionaire, String quizname, String instructions) {
		// TODO Auto-generated method stub
		int Q_ID = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__insertQuiz);
			ps.setInt(1, U_ID);
			ps.setString(2, quizname);
			ps.setString(3, instructions);
			int rs= ps.executeUpdate();
			ps1 = conn.prepareStatement(__getQ_ID);
			ps1.setInt(1, U_ID);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				Q_ID=rs1.getInt(1);
			}			ps2 = conn.prepareStatement(__insertQuestion);
			ps2.setInt(1, Q_ID);
			ps2.setString(2, questionaire.getQuestion());
			ps2.setString(3, questionaire.getAnswer());
			int rs2= ps2.executeUpdate();
			if(rs == 1 && Q_ID != 0 && rs2 == 1) {
				return 1;
			}
			else {
				return 0;
			}
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			System.out.println(sqe.getMessage());
			System.out.println(sqe.getStackTrace());
			return -1;
		}
		
		//return 0;
	}


}
