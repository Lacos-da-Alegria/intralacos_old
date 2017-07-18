package com.lacosdaalegria.intra.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Atividade {

	private int ativid;
	private String nome;
	private String tag;
	private String descricao;
	private String endereco;
	private String local_preparo;
	private String horario;
	private Integer status;
	private Integer periodo;
	private Integer limite_voluntario;
	private Integer limite_apoio;
	private Integer limite_novato;
	

	public List<Atividade> listaAtividade (){
		
		List<Integer> ativid_Ativas = new ArrayList<Integer>();
		List<Atividade> atividades = new ArrayList<Atividade>();
		
		//Pega as atividades Ativas
		for(HashMap.Entry<Integer,Integer> atividade : HashMapAtividade.status_atividades.entrySet()){
			
			if(atividade.getValue()==1){
				
				ativid_Ativas.add(atividade.getKey());
				
			} else {}
		
		}
		
		for(int id : ativid_Ativas){
			
			Atividade atividade = new Atividade();
			
			atividade.setAtivid(id);
			atividade.setNome(HashMapAtividade.nomes.get(id));
			atividade.setTag(HashMapAtividade.tags.get(id));
			atividade.setDescricao(HashMapAtividade.descricoes.get(id));
			atividade.setPeriodo(HashMapAtividade.periodos.get(id));
			atividade.setHorario(HashMapAtividade.horarios.get(id));
			atividade.setEndereco(HashMapAtividade.enderecos.get(id));
			atividade.setLocal_preparo(HashMapAtividade.locais_preparos.get(id));
			atividade.setLimite_voluntario(HashMapAtividade.limites_voluntarios.get(id));
			atividade.setLimite_apoio(HashMapAtividade.limites_apoios.get(id));
			atividade.setLimite_novato(HashMapAtividade.limites_novatos.get(id));
			
			atividades.add(atividade);
			
		}
			
		return atividades;
	}
	
	public Atividade getAtividade(int id){
		
		Atividade atividade = new Atividade();
		
		atividade.setNome(HashMapAtividade.nomes.get(id));
		atividade.setEndereco(HashMapAtividade.enderecos.get(id));
		atividade.setHorario(HashMapAtividade.horarios.get(id));
		atividade.setLocal_preparo(HashMapAtividade.locais_preparos.get(id));
		atividade.setTag(HashMapAtividade.tags.get(id));
		atividade.setDescricao(HashMapAtividade.descricoes.get(id));
		atividade.setStatus(HashMapAtividade.status_atividades.get(id));
		atividade.setPeriodo(HashMapAtividade.periodos.get(id));
		
		atividade.setLimite_voluntario(HashMapAtividade.limites_voluntarios.get(id));
		atividade.setLimite_novato(HashMapAtividade.limites_novatos.get(id));
		atividade.setLimite_apoio(HashMapAtividade.limites_apoios.get(id));
		
		return atividade;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLocal_preparo() {
		return local_preparo;
	}

	public void setLocal_preparo(String local_preparo) {
		this.local_preparo = local_preparo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLimite_voluntario() {
		return limite_voluntario;
	}

	public void setLimite_voluntario(Integer limite_voluntario) {
		this.limite_voluntario = limite_voluntario;
	}

	public Integer getLimite_apoio() {
		return limite_apoio;
	}
	
	public void setLimite_apoio(Integer limite_apoio) {
		this.limite_apoio = limite_apoio;
	}

	public Integer getLimite_novato() {
		return limite_novato;
	}
	
	public void setLimite_novato(Integer limite_novato) {
		this.limite_novato = limite_novato;
	}
	
	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public int getAtivid() {
		return ativid;
	}


	public void setAtivid(int ativid) {
		this.ativid = ativid;
	}


	
}
