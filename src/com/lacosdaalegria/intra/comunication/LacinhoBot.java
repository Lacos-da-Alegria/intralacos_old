package com.lacosdaalegria.intra.comunication;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.lacosdaalegria.intra.hibernate.model.TelegramUser;

public class LacinhoBot extends TelegramLongPollingBot {
	
	@Override
	public String getBotUsername() {
		//token de Produção
		return "LacinhoBot";
		//token to bot para teste
//		return "lacos_test_bot";
	}

	@Override
	public void onUpdateReceived(Update update) {
		   // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    	
	    	
	    	//TelegramUser user = new TelegramUser(update.getMessage().getChatId().toString(),
	    	//										update.getMessage().getText());
	    	
	    	//user.trataUsuario();
	    	
	    	//this.sendMensage(user);
	    	 
	    }		
	}

	@Override
	public String getBotToken() {
		//token de Produção
		return "378683860:AAFB5RQltcQv_iF2DQIU5dN2Hl3m_Js5s1o";
		//token to bot para teste
//		return "304988494:AAGkEbXwj3W4y34ZRmkyeYN5BtlMZypQWVA";
	}
	
	public void sendMensage(TelegramUser user){
		
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(user.getChat_id())
	                .setText(user.getResposta());
	        
	        try {
	            sendMessage(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	        
	}
	
	public void sendMensage(String chatId, String resposta){
		
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(chatId)
                .setText(resposta);
        
        try {
            sendMessage(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        
	}
	
	public void enviaMensagemFila(){
		
		/*FilaAtividade fila = null;	
		
		if(!registro.rodadaRandomica())
			fila = new FilaAtividade(registro.getAtividade().getId());
		
		UserDetail detail = new UserDetail();
		
		if(fila != null && fila.posicaoFila(detail.user(session).getUser().getId())==-1){
			Voluntario userZero = null;
			
			if(fila.getLista_espera().size() > 0)
				userZero = fila.getLista_espera().get(0);
			
			if(userZero != null && userZero.getTelegram_id() != null && fila.entrouNaFila(detail.user(session).getUser(), userZero)){
				
				LacinhoBot bot = new LacinhoBot();
				
				bot.sendMensage(userZero.getTelegram_id(), userZero.getPrimerio_nome() + "! "
						+ "Só passando para avisar que você acabou de entrar na lista do " + 
						registro.getAtividade().getTag() + "!! Caso não vá participar "
						+ "não se esqueça de Cancelar sua participação no IntraLaços");
			}
		}*/
		
	}
	
}
