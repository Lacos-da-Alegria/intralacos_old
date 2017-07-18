package com.lacosdaalegria.intra.model;

import java.util.List;

public class Notificacoes {

	static List<Notificacao> notificacoes;

	public static List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public static void setNotificacoes(List<Notificacao> notificacoes) {
		Notificacoes.notificacoes = notificacoes;
	}
	
}
