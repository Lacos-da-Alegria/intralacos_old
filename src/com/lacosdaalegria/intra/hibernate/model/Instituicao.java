package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;
import com.lacosdaalegria.intra.hibernate.dao.TagDAO;

@Entity
@Table(name="tb_institucoes")
@DynamicUpdate
public class Instituicao implements Serializable {
	
	private static final long serialVersionUID = 3226717357113908470L;
	
	private Integer id;
	private String nome;
	private Integer status = 1;
	private String telefone;
	private String email;
	private String responsavel;
	private String contato_resp;
	private String email_resp;
	private String descricao_insti;
	private String descricao_ativ;
	private List<Tag> tags = new ArrayList<>();
	private boolean ja_fomos;
	private String descricao_p_criticos;
	private RegiaoAdministrativa ra;
	private String imagem;
	
	public String jaFomos(){
		return (ja_fomos ? "Sim" : "Não");
	}
	public int jaFomosInt(){
		return (ja_fomos ? 1 : 0);
	}
	
	public List<Tag> caracteristicas(){
		return tags.stream().filter(tag -> tag.getTipo().equals(1)).
				collect(Collectors.toList());
	}
	
	public List<Tag> atividades(){
		return tags.stream().filter(tag -> tag.getTipo().equals(2)).
				collect(Collectors.toList());
	}
	
	public List<Tag> pontosCriticos(){
		return tags.stream().filter(tag -> tag.getTipo().equals(1)).
				collect(Collectors.toList());
	}
	
	public void iniciaTags(List<String> tags){
		TagDAO dao = new TagDAO();
		for(String id : tags){
			this.tags.add(dao.TagPelaId(Integer.parseInt(id)));
		}
	}
	
	@Transient
	public void setRa(int raId) {
		
		RegiaoAdministrativaDAO dao = new RegiaoAdministrativaDAO();
		
		this.ra = dao.RegiaoAdministrativaPelaId(raId);
	}
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "inst_id")
	public Integer getId() {
		return id;
	}
	
	@NotEmpty
	@Column(name = "instituicao", length = 60)
	public String getNome() {
		return nome;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	
	@Column(name = "telefone", length = 30)
	public String getTelefone() {
		return telefone;
	}
	
	@Column(name = "email", length = 30)
	public String getEmail() {
		return email;
	}
	
	@Column(name = "responsavel", length = 30)
	public String getResponsavel() {
		return responsavel;
	}
	
	@Column(name = "contato_resp", length = 30)
	public String getContato_resp() {
		return contato_resp;
	}
	
	@Column(name = "email_resp", length = 30)
	public String getEmail_resp() {
		return email_resp;
	}
	
	@Column(name = "descricao")
	@Type(type="text")
	public String getDescricao_insti() {
		return descricao_insti;
	}
	
	@Column(name = "atividade")
	@Type(type="text")
	public String getDescricao_ativ() {
		return descricao_ativ;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)  
	@JoinTable(name = "instituicao_tag", joinColumns = {
			@JoinColumn(name = "inst_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "tag_id",
					nullable = false, updatable = false) })
	public List<Tag> getTags() {
		return tags;
	}
	
	@Column(name = "ja_fomos")
	@Type(type="true_false")
	public boolean isJa_fomos() {
		return ja_fomos;
	}
	
	@Column(name = "pontos_criticos")
	@Type(type="text")
	public String getDescricao_p_criticos() {
		return descricao_p_criticos;
	}
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "ra_id")
	public RegiaoAdministrativa getRa() {
		return ra;
	}
	
	@Column(name = "imagem")
	public String getImagem() {
		return imagem;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public void setContato_resp(String contato_resp) {
		this.contato_resp = contato_resp;
	}
	public void setEmail_resp(String email_resp) {
		this.email_resp = email_resp;
	}
	public void setDescricao_insti(String descricao_insti) {
		this.descricao_insti = descricao_insti;
	}
	public void setDescricao_ativ(String descricao_ativ) {
		this.descricao_ativ = descricao_ativ;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public void setJa_fomos(boolean ja_fomos) {
		this.ja_fomos = ja_fomos;
	}
	public void setDescricao_p_criticos(String descricao_p_criticos) {
		this.descricao_p_criticos = descricao_p_criticos;
	}
	public void setRa(RegiaoAdministrativa ra) {
		this.ra = ra;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	
}
