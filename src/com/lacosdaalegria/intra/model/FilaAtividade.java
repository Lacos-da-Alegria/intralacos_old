package com.lacosdaalegria.intra.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lacosdaalegria.intra.hibernate.dao.RegistroAtividadeDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.RegistroAtividade;
import com.lacosdaalegria.intra.hibernate.model.Semana;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class FilaAtividade {
	
	private Atividade atividade;
	private List<Voluntario> lista_voluntarios = new ArrayList<>();
	private List<Voluntario> lista_apoios = new ArrayList<>();
	private List<Voluntario> lista_novatos = new ArrayList<>();
	private List<Voluntario> lista_espera = new ArrayList<>();
	private List<Voluntario> lista_temp = new ArrayList<>();
	private List<Voluntario> chamada_voluntario = new ArrayList<>();
	private List<Voluntario> todos = new ArrayList<>();
	private List<RegistroAtividade> novatos_inscritos;
	
	public FilaAtividade(Atividade atividade){
		super();
		this.atividade = atividade;
		
		RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
		Semana semana = Semana.getInstance(); 
		
		List<RegistroAtividade> registros = regDao.filaAtividade(atividade, semana);

		if(registros == null){
			registros = new ArrayList<>();
		}
		
		for(RegistroAtividade registro : registros){
			this.distribuiFila(registro.getVoluntario().defineRegistroAtual(registro));
		}
		
		lista_apoios.addAll(lista_temp);
		
		this.inicializaChamadaVoluntarios();
		
	}
	
	public FilaAtividade(List<Voluntario> novatos, Atividade atividade){
		super();
		this.lista_novatos = novatos;
		
		RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
		Semana semana = Semana.getInstance(); 
			
		this.novatos_inscritos = regDao.novatosAtividade(atividade, semana);
		
		this.retiraNovatosInscritos();
		
	}
	
	public List<RegistroAtividade> getNovatosInscritos(){
		
		 return novatos_inscritos;
	}
	
	private void retiraNovatosInscritos(){
		if(lista_novatos!=null){
			lista_novatos = lista_novatos.stream().filter(nov -> novatos_inscritos == null ||
								!novatos_inscritos.stream().anyMatch(regN -> nov.getId().equals(regN.getVoluntario().getId()))
					).collect(Collectors.toList());
		}
	}
	
	public void distribuiFila(Voluntario voluntario){
		
		if(voluntario.isNovato()) {
			
			this.lista_novatos.add(voluntario);
			
		} else {
			
			if(voluntario.getMembro_atividade() != null) {
				
				if(voluntario.ehCoordenador(atividade)){
					voluntario.setNome(voluntario.getNome() + "-COORD");
					lista_temp.add(voluntario);
				} else {
					if(voluntario.ehApoio(atividade)){
						//Verifica se é apoio ou lista de apoio está lotada
						// Necessita verificar se eh apoio
						if(lista_apoios.size() >= atividade.getLimite_apoio().intValue()){

							//Verifica se a lista de voluntarios esta lotada caso esteja
							//manda para a lista de espera
							if(lista_voluntarios.size() < atividade.getLimite_voluntario().intValue()){
								lista_voluntarios.add(voluntario);
							} else { 
								//Lista de Espera
								this.lista_espera.add(voluntario);
							}
						} else {
							//Lista de Apoios
							lista_apoios.add(voluntario);
						}
					} else {
						
						this.distribuiDoutores(voluntario);	
					}
					
				}
				
			} else {
				
				if(voluntario.getDiretoria() != null && voluntario.acessoDiretorHosp()){
					voluntario.setNome(voluntario.getNome() + "-Diretor");
						lista_temp.add(voluntario);
					
				} else {
					
					this.distribuiDoutores(voluntario);	
				
				}
						
			} 
		}
	}
	
	private void distribuiDoutores(Voluntario voluntario){

		//Verifica se a lista de voluntarios esta lotada caso esteja
		//manda para a lista de espera
		if(lista_voluntarios.size() < atividade.getLimite_voluntario().intValue()){
			lista_voluntarios.add(voluntario);
		} else { 
			//Lista de Espera
			this.lista_espera.add(voluntario);
		}
	}	
	
	private void inicializaChamadaVoluntarios(){
		
		chamada_voluntario.addAll(lista_voluntarios);
		chamada_voluntario.addAll(lista_apoios);
		
	}
	
	public boolean chamadaRealizada(){
		
		for(Voluntario volunt : chamada_voluntario){
			if(volunt.getStatus().equals(0))
				return false;			
		}
		
		for(Voluntario novato : lista_novatos){
			if(novato.getStatus().equals(0))
				return false;			
		}
		
		return true;
	}
	
	public int posicaoFila(int userId){
		
		for(int i = 0; i < lista_espera.size() ; i++){
			
			if(lista_espera.get(i).getId().equals(userId))
				return i+1;
		}
		
		return -1;
		
	}
	
	public int voluntariosEscritos(){
		
		return this.getLista_voluntarios().size() + this.getLista_espera().size();
	}
	
	public boolean entrouNaFila(Voluntario vRetirado, Voluntario vZero){

		todos.addAll(chamada_voluntario);
		todos.addAll(lista_espera);
		todos = todos.stream().filter(vol -> !vol.getId().equals(vRetirado.getId())).collect(Collectors.toList());
		
		this.clearAllLists();
		
		for(Voluntario volunt : todos){
			this.distribuiFila(volunt);
		}

		return this.posicaoFila(vZero.getId()) == -1;
		
	}
	
	private void clearAllLists(){
		lista_apoios.clear();
		lista_voluntarios.clear();
		lista_espera.clear();
	}
	
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<Voluntario> getLista_voluntarios() {
		return lista_voluntarios;
	}
	public void setLista_voluntarios(List<Voluntario> lista_voluntarios) {
		this.lista_voluntarios = lista_voluntarios;
	}
	public List<Voluntario> getLista_apoios() {
		return lista_apoios;
	}
	public void setLista_apoios(List<Voluntario> lista_apoios) {
		this.lista_apoios = lista_apoios;
	}
	public List<Voluntario> getLista_novatos() {
		return lista_novatos;
	}
	public void setLista_novatos(List<Voluntario> lista_novatos) {
		this.lista_novatos = lista_novatos;
	}
	public List<Voluntario> getLista_espera() {
		return lista_espera;
	}
	public void setLista_espera(List<Voluntario> lista_espera) {
		this.lista_espera = lista_espera;
	}

	public List<Voluntario> getChamadaVoluntarios() {
		return chamada_voluntario;
	}

	public List<Voluntario> getTodos() {
		return todos;
	}

	public void setTodos(List<Voluntario> todos) {
		this.todos = todos;
	}

}
