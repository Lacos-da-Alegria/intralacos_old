package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import com.lacosdaalegria.intra.model.HashMapEstaticos;

@Entity
@Table(name="tb_notas")
@DynamicUpdate
public class Nota implements Serializable{
	
	private static final long serialVersionUID = -5882897192762529210L;
	
	private Integer id;
	private String nota;
	private Date dt_criacao = new Date();
	private Voluntario criador;
	private Integer status = 1;
	private Integer tipo;
	private Demanda demanda;

	@Transient
	public String getPrimerio_nome() {
		return criador.getPrimerio_nome();
	}
	
	@Transient
	public String getHexCor(){
		return HashMapEstaticos.cores_responsaveis.get(this.tipo+10);
	}
	
	@Transient
	public String getTipoTexto(){
		
		switch(this.tipo){
			case 0:
				return "Nota";
			case 1:
				return "Pendência";
			case 2:
				return "Solução de Pendência";
			case 3:
				return "Reabertura";
			case 4:
				return "Conclusão";
			case 5:
				return "Arquivamento";
			default:
				return "Nota";			
		}
		
	}	
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/
	
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

	@NotEmpty
	@Column(name = "nota")
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}

	@NotNull
	@Column(name = "dt_criacao")
	public Date getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	
	@ManyToOne
    @JoinColumn(name = "criador", nullable = false)
	public Voluntario getCriador() {
		return criador;
	}
	public void setCriador(Voluntario criador) {
		this.criador = criador;
	}

	@NotNull
	@Column(name = "tipo")
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	@ManyToOne
    @JoinColumn(name = "demanda", nullable = false)
	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/*----------------------------------------------------------------------------
	 * Fim dos Getters e Setters
	------------------------------------------------------------------------------*/

}
