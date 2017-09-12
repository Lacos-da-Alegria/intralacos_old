package com.lacosdaalegria.intra.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lacosdaalegria.intra.hibernate.dao.AgendaAtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.InstituicaoDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.AgendaAtividade;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.model.UserDetail;

@Controller
public class AgendaController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("CalendarioOngs")
	public String calendarioOngs( Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/

		InstituicaoDAO isntDao = new InstituicaoDAO();
		AgendaAtividadeDAO agDao = new AgendaAtividadeDAO();
		
		if(userDetail.user(session).getUser().getDiretoria()!= null && userDetail.user(session).getUser().getDiretoria().ehOngs()){
			model.addAttribute("instituicoes", isntDao.listaInstituicaos());
			model.addAttribute("acoes", agDao.listaAgendaAtiva());
		} else {
			model.addAttribute("instituicoes", isntDao.listaInstituicaosRegional(userDetail.user(session).getUser().getPolo()));
			model.addAttribute("acoes", agDao.listaAgendaAtivaPolo(userDetail.user(session).getUser().getPolo()));
		}
		
		session.setAttribute("currentpage", "CalendarioOngs");
		
		return "calendario";
		
	}
	
	@RequestMapping("pesquisaAgenda")
	  public @ResponseBody String pesquisaAgenda(@RequestParam("idAgenda") int idAgenda) {	  
		  
		 Gson g = new Gson();
		 AgendaAtividadeDAO dao = new AgendaAtividadeDAO();
		 VoluntarioDAO volDao = new VoluntarioDAO();
		 SimpleDateFormat dtFor = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		 
		 AgendaAtividade agenda = dao.AgendaAtividadePelaId(idAgenda);
		 
		 agenda.setNomeCriador(volDao.VoluntarioPelaId(agenda.getCriador_id()).getNome());
		 agenda.setHorarioFormat(dtFor.format(agenda.getHorario()));
		 
		 agenda.getInstituicao().setRa(null);
		 agenda.getInstituicao().setTags(null);
		 
		 String jsonString = g.toJson(agenda);
		 
		  // Retorna o IDParametro do registro salvo para ser usado na inserção da tabela detalhe;
	    return jsonString;	     
	  }
	 
	 @RequestMapping("cancelarAcao")
		public String cancelarAcao(@RequestParam("agenda_id") int idAgenda) {
		 
		 	AgendaAtividadeDAO dao = new AgendaAtividadeDAO();

		 	AgendaAtividade agenda = dao.AgendaAtividadePelaId(idAgenda);
		 	
		 	agenda.setStatus(0);
			
		 	dao.updateAgendaAtividade(agenda);
		 	
			return "redirect:CalendarioOngs";
			
		}

	 @RequestMapping("agendarAcao")
		public String agendarAcao(AgendaAtividade agenda, HttpSession session) {
		 
		 	AgendaAtividadeDAO dao = new AgendaAtividadeDAO();
		 	UserDetail userDetail = new UserDetail();
		 	
		 	agenda.setCriador_id(userDetail.getUserId(session));
			
			dao.addAgendaAtividade(agenda);
			
			return "redirect:CalendarioOngs";
			
		}

}
