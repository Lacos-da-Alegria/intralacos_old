package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.MembroAtividadeDAO;
import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.FilaAtividade;

@Entity
@Table(name="tb_atividades")
@DynamicUpdate
public class Atividade extends DateHandler implements Serializable {
	
	private static final long serialVersionUID = -8227592328680154697L;
	
	private Integer id;
	private String nome;
	private String tag;
	private Integer status = 0;
	private String descricao;
	private String endereco;
	private String local_preparo;
	private String horario;
	private Integer dia;
	private Integer periodo;
	private Integer limite_voluntario;
	private Integer limite_apoio;
	private Integer limite_novato;
	private Integer tipo;
	private String imagem;
	private boolean chamada_liberada;
	private boolean inscricao_liberada;
	private List<MembroAtividade> membros = new ArrayList<>();
	private Diretoria diretoria;
	private List<RegistroAtividade> inscritos = new ArrayList<>();
	
	private static final String URL_S3 = "https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-318693850464/";
	
	@Transient
	public FilaAtividade getFila(){
		
		return new FilaAtividade(this);
	}
	
	@Transient
	public static List<Atividade> atividadeAtivas(){
		AtividadeDAO dao = new AtividadeDAO();
		return dao.listaAtividadesAtivas();
	}
	

	public Atividade atualizaInfo(Atividade ativ){
	
		this.nome = ativ.nome;
		this.endereco = ativ.endereco;
		this.local_preparo = ativ.local_preparo;
		this.horario = ativ.horario;
		this.limite_apoio = ativ.limite_apoio;
		this.limite_novato = ativ.limite_novato;
		this.limite_voluntario = ativ.limite_voluntario;
		this.periodo = ativ.periodo;
		this.dia = ativ.dia;
		this.descricao = ativ.descricao;
		
		return this;
	}
	
	public boolean podeIncrever(){
		return !chamada_liberada && inscricao_liberada;
	}
	
	public boolean ehNoMesmoHorario(Atividade atividade){
		return this.dia.equals(atividade.getDia()) && this.periodo.equals(atividade.getPeriodo());
	}
	
	public boolean ehAcao(){
		return tipo.equals(2);
	}
	
	public boolean ehMatutina(){
		return this.periodo.equals(1);
	}
	public boolean ehVespertina(){
		return this.periodo.equals(2);
	}
	public boolean ehNoturna(){
		return this.periodo.equals(3);
	}
	public boolean ehHoje(){
		return super.ehHoje(this.getDia());
	}
	
	public boolean atividadeLotada(){
		return !this.getFila().getLista_espera().isEmpty();
	}
	
	public String urlS3(){
		if(imagem == null)
			return "assets/img/padraoHosp.jpg";
		else
			return URL_S3 + imagem;
	}
	
	public void inicializaMembros(){
		
		MembroAtividadeDAO dao = new MembroAtividadeDAO();
		
		membros = dao.listaMembroAtividade(this);
		
	}

	public List<MembroAtividade> listaApoios(){
		if(membros == null)
			return null;
		else 
			return membros.stream().filter(m -> m.isApoio()).collect(Collectors.toList());
	}
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "ativ_id")
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
	
	@NotEmpty
	@Column(name = "tag")
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
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
	@Column(name = "descricao")
	@Type(type="text")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@NotEmpty
	@Column(name = "endereco")
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@NotEmpty
	@Column(name = "local_preparo")
	public String getLocal_preparo() {
		return local_preparo;
	}
	public void setLocal_preparo(String local_preparo) {
		this.local_preparo = local_preparo;
	}
	
	@NotEmpty
	@Column(name = "horario")
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	
	@NotNull
	@Column(name = "dia")
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	
	@NotNull
	@Column(name = "periodo")
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
	@NotNull
	@Column(name = "limite_voluntario")
	public Integer getLimite_voluntario() {
		return limite_voluntario;
	}
	public void setLimite_voluntario(Integer limite_voluntario) {
		this.limite_voluntario = limite_voluntario;
	}
	
	@NotNull
	@Column(name = "limite_apoio")
	public Integer getLimite_apoio() {
		return limite_apoio;
	}
	public void setLimite_apoio(Integer limite_apoio) {
		this.limite_apoio = limite_apoio;
	}
	
	@NotNull
	@Column(name = "limite_novato")
	public Integer getLimite_novato() {
		return limite_novato;
	}
	public void setLimite_novato(Integer limite_novato) {
		this.limite_novato = limite_novato;
	}
	
	@NotNull
	@Column(name = "tipo")
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	@NotNull
	@Type(type="true_false")
	@Column(name = "chamada_liberada")
	public boolean getChamada_liberada() {
		return chamada_liberada;
	}
	public void setChamada_liberada(boolean chamada_liberada) {
		this.chamada_liberada = chamada_liberada;
	}
	
	@NotNull
	@Type(type="true_false")
	@Column(name = "inscricao_liberada")
	public boolean isInscricao_liberada() {
		return inscricao_liberada;
	}

	public void setInscricao_liberada(boolean inscricao_liberada) {
		this.inscricao_liberada = inscricao_liberada;
	}

	@Column(name = "imagem")
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	@OneToMany(mappedBy="atividade", cascade=CascadeType.ALL)
	public List<MembroAtividade> getMembros() {
		return membros;
	}

	public void setMembros(List<MembroAtividade> membros) {
		this.membros = membros;
	}

	@ManyToOne
	@JoinColumn(name = "diretoria_id", nullable = false)
	public Diretoria getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(Diretoria diretoria) {
		this.diretoria = diretoria;
	}

	@OneToMany(mappedBy="atividade", cascade=CascadeType.ALL)
	public List<RegistroAtividade> getInscritos() {
		return inscritos;
	}

	public void setInscritos(List<RegistroAtividade> inscritos) {
		this.inscritos = inscritos;
	}
	
	/*----------------------------------------------------------------------------
	 * Fim dos Getters e Setters
	------------------------------------------------------------------------------*/
	
}