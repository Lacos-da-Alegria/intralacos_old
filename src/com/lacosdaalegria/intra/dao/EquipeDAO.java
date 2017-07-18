package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lacosdaalegria.intra.model.Voluntario;

public class EquipeDAO {
	
	private Connection connection;

	//Toda instacia DAO obrigatoriamente estabelece um conexao com o BD atraves da ConnectionFactory.
	public EquipeDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public int[] serApoio(int userid) {
		
		int[] ativ = new int[2];
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement("select ativid_id, status from tb_equipe where status in (1,2) and user_id = ?");
			
			stmt.setInt(1, userid);

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				ativ[0] = rs.getInt(1);
				ativ[1] = rs.getInt(2);
				
			}
			
			rs.close();
			stmt.close();
			
			return ativ;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//O metodo a baixo adiciona um apoio no banco de dados.
		public void adicionaApoio(int userid, int ativid){
			
				String sql = "insert into tb_equipe (user_id, ativid_id, cargo) values (?,?,1)";
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, userid);
				stmt.setInt(2, ativid);

			
				stmt.execute();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		public List<Voluntario> listaApoios (int ativid){	
			
			List<Voluntario> listaapoios = new ArrayList<Voluntario>();
			
			try {
				PreparedStatement stmt = this.connection.prepareStatement("select user_id, nome, whatsapp, regiao from vw_apoios where ativid_id = ? and status in (1,2)");		
				
				stmt.setInt(1, ativid);
				
				ResultSet rs = stmt.executeQuery();			
				
				while(rs.next()){
					
					Voluntario apoio = new Voluntario();
					
					apoio.setUserid(rs.getInt(1));				
					apoio.setNome(rs.getString(2));
					apoio.setWhatsapp(rs.getString(3));
					apoio.setRegiao(rs.getString(4));
					
					listaapoios.add(apoio);
					
				}
				 
				rs.close();
				stmt.close();
				
				
			} catch (SQLException e) {
				throw new RuntimeException(e); 
			}
			
			return listaapoios;

		}
		
		public void desativarApoio(int userid){
			  
			  String query = "update tb_equipe set status = 2 where user_id = ?"; 
				
				PreparedStatement stmt;
				
				try {
					stmt = connection.prepareStatement(query);
					
					stmt.setInt(1, userid);
					
					stmt.execute();		
					
					stmt.close();
					
					this.connection.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
		  }
		
		public void ativarApoio(int userid){
			  
			  String query = "update tb_equipe set status = 1 where user_id = ?"; 
				
				PreparedStatement stmt;
				
				try {
					stmt = connection.prepareStatement(query);
					
					stmt.setInt(1, userid);
					
					stmt.execute();		
					
					stmt.close();
					
					this.connection.close();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  
		  }
		

}
