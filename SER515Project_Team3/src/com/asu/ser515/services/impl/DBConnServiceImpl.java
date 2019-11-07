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
	
	//private User __userold = new User();
	private static String __jdbcUrl;
	private static String __jdbcUser;
	private static String __jdbcPasswd;
	private static String __jdbcDriver;
	private static String __getUser;
	private static String __insertQuiz;
	private static String __insertQuestion;

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
			__insertQuestion=dbProperties.getProperty("insertQuestion");

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}

	// Authenticate User in the database
	@Override
	public User authenticateUser(String username, String password) {
		User user = new User();
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
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user.setU_ID(rs.getInt(1));
				user.setUserType(rs.getInt(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
			}
		} catch (SQLException sqe) {
			sqe.printStackTrace();
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
		return user;
	}

	@Override
	public int quizCreation(int U_ID, String quizname, String instructions) {	
		Connection conn = null;
		PreparedStatement ps = null;
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
			System.out.println(ps);
			int rs= ps.executeUpdate();
			if(rs == 1) {
				return 1;
			}
			else {
				return 0;
			}

			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return -1;
		}finally {
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
	public int questionaireCreation(int U_ID, QuestionAnswer questionaire) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__insertQuestion);
			ps.setString(1, questionaire.getQuestion());
			ps.setString(2, questionaire.getAnswer());
			ps.setInt(3, U_ID);
			int rs= ps.executeUpdate();
			if(rs == 1 ) {
				return 1;
			}
			else {
				return 0;
			}			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return -1;
		}finally {
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



}
