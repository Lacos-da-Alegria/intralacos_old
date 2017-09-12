package com.lacosdaalegria.intra.comunication;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class InitBot extends Thread {
	
	public static void makeStart() {
		{
			new Thread(t1).start();
		}
	}

	private static Runnable t1 = new Runnable() {
		public void run() {
			
			ApiContextInitializer.init();

			TelegramBotsApi botsApi = new TelegramBotsApi();

	        try {
	            botsApi.registerBot(new LacinhoBot());
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
			
		}
	};	
	
}

