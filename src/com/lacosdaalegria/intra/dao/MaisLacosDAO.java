package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lacosdaalegria.intra.model.MaisLacos;

public class MaisLacosDAO {

	
	private Connection connection;	
	
	public MaisLacosDAO() {
		
		this.connection = new ConnectionFactory().getConnection();
		
	}
	
	
	public MaisLacos getMaisLacos(int user_id){
		
	String query = "select * from vw_maislacos where user_id = ?"; 
		
		MaisLacos maisLacos = new MaisLacos();
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, user_id);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){
				
				maisLacos.setAtividades_distintas(rs.getInt(4));
				maisLacos.setAtividade_participadas(rs.getInt(5));
				maisLacos.setIndicacoes(rs.getInt(6));
				maisLacos.setTotal_voluntarios(rs.getInt(7));	
				maisLacos.setTotal_novatos(rs.getInt(8));
				
			}
			
			stmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return maisLacos;
	}
	
}
