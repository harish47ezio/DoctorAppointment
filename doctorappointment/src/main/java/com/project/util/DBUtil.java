package com.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL="jdbc:mysql://localhost:3306/docapp";
	private static final String USER_NAME="root";
	private static final String PASSWORD="Raoji@2001";

	private static Connection connection;	
	
	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USER_NAME,PASSWORD);
			//System.out.println("Connection Success");
		}catch(SQLException e) {
			System.err.println("Error:Connection Failed");
		}
		return connection;
	}
}
