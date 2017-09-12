package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;

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

@Entity
@Table(name="tb_feedback_novatos")
@DynamicUpdate
public class FeedbackNovato implements Serializable{
	
	private static final long serialVersionUID = 8575424332550783633L;
	
	private Integer id;
	private Integer nota_1;
	private String comentario_1;
	private Integer nota_2;
	private String comentario_2;
	private Integer nota_3;
	private String comentario_3;
	private Integer nota_4;
	private String comentario_4;
	private Integer nota_5;
	private String comentario_5;
	private Integer nota_6;
	private String comentario_6;
	
	private String sugestao;	
	
	private Voluntario responsavel;
	private Atividade primeira_atividade;
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/

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
	@Column(name = "nota_1")
	public Integer getNota_1() {
		return nota_1;
	}
	public void setNota_1(Integer nota_1) {
		this.nota_1 = this.getPrimeiroDigito(nota_1);
	}

	@Column(name = "comentario_1")
	public String getComentario_1() {
		return comentario_1;
	}
	public void setComentario_1(String comentario_1) {
		this.comentario_1 = comentario_1;
	}
	
	@NotNull
	@Column(name = "nota_2")
	public Integer getNota_2() {
		return nota_2;
	}
	public void setNota_2(Integer nota_2) {
		this.nota_2 = this.getPrimeiroDigito(nota_2);;
	}
	
	@Column(name = "comentario_2")
	public String getComentario_2() {
		return comentario_2;
	}
	public void setComentario_2(String comentario_2) {
		this.comentario_2 = comentario_2;
	}
	
	@NotNull
	@Column(name = "nota_3")
	public Integer getNota_3() {
		return nota_3;
	}
	public void setNota_3(Integer nota_3) {
		this.nota_3 = this.getPrimeiroDigito(nota_3);;
	}
	
	@Column(name = "comentario_3")
	public String getComentario_3() {
		return comentario_3;
	}
	public void setComentario_3(String comentario_3) {
		this.comentario_3 = comentario_3;
	}
	
	@NotNull
	@Column(name = "nota_4")
	public Integer getNota_4() {
		return nota_4;
	}
	public void setNota_4(Integer nota_4) {
		this.nota_4 = this.getPrimeiroDigito(nota_4);;
	}
	
	@Column(name = "comentario_4")
	public String getComentario_4() {
		return comentario_4;
	}
	public void setComentario_4(String comentario_4) {
		this.comentario_4 = comentario_4;
	}
	
	@NotNull
	@Column(name = "nota_5")
	public Integer getNota_5() {
		return nota_5;
	}
	public void setNota_5(Integer nota_5) {
		this.nota_5 = this.getPrimeiroDigito(nota_5);;
	}
	
	@Column(name = "comentario_5")
	public String getComentario_5() {
		return comentario_5;
	}
	public void setComentario_5(String comentario_5) {
		this.comentario_5 = comentario_5;
	}
	
	@NotNull
	@Column(name = "nota_6")
	public Integer getNota_6() {
		return nota_6;
	}
	public void setNota_6(Integer nota_6) {
		this.nota_6 = this.getPrimeiroDigito(nota_6);
	}
	
	@Column(name = "comentario_6")
	public String getComentario_6() {
		return comentario_6;
	}
	public void setComentario_6(String comentario_6) {
		this.comentario_6 = comentario_6;
	}
	
	@Column(name = "sugestao")
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	
	@ManyToOne
    @JoinColumn(name = "responsavel", nullable = false)
	public Voluntario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Voluntario responsavel) {
		this.responsavel = responsavel;
	}
	
	@ManyToOne
    @JoinColumn(name = "primeira_atividade", nullable = false)
	public Atividade getPrimeira_atividade() {
		return primeira_atividade;
	}
	public void setPrimeira_atividade(Atividade primeira_atividade) {
		this.primeira_atividade = primeira_atividade;
	}
	
	/*----------------------------------------------------------------------------
	 * Fim dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	/*----------------------------------------------------------------------------
	 * Inicio Metodos Auxiliares
	------------------------------------------------------------------------------*/
	
	@Transient
	private int getPrimeiroDigito(Integer number) {    
		  return (int) ((number / Math.pow(10, 0)) % 10);
	}
	
	/*----------------------------------------------------------------------------
	 * Fim Metodos Auxiliares
	------------------------------------------------------------------------------*/
	
}
