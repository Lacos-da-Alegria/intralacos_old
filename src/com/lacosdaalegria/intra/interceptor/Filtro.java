package com.lacosdaalegria.intra.interceptor;

import java.util.ArrayList;
import java.util.List;

public class Filtro {
	
	List<String> novato = new ArrayList<String>();
	List<String> voluntario = new ArrayList<String>();
	List<String> cordenador = new ArrayList<String>();
	List<String> todos = new ArrayList<String>();
	List<String> logado = new ArrayList<String>();
	
	
	public Filtro(){
		
		//Paginas que todos podem acessar mesmo não logado
		todos.add("/");
		todos.add("cadastroVoluntario");
		todos.add("cadastrar");
		todos.add("recuperaSenha");
		todos.add("efetuaLogin");
		todos.add("logout");
		
		//Paginas que todos podem acessar porem somente se estiver logado
		
		logado.add("profile");
		logado.add("atualizaInfo");
						
		//Paginas que novatos podem acessar
		
		novato.add("construcaonovato");
		novato.add("area-novato");
				
		//Paginas que voluntarios que não são mais novatos podem acessar

		voluntario.add("area-voluntario");
		voluntario.add("feedback");
		voluntario.add("construcao");
		voluntario.add("inscrever");
		voluntario.add("cancelar");
		voluntario.add("mudar");
		voluntario.add("desativarApoio");
		voluntario.add("ativarApoio");
		voluntario.add("listaAtividade");
		
		//Paginas que voluntarios que somente cordenadores podem acessar
		
		cordenador.add("fazerChamada");
		cordenador.add("atualizaAtividade");
		cordenador.add("statusAtividade");
		cordenador.add("detalheAtividade");
		cordenador.add("adicionarApoio");
		
		
	}
	
	public boolean temAcesso(String uri, List<String> filtro){
		
		for(String request : filtro){
			
			if(uri.endsWith(request)){
				return true;
			}
			
		}
		
		return false;
	}

}
