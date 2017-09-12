/*package com.lacosdaalegria.intra.comunication.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.lacosdaalegria.intra.comunication.dao.TelegramUserDAO;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;

@Entity
@Table(name="tb_telegram_users")
@DynamicUpdate
public class TelegramUser {
	
	private Integer id;
	private String chat_id;
	private String user_validator;
	private String text;
	private int status_telegram;
	private String resposta;
	private Voluntario voluntario;
	
	public TelegramUser(String chat_id, String text) {
		super();
		this.chat_id = chat_id;
		this.text = text;
	}
	
	public void trataUsuario(){
		
		TelegramUserDAO dao = new TelegramUserDAO();
		
		dao.existeRegistro(this);
		
		switch(this.status_telegram) {
		
			case 0:
				dao.insereUsuario(this);
				this.resposta =
						"Oiie, você precisa se autenticar. Entra no IntraLaços e pega "
						+ "seu código de ativação, depois é so me enviar-lo! Que ai te mandarei "
						+ "todas as suas notificações e informação desse nosso grupo maravilhoso!";
				break;
			case 1:
				this.validaUsuario(dao);
				break;
			case 2:
				this.validaDesativacao(dao);				
				break;
			case 3:
				this.usuarioDesativado(dao);
				break;
			default:
				this.text = "Erro! Favor enviar nova mensagem :p";
			break;
		}
		
		dao.close();
		
	}
	
	private void validaDesativacao(TelegramUserDAO dao){
		
		
		if(this.text != null && this.text.toLowerCase().equals("/stop")){
			this.status_telegram = 3;
			dao.atualizaStatus(this);
			this.resposta =
	    			"Entendo, você precisa de um espaço! Não sou eu né... (clichê) rsrs, brincadeira, vou sentir saudades!! Caso mude "
	    			+ "de ideia e queira conversar de novo, so falar /volta que eu volto!";
		} else {
			this.resposta =
	    		"Lind" + voluntario.conjugaGenero() + ", então... Isso é um pouco constrangedor, mas eu somente ativo nossa conexão e pronto rsrs e no nosso caso, "
	    				+ "já estamos conectados!! Agora caso queira me abandonar só enviar /stop que te dou o espaço que você deseja!";
		}
			
		
	}
	
	private void validaUsuario(TelegramUserDAO dao){
		
		dao.validaUsuario(this);
		
		if(voluntario.getId() != 0){
			dao.relacionaUsuario(this);
			this.resposta =
					voluntario.getPrimerio_nome() + ", a ativação foi realizada com sucesso, "
							+ "a partir de agora pode relaxar, eu vou te avisar te tudo que estiver acontecendo no Laços e no Intralaços! "
							+ "Caso queria parar de receber notificações só precisa me enviar /stop que vou sair do seu pé rsrs";
		} else {
			this.resposta =
    			"Opa!! Código de ativação inválido. Vamos olhar no IntraLaços e verifica o bixinho. Ai tentamos novamente, que vai :p";
			}
			
		
	}
	
	private void usuarioDesativado(TelegramUserDAO dao){
		
		
		if(this.text != null && this.text.toLowerCase().equals("/volta")){
			this.status_telegram = 2;
			dao.atualizaStatus(this);
			this.resposta =
	    			"Ai meu coração! Como é bom ter você de volta, pode deixar que dessa vez não vou te decepcionar! Ai que animação, vou te enviar"
	    			+ " todas as suas notificações. Assim, agora caso queira dar um tempo de novo, assim hipoteticamente falando."
	    			+ " É só falar /stop mas assim, hipoteticamente falando claro!";
		} else {
			this.resposta =
					"Lind" + voluntario.conjugaGenero() + ", assim... A gente não está se falando entende! Agora se quiser voltar, é só falar /volta que eu volto"
							+ " correndo!";
		}
			
		
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getChat_id() {
		return chat_id;
	}

	public void setChat_id(String chat_id) {
		this.chat_id = chat_id;
	}

	public String getUser_validator() {
		return user_validator;
	}

	public void setUser_validator(String user_validator) {
		this.user_validator = user_validator;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStatus_telegram() {
		return status_telegram;
	}

	public void setStatus_telegram(int status_telegram) {
		this.status_telegram = status_telegram;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Voluntario getVoluntario() {
		return voluntario;
	}

	public void setVoluntario(Voluntario voluntario) {
		this.voluntario = voluntario;
	}

}
*/