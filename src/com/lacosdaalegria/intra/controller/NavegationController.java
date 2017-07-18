package com.lacosdaalegria.intra.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.dao.AtividadeDAO;
import com.lacosdaalegria.intra.dao.FeedbackDAO;
import com.lacosdaalegria.intra.dao.NotificationDAO;
import com.lacosdaalegria.intra.model.Atividade;
import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.HashMapAtividade;
import com.lacosdaalegria.intra.model.Notificacao;
import com.lacosdaalegria.intra.model.Notificacoes;
import com.lacosdaalegria.intra.model.UserDetail;

@Controller
public class NavegationController {


	@RequestMapping("area-voluntario")
	public String voluntario(Model model, HttpSession session) {
		
		
		DateHandler date = new DateHandler();
		AtividadeDAO ativDao = new AtividadeDAO();
		UserDetail userDetail = new UserDetail();
		Atividade atividade = new Atividade();
		boolean[] lotada = new boolean[20];
	
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("maislacos", userDetail.user(session).getMaisLacos());
		model.addAttribute("ativAtual", userDetail.user(session).getAtividadesAtuais());
		model.addAttribute("ativsPeriodo", userDetail.user(session).getPeriodoInscritos());
		model.addAttribute("faltante", userDetail.user(session).isFaltante());
		model.addAttribute("tags", HashMapAtividade.tags);		
		model.addAttribute("aberto", date.inscricaoAberta());
		model.addAttribute("serapoio", userDetail.user(session).serapoio);		
		model.addAttribute("feeds", Notificacoes.getNotificacoes());		
		model.addAttribute("Atividade", atividade.listaAtividade());
		model.addAttribute("todas_Ativs", HashMapAtividade.tags);
		model.addAttribute("fase_randomica_acabou", !date.rodadaRandomica());
		
		
		//Logica que levanta a posição do usuario em todas as atividades inscritas
		//Futuramente deve ser elaborado um metodo no  RegistrosAtivDAO para ficar responsavel por isso
		if(!date.rodadaRandomica()) {
			
			lotada = ativDao.atividadeLotada();
			
			model.addAttribute("lotada", lotada);
			
			HashMap<Integer, Integer> posicoes = new HashMap<>();
			
			for (Map.Entry<Integer, Boolean> entry : userDetail.user(session).getAtividadesAtuais().entrySet()){
				
				if(entry.getValue() == true && lotada[entry.getKey()]){
					
					posicoes.put(entry.getKey(), ativDao.estaEmFila(entry.getKey(), 
																	userDetail.user(session).getUser().userid));
					
				}
				
			}
			
			model.addAttribute("posicoes", posicoes);
			
		}
		
		session.setAttribute("currentpage", "area-voluntario");
		
		return "volunteer-area";
		
	}
	
	@RequestMapping("area-novato")
	public String novato(Model model,  HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		
	
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("quantVolutarios", "956");		
		
		return "newbee-area";
		
	}
	
	@RequestMapping("profile")
	public String profile(Model model,  HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		Atividade atividade = new Atividade();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());	
		model.addAttribute("Atividade", atividade.listaAtividade());
		session.setAttribute("currentpage", "profile");
		model.addAttribute("todas_Ativs", HashMapAtividade.tags);
		
		return "user-page";
		
	}
	
	//essa é a alteração
	@RequestMapping("cadastrarNotif")
	public String cadastrarNotif() {

		return "cadastrar_notif";	
		
	}
	
	@RequestMapping("insertNotif")
	public String insertNotif(Notificacao notificacao, HttpSession session) {
		
	
		
		UserDetail userDetail = new UserDetail();
		
		NotificationDAO dao = new NotificationDAO();
			
		dao.adicionaNotificacao(notificacao, userDetail.user(session).getUser().userid);
		
		return "redirect:area-voluntario";
		
		
	}
	
	@RequestMapping("feedback")
	public String feedback(@RequestParam("feedback") String feedback, HttpSession session) {
		
	
		
		FeedbackDAO dao = new FeedbackDAO();
		
		dao.adicionaFeedback(feedback);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
		
	}
	
	@RequestMapping("construcao")
	public String construcao (Model model, HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		
	
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		
		session.setAttribute("currentpage", "construcao");
		
		return "construcao";
		
	}

	
}
