package com.lacosdaalegria.intra.interceptor;

import com.lacosdaalegria.intra.model.HashMapEstaticos;

public class ServicoInicializa extends Thread {
	
	public void start() {
		{
			new Thread(t1).start();
		}
	}
	
	private static Runnable t1 = new Runnable() {
		public void run() {
			
			  new Filtro();
			
			  HashMapEstaticos.cores_responsaveis.put(0, "#9966cc");
			  HashMapEstaticos.cores_responsaveis.put(1, "#e52b50");
			  HashMapEstaticos.cores_responsaveis.put(2, "#7fffd4");
			  HashMapEstaticos.cores_responsaveis.put(3, "#4b5320");
			  HashMapEstaticos.cores_responsaveis.put(4, "#ffbf00");
			  HashMapEstaticos.cores_responsaveis.put(5, "#5d8aa8");
			  HashMapEstaticos.cores_responsaveis.put(6, "#a52a2a");
			  HashMapEstaticos.cores_responsaveis.put(7, "#ff2052");
			  HashMapEstaticos.cores_responsaveis.put(8, "#007fff");
			  HashMapEstaticos.cores_responsaveis.put(9, "#dd3cc6");
			  HashMapEstaticos.cores_responsaveis.put(10, "#89cff0");
			  HashMapEstaticos.cores_responsaveis.put(11, "#98777b");
			  HashMapEstaticos.cores_responsaveis.put(12, "#848482");
			  HashMapEstaticos.cores_responsaveis.put(13, "#cc0000");
			  HashMapEstaticos.cores_responsaveis.put(14, "#8a2be2");
			  HashMapEstaticos.cores_responsaveis.put(15, "#318ce7");
			  HashMapEstaticos.cores_responsaveis.put(16, "#006a4e");
			  HashMapEstaticos.cores_responsaveis.put(17, "#cd7f32");
			  HashMapEstaticos.cores_responsaveis.put(18, "#cc5500");
			  HashMapEstaticos.cores_responsaveis.put(19, "#007aa5");
			  HashMapEstaticos.cores_responsaveis.put(20, "#ed872d");
			  HashMapEstaticos.cores_responsaveis.put(21, "#fff600");
			  HashMapEstaticos.cores_responsaveis.put(22, "#007ba7");

			  
		    //Notification that the web application initialization process is starting
		}
	};
	

}
