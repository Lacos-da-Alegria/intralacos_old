package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


	public Connection getConnection() {

		try {
			String driverName = "net.sourceforge.jtds.jdbc.Driver";                        
			 
			Class.forName(driverName);
			return DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433;instanceName=SQLEXPRESS;DatabaseName=dev;user=lacosadmin;password=desenvol");
            
            
		} catch (SQLException | ClassNotFoundException e) {
	         throw new RuntimeException(e);
	     }
	}


	
}
