package com.lacosdaalegria.intra.hibernate.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.lacosdaalegria.intra.hibernate.dao.DemandaDAO;
import com.lacosdaalegria.intra.hibernate.dao.EquipeDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegistroAtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.interceptor.Filtro;
import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.HashMapEstaticos;
import com.lacosdaalegria.intra.model.MaisLacos;

@Entity
@Table(name="tb_usuarios")
@DynamicUpdate
public class Voluntario implements Serializable {

	private static final long serialVersionUID = -6628583164045760574L;
	
	private Integer id;
	private Date dt_criacao = new Date();
	private String login;
	private String senha;
	@Transient
	private String dual_senha;
	private String email;
	private String cpf;
	private String nome;
	private String nome_doutor;
	private String dt_Nascimento;
	private String ddd;
	private String whatsapp;
	private RegiaoAdministrativa regiao;
	private String endereco;
	private Atividade preferencia;
	private String sexo;
	private String como_conheceu;
	private Integer status = 1;
	private String profile;
	private boolean quer_ongs;
	private boolean novato_ongs = true;
	private boolean novato = true;
	private boolean aceitou_termo;
	private Date data_aceite;
	private String observacao;
	private boolean admin = false;
	private String telegram_id = UUID.randomUUID().toString();
	private Integer feedbackNovato = 0;
	//Atividades que o usuário esta inscrito
	private List<RegistroAtividade> registro_ativs;
	//One to One
	private Diretoria diretoria;
	private Polo polo;
	private MembroAtividade membro_atividade;
	private Voluntario responsavel;
	private List<Equipe> equipes;
	private List<Demanda> demandas;
	private Atendimento atendimento;
	//São propriedades Transients
	private int cor;
	private String erro;	
	private MaisLacos maisLacos;
	@Transient
	private RegistroAtividade registro_atual;
	
	private static final String URL_S3 = "https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-318693850464/";
	
	public Voluntario(){
		super();
	}
	
	public Voluntario defineRegistroAtual(RegistroAtividade registro){
		this.registro_atual = registro;
		return this;
	}
	
	public RegistroAtividade registroAtual(){
		return registro_atual;
	}
	
	public boolean registroAberto(){
			return this.registro_atual.getStatus() == 0;
	}
	
	public Voluntario(boolean registroAtividade){
		super();
		if(registroAtividade)
			this.atividadeInscritas();
	}
	@Transient
	public MaisLacos getMaisLacos(){
		return maisLacos;
	}
	
	public void iniciaMaisLacos(){
		
		//Implementa inicialização de mais laços
		
	}
	
	public void atividadeInscritas(){
		
		RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
		registro_ativs = regDao.listaRegistroAtividade(this, Semana.getInstance());
		
	}
	
	public RegistroAtividade registroAtividade(Atividade atividade){
			return registro_ativs.stream().filter(reg -> reg.getAtividade().getId().equals(atividade.getId())).findFirst().get();
	}
	
	public boolean estaInscrito(Atividade atividade){
		if(registro_ativs == null)
			return false;
		else
			return registro_ativs.stream().filter(reg -> reg.getAtividade().getId().equals(atividade.getId())).findFirst().isPresent();
	}
	
	public int posicaoFila(Atividade atividade){
		
		return atividade.getFila().posicaoFila(this.id);
	}
	
	public boolean podeSeInscrever(Atividade atividade){
		
		if(registro_ativs == null)
			return true;
		else
			return !registro_ativs.stream().anyMatch(reg -> (reg.getAtividade().getId().equals(atividade.getId())) || (!reg.getAtividade().getId().equals(atividade.getId()) && 
														   reg.getAtividade().ehNoMesmoHorario(atividade)));
	}
	
	public boolean estaDesativado(){
		return this.status.equals(2);
	}
	
	public void atualizaInformacao(Voluntario voluntario){
		this.setNome(voluntario.getNome());
		this.setNome_doutor(voluntario.getNome_doutor());
		this.setDt_Nascimento(voluntario.getDt_Nascimento());
		this.setWhatsapp(voluntario.getWhatsapp());
		this.setEndereco(voluntario.getEndereco());
		this.setRegiao(voluntario.getRegiao());
	}
	
	public void promoverNovato(){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		this.novato = false;
		this.observacao = dtf.format(now);
		this.feedbackNovato = 1;
	}
	
	public void novatoFaltou(){
		this.status = 2;
		this.observacao = "Novato Não Foi na Atividade Confirmada";
	}
	
	public boolean lideraEquipe(int equipeId){
		return this.equipes.stream().anyMatch(eq -> eq.getId() == equipeId && eq.getLider()!= null && eq.getLider().getId().equals(id));
	}
	
	public boolean pertenceEquipe(int equipeId){
		return this.equipes.stream().anyMatch(eq -> eq.getId().equals(equipeId));
	}
	
	@Transient
	public String getErro() {
		return erro;
	}
	public void setErro(String erro) {
		this.erro = erro;
	}
	
	@Transient
	public String getDual_senha() {
		return dual_senha;
	}
	public void setDual_senha(String dual_senha) {
		this.dual_senha = dual_senha;
	}
	
	@Transient
	public int getCor() {
		return cor;
	}
	public void setCor(int cor) {
		this.cor = cor;
	}
	
	@Transient
	public String getPrimerio_nome() {
		return nome.substring(0, nome.indexOf(" "));
	}
	
	@Transient
	public String getHexCor(){
		return HashMapEstaticos.cores_responsaveis.get(this.cor);
	}
	
	public boolean ehAtendente(Atendimento atendimento){
		if(this.atendimento != null && this.atendimento.getId().equals(atendimento.getId()))
			return true;
		else
			return false;
	}
	
	public boolean realizarFeedBack() {
		return feedbackNovato == 1;
	}
	
	public void hashSenhas(){
		senha = (senha == null ? null : hashPassword(senha));
		dual_senha = (dual_senha == null ? null :  hashPassword(dual_senha));
	}
	
	public void iniciaDemandas(){
		if(demandas == null){
			DemandaDAO dao = new DemandaDAO();
			demandas = dao.demandasVoluntario(this);
		}
	}
	
	public List<Demanda> minhasDemandas(){
		if(demandas == null){
			DemandaDAO dao = new DemandaDAO();
			demandas = dao.demandasVoluntario(this);
		}
		
		return demandas;
	}
	
	public String urlS3(){
		if(profile == null)
			return "assets/img/ui-sam.jpg";
		else
			return URL_S3 + profile;
	}
	
	public void atualizaSenha(Voluntario vol){
		this.senha = vol.getSenha();
		this.dual_senha = vol.getDual_senha();
		
		hashSenhas();
	}
	
	public void aceitaTermo(){
		aceitou_termo = true;
		data_aceite = new Date();
	}
	
	/*----------------------------------------------------------------------------
	 * Inicio dos Getters e Setters
	------------------------------------------------------------------------------*/
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "user_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao")
	public Date getDt_criacao() {
		return dt_criacao;
	}
	public void setDt_criacao(Date dt_criacao) {
		this.dt_criacao = dt_criacao;
	}

	@Column(name = "observacao")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@ManyToOne
    @JoinColumn(name = "regiao", nullable = false)
	public RegiaoAdministrativa getRegiao() {
		return regiao;
	}
	public void setRegiao(RegiaoAdministrativa regiao) {
		this.regiao = regiao;
	}
	
	@Column(name = "admin")
	@Type(type="true_false")
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@NotEmpty
	@Column(name = "login", updatable = false)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = (login != null ?  login.toLowerCase() : null);
	}
	
	@NotEmpty
	@Column(name = "senha")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@NotEmpty
	@Column(name = "email", updatable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = (email != null ?  email.toLowerCase() : null);
	}
	
	@NotEmpty
	@Column(name = "cpf")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf.replace("-", "").replace(".", "");
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
	@Column(name = "dt_de_nascimento")
	public String getDt_Nascimento() {
		return dt_Nascimento;
	}
	public void setDt_Nascimento(String dt_Nascimento) {
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			formatter.parse(dt_Nascimento);
			
			this.dt_Nascimento = dt_Nascimento;
			
		} catch (ParseException e) {
			this.dt_Nascimento = "Erro";
		}
	}
	
	@NotEmpty
	@Column(name = "contato")
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		if(whatsapp != null) {
			whatsapp = whatsapp.replace("-", "").replace(" ", "");
			this.whatsapp = (whatsapp.length() >= 8 ? "9" + whatsapp.substring(whatsapp.length()-8) : "Erro");
		} else 
			this.whatsapp = whatsapp;
	}
	
	@Column(name = "endereco")
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@ManyToOne
    @JoinColumn(name = "preferencia", nullable = false)
	public Atividade getPreferencia() {
		return preferencia;
	}
	public void setPreferencia(Atividade preferencia) {
		this.preferencia = preferencia;
	}
	
	@NotEmpty
	@Column(name = "sexo")
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	@Column(name = "como_conheceu")
	public String getComo_conheceu() {
		return como_conheceu;
	}
	public void setComo_conheceu(String como_conheceu) {
		this.como_conheceu = como_conheceu;
	}
	
	@NotNull
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "nome_doutor")
	public String getNome_doutor() {
		return nome_doutor;
	}
	public void setNome_doutor(String nome_doutor) {
		this.nome_doutor = nome_doutor;
	}
	
	@ManyToOne
    @JoinColumn(name = "responsavel")
	public Voluntario getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Voluntario responsavel) {
		this.responsavel = responsavel;
	}
	
	@Column(name = "profile")
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	@NotNull
	@Column(name = "feedback_novato")
	public Integer getFeedbackNovato() {
		return feedbackNovato;
	}
	public void setFeedbackNovato(Integer feedbackNovato) {
		this.feedbackNovato = feedbackNovato;
	}
	
	@Transient
	public List<Demanda> getDemandas() {
		return demandas;
	}
	public void setDemandas(List<Demanda> demandas) {
		this.demandas = demandas;
	}
	
	@NotEmpty
	@Column(name = "telegram_id")
	public String getTelegram_id() {
		return telegram_id;
	}
	public void setTelegram_id(String telegram_id) {
		this.telegram_id = telegram_id;
	}
	
	@NotEmpty
	@Column(name = "ddd")
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		if(ddd != null){
			ddd = ddd.replace("(", "").replace(" ", "").replace(")", "");
			this.ddd = (ddd.length() >= 2 ? ddd.substring(ddd.length() - 2) : ddd);
		} else 
			this.ddd = ddd;
	}
	
	@Column(name = "quer_ongs")
	@Type(type="true_false")
	public boolean isQuer_ongs() {
		return quer_ongs;
	}
	public void setQuer_ongs(boolean quer_ongs) {
		this.quer_ongs = quer_ongs;
	}
	
	@Column(name = "novato_ongs")
	@Type(type="true_false")
	public boolean isNovato_ongs() {
		return novato_ongs;
	}
	public void setNovato_ongs(boolean novato_ongs) {
		this.novato_ongs = novato_ongs;
	}
	
	@Column(name = "novato")
	@Type(type="true_false")
	public boolean isNovato() {
		return novato;
	}
	public void setNovato(boolean novato_hosp) {
		this.novato = novato_hosp;
	}
	
	@Column(name = "aceitou_termo")
	@Type(type="true_false")
	public boolean isAceitou_termo() {
		return aceitou_termo;
	}
	public void setAceitou_termo(boolean aceitou_termo) {
		this.aceitou_termo = aceitou_termo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_aceite")
	public Date getData_aceite() {
		return data_aceite;
	}

	public void setData_aceite(Date data_aceite) {
		this.data_aceite = data_aceite;
	}

	@OneToMany(mappedBy="voluntario")
	public List<RegistroAtividade> getRegistro_ativs() {
		return registro_ativs;
	}
	public void setRegistro_ativs(List<RegistroAtividade> registro_ativs) {
		this.registro_ativs = registro_ativs;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diretoria")
	public Diretoria getDiretoria() {
		return diretoria;
	}
	public void setDiretoria(Diretoria diretoria) {
		this.diretoria = diretoria;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "polo")
	public Polo getPolo() {
		return polo;
	}
	public void setPolo(Polo polo) {
		this.polo = polo;
	}

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "membro", cascade = CascadeType.ALL)
	public MembroAtividade getMembro_atividade() {
		return membro_atividade;
	}
	public void setMembro_atividade(MembroAtividade membro_atividade) {
		this.membro_atividade = membro_atividade;
	}
	
	@Transient
	public List<Equipe> getEquipes() {
		
		if(equipes==null){
			EquipeDAO dao = new EquipeDAO();
			equipes = dao.equipeVoluntario(this);
		}
		
		return equipes;
	}
	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "atendimento")
	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
	/*----------------------------------------------------------------------------
	 * Fim dos Getters e Setters
	------------------------------------------------------------------------------*/

	/*----------------------------------------------------------------------------
	 * Inicio de Logica de Validação da Informações
	------------------------------------------------------------------------------*/
	
	public boolean validaCadastroJaExiste(){
		VoluntarioDAO dao = new VoluntarioDAO();
		Voluntario duplicado = dao.voluntarioDuplicado(this);
		
		if(duplicado == null){
			return true;
		} else {
			if(this.login.equals(duplicado.getLogin())){
				erro = "Esse Login já se encontra cadastrado!";
				return false;
			}
			if(this.email.equals(duplicado.getEmail())){
				erro = "Esse E-mail já se encontra cadastrado!";
				return false;
			}
			if(this.cpf.equals(duplicado.getCpf())){
				erro = "Esse CPF já se encontra cadastrado!";
				return false;
			}
			if(this.whatsapp.equals(duplicado.getWhatsapp())){
				erro = "Esse WhatsApp já se encontra cadastrado!";
				return false;
			}
			return true;
		}
	}
	
	public boolean validaValorAtualizadoJaExiste(){
		VoluntarioDAO dao = new VoluntarioDAO();
		Voluntario duplicado = dao.voluntarioDuplicado(this);
		
		if(duplicado == null){
			return true;
		} else {
			if(this.cpf.equals(duplicado.getCpf()) && !this.id.equals(duplicado.getId())){
				erro = "Esse CPF já se encontra cadastrado!";
				return false;
			}
			if(this.whatsapp.equals(duplicado.getWhatsapp()) && !this.id.equals(duplicado.getId())){
				erro = "Esse WhatsApp já se encontra cadastrado!";
				return false;
			}
			return true;
		}
	}
	public boolean validaLogin(){
		if(login == null || login.contains(" ") || login.contains("@") || login.contains(",") || login.contains(";")){
			erro = "Login inválido! Não utilizar espaços ou caracteres especiais como @ , / ;";
			return false;
		}
		return true;
	}
	public boolean validaEmail(){
		if(email == null || email.contains(" ") || !email.contains("@") || email.contains(";")){
			erro = "Email fornecido é inválido!";
			return false;
		}
		return true;
	}
	public boolean validaSenha(){
		if(senha ==  null || dual_senha == null || this.senha.length() < 6 || !senha.equals(dual_senha)){
			erro = "Senha inválida, favor fornecer senha nos padrões solicitados!";
			return false;
		}
		return true;
	}
	public boolean validaTelefone(){
		if(whatsapp == null || ddd == null || ddd.length() < 2 ){
			erro = "DDD ou Whatsapp inválidos! Somente utilize números!";
			return false;
		}
		
		try {
			Integer.parseInt(whatsapp);
			Integer.parseInt(ddd);
		} catch (NumberFormatException e) {
			erro = "DDD ou Whatsapp inválidos! Somente utilize números!";
					return false;
		}
		return true;
	}
	public boolean validaDtNascimento(){
		if(dt_Nascimento.equals("Erro") || dt_Nascimento == null){
			erro = "Data de Nascimento inválida, favor usar formato exemplo 12/03/1992";
			return false;
		}
		return true;
	}
	
	public boolean validaCpf(){

		int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		   
		if ((cpf == null) || (cpf.length()!=11)) {
			this.erro = "CPF inválido";
			return false;
		}
		
		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		if(cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString()))
			return true;
		else{
			this.erro = "CPF inválido";
			return false;
		}
	}
	
	private int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	
	public boolean validaCadastro(){
		
		if(!validaLogin() || !validaEmail() || !validaCpf() || 
				!validaSenha() || !validaDtNascimento() || !validaNaoEntradaNula() ||
				!validaTelefone() || !validaCadastroJaExiste())
			return false;
		
		return true;
	}
	
	public boolean validaAtualizacao(){
		
		if(!validaTelefone() || !validaCpf() || !validaDtNascimento() || !validaValorAtualizadoJaExiste())
			return false;
		
		if(regiao == null){
			erro = "Erro inválida favor selecionar região do menu!";
			return false;
		}
		
		return true;
	}
	
	private boolean validaNaoEntradaNula(){
		if(regiao == null){
			this.erro = "Favor selecionar uma região!";
			return false;
		}
		if(preferencia == null){
			this.erro = "Favor selecionar uma preferência!";
			return false;
		}
		return true;
	}
	
/*	public boolean nulidadeNaDuplicidade(){
		if(login==null&&email==null&&cpf==null&&whatsapp==null)
			return true;
		else
			return false;
	}
	
	public boolean nulidadeNaDuplicidadeAtualizacao(){
		if(cpf==null&&whatsapp==null)
			return true;
		else
			return false;
	}*/
	
	/*----------------------------------------------------------------------------
	 * Fim de Logica de Validação da Informações
	------------------------------------------------------------------------------*/
	
	/*----------------------------------------------------------------------------
	 * Logicas Adicionais
	------------------------------------------------------------------------------*/
	
	@Transient
	public int getIdade(){
		
		LocalDate now = LocalDate.now();
			
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate aniversario = LocalDate.parse(dt_Nascimento, formato);
	
		return Period.between(aniversario, now).getYears();
	}
	
	@Transient
	public boolean isAniversariante() throws ParseException{
		
		if(dt_Nascimento.equals("Erro"))
			return false;
		
		DateHandler local = new DateHandler();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(dt_Nascimento);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, 2016);
		
		return cal.get(Calendar.WEEK_OF_YEAR) == local.numeroSemana() || cal.get(Calendar.WEEK_OF_YEAR) == local.numeroSemana()+1;
	}
	
	public String hashPassword(String passWordToHash){
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passWordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return  sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
		
		return null;
	}
	
	@Transient
	public String getTelefone(){
		return "("+this.ddd+") " + this.getWhatsapp(); 
	}
	
	public boolean temDemanda() {
		return minhasDemandas() != null;
	}
	
//	public void trataAcesso(){
//		
//		if(this.getAcesso() != 0){
//			
//			OrgEquipeDAO dao =  new OrgEquipeDAO();
//			this.setEquipe_membro(dao.qualEquipeMebro(this.getId()));
//			dao.close();
//		}
//	}
	
	
	public String conjugaGenero(){
		if(this.sexo.equals("masculino"))
			return "o";
		else
			return "a";
		
	}
	
	public String sexoDiretor(){
		if(this.sexo.equals("masculino"))
			return "Diretor";
		else
			return "Diretora";
	}
	
//	public String cargoDiretoria(){
//		
//		if(super.getAcesso() != 42)
//			return "Não é Diretor";
//		
//		String nome = this.sexoDiretor();
//		
//		if(this.getAtiv_membro_id()==1){
//			return nome + " Executiv" + (this.sexo.equals("masculino") ? "o" : "a");
//		} else {
//			if(this.getAtiv_membro_id()==2){
//				return nome + " de Comunicação";
//			} else {
//				if(this.getAtiv_membro_id()==3){
//					return nome + " de ONG's";
//				} else {
//					return nome + " de Hospitais";
//				}
//			}
//		}
//	}
	
	/*----------------------------------------------------------------------------
	 * Inicio Logica de Acesso e Renderização
	------------------------------------------------------------------------------*/
	
	public String hrefVoluntario(){
		return novato ? "area-novato" : "area-voluntario";
	}
	
	public String designacao(){
		return novato ? "Novato" : "Voluntário";
	}
	
	public boolean acessoCoordenador(){
		if (membro_atividade == null)
			return false;
		else
			return this.membro_atividade.isCoordenador();
	}
	
	public boolean ehCoordenador(Atividade atividade){
		if (membro_atividade == null)
			return false;
		else
			return this.membro_atividade.ehCoordenadorAtividade(atividade);
	}
	
	public boolean ehApoio(Atividade atividade){
		if (membro_atividade == null)
			return false;
		else
			return this.membro_atividade.ehApoioAtividade(atividade);
	}
	
	public boolean ehApoio(){
		if (membro_atividade == null)
			return false;
		else
			return this.membro_atividade.isApoio();
	}
	
	public boolean ehApoioAtivo(){
		if (membro_atividade == null)
			return false;
		else
			return this.membro_atividade.isApoio();
	}
	
	
	public boolean acessoControleNovatos(){
		if (membro_atividade == null)
			return false;
		else
			return membro_atividade.isCoordenador() || membro_atividade.isControle_novato();
	}
	public boolean acessoDiretorHosp(){
		if (diretoria == null)
			return false;
		else
			return diretoria.ehHospitais() || diretoria.ehExecutivo();
	}
	
	public boolean acessoPolo(){
		return polo != null;
	}
	
	public boolean acessoExecutivo(){
		if (diretoria == null)
			return false;
		else
			return diretoria.ehExecutivo();
	}
	
	public boolean pertenceEquipe(){
		return equipes != null;
	}
	
	public boolean acessoDiretorOngs(){
		if (diretoria == null)
			return false;
		else
			return diretoria.ehOngs() || diretoria.ehExecutivo();
	}
	
	/*----------------------------------------------------------------------------
	 * Inicio de Logica de Restrição de acesso
	------------------------------------------------------------------------------*/
	
	public boolean podeAcessarRequest(String uri, int equipeId, int ativId){
		
		if(equipeId == 0 && ativId == 0){
			return temAcesso(uri, Filtro.listaAcesso(this, 0));
		} else {
			if(equipeId != 0){
				return validaEquipe(equipeId, uri);
			} else {
				return validaAtividade(ativId, uri);
			}
		}
	}
	
	private boolean validaAtividade(int ativId, String uri){
		
		if(membro_atividade != null){
			 if(membro_atividade.ehMembroDaAtividade(ativId)){
				 return temAcesso(uri, Filtro.listaAcesso(this, 0)); 
			 }else {
				 return false;
			 }
		} else {
			return false;
		} 
	}
	
	private boolean validaEquipe(int equipeId, String uri){
		
		if(pertenceEquipe()){
			 if(pertenceEquipe(equipeId)){
				 return temAcesso(uri, Filtro.listaAcesso(this, equipeId)); 
			 }else {
				 return false;
			 }
		} else {
			return false;
		} 
	}
	
	private boolean temAcesso(String uri, List<String> filtro){
		
		for(String request : filtro){
			
			if(uri.endsWith(request)){
				return true;
			}
		}
		
		return false;
	}
	
	
	
}
