package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="tb_equipes")
@DynamicUpdate
public class Equipe implements Serializable{
	
	private static final long serialVersionUID = -8229318327124217434L;
	
	private Integer id;
	private String nome;
	private Diretoria diretoria;
	private Voluntario lider;
	private String objetivo;
	private String descricao;
	private Integer status;
	private Integer num_membros;
	private List<Voluntario> membros;
	
	public boolean ehMembro(Voluntario voluntario){
		return membros.stream().anyMatch(vol -> vol.getId().equals(voluntario.getId()));
	}
	
	public void retiraMembro(int volId){
		membros = membros.stream().filter(vol -> vol.getId().equals(volId)).collect(Collectors.toList());
	}
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "equipe_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@NotEmpty
	@Column(name = "nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@ManyToOne
    @JoinColumn(name = "diretoria", nullable = false)
	public Diretoria getDiretoria() {
		return diretoria;
	}
	public void setDiretoria(Diretoria diretoria) {
		this.diretoria = diretoria;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Voluntario getLider() {
		return lider;
	}
	public void setLider(Voluntario lider) {
		this.lider = lider;
	}
	
	@NotEmpty
	@Column(name = "objetivo")
	@Type(type="text")
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	@Column(name = "descricao")
	@Type(type="text")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	@Column(name = "num_membros")
	public Integer getNum_membros() {
		return num_membros;
	}
	public void setNum_membros(Integer num_membros) {
		this.num_membros = num_membros;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)  
	@JoinTable(name = "equipe_membro", joinColumns = {
			@JoinColumn(name = "equipe_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "user_id",
					nullable = false, updatable = false) })
	public List<Voluntario> getMembros() {
		return membros;
	}
	public void setMembros(List<Voluntario> membros) {
		this.membros = membros;
	}

}
