package com.lacosdaalegria.intra.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lacosdaalegria.intra.dao.AtividadeDAO;
import com.lacosdaalegria.intra.dao.NotificationDAO;

public class MyServletContextListener implements ServletContextListener {
	
	@Override
	  public void contextDestroyed(ServletContextEvent arg0) {
	    //Notification that the servlet context is about to be shut down.   
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent arg0) {
	    // do all the tasks that you need to perform just after the server starts
		  
		  NotificationDAO dao = new NotificationDAO();
		  AtividadeDAO ativ = new AtividadeDAO();
		  
		  dao.getNotificacoes();
		  ativ.Atividades();

	    //Notification that the web application initialization process is starting
	  }

}
