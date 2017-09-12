package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.DiretoriaDAO;
import com.lacosdaalegria.intra.hibernate.dao.EquipeDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Equipe;
import com.lacosdaalegria.intra.model.UserDetail;


@Controller
public class EquipeController {
	
	@RequestMapping("CadastroEquipe")
	public String cadastroEquipe( Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "CadastroEquipe");
		
		return "cadastra_equipe";
		
	}
	
	@RequestMapping("cadastrarEquipe")
	public String cadastrarEquipe(Equipe equipe, @RequestParam("email") String email,
			@RequestParam("diretoria") int diretoria, HttpSession session) {
		
		EquipeDAO dao = new EquipeDAO();
		VoluntarioDAO voluntdao = new VoluntarioDAO();
		
		if(email !=null)
			equipe.setLider(voluntdao.VoluntarioPeloEmail(email));
			
		equipe.setDiretoria(new DiretoriaDAO().DiretoriaPelaId(diretoria));
		
		dao.addEquipe(equipe);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
			
	}
	
	@RequestMapping("atualizarEquipe")
	public String atualizarEquipe(Equipe equipe, HttpSession session) {
		
		EquipeDAO dao = new EquipeDAO();
		
		dao.updateEquipe(equipe);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}

	
	@RequestMapping("adicionarMembro")
	public String adicionaMembro(@RequestParam("email") String email,
							@RequestParam("equipeId") int equipeId, HttpSession session) {
		
		VoluntarioDAO voluntdao = new VoluntarioDAO();
		EquipeDAO equipedao = new EquipeDAO();
		
		Equipe equipe = equipedao.EquipePelaId(equipeId);
		
		equipe.getMembros().add(voluntdao.VoluntarioPeloEmail(email));
		
		equipedao.updateEquipe(equipe);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	
	@RequestMapping("adicionarLider")
	public String adicionaLider(@RequestParam("email") String email,
							@RequestParam("equipeId") int equipeId, HttpSession session) {
		
		VoluntarioDAO voluntdao = new VoluntarioDAO();
		EquipeDAO equipedao = new EquipeDAO();
		
		Equipe equipe = equipedao.EquipePelaId(equipeId);
		
		equipe.setLider(voluntdao.VoluntarioPeloEmail(email));
		
		equipedao.updateEquipe(equipe);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	@RequestMapping("retirarMembro")
	public String retirarApoio(@RequestParam("userId") int userId, 
							   @RequestParam("equipeId") int equipeId, HttpSession session) {
		
		
		EquipeDAO dao = new EquipeDAO();
		
		Equipe equipe = dao.EquipePelaId(equipeId);
		
		equipe.retiraMembro(userId);
		
		dao.updateEquipe(equipe);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	
	
}
