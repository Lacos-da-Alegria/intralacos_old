package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {

		try {
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";                        
			 
			Class.forName(driverName);
			
            return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=lacosDB", "lacos", "desenvol");
	  
		} catch (SQLException | ClassNotFoundException e) {
	         throw new RuntimeException(e);
	     }
	}


	
}
