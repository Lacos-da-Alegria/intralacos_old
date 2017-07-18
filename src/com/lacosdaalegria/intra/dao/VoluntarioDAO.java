package com.lacosdaalegria.intra.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.Voluntario;

public class VoluntarioDAO extends DateHandler{
	
	private List<Voluntario> listanovatos = new ArrayList<Voluntario>();
	
	/**
	 * Essa classe realizara comunicacao com o banco via ConnectionFactory, realizando as atraves necessarias em cada metodo.
	 */
	
	private Connection connection;

	//Toda instacia DAO obrigatoriamente estabelece um conexao com o BD atraves da ConnectionFactory.
	public VoluntarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	//O metodo a baixo adiciona um voluntaio num banco de dados.
	public void adicionaVoluntario(Voluntario voluntario){
		
			String sql = "insert into tb_usuarios (login, senha, email, cpf, nome, nascimento, whatsapp, regiao, "
					+ "endereco, preferencia, sexo, como_conheceu) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, voluntario.getLogin());
			stmt.setString(2, voluntario.getSenha());
			stmt.setString(3, voluntario.getEmail());
			stmt.setString(4, voluntario.getCpf());
			stmt.setString(5, voluntario.getNome());
			stmt.setString(6, voluntario.getDt_Nascimento());
			stmt.setString(7, voluntario.getWhatsapp());
			stmt.setString(8, voluntario.getRegiao());
			stmt.setString(9, voluntario.getEndereco());
			stmt.setString(10, voluntario.getPreferencia());
			stmt.setString(11, voluntario.getSexo());
			stmt.setString(12, voluntario.getComo_conheceu());
		
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean login(Voluntario voluntario) {
			
			if(voluntario == null) {
				throw new IllegalArgumentException("Usuario nao deve ser nulo");
			}
			
			try {
				PreparedStatement stmt = this.connection.prepareStatement("select * from tb_usuarios where (login = ? and senha = ?) or (email = ? and senha = ?)");
				stmt.setString(1, voluntario.getLogin());
				stmt.setString(2, voluntario.getSenha());
				stmt.setString(3, voluntario.getLogin());
				stmt.setString(4, voluntario.getSenha());
				ResultSet rs = stmt.executeQuery();
	
				boolean encontrado = rs.next();
				rs.close();
				stmt.close();
				
				return encontrado;
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	
	public boolean existeLogin(Voluntario voluntario) {
		
		if(voluntario == null) {
			throw new IllegalArgumentException("Usuario nao deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from tb_usuarios where login = ? or email = ?");
			stmt.setString(1, voluntario.getLogin());
			stmt.setString(2, voluntario.getEmail());
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			rs.close();
			stmt.close();
			
			return encontrado;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
}
		
	public Voluntario acessoVoluntario (Voluntario voluntario){	
		
		Voluntario usuario = new Voluntario();
		
		if(voluntario == null) {
			throw new IllegalArgumentException("Usuario nao deve ser nulo");
		}
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select * from tb_usuarios where (login = ? and senha = ?) or (email = ? and senha = ?)");
			
			stmt.setString(1, voluntario.getLogin());
			stmt.setString(2, voluntario.getSenha());
			stmt.setString(3, voluntario.getLogin());
			stmt.setString(4, voluntario.getSenha());
			
			ResultSet rs = stmt.executeQuery();			
			
			while(rs.next()){
				
				usuario.setUserid(rs.getInt(1));
				usuario.setAcesso(rs.getInt(3));
				usuario.setStatus(rs.getInt(4));
				usuario.setLogin(rs.getString(5));
				usuario.setEmail(rs.getString(7));
				usuario.setCpf(rs.getString(8));
				usuario.setNome(rs.getString(9));
				usuario.setDt_Nascimento(rs.getString(10));
				usuario.setWhatsapp(rs.getString(11));
				usuario.setRegiao(rs.getString(12));
				usuario.setEndereco(rs.getString(13));
				usuario.setPreferencia(rs.getString(14));
				usuario.setNome_doutor(rs.getString(17));
				
			}
			 
			rs.close();
			stmt.close();
			
			return usuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	public List<Voluntario> listarNovatos (){	
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select user_id, nome, whatsapp, regiao from tb_usuarios where acesso = 0");		
			
			ResultSet rs = stmt.executeQuery();			
			
			while(rs.next()){
				
				Voluntario novato = new Voluntario();
				
				novato.setUserid(rs.getInt(1));				
				novato.setNome(rs.getString(2));
				novato.setWhatsapp(rs.getString(3));
				novato.setRegiao(rs.getString(4));
				
				this.listanovatos.add(novato);
				
			}
			 
			rs.close();
			stmt.close();
			
			return this.listanovatos;
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}

	}	
	
	public void atualizaAcesso (String userid){	
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement("update tb_usuarios set acesso = 1 where user_id in " + userid);		
			
			stmt.executeUpdate();			
			
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public void atualizaInformacoes (Voluntario voluntario, int user_id){	
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement
				("update tb_usuarios set nome = ?, nome_dr = ?, nascimento = ?, whatsapp = ?, endereco = ?,  "
						+ "regiao = ? where user_id = ? " );
			
			
			stmt.setString(1, voluntario.getNome());
			stmt.setString(2, voluntario.getNome_doutor());
			stmt.setString(3, voluntario.getDt_Nascimento());
			stmt.setString(4, voluntario.getWhatsapp());
			stmt.setString(5, voluntario.getEndereco());
			stmt.setString(6, voluntario.getRegiao());
			stmt.setInt(7, user_id);
			
			stmt.executeUpdate();			
			
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	
	public Voluntario infoVoluntario (String email){	
		
		Voluntario usuario = new Voluntario();
		
		try {
			
			PreparedStatement stmt = this.connection.prepareStatement("select * from tb_usuarios where email = ?");		
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();			
			
			while(rs.next()){
				
				usuario.setUserid(rs.getInt(1));
				usuario.setAcesso(rs.getInt(3));
				usuario.setStatus(rs.getInt(4));
				usuario.setLogin(rs.getString(5));
				usuario.setSenha(rs.getString(6));
				usuario.setCpf(rs.getString(8));
				usuario.setNome(rs.getString(9));
				usuario.setDt_Nascimento(rs.getString(10));
				usuario.setWhatsapp(rs.getString(11));
				usuario.setRegiao(rs.getString(12));
				usuario.setPreferencia(rs.getString(14));		
	
			}
			 
			rs.close();
			stmt.close();
			
			return usuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}

	}
	
	public boolean faltou(int userid) {
	
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement("select registro_id from tb_registro_ativ where (semana = ? or semana = ?) and status = 3 and user_id = ?");
			
			stmt.setInt(1, this.numeroSemana()-1);
			stmt.setInt(2, this.numeroSemana());
			stmt.setInt(3, userid);
			
			ResultSet rs = stmt.executeQuery();

			boolean encontrado = rs.next();
			
			rs.close();
			stmt.close();
			
			return encontrado;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	 
}
