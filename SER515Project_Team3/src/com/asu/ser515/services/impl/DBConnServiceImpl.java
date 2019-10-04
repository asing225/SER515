package com.asu.ser515.services.impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.asu.ser515.model.User;
import com.asu.ser515.services.DBConnService;
import com.asu.ser515.services.helper.DBConnServiceHelper;

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
 * */

public class DBConnServiceImpl implements DBConnService{
	
	private static String __jdbcUrl;
	private static String __jdbcUser;
	private static String __jdbcPasswd;
	private static String __jdbcDriver;
	private static String __getUser;
	
	// static block to be executed when class loads to read DB configs from properties file.
	static {
		try {
			Properties dbProperties = new Properties();
			dbProperties.load(DBConnServiceImpl.class.getClassLoader()
					.getResourceAsStream("rdbm.properties"));
			__jdbcUrl = dbProperties.getProperty("jdbcUrl");
			__jdbcUser = dbProperties.getProperty("jdbcUser");
			__jdbcPasswd = dbProperties.getProperty("jdbcPasswd");
			__jdbcDriver = dbProperties.getProperty("jdbcDriver");
			__getUser = dbProperties.getProperty("getUser");
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}

	//Authenticate User in the database
	@Override
	public int authenticateUser(User oldUser) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			ps = conn.prepareStatement(__getUser);
			ps.setString(1, oldUser.getUserName());
			ps.setString(2, oldUser.getPassword());
			//DBConnServiceHelper dbHelper = new DBConnServiceHelper();
			//ps.setInt(3, dbHelper.mapUserTypeToDB(oldUser.getUserType()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
//				oldUser.setFirstName(rs.getString(1));
//			    oldUser.setLastName(rs.getString(2));
				int res = rs.getInt(1);
				System.out.println("the result is" + res);
			    return  res;
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
			}
			finally {
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
