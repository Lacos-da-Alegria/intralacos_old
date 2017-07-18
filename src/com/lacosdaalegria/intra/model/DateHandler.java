package com.lacosdaalegria.intra.model;

import java.util.Calendar;
import java.util.Date;

public class DateHandler {
		
	public int numeroSemana(){	
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -3);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		
		return week;
	}
	
	public boolean inscricaoAberta(){
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -3);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR_OF_DAY);


		if (day >= 2 && day<= 6){
			return true;
		}
		if (day == 7){
			if (hour <= 12)
			return true;
			else
			return false;
		}
		
		if (day == 1) {			
			return false;			
		}
		
		return false;
	}
	
	public boolean calcelarAberta(){
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -3);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR_OF_DAY);

		if (day >= 2 && day <=6 ) 			
			return true;			
		
		
		if (day == 1 ) 			
			return false;			
			
		if (day == 7){
			if (hour <= 12)
			return true;
			else
			return false;
		}
		
		return false;
	
		
	}
	
	
	public boolean rodadaRandomica(){
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -3);
		int day = cal.get(Calendar.DAY_OF_WEEK);
		
		if (day >=2 && day <= 3)
				return true;
			else				
				return false;
	}

	public boolean chamadaAberta(){
	
	//Lembrar de levar em consideração o fuso horario
	Date date = new Date();

	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.add(Calendar.HOUR_OF_DAY, -3);
	int day = cal.get(Calendar.DAY_OF_WEEK);
	int hour = cal.get(Calendar.HOUR_OF_DAY);
	
	if (day == 7 ){
		if (hour >= 13)
		return true;
	}else {if (day == 1)
				return true;
			else				
				return false;
	}
	
	return false;
}
	
	public boolean ehDomingo(){
		
		//Lembrar de levar em consideração o fuso horario
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, -3);
		int day = cal.get(Calendar.DAY_OF_WEEK);

		if (day == 1) 			
			return true;			
		else
			return false;	
	
		
	}
	
	
}
