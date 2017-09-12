package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;

@Entity
@Table(name="tb_polos")
@DynamicUpdate
public class Polo implements Serializable {
	
	private static final long serialVersionUID = 2689736252341154503L;
	private Integer id;
	private String nome;
	private List<RegiaoAdministrativa> rAs = new ArrayList<>();
	private List<Voluntario> membros = new ArrayList<>();
	private Integer status = 1;
	
	public void iniciaRas(List<Integer> ras){
		RegiaoAdministrativaDAO dao = new RegiaoAdministrativaDAO();
		for(Integer id : ras){
			RegiaoAdministrativa ra = dao.RegiaoAdministrativaPelaId(id);
			ra.setPolo(this);
			dao.updateRegiaoAdministrativa(ra);
		}
	}
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "polo_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "nome_polo")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="polo", cascade=CascadeType.ALL)
	public List<RegiaoAdministrativa> getrAs() {
		return rAs;
	}
	public void setrAs(List<RegiaoAdministrativa> rAs) {
		this.rAs = rAs;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="polo", cascade=CascadeType.ALL)
	public List<Voluntario> getMembros() {
		return membros;
	}

	public void setMembros(List<Voluntario> membros) {
		this.membros = membros;
	}
	
}
