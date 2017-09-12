package com.lacosdaalegria.intra.model;

import java.util.List;

import com.lacosdaalegria.intra.hibernate.dao.NotificacaoDAO;
import com.lacosdaalegria.intra.hibernate.model.Notificacao;

public class Notificacoes {

	private static List<Notificacao> notificacoes;

	public static synchronized List<Notificacao> getNotificacoes() {
		
		if(notificacoes == null){
			NotificacaoDAO dao = new NotificacaoDAO();
			notificacoes = dao.ultimasCinco();
		}
		
		return notificacoes;
	}
	
	public static synchronized void atualizaNotificacoes() {
		notificacoes = null;
	}
	
}
