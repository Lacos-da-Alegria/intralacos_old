package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import com.lacosdaalegria.intra.model.DateHandler;

@Entity
@Table(name="tb_registros_atividade")
@DynamicUpdate
public class RegistroAtividade extends DateHandler implements Serializable{
	
	private static final long serialVersionUID = -8416314752399677885L;

	private Integer id;
	private Date dt_criacao = new Date();
	private Integer status = 0;
	private Integer posicao;
	private Atividade atividade;
	private Voluntario voluntario;
	private Semana semana;
	
	{
		if(this.rodadaRandomica()){
			this.posicaoRandomica();
		} else {
			this.posicao = 10001;
		} 
	}
	
	public void posicaoRandomica() {
		  
		  Random rand = new Random();
		  
		  this.posicao = rand.nextInt((10000 - 1) + 1) + 1;
		  
	}
	
	public RegistroAtividade() {
		super();
	}
	
	public RegistroAtividade(Atividade atividade, Voluntario voluntario, Semana semana) {
		super();
		this.atividade = atividade;
		this.voluntario = voluntario;
		this.semana = semana;
	}
	
	public boolean podeInscrever(List<RegistroAtividade> registros){
		
		if(registros == null)
			return true;
		
		return registros.stream().filter(
					reg -> reg.getStatus().equals(0) && !reg.getAtividade().ehNoMesmoHorario(this.atividade)
				).findFirst().isPresent();
	}
	
	public void cancelarRegistro(){
		this.status = 2;
	}
	
	public void voluntarioFoi(){
		this.status = 1;
	}
	
	public void voluntarioFaltou(){
		this.status = 3;
	}
	
	public boolean podeCancelar(){
		return this.podeCancelar(atividade.getDia(), atividade.getPeriodo());
	}

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "registro_id")
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
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@NotNull
	@Column(name = "posicao")
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ativ_id", nullable = false)
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
	public Voluntario getVoluntario() {
		return voluntario;
	}
	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}
	
	@ManyToOne
    @JoinColumn(name = "semana_id", nullable = false)
	public Semana getSemana() {
		return semana;
	}
	public void setSemana(Semana semana) {
		this.semana = semana;
	}
	
}
