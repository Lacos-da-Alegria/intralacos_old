package com.lacosdaalegria.intra.model;

import java.util.List;

import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class InserirBanco implements Runnable {

	private List<Voluntario> vols;
	
	public InserirBanco(List<Voluntario> vols){
		super();
		this.vols = vols;
	}
		
	
	@Override
	public void run() {
		
		System.out.println("Thread Iniciada!");
		
		VoluntarioDAO dao = new VoluntarioDAO();
		
		for(Voluntario vol : vols){
			System.out.println("Inserindo - " + vol.getNome());
			dao.addVoluntario(vol);
		}
		
		System.out.println("Fim de tread");
		
	}

}
