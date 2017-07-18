package com.lacosdaalegria.intra.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lacosdaalegria.intra.model.UserDetail;

public class Interceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		
		String uri = request.getRequestURI();
		Filtro filtro = new Filtro();
		
		//Requests que podem ser feitos por qualuqer um
		if(uri.contains("assets")||filtro.temAcesso(uri, filtro.todos)){
			return true;
		} 
		
		if(request.getSession().getAttribute("logado") != null){
			
			UserDetail detail = new UserDetail();
			
			int acesso = detail.user(request).getUser().acesso;
			
			//Requests que necessita estar logado para ser feito
			if(filtro.temAcesso(uri, filtro.logado)){
				return true;
			} 
			
			if (acesso != 69 ){
				
				if (acesso != 0 ){
						
					if(filtro.temAcesso(uri, filtro.voluntario)) 
					{
						return true;
								
					} else{
						
						if (acesso != 1) {
							
							if(filtro.temAcesso(uri, filtro.cordenador)){
								
								return true;
								
							} 
							
						} else {

								response.sendRedirect("area-voluntario");	
								return false;
								
							} 
							
						
						}


				} else {
					
					if(filtro.temAcesso(uri, filtro.novato)) 
					{
						return true;
								
					} else{
									
						response.sendRedirect("area-novato");	
						return false;	
						
							}
				}
			 
			} else{
				
				return true;
				
				}
			
		}  
		
		response.sendRedirect("/");	
		return false;
		

	}



}