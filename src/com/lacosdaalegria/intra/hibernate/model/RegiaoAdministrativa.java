package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_regioes_administrativas")
@DynamicUpdate
public class RegiaoAdministrativa implements Serializable {
	
	private static final long serialVersionUID = -2416277758149103739L;
	
	private Integer id;
	private String nome;
	private Integer status = 1;
	private Polo polo;
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "ra_id")
	public Integer getId() {
		return id;
	}
	
	@NotEmpty
	@Column(name = "regiao_administrativa")
	public String getNome() {
		return nome;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	@ManyToOne
    @JoinColumn(name = "polo_id")
	public Polo getPolo() {
		return polo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setPolo(Polo polo) {
		this.polo = polo;
	}
	
}
