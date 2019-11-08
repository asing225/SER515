package com.asu.ser515.services.impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.asu.ser515.model.QuestionAnswer;
import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.JsonArray;

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
	private static String __selectQuiz;
	private static String __selectQuestionTable;

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
			__selectQuiz=dbProperties.getProperty("selectQuiz");
			__selectQuestionTable=dbProperties.getProperty("selectQuestion");

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
	
		public List<String>[] teacherQuizJsonExtraction() {
			Connection conn = null;
			Statement stmt = null;
			ArrayList<String> quizIds = new ArrayList<String>();
			ArrayList<String> quizNames = new ArrayList<String>();
			ArrayList<String> instructions = new ArrayList<String>();
			try {
				try {
					Class.forName(__jdbcDriver);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			
				conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(__selectQuiz);
				//int counter = 0;
				while(rs.next()) {
					quizIds.add(String.valueOf(rs.getInt("Quiz_id")));
					quizNames.add(rs.getString("quizName"));
					instructions.add( rs.getString("instructions"));
					//counter +=1;
				}
				@SuppressWarnings("unchecked")
				List<String>[] res = new ArrayList[3];
				res[0] = quizIds;
				res[1] = quizNames;
				res[2] = instructions;
				return res;
			}
			catch (SQLException sqe) {
				sqe.printStackTrace();
				return null;
			}finally {
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
		
		public String quizQuestionJsonExtraction(int quizId) {
			JsonObject questionObject = new JsonObject();
			JsonArray questionArray = new JsonArray();
			Connection conn = null;
			PreparedStatement ps = null;
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
				int questionCounter = 0;
				while(rs.next()) {
					questionCounter += 1;
					JsonObject questionRecord = new JsonObject();
					questionRecord.put("questionId", questionCounter);
					questionRecord.put("question", rs.getString("question"));
					questionRecord.put("solution", rs.getString("solution"));
					questionArray.add(questionRecord);
				}
				questionObject.put("quizData", questionArray);
				//questionCounter = 0;
				
				
			    
				String quiz = null;
				return quiz;
			}
			catch (SQLException sqe) {
				sqe.printStackTrace();
				return null;
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
