package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.DemandaDAO;
import com.lacosdaalegria.intra.hibernate.dao.EquipeDAO;
import com.lacosdaalegria.intra.hibernate.dao.NotaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Demanda;
import com.lacosdaalegria.intra.hibernate.model.Equipe;
import com.lacosdaalegria.intra.hibernate.model.Nota;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.UserDetail;


@Controller
public class ControleDemandaController {
	
	
	@RequestMapping("ControleDemandas")
	public String controleDemandas(@RequestParam("equipeId") int equipeId, Model model, HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		
		Demanda demanda = new Demanda();
		DemandaDAO demDao = new DemandaDAO();
		EquipeDAO equipeDao =  new EquipeDAO();
		
		Equipe equipe = equipeDao.EquipePelaId(equipeId);
		
		demanda.salvaDemandas(demDao.listaDemandaAtivas());
		
		//Lista de demandas Distribuidas
		model.addAttribute("demanda", demanda);
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());	
		
		model.addAttribute("equipes", equipeDao.listaEquipe());
		model.addAttribute("equipe", equipe);

		session.setAttribute("currentpage", "ControleDemandas?equipeId="+equipeId);
		
		session.setAttribute("equipeAtual", equipeId);
		
		return "controleDemandas";
		
	}
	
	@RequestMapping("cadastrarDemanda")
	public String cadastrarDemanda(@RequestParam("equipeId") int equipeId, Demanda demanda, HttpSession session) {
			
			DemandaDAO dao = new DemandaDAO();
			UserDetail userDetail = new UserDetail();
			
			EquipeDAO equipeDao =  new EquipeDAO();
			
			Equipe equipe = equipeDao.EquipePelaId(equipeId);
			
			demanda.setEquipe(equipe);
			demanda.setCriador(userDetail.user(session).getUser());

			dao.addDemanda(demanda);
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("capturaDemanda")
	public String capturaDemanda(@RequestParam("demandaId") int demandaId, @RequestParam("equipeId") int equipeId, HttpSession session) {
			
			DemandaDAO dao = new DemandaDAO();
			Demanda demanda = dao.DemandaPelaId(demandaId);
			UserDetail userDetail = new UserDetail();
			
			if(demanda.semResponsavel() && demanda.isFazer()){
				
				demanda.setResponsavel(userDetail.user(session).getUser());
				
				dao.updateDemanda(demanda);
			}
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("designarDemanda")
	public String designarDemanda(@RequestParam("demandaId") int demandaId, 
								  @RequestParam("equipeId") int equipeId,
								  @RequestParam("responsavelId") int responsavel_id, HttpSession session) {
			
		DemandaDAO dao = new DemandaDAO();
		Demanda demanda = dao.DemandaPelaId(demandaId);

			if(demanda.semResponsavel() && demanda.isFazer()){
				VoluntarioDAO volDao = new VoluntarioDAO();
				Voluntario responsavel = volDao.VoluntarioPelaId(responsavel_id);
				demanda.setResponsavel(responsavel);
				dao.updateDemanda(demanda);
			}
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("descapturaDemanda")
	public String descapturaDemanda(@RequestParam("demandaId") int demandaId, @RequestParam("equipeId") int equipeId, HttpSession session) {
			
		DemandaDAO dao = new DemandaDAO();
		Demanda demanda = dao.DemandaPelaId(demandaId);
		UserDetail userDetail = new UserDetail();
			
			if((demanda.isFazendo() || demanda.isPendente()) && 
					(demanda.isResponsavel(userDetail.user(session).getUser()) || 
							userDetail.user(session).getUser().lideraEquipe(equipeId))){
				
				demanda.setResponsavel(null);
				dao.updateDemanda(demanda);
			}

			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("adicionarPendencia")
	public String adicionarPendencia(Nota nota, @RequestParam("demandaId") int demandaId, @RequestParam("equipeId") int equipeId, HttpSession session) {
			
			DemandaDAO dao = new DemandaDAO();
			Demanda demanda = dao.DemandaPelaId(demandaId);
			UserDetail userDetail = new UserDetail();
			
			nota.setDemanda(demanda);
			
			if(demanda.isFazendo() && demanda.isResponsavel(userDetail.user(session).getUser())){
				
				NotaDAO notaDao = new NotaDAO();
				
				nota.setCriador(userDetail.user(session).getUser());
				nota.setTipo(1);
				demanda.setStatus(2);
				
				notaDao.addNota(nota);
				
				dao.updateDemanda(demanda);
				
			}

			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("resolverPendencia")
	public String resolverPendencia(Nota nota, @RequestParam("demandaId") int demandaId,
			@RequestParam("equipeId") int equipeId, HttpSession session) {
			
		DemandaDAO dao = new DemandaDAO();
		Demanda demanda = dao.DemandaPelaId(demandaId);
		UserDetail userDetail = new UserDetail();
		
		if(demanda.isPendente() && demanda.isResponsavel(userDetail.user(session).getUser())){
			
			NotaDAO notaDao = new NotaDAO();
			
			nota.setDemanda(demanda);
			nota.setCriador(userDetail.user(session).getUser());
			nota.setTipo(2);
			demanda.setStatus(1);
			
			notaDao.addNota(nota);
			dao.updateDemanda(demanda);
			
		}
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
	}
	
	@RequestMapping("concluirDemanda")
	public String concluirDemanda(Nota nota, @RequestParam("equipeId") int equipeId, 
			@RequestParam("demandaId") int demandaId, HttpSession session) {
			
			UserDetail userDetail = new UserDetail();
			DemandaDAO dao = new DemandaDAO();
			Demanda demanda = dao.DemandaPelaId(demandaId);
			
			if((demanda.isFazendo() || demanda.isPendente()) && demanda.isResponsavel(userDetail.user(session).getUser())){
				
				NotaDAO notaDao = new NotaDAO();

				nota.setDemanda(demanda);
				nota.setCriador(userDetail.user(session).getUser());
				demanda.setStatus(3);
				nota.setTipo(4);
				
				notaDao.addNota(nota);
				
				dao.updateDemanda(demanda);
				
			}
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("arquivarDemanda")
	public String arquivarDemanda(Nota nota, @RequestParam("equipeId") int equipeId,
			@RequestParam("demandaId") int demandaId, HttpSession session) {
			
		UserDetail userDetail = new UserDetail();
		DemandaDAO dao = new DemandaDAO();
		Demanda demanda = dao.DemandaPelaId(demandaId);
		
		if(demanda.isConcluida() && userDetail.user(session).getUser().lideraEquipe(equipeId)){
		
			NotaDAO notaDao = new NotaDAO();
			
			nota.setDemanda(demanda);
			nota.setCriador(userDetail.user(session).getUser());
			nota.setTipo(5);
			demanda.setStatus(4);
			
			notaDao.addNota(nota);
			
			dao.updateDemanda(demanda);
			
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	@RequestMapping("adicionarNota")
	public String adicionarNota(Nota nota, @RequestParam("equipeId") int equipeId, 
			@RequestParam("demandaId") int demandaId, HttpSession session) {
			
			UserDetail userDetail = new UserDetail();
			NotaDAO notaDao = new NotaDAO();
			DemandaDAO dao = new DemandaDAO();
			Demanda demanda = dao.DemandaPelaId(demandaId);
			
			nota.setDemanda(demanda);
			nota.setCriador(userDetail.user(session).getUser());
			nota.setTipo(0);
			
			notaDao.addNota(nota);
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("reabrirDemanda")
	public String reabrirDemanda(Nota nota, @RequestParam("equipeId") int equipeId, 
			@RequestParam("demandaId") int demandaId, HttpSession session) {
		
		DemandaDAO dao = new DemandaDAO();
		Demanda demanda = dao.DemandaPelaId(demandaId);
		UserDetail userDetail = new UserDetail();
			
		if(userDetail.user(session).getUser().lideraEquipe(equipeId) || demanda.isResponsavel(userDetail.user(session).getUser())){
				
				NotaDAO notaDao = new NotaDAO();
				
				nota.setDemanda(demanda);
				nota.setCriador(userDetail.user(session).getUser());
				nota.setTipo(3);
				demanda.setStatus(1);
				
				notaDao.addNota(nota);
				
				dao.updateDemanda(demanda);
				
			}
		
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		}
	
	@RequestMapping("Notas")
	public String notas(@RequestParam("demandaId") int demandaId, 
			@RequestParam("equipeId") int equipeId, HttpSession session, Model model) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		DemandaDAO dao = new DemandaDAO();
		NotaDAO notaDao = new NotaDAO();
			
		Demanda demanda = dao.DemandaPelaId(demandaId);
		
		demanda.setNotas(notaDao.listaNota(demanda));
		
		model.addAttribute("demanda", demanda);
		
		session.setAttribute("currentpage", "Notas?demandaId="+demandaId+"&equipeId="+equipeId);
		
			return "notas";
			
		}
	

	
	
}
