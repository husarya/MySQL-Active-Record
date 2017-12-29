package org.majesty.mar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbClient {

	private static String DB_URL = "jdbc:mysql://localhost:3306/Workshop?useSSL=false";
	private static String DB_USER = "husarya";
	private static String DB_PASS = "kepa8901";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {

		}
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

}

