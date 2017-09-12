package com.lacosdaalegria.intra.hibernate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(name="tb_membros_atividades")
@DynamicUpdate
public class MembroAtividade {
	
	private Integer id;
	private Atividade atividade;
	private boolean apoio;
	private boolean coordenador;
	private boolean controle_novato;
	private Voluntario membro;
	
	@Transient
	public boolean ehMembroDaAtividade(int id){
		return possuiAtivdade() && atividade.getId().equals(id);
	}
	
	@Transient
	public boolean ehMembroDaAtividade(Atividade atividade){
		return possuiAtivdade() && this.atividade.getId().equals(atividade.getId());
	}
	

	public boolean ehCoordenadorAtividade(Atividade atividade){
		return coordenador && possuiAtivdade() && this.atividade.getId().equals(atividade.getId());
	}
	
	public boolean ehApoioAtividade(Atividade atividade){
		return apoio && possuiAtivdade() && this.atividade.getId().equals(atividade.getId());
	}

	public boolean ehControleNovatosAtividade(Atividade atividade){
		return controle_novato && possuiAtivdade() && this.atividade.getId().equals(atividade.getId());
	}
	
	public boolean possuiAtivdade(){
		return atividade != null;
	}
	
	
	/* ======================================================================
	 *        Metodos auxiliares para tratar com lista de membros
	 * ====================================================================== */
	
	@Transient
	private List<MembroAtividade> membros = new ArrayList<>();
	
	@Transient
	public void defineMembros(List<MembroAtividade> membros){
		this.membros = membros;
	}
	
	public List<MembroAtividade> coordenadores(){
		if(membros != null)
			return membros.stream().filter(mem -> mem.isCoordenador()).collect(Collectors.toList());
		else return null;
	}
	public List<MembroAtividade> apoios(){
		if(membros != null)
			return membros.stream().filter(mem -> mem.isApoio()).collect(Collectors.toList());
		else return null;
	}
	public List<MembroAtividade> controleNovatos(){
		if(membros != null)
			return membros.stream().filter(mem -> mem.isControle_novato()).collect(Collectors.toList());
		else return null;
	}
	
	
	/* ======================================================================
	 *      --------------------------FIM------------------------------------
	 * ====================================================================== */
	
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
	
	@ManyToOne
	@JoinColumn(name = "ativ_id")
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "membro")
	public Voluntario getMembro() {
		return membro;
	}
	public void setMembro(Voluntario membro) {
		this.membro = membro;
	}

	@NotNull
	@Type(type="true_false")
	@Column(name = "apoio")
	public boolean isApoio() {
		return apoio;
	}

	public void setApoio(boolean apoio) {
		this.apoio = apoio;
	}

	@NotNull
	@Type(type="true_false")
	@Column(name = "coordenador")
	public boolean isCoordenador() {
		return coordenador;
	}

	public void setCoordenador(boolean coordenador) {
		this.coordenador = coordenador;
	}

	@NotNull
	@Type(type="true_false")
	@Column(name = "controle_novato")
	public boolean isControle_novato() {
		return controle_novato;
	}

	public void setControle_novato(boolean controle_novato) {
		this.controle_novato = controle_novato;
	}
	
	/*
	 *  <c:if test="${voluntario.ehApoio()}">
                  
						<c:choose>
							<c:when test="${voluntario.ehApoioAtivo()}">
							
								<h3>Apoio Ativo</h3>
								
								   <div class="desc box2 centered">
                      				<div class="btn-group" >
									  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
									    Ações Possiveis <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" role="menu">
									    <li><a href="desativarApoio">Desativar Apoio</a></li>
									    <li><a >Deixar de Ser Apoio</a></li>
										
									  </ul>
									</div>						
			                      </div> 
											
							</c:when>
						<c:otherwise>								
								<h3>Apoio Desativado</h3>	
								
								 <div class="desc box2 centered">
                      				<div class="btn-group" >
									  <button type="button" class="btn btn-theme04 dropdown-toggle" data-toggle="dropdown">
									    Aes Possiveis <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" role="menu">
									    <li><a href="ativarApoio">Ativar Apoio</a></li>
									    <li><a >Deixar de Ser Apoio</a></li>

										
									  </ul>
									</div>						
			                      </div> 
																				
						</c:otherwise>								
						</c:choose>                      
                  
                  </c:if>
	 */
	
}
