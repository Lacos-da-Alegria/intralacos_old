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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_notificacoes")
@DynamicUpdate
public class Notificacao implements Serializable{

	private static final long serialVersionUID = 6551558790192853030L;
	
	private Integer id;
	private Integer tipo;
	private Voluntario criador;
	private String tag;
	private String link;
	private String mensagem;
	private Date dt_cricao = new Date();
	
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
	@Column(name = "tipo")
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	@ManyToOne
    @JoinColumn(name = "criador", nullable = false)
	public Voluntario getCriador() {
		return criador;
	}
	public void setCriador(Voluntario criador) {
		this.criador = criador;
	}
	
	@NotEmpty
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Column(name = "link")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@NotEmpty
	@Column(name = "mensagem")
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@NotNull
	@Column(name = "dt_criacao")
	public Date getDt_cricao() {
		return dt_cricao;
	}
	public void setDt_cricao(Date dt_cricao) {
		this.dt_cricao = dt_cricao;
	}
	
}
