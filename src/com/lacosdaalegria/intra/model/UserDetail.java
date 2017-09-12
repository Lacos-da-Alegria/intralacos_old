package com.lacosdaalegria.intra.model;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class UserDetail {
	
	private Voluntario user;
	private MaisLacos maisLacos;
	private HashMap<Integer, Boolean> atividadesAtuais = new HashMap<>();
	private HashMap<Integer, Boolean> periodoInscritos = new HashMap<>();
	private int[] serApoio;
	private boolean faltante;
	private boolean primeiro_acesso;
	
	public Voluntario getUser() {
		return user;
	}
	public void setUser(Voluntario user) {
		this.user = user;
	}
	public MaisLacos getMaisLacos() {
		return maisLacos;
	}
	public int[] getSerApoio() {
		return serApoio;
	}
	public void setSerApoio(int[] serapoio) {
		this.serApoio = serapoio;
	}
	public void setMaisLacos(MaisLacos maisLacos) {
		this.maisLacos = maisLacos;
	}
	
	public UserDetail user(HttpSession session){
	
		return  (UserDetail) session.getAttribute("userDetail");
			
	}
	
	public HashMap<Integer, Boolean> getAtividadesAtuais() {
		return atividadesAtuais;
	}
	public void setAtividadesAtuais(HashMap<Integer, Boolean> atividadesAtuais) {
		this.atividadesAtuais = atividadesAtuais;
	}
	public HashMap<Integer, Boolean> getPeriodoInscritos() {
		return periodoInscritos;
	}
	public void setPeriodoInscritos(HashMap<Integer, Boolean> periodoInscritos) {
		this.periodoInscritos = periodoInscritos;
	}
	public UserDetail user(HttpServletRequest request){
		
		return  (UserDetail) request.getSession().getAttribute("userDetail");
			
	}
	public boolean isFaltante() {
		return faltante;
	}
	public void setFaltante(boolean faltante) {
		this.faltante = faltante;
	}
	
	public int getUserId(HttpSession session){
		return this.user(session).getUser().getId();		
	}
	public boolean isPrimeiro_acesso() {
		return primeiro_acesso;
	}
	public void setPrimeiro_acesso(boolean primeiro_acesso) {
		this.primeiro_acesso = primeiro_acesso;
	}

}
