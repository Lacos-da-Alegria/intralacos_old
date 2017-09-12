package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_tags")
@DynamicUpdate
public class Tag implements Serializable{
	
	private static final long serialVersionUID = 8743555976807799802L;
	private Integer id;
	private Integer tipo;
	private String nome;
	private Integer status = 1;
	private String descricao;
	
	public Tag(){
		super();
	}
	public Tag(Integer id){
		this.id = id;
	}	
	
	public String textoStatus(){
		return (status == 1 ? "Ativo" : "Inativo");
	}
	
	public String textoTipo(){
		if(tipo == 1)
			return "Caracteristica";
		
		if(tipo == 2)
			return "Atividade";
		else
			return "Ponto Crítico";
	}
	
	public void inverteStatus(){
		if(status == 1)
			status = 0;
		else
			status = 1;
	}
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "tag_id")
	public Integer getId() {
		return id;
	}
	
	@NotNull
	@Column(name = "tipo")
	public Integer getTipo() {
		return tipo;
	}
	
	@NotEmpty
	@Column(name = "tag", length = 30)
	public String getNome() {
		return nome;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	@Column(name = "descricao", length = 255)
	public String getDescricao() {
		return descricao;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	/*----------------------------------------------------------------------------
	 * Fim dos Getters e Setters
	------------------------------------------------------------------------------*/

}
