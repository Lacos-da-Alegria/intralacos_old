package com.lacosdaalegria.intra.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lacosdaalegria.intra.dao.ConnectionFactory;
import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegistroAtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.SemanaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.RegiaoAdministrativa;
import com.lacosdaalegria.intra.hibernate.model.RegistroAtividade;
import com.lacosdaalegria.intra.hibernate.model.Semana;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class Integrador {
	
	private List<Atividade> atividades;
	private List<RegiaoAdministrativa> ras;
	private List<Semana> semanas;
	private List<Voluntario> vols;
	
	//INSERT INTO prd.dbo.tb_registros_atividade ( registro_id, dt_criacao, posicao, status, ativ_id, semana_id, user_id )
	//select (ROW_NUMBER() OVER (ORDER BY a.data_criacao asc) + 5575) as id, a.data_criacao, a.posicao, 
	//a.status, f.ativ_id, e.semana_id, d.user_id from tb_registro_ativ a inner join tb_usuarios b on a.user_id =
	//b.user_id inner join tb_atividades c on a.ativid_id = c.ativ_id inner join prd.dbo.tb_usuarios d on b.email = 
	//d.email inner join prd.dbo.tb_semanas e on a.semana = e.semana and a.ano = e.ano inner join prd.dbo.tb_atividades 
	//f on c.tag_ativ = f.tag where c.tag_ativ in ('HRG', 'HRT', 'HUB', 'HFA', 'HRP', 'HRS', 'HRAN') and a.data_criacao > 
	//'2017-01-23 07:47:56.157' order by a.registro_id asc;

	
	public Integrador(){
		super();
		AtividadeDAO aDao = new AtividadeDAO();
		RegiaoAdministrativaDAO rDao =  new RegiaoAdministrativaDAO();
		SemanaDAO sDao = new SemanaDAO();
		VoluntarioDAO vDao = new VoluntarioDAO(); 
		
		atividades = aDao.listaAtividades();
		ras = rDao.listaRegiaoAdministrativa();
		semanas = sDao.listaSemana();
		vols = vDao.listaVoluntario();
	}
	
	public Semana semPeloPeriodo(int ano, int semana){
		return semanas.stream().filter(s -> s.getSemana() == semana && s.getAno() == ano).findFirst().get();
	}
	
	public Voluntario volPeloEmail(String email){
		return vols.stream().filter(v -> v.getEmail().equals(email)).findFirst().get();
	}
	
	public Voluntario volPelaId (int id){
		return vols.stream().filter(v -> v.getId() == id).findFirst().get();
	}
	
	public Atividade ativPelaTag(String tag){
		return atividades.stream().filter(a -> a.getTag().equals(tag)).findFirst().get();
	}
	
	public RegiaoAdministrativa raPeloNome(String nome){
		return ras.stream().filter(r -> r.getNome().equals(nome)).findFirst().get();
	}
	
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Inicia Integrador!!");
		
		ConnectionFactory fac = new ConnectionFactory();
		Integrador integra = new Integrador();
		
		Connection con = fac.getConnection();
		
		PreparedStatement stmt = con.prepareStatement("select a.data_criacao, a.semana, a.status, a.ano, a.posicao, d.user_id, c.tag_ativ from tb_registro_ativ a inner join tb_usuarios b on a.user_id = b.user_id inner join tb_atividades c on a.ativid_id = c.ativ_id inner join prd.dbo.tb_usuarios d on b.email = d.email where c.tag_ativ in ('HRG', 'HRT', 'HUB', 'HFA', 'HRP', 'HRS', 'HRAN')  order by a.registro_id asc");
		
		ResultSet rs = stmt.executeQuery();
		
		List<RegistroAtividade> regs = new ArrayList<>();
		
		while(rs.next()){
			RegistroAtividade reg = integra.retornaRegisto(rs);
			
			regs.add(reg);
		}
		
		RegistroAtividadeDAO dao = new RegistroAtividadeDAO();
		
		for(RegistroAtividade reg : regs){
			dao.addRegistroAtividade(reg);
		}
		
		
		System.out.println("FIM");
		
		
	}

	
	/*ConnectionFactory fac = new ConnectionFactory();
	Integrador integra = new Integrador();
	
	Connection con = fac.getConnection();
	
	PreparedStatement stmt = con.prepareStatement("select * from tb_usuarios where user_id > 1818");
	
	ResultSet rs = stmt.executeQuery();
	
	List<Voluntario> vols = new ArrayList<>();
	
	while(rs.next()){
		Voluntario vol = integra.retornaVolutnario(rs);
		vols.add(vol);
	}
	
	System.out.println("Total de - " + vols.size() + " Voluntarios");
	
	con.close();

	new Thread(new InserirBanco(vols)).start();*/
	
	
	
	/*public static void main(String[] args) {
		System.out.println("Iniciou");
		Integrador integrador = new Integrador();
		
		integrador.criaSemanas();
		
		System.out.println("FIm");
	}*/
	
	public void criaSemanas(){
		
		SemanaDAO dao = new SemanaDAO();
		
		for(int i = 1; i <= 34; i++){
			System.out.println(i);
			Semana sem = new Semana();
			sem.setAno(2017);
			sem.setStatus(0);
			if(i==34)
				sem.setStatus(1);
			sem.setSemana(i);
			dao.addSemana(sem);
		}
		
	}
	
	public RegistroAtividade retornaRegisto(ResultSet rs) throws SQLException{
		
		RegistroAtividade reg = new RegistroAtividade();
		
		reg.setDt_criacao(rs.getTimestamp(1));
		reg.setSemana(semPeloPeriodo(rs.getInt(4), rs.getInt(2)));
		reg.setStatus(rs.getInt(3));
		reg.setPosicao(rs.getInt(5));
		reg.setVoluntario(volPelaId(rs.getInt(6)));
		reg.setAtividade(ativPelaTag(rs.getString(7)));
		
		return reg;
	}


	public Voluntario retornaVolutnario(ResultSet rs) throws SQLException{
		
		Voluntario vol = new Voluntario();
		
		vol.setId(rs.getInt(1));
		vol.setDt_criacao(rs.getDate(2));
		if(rs.getInt(3) != 0	){
			vol.setNovato(false);
			vol.setNovato_ongs(false);
		}
		vol.setStatus(rs.getInt(4));
		vol.setLogin(rs.getString(5));
		vol.setSenha(rs.getString(6));
		vol.setEmail(rs.getString(7));
		vol.setCpf(rs.getString(8));
		vol.setNome(rs.getString(9));
		vol.setDt_Nascimento(rs.getString(10));
		vol.setWhatsapp(rs.getString(11));
		vol.setRegiao(raPeloNome(rs.getString(12)));
		vol.setEndereco(rs.getString(13));
		vol.setPreferencia(ativPelaTag(rs.getString(14)));
		vol.setSexo(rs.getString(15));
		vol.setComo_conheceu(rs.getString(16));
		vol.setNome_doutor(rs.getString(17));
		vol.setObservacao(rs.getString(18));
		vol.setProfile(rs.getString(21));
		vol.setFeedbackNovato(rs.getInt(22));
		vol.setDdd(rs.getString(24));
	
		return vol;
	}
	
}
