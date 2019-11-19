package com.asu.ser515.services.impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.asu.ser515.model.Question;
import com.asu.ser515.model.Quiz;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;
import com.asu.ser515.services.helper.DBConnServiceHelper;

/**
 * Implementation to handle DB connectivity
 * 
 * @author anurag933103
 * @date 09/28/2019
 * 
 *       Edit @author amanjotsingh
 * @date 09/28/2019
 * 
 *       Edit @author kushagrjolly
 * @date 09/29/2019
 * 
 * @author anurag933103
 * @date 11/10/2019
 * 
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
	private static String __selectQuiz;
	private static String __selectQuestionTable;
	private static String __getQuizID;
	private static String __getQuiz;
	private static String __getQuestions;
	private static String __getUserList;

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
			__selectQuiz = dbProperties.getProperty("selectQuiz");
			__selectQuestionTable = dbProperties.getProperty("selectQuestion");
			__getQuizID = dbProperties.getProperty("getQuizID");
			__getQuiz = dbProperties.getProperty("getQuiz");
			__getQuestions = dbProperties.getProperty("getQuestions");
			__getUserList = dbProperties.getProperty("getUserList");

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
		PreparedStatement ps1 = null;

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
			int rs = ps.executeUpdate();
			if (rs == 1) {
				ps1 = conn.prepareStatement(__getQuizID);
				ResultSet rs1 = ps1.executeQuery();
				while (rs1.next()) {
					return rs1.getInt(1);
				}
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
	public int questionaireCreation(int user_Id, Quiz quiz) {
		Connection conn = null;

		PreparedStatement ps = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			int rs = 0;
			for (int i = 0; i < quiz.getQuestions().size(); i++) {
				ps = conn.prepareStatement(__insertQuestion);
				ps.setString(1, quiz.getQuestions().get(i).getQuestion());
				ps.setString(2, quiz.getQuestions().get(i).getAnswer());
				ps.setInt(3, user_Id);
				rs = ps.executeUpdate();
			}
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

	public List<String>[] teacherQuizJsonExtraction() {
		Connection conn = null;
		Statement stmt = null;
		ArrayList<String> quizIds = new ArrayList<String>();
		ArrayList<String> quizNames = new ArrayList<String>();
		ArrayList<String> instructions = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String>[] quizEntry = new ArrayList[3];
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}

			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(__selectQuiz);
			// int counter = 0;
			while (rs.next()) {
				quizIds.add(String.valueOf(rs.getInt("Quiz_id")));
				quizNames.add(rs.getString("quizName"));
				instructions.add(rs.getString("instructions"));
				// counter +=1;
			}
			quizEntry[0] = quizNames;
			quizEntry[1] = quizIds;
			quizEntry[2] = instructions;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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
		return quizEntry;
	}

	public List<String>[] quizQuestionJsonExtraction(int quizId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<String> question = new ArrayList<String>();
		ArrayList<String> solution = new ArrayList<String>();
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}

			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__selectQuestionTable);
			ps.setInt(1, quizId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				question.add(rs.getString("question"));
				solution.add(rs.getString("solution"));
			}

			@SuppressWarnings("unchecked")
			List<String>[] questionObject = new ArrayList[2];
			questionObject[0] = question;
			questionObject[1] = solution;
			// questionCounter = 0;

			return questionObject;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return null;
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
	public ArrayList<Quiz> getQuiz(int usertype) {
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
			ps.setInt(1,usertype);
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
		ArrayList<Question> listquestion = new ArrayList<Question>();
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
				Question question = new Question();
				question.setQuestion(rs.getString("question"));
				question.setAnswer(rs.getString("solution"));
				listquestion.add(question);
			}
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

	public List<String>[] getUserList() {
		Connection conn = null;
		Statement stmt = null;
		@SuppressWarnings("unchecked")
		List<String>[] userList = new ArrayList[5];
		ArrayList<String> firstName = new ArrayList<String>();
		ArrayList<String> lastName = new ArrayList<String>();
		ArrayList<String> userType = new ArrayList<String>();
		ArrayList<String> userName = new ArrayList<String>();
		ArrayList<String> password = new ArrayList<String>();
		DBConnServiceHelper dbHelper = new DBConnServiceHelper();
		try {
			try {
				Class.forName(__jdbcDriver);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(__getUserList);
			while (rs.next()) {
				firstName.add(rs.getString("firstName"));
				lastName.add(rs.getString("lastName"));
				userType.add(dbHelper.mapDBtoUsertype(rs.getInt("userType")));
				userName.add(rs.getString("userName"));
				password.add(rs.getString("password"));
			}
			userList[0] = firstName;
			userList[1] = lastName;
			userList[2] = userType;
			userList[3] = userName;
			userList[4] = password;
			return userList;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return null;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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
