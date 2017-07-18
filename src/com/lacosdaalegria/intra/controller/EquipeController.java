package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.dao.EquipeDAO;
import com.lacosdaalegria.intra.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.model.UserDetail;

@Controller
public class EquipeController {
	
	
	@RequestMapping("adicionarApoio")
	public String adicionaApoio(@RequestParam("email") String email,
							@RequestParam("atividade") int atividade, HttpSession session) {
		
		VoluntarioDAO voluntdao = new VoluntarioDAO();
		EquipeDAO equipedao = new EquipeDAO();
		
		int userid = voluntdao.infoVoluntario(email).userid; 
		
		if(equipedao.serApoio(userid)[0] != 0){
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		} else {
			
			equipedao.adicionaApoio(userid, atividade);
			
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	@RequestMapping("ativarApoio")
	public String ativarApoio(HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		EquipeDAO dao = new EquipeDAO();
		
		dao.ativarApoio(userDetail.user(session).getUser().getUserid());
		userDetail.user(session).serapoio[1]=1;
		 
		return "redirect:area-voluntario";
		
	}
	
	@RequestMapping("desativarApoio")
	public String desativarApoio(HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		EquipeDAO dao = new EquipeDAO();
		
		dao.desativarApoio(userDetail.user(session).getUser().getUserid());
		userDetail.user(session).serapoio[1]=2;

		return "redirect:area-voluntario";
		
	}

}
