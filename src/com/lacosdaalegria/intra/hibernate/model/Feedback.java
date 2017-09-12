package com.lacosdaalegria.intra.hibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_feedbacks")
@DynamicUpdate
public class Feedback {
	
	private Integer id;
	private Date dt_criacao = new Date();
	private String mensagem;
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
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao", updatable = false)
	public Date getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	
	@NotEmpty
	@Column(name = "mensagem")
	@Type(type="text")
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
