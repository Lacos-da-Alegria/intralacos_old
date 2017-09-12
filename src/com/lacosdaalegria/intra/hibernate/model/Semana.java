package com.lacosdaalegria.intra.hibernate.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Calendar;
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

import com.lacosdaalegria.intra.hibernate.dao.SemanaDAO;


@Entity
@Table(name="tb_semanas")
@DynamicUpdate
public class Semana implements Serializable{

	private static final long serialVersionUID = 7451829411960792228L;
	
	private Integer id;
	private Date dt_criacao = new Date();
	private Integer semana;
	private Integer ano;
	private static Semana instance;
	private Integer status = 1;
	
	public Semana(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.dt_criacao);
		cal.add(Calendar.HOUR_OF_DAY, - 3);
		this.semana = cal.get(Calendar.WEEK_OF_YEAR);
		this.ano = cal.get(Calendar.YEAR);
	}
	
	@Transient
	public static synchronized Semana getInstance(){
		if(instance == null){
			SemanaDAO semanaDao = new SemanaDAO();
			instance = semanaDao.ultimaSemana();
		}
		
		return instance;
	}
	
	public static synchronized void atualizaInstace(){
		instance = null;
	}
	

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "semana_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao")
	public Date getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	@NotNull
	@Column(name = "semana")
	public Integer getSemana() {
		return semana;
	}

	public void setSemana(Integer semana) {
		this.semana = semana;
	}

	@NotNull
	@Column(name = "ano")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
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
