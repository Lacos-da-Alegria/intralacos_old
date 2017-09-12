package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.AtendimentoDAO;
import com.lacosdaalegria.intra.hibernate.dao.CategoriaDAO;
import com.lacosdaalegria.intra.hibernate.dao.OuvidoriaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atendimento;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Categoria;
import com.lacosdaalegria.intra.hibernate.model.Ouvidoria;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.UserDetail;

@Controller
@Transactional
public class OuvidoriaController {
	
	@RequestMapping("Ouvidoria")
	public String ouvidoriaPage(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		CategoriaDAO cDao = new CategoriaDAO();
		
		model.addAttribute("atendimentos", oDao.meusAtendimentos(userDetail.user(session).getUser()));
		model.addAttribute("tipos", cDao.listaCategoriaAtivas());
		
		return "ouvidoria";
		
	}
	
	@RequestMapping("abrirAtendimeto")
	public String ouvidoriaPage(Ouvidoria ouvidoria, @RequestParam("catId") int catId, HttpSession session) {
		
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		UserDetail userDetail = new UserDetail();
		
		CategoriaDAO cDao = new CategoriaDAO();
		
		ouvidoria.setCategoria(cDao.CategoriaPelaId(catId));
		ouvidoria.setVoluntario(userDetail.user(session).getUser());
		
		oDao.addOuvidoria(ouvidoria);
		
		return "redirect:Ouvidoria";
		
	}
	
	@RequestMapping("gruposAtendimento")
	public String gruposAtendimento(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		AtendimentoDAO aDao = new AtendimentoDAO();
		CategoriaDAO cDao = new CategoriaDAO();
		
		model.addAttribute("grupos", aDao.listaAtendimento());
		model.addAttribute("categorias", cDao.listaCategoriasLivres());
		
		return "gruposAtendimento";
	}
	
	@RequestMapping("Atendimento")
	public String atendimento(Model model,  HttpSession session, @RequestParam("atendId") int atendId) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		AtendimentoDAO aDao = new AtendimentoDAO();
		
		Atendimento atendimento = aDao.AtendimentoPelaId(atendId);
		
		if(userDetail.user(session).getUser().ehAtendente(atendimento)){
			
			session.setAttribute("currentpage", "Atendimento?atendId="+atendId);

			model.addAttribute("ouvidorias", oDao.OuvidoriaPelaAtendimento(atendId));
			model.addAttribute("atendimento", atendimento);
			
			return "atendimento";
			
		} else {
			
			return "redirect:area-voluntario";
		}
		
		
		
	}
	
	@RequestMapping("cadastrarGrupo")
	public String cadastrarGrupo(Atendimento atendimento) {
		
		AtendimentoDAO aDao = new AtendimentoDAO();
		
		aDao.addAtendimento(atendimento);
		
		return "redirect:gruposAtendimento";
		
	}
	
	@RequestMapping("cadastrarCategoria")
	public String cadastrarCategoria(Categoria categoria, @RequestParam("idGrupo") int grupoId) {
		
		AtendimentoDAO aDao = new AtendimentoDAO();
		CategoriaDAO cDao = new CategoriaDAO();
		
		categoria.setAtendimento(aDao.AtendimentoPelaId(grupoId));
		
		cDao.addCategoria(categoria);
		
		return "redirect:gruposAtendimento";
		
	}
	
	@RequestMapping("vincularAtendente")
	public String vincularAtendente(@RequestParam("email") String email, @RequestParam("idGrupo") int grupoId) {
		
		VoluntarioDAO vDao = new VoluntarioDAO();
		
		Voluntario vol = vDao.VoluntarioPeloEmail(email);
		
		if(vol != null){
			AtendimentoDAO aDao = new AtendimentoDAO();
			
			Atendimento atend = aDao.AtendimentoPelaId(grupoId);
			
			vol.setAtendimento(atend);
			
			vDao.updateVoluntario(vol);
			
		}
		
		return "redirect:gruposAtendimento";
		
	}
	
	@RequestMapping("retirarCategoria")
	public String retirarCategoria(@RequestParam("idCat") int catId) {
		
		CategoriaDAO cDao = new CategoriaDAO();
		
		Categoria categoria = cDao.CategoriaPelaId(catId);
		
		categoria.setAtendimento(null);
		
		cDao.updateCategoria(categoria);
		
		return "redirect:gruposAtendimento";
		
	}
	
	@RequestMapping("vincularCategoria")
	public String vincularCategoria(@RequestParam("cat") int catId, @RequestParam("idGrupo") int idGrupo) {
		
		CategoriaDAO cDao = new CategoriaDAO();
		AtendimentoDAO aDao = new AtendimentoDAO();
		
		Categoria categoria = cDao.CategoriaPelaId(catId);
		
		categoria.setAtendimento(aDao.AtendimentoPelaId(idGrupo));
		
		cDao.updateCategoria(categoria);
		
		return "redirect:gruposAtendimento";
		
	}
	
	@RequestMapping("capturarAtend")
	public String vincularCategoria(@RequestParam("atendId") int atendId, HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		
		Ouvidoria ouvidoria = oDao.OuvidoriaPelaId(atendId);
		
		if(userDetail.user(session).getUser().ehAtendente(ouvidoria.getCategoria().getAtendimento())){
			
			if(ouvidoria.getResponsavel() == null){
				ouvidoria.setResponsavel(userDetail.user(session).getUser());
				oDao.updateOuvidoria(ouvidoria);
			}
			
			return "redirect:"+(String)session.getAttribute("currentpage");
			
		} else {
			
			return "redirect:area-voluntario";
		}
		
	}
	
	@RequestMapping("descapturarAtend")
	public String descapturarAtend(@RequestParam("atendId") int atendId, HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		
		Ouvidoria ouvidoria = oDao.OuvidoriaPelaId(atendId);
		
		if(ouvidoria.getResponsavel() != null && ouvidoria.getResponsavel().getId().equals(userDetail.user(session).getUser().getId())){
			ouvidoria.setResponsavel(null);
			oDao.updateOuvidoria(ouvidoria);
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	@RequestMapping("reponderOuvidoria")
	public String reponderOuvidoria(@RequestParam("idOuvidoria") int atendId, 
			@RequestParam("resposta") String resposta, HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		
		Ouvidoria ouvidoria = oDao.OuvidoriaPelaId(atendId);
		
		if(ouvidoria.getResponsavel() != null && ouvidoria.getResponsavel().getId().equals
				(userDetail.user(session).getUser().getId()) && resposta != null && !resposta.isEmpty()){
			ouvidoria.setResposta(resposta);
			ouvidoria.setStatus(1);
			oDao.updateOuvidoria(ouvidoria);
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	@RequestMapping("concluirOuvidoria")
	public String reponderOuvidoria(@RequestParam("idOuvidoria") int atendId, HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		OuvidoriaDAO oDao = new OuvidoriaDAO();
		
		Ouvidoria ouvidoria = oDao.OuvidoriaPelaId(atendId);
		
		if(ouvidoria.getResponsavel() != null && ouvidoria.getResponsavel().getId().equals
				(userDetail.user(session).getUser().getId()) && ouvidoria.getResposta() != null && !ouvidoria.getResposta().isEmpty()){
			ouvidoria.setStatus(2);
			oDao.updateOuvidoria(ouvidoria);
			session.setAttribute("atendidos", (Integer)session.getAttribute("atendidos")-1);
		}
		
		return "redirect:Ouvidoria";
		
	}
	
	/*
	 
	 desativar categoria
	 desativar grupo
	 
	 */

}
