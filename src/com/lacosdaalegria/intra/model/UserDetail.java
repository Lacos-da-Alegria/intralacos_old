package com.lacosdaalegria.intra.model;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lacosdaalegria.intra.dao.EquipeDAO;
import com.lacosdaalegria.intra.dao.VoluntarioDAO;

public class UserDetail {
	
	public Voluntario user;
	public MaisLacos maisLacos;
	public HashMap<Integer, Boolean> atividadesAtuais = new HashMap<>();
	public HashMap<Integer, Boolean> periodoInscritos = new HashMap<>();
	public int[] serapoio;
	private boolean faltante;
	
	public Voluntario getUser() {
		return user;
	}
	public boolean setUser(Voluntario user) {
		
		VoluntarioDAO dao = new VoluntarioDAO();
		EquipeDAO equipe = new EquipeDAO();
		
		if(dao.login(user)){
		
		this.user = dao.acessoVoluntario(user);
		this.serapoio = equipe.serApoio(this.user.userid); 
		
		return true;
		
		} else 
			
		return false;
	}
	public MaisLacos getMaisLacos() {
		return maisLacos;
	}
	public int[] isSerapoio() {
		return serapoio;
	}
	public void setSerapoio(int[] serapoio) {
		this.serapoio = serapoio;
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
	
	

}
