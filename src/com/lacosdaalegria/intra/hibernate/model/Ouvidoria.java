package com.lacosdaalegria.intra.hibernate.model;

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
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_ouvidorias")
@DynamicUpdate
public class Ouvidoria {
	
	private Integer id;
	private Date dt_criacao = new Date();
	private String ouvidoria;
	private String resposta;
	private Integer status = 0;
	private Categoria categoria;
	private Voluntario voluntario;
	private Voluntario responsavel;
	
	public String nomeAtendente(){
		return responsavel == null ? "Sem Responsável" : responsavel.getPrimerio_nome();
	}
	
	public String mostraResposta(){
		return resposta == null ? "Ouvidoria ainda <b>não</b> foi respondida!" : StringEscapeUtils.escapeHtml(resposta.replaceAll("[/\r?\n|\r/]", ""));
	}
	
	public String mostraOuvidoria(){
		
		return StringEscapeUtils.escapeHtml(ouvidoria.replaceAll("[/\r?\n|\r/]", ""));
	}
	
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
	
	@NotNull
	@Type(type="text")
	@Column(name = "ouvidoria")
	public String getOuvidoria() {
		return ouvidoria;
	}
	public void setOuvidoria(String ouvidoria) {
		this.ouvidoria = ouvidoria;
	}
	
	@Type(type="text")
	@Column(name = "resposta")
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "voluntario", nullable = false)
	public Voluntario getVoluntario() {
		return voluntario;
	}
	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsavel")
	public Voluntario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Voluntario responsavel) {
		this.responsavel = responsavel;
	}

	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
