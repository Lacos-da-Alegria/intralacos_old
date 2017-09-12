package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_diretorias")
@DynamicUpdate
public class Diretoria implements Serializable{

	private static final long serialVersionUID = -6468635910610692376L;
	
	private Integer id;
	private String nome;
	//1- executiva 2-comunicacao 3-ongs 4-hospitais 5- rh
	private Integer tipo;
	private List<Atividade> atividades;
	private List<Equipe> equipes;
	private List<Voluntario> diretores;
	
	
	public boolean ehExecutivo(){
		return tipo.equals(1);
	}
	
	public boolean ehComunicacao(){
		return tipo.equals(2);
	}
	
	public boolean ehOngs(){
		return tipo.equals(3);
	}
	
	public boolean ehHospitais(){
		return tipo.equals(4);
	}
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty
	@Column(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="diretoria", cascade=CascadeType.ALL)
	public List<Atividade> getAtividades() {
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="diretoria", cascade=CascadeType.ALL)
	public List<Equipe> getEquipes() {
		return equipes;
	}
	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	@OneToMany(mappedBy="diretoria", cascade=CascadeType.ALL)
	public List<Voluntario> getDiretores() {
		return diretores;
	}
	public void setDiretores(List<Voluntario> diretores) {
		this.diretores = diretores;
	}
	
	@NotNull
	@Column(name="tipo")
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
