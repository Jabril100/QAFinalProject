package com.cohort.db;

	
	import java.sql.Connection;
	import java.sql.DriverManager;

	public class DBUtils {

		public static Connection getConnection() throws Exception {

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db1", "root", "password");

			return conn;
		}	

}
