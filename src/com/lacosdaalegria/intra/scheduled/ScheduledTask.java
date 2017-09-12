package com.lacosdaalegria.intra.scheduled;

import java.util.UUID;

import org.springframework.scheduling.annotation.Scheduled;

import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.SemanaDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Semana;
import com.lacosdaalegria.intra.model.CodigoAtivacao;


public class ScheduledTask {
	
	@Scheduled(cron="0 0 0 * * FRI")
	public void generateCodigoAtivacao() {
			
		CodigoAtivacao.codigoAtivacao = "lacos"+UUID.randomUUID().toString().substring(0, 4);
	}
	
	@Scheduled(cron="0 0 0 * * MON")
	public void geraNovaSemana() {
		
		SemanaDAO dao = new SemanaDAO();
		Semana semana = new Semana();
		
		Semana ultima = Semana.getInstance();
		
		ultima.setStatus(0);
		
		dao.updateSemana(ultima);
		
		while(dao.addSemana(semana)==null);
		
		Semana.atualizaInstace();
		
		AtividadeDAO aDao = new AtividadeDAO();
		
		for(Atividade ativ : aDao.listaAtividades()){
			ativ.setInscricao_liberada(true);
			aDao.updateAtividade(ativ);
		}
		
	}
	
	/*@Scheduled(cron="0 0 * * * *")
	public void corrigeErroRegistro() {
		
		RegistroAtividadeDAO erro = new RegistroAtividadeDAO();
		
		erro.corrigeErroMultiplasEntradas();
		
		erro.close();
	}*/
	
	/*@Scheduled(cron="0 0 0 * * SUN")
	public void desativaVoluntarios() {
		
		VoluntarioDAO dao = new VoluntarioDAO();
		EquipeDAO equiDao = new EquipeDAO();
		
		dao.desativaVoluntario();
		equiDao.rotinaDesativaApoio();
		
		dao.close();
		equiDao.close();
	}*/
	
	@Scheduled(cron="0 0 11 * * *")
	public void atividadeMatutina() {
		
		AtividadeDAO ativDao = new AtividadeDAO();
		
		for(Atividade ativ : ativDao.listaAtividadesAtivas()){
			if(ativ.ehMatutina() && ativ.ehHoje()){
				ativ.setChamada_liberada(true);
				ativ.setInscricao_liberada(false);
				ativDao.updateAtividade(ativ);
			}
		}
		
	}
	
	@Scheduled(cron="0 0 17 * * *")
	public void atividadeVespertina() {

		AtividadeDAO ativDao = new AtividadeDAO();
		
		for(Atividade ativ : ativDao.listaAtividadesAtivas()){
			if(ativ.ehVespertina() && ativ.ehHoje()){
				ativ.setChamada_liberada(true);
				ativ.setInscricao_liberada(false);
				ativDao.updateAtividade(ativ);
			}
		}
	}
	
	@Scheduled(cron="0 0 21 * * *")
	public void atividadeNoturna() {
		
		AtividadeDAO ativDao = new AtividadeDAO();
		
		for(Atividade ativ : ativDao.listaAtividadesAtivas()){
			if(ativ.ehNoturna() && ativ.ehHoje()){
				ativ.setChamada_liberada(true);
				ativ.setInscricao_liberada(false);
				ativDao.updateAtividade(ativ);
			}
		}
	}

}
