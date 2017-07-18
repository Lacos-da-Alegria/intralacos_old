package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDAO {

	private Connection connection;
	
	public FeedbackDAO() {
		
		this.connection = new ConnectionFactory().getConnection();
		
	}
	
	public void adicionaFeedback(String feedback){
		
		String query = "insert into tb_feedback (descricao) values (?)"; 
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setString(1, feedback);
			
			stmt.execute();
			
			stmt.close();
			this.connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
