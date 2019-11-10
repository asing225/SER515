package com.asu.ser515.services.impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.asu.ser515.model.QuestionAnswer;
import com.asu.ser515.model.Quiz;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;

/**
 * Implementation to handle DB connectivity
 * 
 * @author anurag mishra
 * @date 09/28/2019
 * 
 *       Edit @author amanjotsingh
 * @date 09/28/2019
 * 
 *       Edit @author kushagrjolly
 * @date 09/29/2019
 * 
 */

public class DBConnServiceImpl implements DBConnService {

	// private User __userold = new User();
	private static String __jdbcUrl;
	private static String __jdbcUser;
	private static String __jdbcPasswd;
	private static String __jdbcDriver;
	private static String __getUser;
	private static String __insertQuiz;
	private static String __insertQuestion;
	private static String __getQuiz;
	private static String __getQuestions;

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
			__insertQuiz = dbProperties.getProperty("insertQuiz");
			__insertQuestion = dbProperties.getProperty("insertQuestion");
			__getQuiz = dbProperties.getProperty("getQuiz");
			__getQuestions = dbProperties.getProperty("getQuestions");

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
				user.setUser_Id(rs.getInt(1));
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
			int rs = ps.executeUpdate();
			if (rs == 1) {
				return 1;
			} else {
				return 0;
			}

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
	public int questionaireCreation(int user_Id, QuestionAnswer questionaire) {
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
			ps.setInt(3, user_Id);
			int rs = ps.executeUpdate();
			if (rs == 1) {
				return 1;
			} else {
				return 0;
			}
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
	public ArrayList<Quiz> getQuiz() {
		// TODO Auto-generated method stub
		ArrayList<Quiz> listquiz = new ArrayList<Quiz>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__getQuiz);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Quiz quiz = new Quiz();
				quiz.setQuiz_id(rs.getInt("quiz_id"));
				quiz.setQuizname(rs.getString("quizname"));
				quiz.setInstructions(rs.getString("instructions"));
				listquiz.add(quiz);
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

		return listquiz;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.asu.ser515.services.DBConnService#getQuestion(java.lang.String)
	 */
	@Override
	public Quiz getQuestion(int quiz_id) {
		// TODO Auto-generated method stub
		ArrayList<String> listquestion = new ArrayList<String>();
		ArrayList<String> listanswer = new ArrayList<String>();
		Quiz quiz = new Quiz();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__getQuestions);
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				listanswer.add(rs.getString("solution"));
				listquestion.add(rs.getString("question"));
			}
			quiz.setAnswers(listanswer);
			quiz.setQuestions(listquestion);
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

		return quiz;
	}

}
