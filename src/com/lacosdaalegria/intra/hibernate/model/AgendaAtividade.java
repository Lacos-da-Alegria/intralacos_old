package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_agenda")
@DynamicUpdate
public class AgendaAtividade implements Serializable {

	private static final long serialVersionUID = -6456619026296489755L;
	
	private Integer id;
	private Instituicao instituicao = new Instituicao();
	private Integer voluntarios;
	private boolean tem_limite;
	private Date criacao = new Date();
	private Integer criador_id;
	private Integer status = 1;
	private Date horario;
	private Integer duracao;
	private boolean fez_chamada;
	
	@Transient
	private String nomeCriador;
	@Transient
	private String horarioFormat;
	
	@Transient
	public int getAno(){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(horario);
	    return  cal.get(Calendar.YEAR);
	}
	
    @Transient
	public int getMes(){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(horario);
	    return  cal.get(Calendar.MONTH);
	} 
    
    @Transient
	public int getDia(){
		Calendar cal = Calendar.getInstance();
	    cal.setTime(horario);
	    return  cal.get(Calendar.DAY_OF_MONTH);
	} 
    
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "agenda_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
    @JoinColumn(name = "inst_id")
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	
	@NotNull
	@Column(name = "quant_voluntarios")
	public Integer getVoluntarios() {
		return voluntarios;
	}
	public void setVoluntarios(Integer voluntarios) {
		this.voluntarios = voluntarios;
	}
	
	@Type(type="true_false")
	@Column(name="tem_limite")
	public boolean isTem_limite() {
		return tem_limite;
	}
	public void setTem_limite(boolean tem_limite) {
		this.tem_limite = tem_limite;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao", nullable = false)
	public Date getCriacao() {
		return criacao;
	}
	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}
	
	@NotNull
	@Column(name = "criador_id")
	public Integer getCriador_id() {
		return criador_id;
	}
	public void setCriador_id(Integer criador_id) {
		this.criador_id = criador_id;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "horario", nullable = false)
	public Date getHorario() {
		return horario;
	}
	
	public void setHorario(Date horario) {
		this.horario = horario;
	}
	
	@NotNull
	@Column(name = "duracao")
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	@Transient
	public String getNomeCriador() {
		return nomeCriador;
	}

	public void setNomeCriador(String nomeCriador) {
		this.nomeCriador = nomeCriador;
	}

	@Transient
	public String getHorarioFormat() {
		return horarioFormat;
	}

	public void setHorarioFormat(String horarioFormat) {
		this.horarioFormat = horarioFormat;
	}

	@Type(type="true_false")
	@Column(name="fez_chamada")
	public boolean isFez_chamada() {
		return fez_chamada;
	}

	public void setFez_chamada(boolean fez_chamada) {
		this.fez_chamada = fez_chamada;
	}
	
}
