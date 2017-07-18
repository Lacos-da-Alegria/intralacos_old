package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lacosdaalegria.intra.model.Notificacao;
import com.lacosdaalegria.intra.model.Notificacoes;

public class NotificationDAO {

	private Connection connection;
	
	private List<Notificacao> listafeed = new ArrayList<Notificacao>();

	public NotificationDAO() {
		
		this.connection = new ConnectionFactory().getConnection();
		
	}
	
	public void getNotificacoes(){
		
		String query = "select * from vw_5_notificacoes"; 
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(query);
			
			listaPadrao(stmt.executeQuery());
			
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Notificacoes.setNotificacoes(listafeed);
	}
	
	
	public void listaPadrao (ResultSet rs){
	
		try {
			while (rs.next()) {
				
				Notificacao feed = new Notificacao();
				
				
				feed.setTipo(rs.getInt(1));
				feed.setSubmitter(rs.getString(2));
				feed.setTag(rs.getString(3));
				feed.setLink(rs.getString(4));
				feed.setMensagem(rs.getString(5));
				feed.setTempo(rs.getString(6));

				this.listafeed.add(feed);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
			
	}
	
	public void adicionaNotificacao(Notificacao notificacao, int userid){
		String sql = "insert into tb_notificacoes (tipo, tag, link, mensagem, submitter) values (?,?,?,?, ?)";
	
	PreparedStatement stmt;
	
	try { 
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, notificacao.getTipo());
		stmt.setString(2, notificacao.getTag());
		stmt.setString(3, notificacao.getLink());
		stmt.setString(4, notificacao.getMensagem());
		stmt.setInt(5, userid);
	
		stmt.execute();
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
		getNotificacoes();
	
	}
	
}
