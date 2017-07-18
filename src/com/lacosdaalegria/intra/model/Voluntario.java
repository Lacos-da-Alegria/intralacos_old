 package com.lacosdaalegria.intra.model;

public class Voluntario {

	public String login;
	public int userid;
	public String senha;
	public String email;
	public String cpf;
	public String nome;
	public String nome_doutor;
	public String dt_Nascimento;
	public String whatsapp;
	public String regiao;
	public String endereco;
	public String preferencia;
	public String sexo;
	public String como_conheceu;
	public int acesso;
	public int status;
	public int posicao;
	public int atividade;
	public int apoio;	
	
	
	public int getAtividade() {
		return atividade;
	}
	public void setAtividade(int atividade) {
		this.atividade = atividade;
	}
	public int getApoio() {
		return apoio;
	}
	public void setApoio(int apoio) {
		this.apoio = apoio;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDt_Nascimento() {
		return dt_Nascimento;
	}
	public void setDt_Nascimento(String dt_Nascimento) {
		
		this.dt_Nascimento = dt_Nascimento;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		
		whatsapp = whatsapp.replace("-", "").replace(" ", "");
		
		this.whatsapp = whatsapp.substring(whatsapp.length()-8, whatsapp.length());
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getPreferencia() {
		return preferencia;
	}
	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public String getComo_conheceu() {
		return como_conheceu;
	}
	public void setComo_conheceu(String como_conheceu) {
		this.como_conheceu = como_conheceu;
	}
	public int getAcesso() {
		return acesso;
	}
	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNome_doutor() {
		return nome_doutor;
	}
	public void setNome_doutor(String nome_doutor) {
		this.nome_doutor = nome_doutor;
	}
	
}
