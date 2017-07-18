package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lacosdaalegria.intra.model.HashMapAtividade;
import com.lacosdaalegria.intra.model.Voluntario;

public class AtividadeDAO {

	private Connection connection;

	public AtividadeDAO() {
		
		this.connection = new ConnectionFactory().getConnection();	

		}

	public int[] VoluntPorAtividade (){
		
		int[] ativs = new int[20];
		
		String query = "select * from vw_volunt_por_ativid"; 
		
		PreparedStatement stmt;
			
			try {
				
				stmt = connection.prepareStatement(query);
				
				ResultSet rs =stmt.executeQuery();
				
				while (rs.next()) {

					ativs[rs.getInt(1)] = rs.getInt(2);		
					
				}
				
				
				stmt.close();				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ativs;
		
	}
	
	public boolean[] atividadeLotada (){
		
		boolean[] ativs = new boolean[20];
		
		String query = "select * from vw_volunt_por_ativid"; 
		
		PreparedStatement stmt;
			
			try {
				
				stmt = connection.prepareStatement(query);
				
				ResultSet rs =stmt.executeQuery();
				
				while (rs.next()) {
					if(rs.getInt(2)>HashMapAtividade.limites_voluntarios.get(rs.getInt(1)))	
					ativs[rs.getInt(1)] = true;		
					else
					ativs[rs.getInt(1)] = false;	
				}
				
				
				stmt.close();				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return ativs;
	}

	
	public int estaEmFila(int ativAtual, int userid){
		
		List<Voluntario> lista_voluntarios = new ArrayList<Voluntario>();
		List<Voluntario> lista_apoios = new ArrayList<Voluntario>();
		List<Integer> fila_espera_id = new ArrayList<Integer>();
		
		
		if (ativAtual == 0) {
			
			return 0;
			
		} else {
		
			this.filaAtividadeId(ativAtual, lista_voluntarios, lista_apoios, fila_espera_id);
			
			return fila_espera_id.indexOf(userid) + 1;
			
		}

		
	}
	
	public void Atividades(){
		
		String sql = "select * from tb_atividades";

		PreparedStatement stmt;
		
		try {
			
			stmt = this.connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				
				HashMapAtividade.nomes.put(rs.getInt(1), rs.getString(5));
				HashMapAtividade.tags.put(rs.getInt(1), rs.getString(6));
				HashMapAtividade.descricoes.put(rs.getInt(1), rs.getString(7));
				HashMapAtividade.enderecos.put(rs.getInt(1), rs.getString(8));
				HashMapAtividade.locais_preparos.put(rs.getInt(1), rs.getString(9));
				HashMapAtividade.periodos.put(rs.getInt(1), rs.getInt(10));
				HashMapAtividade.status_atividades.put(rs.getInt(1), rs.getInt(12));
				HashMapAtividade.horarios.put(rs.getInt(1), rs.getString(17));
				
				HashMapAtividade.limites_voluntarios.put(rs.getInt(1), rs.getInt(14));
				HashMapAtividade.limites_novatos.put(rs.getInt(1), rs.getInt(15));
				HashMapAtividade.limites_apoios.put(rs.getInt(1), rs.getInt(16));
				
			}
		
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}
	
	public void atualizaAtividade (int ativid){	
		
		String sql = "update tb_atividades set nome_ativ = ?, descricao = ?, local_ativ = ?, horatio = ?, "
				+ "local_prep = ?,  num_volunt = ?, num_apoios = ?, num_novato = ?, periodo = ? "
			+ "where ativ_id =?";
		
		PreparedStatement stmt;
		
		try {
			
			stmt = this.connection.prepareStatement(sql);	
			
			stmt.setString(1, HashMapAtividade.nomes.get(ativid));
			stmt.setString(2, HashMapAtividade.descricoes.get(ativid));
			stmt.setString(3, HashMapAtividade.enderecos.get(ativid));
			stmt.setString(4, HashMapAtividade.horarios.get(ativid));
			stmt.setString(5, HashMapAtividade.locais_preparos.get(ativid));
			stmt.setInt(6, HashMapAtividade.limites_voluntarios.get(ativid));
			stmt.setInt(7, HashMapAtividade.limites_apoios.get(ativid));
			stmt.setInt(8, HashMapAtividade.limites_novatos.get(ativid));
			stmt.setInt(9, HashMapAtividade.periodos.get(ativid));
			stmt.setInt(10, ativid);
			
			stmt.executeUpdate();			
			
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public void filaAtividade(int ativid, List<Voluntario> lista_voluntarios,	
								List<Voluntario> lista_apoios, List<Voluntario> fila_espera){
		
		String sql = "select * from vw_fila_ativid where ativid_id = ? order by posicao asc, data_criacao asc";

		PreparedStatement stmt;
		
		try {
			
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setInt(1, ativid);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				
				Voluntario voluntario = new Voluntario();
				
				voluntario.setUserid(rs.getInt(2));
				voluntario.setNome(rs.getString(3));
				voluntario.setRegiao(rs.getString(4));
				voluntario.setWhatsapp(rs.getString(5));
				voluntario.setAtividade(rs.getInt(6));
				voluntario.setApoio(rs.getInt(7));
				voluntario.setStatus(rs.getInt(8));
				
				if(voluntario.getApoio()!= ativid || lista_apoios.size() >= HashMapAtividade.limites_apoios.get(ativid)){
					//Não é apoio ou lista de apoio lotada
					
					if(lista_voluntarios.size() < HashMapAtividade.limites_voluntarios.get(ativid)){
						
						lista_voluntarios.add(voluntario);
						
					} else { fila_espera.add(voluntario);}
					
				} else {
					//É um apoio
					lista_apoios.add(voluntario);
				}
			
			}
		
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}
	
	
	public void filaAtividade(int ativid, List<Voluntario> lista_voluntarios,	
								List<Voluntario> lista_apoios){
		
		String sql = "select * from vw_fila_ativid where ativid_id = ? order by posicao asc, data_criacao asc";

		PreparedStatement stmt;
		
		try {
			
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setInt(1, ativid);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()){
				
				Voluntario voluntario = new Voluntario();
				
				voluntario.setUserid(rs.getInt(2));
				voluntario.setNome(rs.getString(3));
				voluntario.setRegiao(rs.getString(4));
				voluntario.setWhatsapp(rs.getString(5));
				voluntario.setAtividade(rs.getInt(6));
				voluntario.setApoio(rs.getInt(7));
				voluntario.setStatus(rs.getInt(8));
				
				if(voluntario.getApoio()!= ativid || lista_apoios.size() >= HashMapAtividade.limites_apoios.get(ativid)){
					//Não é apoio ou lista de apoio lotada
					
					if(lista_voluntarios.size() < HashMapAtividade.limites_voluntarios.get(ativid)){
						
						lista_voluntarios.add(voluntario);
						
					} else {}
					
				} else {
					//É um apoio
					lista_apoios.add(voluntario);
				}
			
			}
		
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}
	
	public void filaAtividadeId(int ativid, List<Voluntario> lista_voluntarios,	
			List<Voluntario> lista_apoios, List<Integer> fila_espera_id){

		String sql = "select * from vw_fila_ativid where ativid_id = ? order by posicao asc, data_criacao asc";
		
		PreparedStatement stmt;
		
		try {
		
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setInt(1, ativid);
			
			ResultSet rs = stmt.executeQuery();
		
		while (rs.next()){
			
			Voluntario voluntario = new Voluntario();
			
			voluntario.setUserid(rs.getInt(2));
			voluntario.setNome(rs.getString(3));
			voluntario.setRegiao(rs.getString(4));
			voluntario.setWhatsapp(rs.getString(5));
			voluntario.setAtividade(rs.getInt(6));
			voluntario.setApoio(rs.getInt(7));
			voluntario.setStatus(rs.getInt(8));
			
			if(voluntario.getApoio()!= ativid || lista_apoios.size() >= HashMapAtividade.limites_apoios.get(ativid)){
			//Não é apoio ou lista de apoio lotada
			
			if(lista_voluntarios.size() < HashMapAtividade.limites_voluntarios.get(ativid)){
				
				lista_voluntarios.add(voluntario);
				
			} else { fila_espera_id.add(voluntario.getUserid());}
			
			} else {
				//É um apoio
				lista_apoios.add(voluntario);
				}
		
		}
		
		
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}

	}
	
	
	
	public void atualizaStatus (int ativid, int status){	
		
		String sql = "update tb_atividades set status_ativ = ? where ativ_id = ?";
		
		PreparedStatement stmt;
		
		try {
			
			stmt = this.connection.prepareStatement(sql);	
			
			stmt.setInt(1, status);
			stmt.setInt(2, ativid);
			
			stmt.executeUpdate();			
			
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}


	
}
