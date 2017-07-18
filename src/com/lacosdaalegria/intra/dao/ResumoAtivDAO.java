package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lacosdaalegria.intra.model.ResumoAtiv;

public class ResumoAtivDAO {
	
	
	private Connection connection;	
	
	public ResumoAtivDAO() {
		
		this.connection = new ConnectionFactory().getConnection();
		
	}
	
	public ResumoAtiv taxaRetorno(int ativid){
		
		String query = "select * from vw_taxa_de_retorno where ativid_id = ?"; 
		
		ResumoAtiv retorno = new ResumoAtiv();
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, ativid);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){
				
				retorno.setTotal_retorno(rs.getInt(3));
				retorno.setTaxa_retorno(rs.getInt(4));

				
			}
			
			stmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return retorno;
		
		
	}
	
	public List<ResumoAtiv> top10AllTimes(int ativid){
		
		List<ResumoAtiv> top10 = new ArrayList<ResumoAtiv>();
		
		String query = "select top 10 ROW_NUMBER() OVER (ORDER BY count(*) desc) posicao, max(b.nome) as nome,"
				+ " count(*) as total_ativ from tb_registro_ativ a inner join tb_usuarios b on a.user_id = "
				+ "b.user_id where a.status = 1 and a.ativid_id = ? group by a.user_id"; 
		
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, ativid);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){
				
				ResumoAtiv top = new ResumoAtiv();
				
				top.setPosicao(rs.getInt(1));
				top.setNome(rs.getString(2));
				top.setQuantidade(rs.getInt(3));
				
				top10.add(top);

				
			}
			
			stmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return top10;
		
		
	}
	
	public List<ResumoAtiv> top10ThreeMonths(int ativid){
		
		List<ResumoAtiv> top10 = new ArrayList<ResumoAtiv>();
		
		String query = "select top 10 ROW_NUMBER() OVER (ORDER BY count(*) desc) posicao, max(b.nome) "
				+ "as nome, count(*) as total_ativ from tb_registro_ativ a inner join tb_usuarios b on "
				+ "a.user_id = b.user_id where a.status = 1 and a.ativid_id = ? and a.data_criacao >= "
				+ "dateadd(MONTH,-3,getdate()) group by a.user_id"; 
		
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, ativid);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){
				
				ResumoAtiv top = new ResumoAtiv();
				
				top.setPosicao(rs.getInt(1));
				top.setNome(rs.getString(2));
				top.setQuantidade(rs.getInt(3));
				
				top10.add(top);

				
			}
			
			stmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return top10;
		
		
	}
	
	
	public String cadastroMes(){
		
	 	Map<Integer,Integer> cadastros = new HashMap<Integer,Integer>();
	 	List<Integer> resultado = new ArrayList<Integer>();
		
		String query = "select * from vw_novatos_por_mes where posicao = 1"; 
		
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){

				cadastros.put(rs.getInt(2), rs.getInt(4));
	
			}
			
			stmt.close();
			rs.close();
			
			for(int i = 1; i<=12; i++){
				
				if (cadastros.containsKey(i)){
					
					resultado.add(cadastros.get(i));
					
				} else {
					
					resultado.add(0);
					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return resultado.toString();	
		
	}
	
	public List<ResumoAtiv> distruicaoVolunt(int ativid){
		
		List<ResumoAtiv> distribuicao = new ArrayList<ResumoAtiv>();
		
		String query = "select TOP 7 * from vw_distribuicao_volunt where ativid_id = ? "
				+ "order by quant desc"; 
		
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, ativid);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){
				
				ResumoAtiv dist = new ResumoAtiv();
				
				dist.setNome(rs.getString(2));
				dist.setQuantidade(rs.getInt(3));
				
				distribuicao.add(dist);

			}
			
			stmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return distribuicao;
		
		
	}
	
	public int distruicaoAtivid (int ativid){
		
		String query = "select total from vw_distribuicao_ativid where ativid_id = ?"; 
		
		int retorno = 0;
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, ativid);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){
				
				retorno = rs.getInt(1);
			
			}
			
			stmt.close();
			rs.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return retorno;
		
		
	}
	
	public int[] frequenciaVolunt(int ativid){
		
		int[] frequencia = new int[2];
		
		String query = "select * from vw_frequencia_volunt a inner join "
				+ "(select AVG(frequencia) as media from vw_frequencia_volunt) b "
				+ "on a.ativid_id = b.media or a.ativid_id != b.media where ativid_id = ?"; 
		
	
		PreparedStatement stmt;
		
		try {
			
			stmt = connection.prepareStatement(query);
			
			stmt.setInt(1, ativid);
	
			ResultSet rs = stmt.executeQuery();	
		
			while (rs.next()){

				frequencia[0] = rs.getInt(2);
				frequencia[1] = rs.getInt(3);
	
			}
			
			stmt.close();
			rs.close();
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return frequencia;	
		
	}

}
