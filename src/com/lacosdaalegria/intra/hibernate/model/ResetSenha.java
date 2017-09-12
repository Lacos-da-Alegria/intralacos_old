package com.lacosdaalegria.intra.hibernate.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="tb_reset_senha")
@DynamicUpdate
public class ResetSenha {
	
	private Integer id;
	private Voluntario voluntario;
	private String token = UUID.randomUUID().toString();
	private Integer status = 1;
	private Date dt_criacao = new Date();
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "user_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voluntario", nullable = false)
	public Voluntario getVoluntario() {
		return voluntario;
	}
	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}
	
	@NotEmpty
	@Column(name = "token", updatable = false)
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	
	

}
