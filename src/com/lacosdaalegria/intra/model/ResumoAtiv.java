package com.lacosdaalegria.intra.model;

public class ResumoAtiv {
	
	public int total_retorno;
	public int taxa_retorno;
	public int posicao;
	public String nome;
	public int quantidade;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getTotal_retorno() {
		return total_retorno;
	}

	public void setTotal_retorno(int total_retorno) {
		this.total_retorno = total_retorno;
	}

	public int getTaxa_retorno() {
		return taxa_retorno;
	}

	public void setTaxa_retorno(int taxa_retorno) {
		this.taxa_retorno = taxa_retorno;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
