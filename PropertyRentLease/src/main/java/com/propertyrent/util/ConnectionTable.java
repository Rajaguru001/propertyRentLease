package com.propertyrent.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTable {
	private ConnectionTable() {
		
	}
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/propertyrentlease", "root","Guru@7733");
		
		
	}

}
