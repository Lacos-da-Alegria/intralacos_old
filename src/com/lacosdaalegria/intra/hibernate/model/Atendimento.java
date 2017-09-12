package com.lacosdaalegria.intra.hibernate.model;

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
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_atendimentos")
@DynamicUpdate
public class Atendimento {
	
	private Integer id;
	private String nome;
	private List<Categoria> categorias;
	private List<Voluntario> atendentes;
	private Integer status = 1;
	
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
	
	@OneToMany(mappedBy="atendimento", cascade=CascadeType.ALL)
	public List<Voluntario> getAtendentes() {
		return atendentes;
	}
	public void setAtendentes(List<Voluntario> atendentes) {
		this.atendentes = atendentes;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@OneToMany(mappedBy="atendimento", cascade=CascadeType.ALL)
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	@NotEmpty
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
