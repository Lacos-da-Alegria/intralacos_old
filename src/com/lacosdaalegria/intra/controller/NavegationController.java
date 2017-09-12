package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lacosdaalegria.intra.hibernate.dao.NotificacaoDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Notificacao;
import com.lacosdaalegria.intra.model.CodigoAtivacao;
import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.Notificacoes;
import com.lacosdaalegria.intra.model.UserDetail;

@Controller
public class NavegationController {

	@RequestMapping("area-voluntario")
	public String voluntario(Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		DateHandler date = new DateHandler();
		
		userDetail.user(session).getUser().atividadeInscritas();
		
		model.addAttribute("detail", userDetail.user(session));
		model.addAttribute("feeds", Notificacoes.getNotificacoes());
		model.addAttribute("codigoSemana", CodigoAtivacao.codigoAtivacao);
		
		model.addAttribute("fase_randomica_acabou", !date.rodadaRandomica());
	
		//model.addAttribute("AtividadesOngs", Atividade.listaAtividadeOngs());
		
		session.setAttribute("currentpage", "area-voluntario");
		
		return "volunteer-area";
		
	}
	
	@RequestMapping("area-novato")
	public String novato(Model model,  HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		VoluntarioDAO dao = new VoluntarioDAO();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("primeiro_acesso", userDetail.user(session).isPrimeiro_acesso());
		model.addAttribute("hospitais", Atividade.atividadeAtivas());
//		model.addAttribute("maislacos", userDetail.user(session).getMaisLacos());
		model.addAttribute("posicao", dao.posicaoNovato(userDetail.user(session).getUser()));
		
		return "newbee-area";
		
	}
	
	@RequestMapping("profile")
	public String profile(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		RegiaoAdministrativaDAO raDao = new RegiaoAdministrativaDAO();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		model.addAttribute("regioes", raDao.listaRegiaoAdministrativa());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "profile");
		
		return "user-page";
		
	}
	
	//essa é a alteração
	@RequestMapping("cadastrarNotificacao")
	public String cadastrarNotif(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "cadastrarNotificacao");

		return "cadastrar_notif";	
		
	}
	
	@RequestMapping("historiaLacos")
	public String historiaLacos(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "historiaLacos");

		return "histLacos";
		
	}
	
	@RequestMapping("comoPossoAjudar")
	public String comoPossoAjudar(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "comoPossoAjudar");

		return "comoAjudar";
		
	}
	
	
	@RequestMapping("insertNotif")
	public String insertNotif(Notificacao notificacao, HttpSession session) {

		UserDetail userDetail = new UserDetail();
		
		NotificacaoDAO dao = new NotificacaoDAO();
		
		notificacao.setCriador(userDetail.user(session).getUser());
		
		dao.addNotificacao(notificacao);
		
		return "redirect:area-voluntario";
		
		
	}
	
	@RequestMapping("construcao")
	public String construcao (Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "construcao");
		
		return "construcao";
		
	}
	
	@RequestMapping("FAQ")
	public String faq (Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "FAQ");
		
		return "faq";
		
	}
	
	@RequestMapping("AniversariantesDoMes")
	public String voluntarioNotaDez(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		VoluntarioDAO dao = new VoluntarioDAO();
		
		model.addAttribute("aniversariantes", dao.aniversariantes());
		
		session.setAttribute("currentpage", "AniversariantesDoMes");
		
		return "aniversariantes";
		
	}
	
	@RequestMapping("Contatos")
	public String contatos(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		VoluntarioDAO volDao = new VoluntarioDAO();
		
		model.addAttribute("diretores", volDao.listaDiretores());
		
		session.setAttribute("currentpage", "Contatos");
		
		return "contato";
		
	}
	
	
	@RequestMapping("TermoAdesao")
	public String termoAdesao(HttpSession session, Model model){
		
		/*UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());*/
		
		return "termo";
		
		
	}
	
	@RequestMapping("LeituraTermo")
	public String leituraTermo (Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		session.setAttribute("currentpage", "LeituraTermo");
		
		return "termo_leitura";
		
	}

}
