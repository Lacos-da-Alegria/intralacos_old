package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import com.lacosdaalegria.intra.hibernate.dao.DemandaDAO;


@Entity
@Table(name="tb_demandas")
@DynamicUpdate
public class Demanda implements Serializable{

	private static final long serialVersionUID = -6897486119888685720L;
	
	private Integer id;
	private Date dt_criacao = new Date();
	private Voluntario criador;
	private Voluntario responsavel;
	private Integer dias_para_atendimento;
	private Integer status;
	private String titulo;
	private String descricao;
	private Equipe equipe;
	private List<Nota> notas = new ArrayList<Nota>();
	
	@Transient
	private List<Demanda> demandas = new ArrayList<>();
	
	public Demanda(){
		super();
	}
	
	public Demanda(int demandaId){
		super();
		DemandaDAO dao = new DemandaDAO();
		this.id = demandaId;
		dao.DemandaPelaId(this);
	}
	
	public List<Demanda> demandasPorStatus(int status){
		return demandas.stream().filter(dem -> dem.getStatus().equals(status)).collect(Collectors.toList());
	}
	
	public void salvaDemandas(List<Demanda> demandas){
		this.demandas = demandas;
	}
	
	
	public boolean semResponsavel(){
		return this.responsavel == null;
	}
	
//	public HashMap<Integer, List<Demanda>> mapeaDemandas(List<Demanda> todasDemandas){
//		
//		HashMap<Integer, List<Demanda>> mapeamento = new HashMap<Integer, List<Demanda>>();
//		
//		for(int i = 0; i < 4; i++){
//			mapeamento.put(i, new ArrayList<Demanda>());
//		}
//		
//		for (Demanda demanda : todasDemandas){
//			mapeamento.get(demanda.getStatus()).add(demanda);			
//		}
//		
//		return mapeamento;
//	}
//	
//	//Relaciona toda demanda a um membro da equipe
//	public List<Demanda> relacionaMembros(List<Voluntario> membros, List<Demanda> demandas){
//		
//		for(Demanda demanda : demandas){
//			percorreMembros:
//			for(Voluntario membro : membros){
//				if(demanda.getResponsavel().getId() == membro.getId()){
//					demanda.setResponsavel(membro);
//					break percorreMembros;
//				}
//			}
//		}
//		
//		return demandas;
//		
//	}
//	
//	//Relaciona toda demanda a um membro da equipe
//	public List<Demanda> relacionaNotas(List<Nota> notas, List<Demanda> demandas){
//		
//		for(Nota nota : notas){
//			percorreDemandas:
//			for(Demanda demanda : demandas){	
//				if(demanda.getId() == nota.getId()){
//					demanda.getNotas().add(nota);
//					break percorreDemandas;
//				}
//			}
//		}
//		
//		return demandas;
//		
//	}
	@Transient
	public boolean isFazer(){
		return this.status.equals(0);
	}
	@Transient
	public boolean isFazendo(){
		return this.status.equals(1);
	}
	@Transient
	public boolean isPendente(){
		return this.status.equals(2);
	}
	@Transient
	public boolean isConcluida(){
		return this.status.equals(3);
	}
	@Transient
	public boolean isHistorica(){
		return this.status.equals(4);
	}
	@Transient
	public boolean isResponsavel(Voluntario voluntario){
		
		if(this.responsavel == null)
			return false;
		
		return this.getResponsavel().getId().equals(voluntario.getId());
	}
	
	public String criticidade(int dias_ate_fim_prazo){
		
		if(dias_ate_fim_prazo >= 10){
			return "success";
		} else {
			if(dias_ate_fim_prazo >= 5){
				return "warning";
			} else {
				return "danger";
			}
		}
		
	}
	
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "demanda_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name = "criador", nullable = false)
	public Voluntario getCriador() {
		return criador;
	}
	public void setCriador(Voluntario criador) {
		this.criador = criador;
	}
	
	@ManyToOne
    @JoinColumn(name = "responsavel", nullable = false)
	public Voluntario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Voluntario responsavel) {
		this.responsavel = responsavel;
	}
	
	@NotNull
	@Column(name = "dias_para_atendimento")
	public Integer getDias_para_atendimento() {
		return dias_para_atendimento;
	}
	public void setDias_para_atendimento(Integer dias_para_atendimento) {
		this.dias_para_atendimento = dias_para_atendimento;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@NotEmpty
	@Column(name = "titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@NotEmpty
	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToOne
    @JoinColumn(name = "equipe", nullable = false)
	public Equipe getEquipe() {
		return equipe;
	}
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@NotNull
	@Column(name = "dt_criacao")
	public Date getDt_criacao() {
		return dt_criacao;
	}

	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}
	
	@OneToMany(mappedBy="demanda", cascade=CascadeType.ALL)
	public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	
	
	/*----------------------------------------------------------------------------
	 * Fim dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	
}
