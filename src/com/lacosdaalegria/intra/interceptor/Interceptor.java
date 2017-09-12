package com.lacosdaalegria.intra.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.UserDetail;

public class Interceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		
		String uri = request.getRequestURI();
		
		//Requests que podem ser feitos por qualuqer um
		if(uri.contains("assets")||this.temAcesso(uri, Filtro.todos)){
			return true;
		} 
		
		if(request.getSession().getAttribute("logado") != null){
			
			UserDetail detail = new UserDetail();
			
			Voluntario vol = detail.user(request).getUser();
			
			if(!vol.isAceitou_termo() && !uri.endsWith("TermoAdesao") && !uri.endsWith("aceitarTermo")){
				response.sendRedirect("TermoAdesao");	
				return false;
			}
			
			if(vol.isAdmin()){
				return true;
			}
			
			int equipeId = request.getParameter("equipeId") == null ? 0 : Integer.parseInt(request.getParameter("equipeId"));
			int ativId = request.getParameter("ativId") == null ? 0 : Integer.parseInt(request.getParameter("ativId"));
				
			
			if(vol.podeAcessarRequest(uri, equipeId, ativId)){
				return true;
			} else {			
				
				if(!detail.user(request).getUser().isNovato()){
					response.sendRedirect("area-voluntario");	
					return false;
				} else {
					response.sendRedirect("area-novato");	
					return false;
				}
			}
				/*
				if(detail.user(request).getUser().isAceitou_termo()){
					return true;
				} else{
			}*/
		}
		
		response.sendRedirect("/");	
		return false;

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
