package com.lacosdaalegria.intra.model;

import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;

public class MaisLacos {

	private Integer total_voluntarios;
	private Integer total_novatos;
	private Integer atividade_participadas;
	private Integer atividades_distintas;
	private Integer indicacoes;
	
	
	public int getTotal_voluntarios() {
		return total_voluntarios;
	}
	public void setTotal_voluntarios(int total_voluntarios) {
		this.total_voluntarios = total_voluntarios;
	}
	public int getTotal_novatos() {
		return total_novatos;
	}
	public void setTotal_novatos(int total_novatos) {
		this.total_novatos = total_novatos;
	}
	public int getAtividade_participadas() {
		return atividade_participadas;
	}
	public void setAtividade_participadas(int atividade_participadas) {
		this.atividade_participadas = atividade_participadas;
	}
	public int getAtividades_distintas() {
		return atividades_distintas;
	}
	public void setAtividades_distintas(int atividades_distintas) {
		
		AtividadeDAO dao = new AtividadeDAO();
		
		atividades_distintas = dao.listaAtividades().size() - atividades_distintas;
		
		this.atividades_distintas = atividades_distintas;
	}
	public int getIndicacoes() {
		return indicacoes;
	}
	public void setIndicacoes(int indicacoes) {
		this.indicacoes = indicacoes;
	}
	
		
}
