package com.lacosdaalegria.intra.comunication.model;
/*package com.lacosdaalegria.intra.comunication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lacosdaalegria.intra.comunication.model.TelegramUser;
import com.lacosdaalegria.intra.dao.ConnectionFactory;

public class TelegramUserDAO {
	
	private Connection connection;

	public TelegramUserDAO() {
		
		this.connection = new ConnectionFactory().getConnection();	

		}
	
	public void existeRegistro(TelegramUser user){
		  
		  String query = "select b.nome, b.user_id, a.status, b.sexo from tb_telegram_users a left "
		  		+ "join tb_usuarios b on a.user_id = b.user_id where a.chat_id = ?"; 
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);			
				stmt.setString(1, user.getChat_id());

				ResultSet rs = stmt.executeQuery();	
				
				while(rs.next()){
					user.setNome(rs.getString(1));
					user.setId(rs.getInt(2));
					user.setStatus_telegram(rs.getInt(3));
					user.setSexo(rs.getString(4));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  
	  }
	
	public void validaUsuario(TelegramUser user){
		
		String query = "select nome, user_id, sexo from tb_usuarios where telegram_id = ?"; 
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);			
				stmt.setString(1, user.getTelegram_id());

				ResultSet rs = stmt.executeQuery();	
				
				while(rs.next()){
					user.setNome(rs.getString(1));
					user.setId(rs.getInt(2));
					user.setSexo(rs.getString(3));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	
	public void insereUsuario(TelegramUser user){
		  
		  String query = "insert into tb_telegram_users (chat_id) values (?)"; 
		  
		  	
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);			
				stmt.setString(1, user.getChat_id());
				stmt.execute();		
				
				stmt.close();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	  }
	
	public void relacionaUsuario(TelegramUser user){
		  
		  String query = "update tb_telegram_users set user_id = ?, status = 2"
		  		+ " where chat_id = ?"; 
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);
				
				stmt.setInt(1, user.getId());
				stmt.setString(2, user.getChat_id());
			
				stmt.executeUpdate();		
				
				stmt.close();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	  }
	
	public void atualizaStatus(TelegramUser user){
		  
		  String query = "update tb_telegram_users set status = ?"
		  		+ " where chat_id = ?"; 
			
			PreparedStatement stmt;
			
			try {
				stmt = connection.prepareStatement(query);
				
				stmt.setInt(1, user.getStatus_telegram());
				stmt.setString(2, user.getChat_id());
			
				stmt.executeUpdate();		
				
				stmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
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
*/