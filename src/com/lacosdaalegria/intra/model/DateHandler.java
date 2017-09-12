package com.lacosdaalegria.intra.model;

import java.util.Calendar;
import java.util.Date;

public class DateHandler {
	
	//Servidor utiliza time zone UTC que são 3 horas de diference para o horario de brasília
	// 2 horas de diferença durante o horario de verão
	private int timeDiference = 3;
		
	public boolean calcelarAberta(){
		
		int day = dia();
		int hour = hora();

		if (day >= 2 && day <=6 ) 			
			return true;			
		
		if (day == 1 ) 			
			return false;			
			
		if (day == 7){
			if (hour < 12)
			return true;
			else
			return false;
		}
		
		return false;
		
	}
	
	
	public boolean rodadaRandomica(){
		return dia() >=2 && dia() <= 3;
	}
	
	public boolean ehDomingo(){
		return dia() == 1;			
	}
	
	public boolean ehHoje(int day){
		return dia() == day;
	}
	
	public boolean podeCancelar(int dia, int periodo){
		
		//Colocando Domingo no final da Fila de dias da semana
		dia = (dia == 1 ? 8 : dia);
		
		if(rodadaRandomica()){
			return true;
		}
		
		if(periodo == 1){
			if(dia() < dia){
				return true;	
			} else {
				return false;
			}
		} else {
			if(dia() < dia){
				return true;	
			} else {
				if(dia == dia()){
					if(periodo == 2){
						return hora() < 12;
					} else {
						return hora() < 17;
					}
				} else
					return false;
			}
				
		}
		
	}
	
	public int numeroSemana(){	
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, - timeDiference);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}
	
	public int dia(){
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, - timeDiference);

		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public int hora(){
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, - timeDiference);

		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	
}
