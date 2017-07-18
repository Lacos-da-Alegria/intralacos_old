package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.HashMapAtividade;
import com.lacosdaalegria.intra.model.UserDetail;

public class RegistroAtividadeDAO extends DateHandler{
	
	private int semana = this.numeroSemana();
	
	private UserDetail userDetail = new UserDetail();
	
	private Connection connection;
	
	public RegistroAtividadeDAO() {
		
		this.connection = new ConnectionFactory().getConnection();
		
	}

  public void inscreverComPosicao(int atividade, HttpSession session){
	  
	  String query = "insert into tb_registro_ativ (semana, user_id, ativid_id, posicao) values (?, ?, ?, ?)"; 
	  
	  	
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);			
			stmt.setInt(1, this.semana);
			stmt.setInt(2, this.userDetail.user(session).getUser().userid);
			stmt.setInt(3, atividade);
			stmt.setInt(4, this.posicao());
			
			stmt.execute();		
			
			stmt.close();
			this.connection.close();
			
			atualizaAtividade(atividade, session, true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
  
  public void inscreverSemPosicao(int atividade, HttpSession session){
	  
	  String query = "insert into tb_registro_ativ (semana, user_id, ativid_id) values (?, ?, ?)"; 
	  
	  	
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);			
			stmt.setInt(1, this.semana);
			stmt.setInt(2, this.userDetail.user(session).getUser().userid);
			stmt.setInt(3, atividade);
			
			stmt.execute();		
			
			stmt.close();
			this.connection.close();
			
			atualizaAtividade(atividade, session, true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
  
  public void cancelar(int atividade, HttpSession session){
	  
	  String query = "update tb_registro_ativ set status = 2 where semana = ? and user_id = ? and status = 0 and ativid_id = ?"; 
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);			
			stmt.setInt(1, this.semana);
			stmt.setInt(2, this.userDetail.user(session).getUser().userid);
			stmt.setInt(3, atividade);
			
			stmt.execute();		
			
			stmt.close();
			this.connection.close();
			
			atualizaAtividade(atividade, session, false);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
	
	public HashMap<Integer, Boolean> atividadesAtuais(int userid){
		
		  String query = "select ativid_id from vw_registro_dinamico where semana = ? and user_id = ?"; 
		  
		  HashMap<Integer, Boolean> ativs = new HashMap<Integer, Boolean>();
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);			
				stmt.setInt(1, this.semana);
				stmt.setInt(2, userid);
				
				ResultSet rs = stmt.executeQuery();	
				
				while (rs.next()){
					
					ativs.put(rs.getInt(1), true);
					
				}
				
				return ativs;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		
			return ativs;
		
	} 
	
	public HashMap<Integer, Boolean> periodosAtuais(int userid){
		
		  String query = "select periodo from vw_registro_dinamico where semana = ? and user_id = ?"; 
		  
		  HashMap<Integer, Boolean> periodos = new HashMap<Integer, Boolean>();
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);			
				stmt.setInt(1, this.semana);
				stmt.setInt(2, userid);
				
				ResultSet rs = stmt.executeQuery();	
				
				while (rs.next()){
					
					periodos.put(rs.getInt(1), true);
					
				}
				
				return periodos;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		
			return periodos;
		
	} 
  
  public void atualizaAtividade(int atividade, HttpSession session, boolean acao){
	  
	  this.userDetail.user(session).atividadesAtuais.put(atividade, acao);
	  
	  this.userDetail.user(session).periodoInscritos.put(HashMapAtividade.periodos.get(atividade), acao);
	  
  }
  
  public boolean existeRegistro(int userid){
	  
	  String query = "select ativid_id from vw_registro_dinamico where semana = ? and user_id = ?"; 
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);			
			stmt.setInt(1, this.semana);
			stmt.setInt(2, userid);
			
			ResultSet rs= stmt.executeQuery();	
			
			boolean existe = rs.next();
			
			rs.close();
			stmt.close();
			
			return existe;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		return false;
  }
  
  public int voluntariosAtiv(int ativid_id){
	  
	  String query = "select count(*) from vw_registro_dinamico where semana = ? group by ativid_id"; 
		
		PreparedStatement stmt;
		int quant = 0 ;
		
		try {
			stmt = connection.prepareStatement(query);			
			stmt.setInt(1, this.semana);
			stmt.setInt(2, ativid_id);
			
			ResultSet rs= stmt.executeQuery();
			
			while (rs.next()){
				
				quant = rs.getInt(1);
			}
			
			rs.close();
			stmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
		return quant;
  }
  
  public void chamadaUsuario(int userid, int status){
	  
	  String query = "update tb_registro_ativ set status = ? where semana = ? and user_id = ? and status = 0"; 
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, status);
			
			if(this.ehDomingo())
				stmt.setInt(2, this.semana - 1);
			else
				stmt.setInt(2, this.semana);
			
			stmt.setInt(3, userid);
			
			stmt.execute();		
			
			stmt.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
  }
  
  public int posicao() {
	  
	  Random rand = new Random();
	  
	  int randomNum = rand.nextInt((10000 - 1) + 1) + 1;
	  
	  return randomNum;
	  
  }
  
  public void close() {
	  
	  try {
		this.connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }


}
