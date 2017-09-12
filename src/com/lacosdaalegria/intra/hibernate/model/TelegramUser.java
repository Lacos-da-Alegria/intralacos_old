package com.lacosdaalegria.intra.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="tb_telegram_users")
@DynamicUpdate
public class TelegramUser {
	
	private Integer id;
	private String chat_id;
	private String text;
	private int status_telegram;
	private String resposta;
	private Voluntario voluntario;


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
	@Column(name = "chat_id")
	public String getChat_id() {
		return chat_id;
	}

	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}
	
	@Transient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	@NotNull
	@Column(name = "status")
	public int getStatus_telegram() {
		return status_telegram;
	}

	public void setStatus_telegram(int status_telegram) {
		this.status_telegram = status_telegram;
	}

	@Transient
	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	//IMplementar one to one
	@OneToOne
	@NotNull
	public Voluntario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}

}
