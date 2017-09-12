package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.DiretoriaDAO;
import com.lacosdaalegria.intra.hibernate.dao.MembroAtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Diretoria;
import com.lacosdaalegria.intra.hibernate.model.MembroAtividade;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.FilaAtividade;
import com.lacosdaalegria.intra.model.HashMapEstaticos;
import com.lacosdaalegria.intra.model.UserDetail;


@Controller
public class MembroAtividadeController {
	
	
	@RequestMapping("adicionarApoio")
	public String adicionaApoio(@RequestParam("email") String email,
							@RequestParam("ativId") int atividade, HttpSession session) {
		
		VoluntarioDAO voluntdao = new VoluntarioDAO();
		
		Voluntario voluntario = voluntdao.VoluntarioPeloEmail(email);
		
		if(voluntario == null)
			return "redirect:"+(String)session.getAttribute("currentpage");
		
					
		MembroAtividadeDAO memdao = new MembroAtividadeDAO();
		AtividadeDAO ativDao = new AtividadeDAO();
		MembroAtividade membro = memdao.RetornaMembro(voluntario);
		
		membro.setAtividade(ativDao.AtividadePelaId(atividade));
		membro.setApoio(true);
		
		memdao.updateMembroAtividade(membro);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	@RequestMapping("retirarApoio")
	public String retirarApoio(@RequestParam("userId") int id, HttpSession session) {
		
		MembroAtividadeDAO membroDao = new MembroAtividadeDAO();
		MembroAtividade membro = membroDao.MembroAtividadePelaId(id);
		
		membro.setApoio(false);
		
		membroDao.updateMembroAtividade(membro);

		return "redirect:"+(String)session.getAttribute("currentpage");
		
	}
	
	/*
	 * Implementar quando possivel
	 */
	
	/*@RequestMapping("ativarApoio")
	public String ativarApoio(HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		
		MembroAtividadeDAO membroDao = new MembroAtividadeDAO();
		MembroAtividade membro = membroDao.RetornaMembro(userDetail.user(session).getUser());
		
		membro.ativaMembro();
		
		membroDao.updateMembroAtividade(membro);
		
		 
		return "redirect:area-voluntario";
		
	}
	
	@RequestMapping("desativarApoio")
	public String desativarApoio(HttpSession session) {
		
		UserDetail userDetail = new UserDetail();
		
		MembroAtividadeDAO membroDao = new MembroAtividadeDAO();
		MembroAtividade membro = membroDao.RetornaMembro(userDetail.user(session).getUser());
		
		membro.desativaMembro();
		
		membroDao.updateMembroAtividade(membro);
		
		 
		return "redirect:area-voluntario";
		
	}*/
	
	@RequestMapping("controleNovatos")
	public String controleNovato(@RequestParam("ativId") int ativId, Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		AtividadeDAO ativDao = new AtividadeDAO();
		Atividade atividade = ativDao.AtividadePelaId(ativId);
		VoluntarioDAO dao = new VoluntarioDAO();
		
		FilaAtividade fila = new FilaAtividade(dao.novatosAtividade(atividade), atividade);
		
		model.addAttribute("novatos", fila.getLista_novatos());
		model.addAttribute("novatosInscritos", fila.getNovatosInscritos());
		model.addAttribute("vagasNovatos", atividade.getLimite_novato()- (fila.getNovatosInscritos() == null ? 0 : fila.getNovatosInscritos().size()));
		model.addAttribute("atividId", ativId);
		model.addAttribute("atividade", atividade);
		model.addAttribute("cores", HashMapEstaticos.cores_responsaveis);
		
		session.setAttribute("currentpage", "controleNovatos?ativId="+ativId);
		
		return "controleNovato";	
		
	}
	
	@RequestMapping("recursoHumano")
	public String recursoHumano(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		MembroAtividadeDAO memDao = new MembroAtividadeDAO();
		
		MembroAtividade mem = new MembroAtividade();
		
		mem.defineMembros(memDao.listaRecursos());
		
		model.addAttribute("recursos", mem);
		
		session.setAttribute("currentpage", "recursoHumano");
		
		return "controleHumano";	
		
	}
	
	@RequestMapping("controleDiretores")
	public String controleDiretores(Model model,  HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		VoluntarioDAO voldao = new VoluntarioDAO();
		DiretoriaDAO dDao = new DiretoriaDAO();
		
		model.addAttribute("diretores", voldao.listaDiretores());
		model.addAttribute("diretorias", dDao.listaDiretoria());
		
		session.setAttribute("currentpage", "controleDiretores");
		
		return "ControleDiretoria";	
		
	}
	
	@RequestMapping("retirarRescurso")
	public String retirarCoordenador(@RequestParam("userId") int membroId, @RequestParam("tipoRecurso") int tipo,HttpSession session) {
		
		MembroAtividadeDAO memDao = new MembroAtividadeDAO();
		
		MembroAtividade membro = memDao.MembroAtividadePelaId(membroId);
		
		if(tipo == 1)
			membro.setCoordenador(false);
		else
			membro.setControle_novato(false);
		
		memDao.updateMembroAtividade(membro);
		
		return "redirect:recursoHumano";
		
	}
	

	@RequestMapping("retirarDiretor")
	public String retirarDiretor(@RequestParam("userId") int userId,HttpSession session) {
		
		VoluntarioDAO dao = new VoluntarioDAO();
		
		Voluntario voluntario = dao.VoluntarioPelaId(userId);
		
		voluntario.setDiretoria(null);
		
		dao.updateVoluntario(voluntario);
		
		return "redirect:controleDiretores";
		
	}
	
	@RequestMapping("adicionarDiretor")
	public String adicionarDiretor(@RequestParam("email") String email, @RequestParam("diretoria") int diretoria_id, 
				HttpSession session) {
		
		DiretoriaDAO dirDao = new DiretoriaDAO();
		VoluntarioDAO voluntdao = new VoluntarioDAO();

		Diretoria diretoria = dirDao.DiretoriaPelaId(diretoria_id);
		Voluntario voluntario = voluntdao.VoluntarioPeloEmail(email);
		
		voluntario.setDiretoria(diretoria);
		
		voluntdao.updateVoluntario(voluntario);
		
		return "redirect:controleDiretores";
		
	}
	
	@RequestMapping("adicionarRecurso")
	public String adicionarRecurso(@RequestParam("email") String email, @RequestParam("atividade") int ativId, 
				@RequestParam("tipo") int tipo, HttpSession session) {
		
		MembroAtividadeDAO membroDao = new MembroAtividadeDAO();
		VoluntarioDAO voluntdao = new VoluntarioDAO();
		AtividadeDAO ativDao = new AtividadeDAO();
		
		MembroAtividade membro = membroDao.RetornaMembro(voluntdao.VoluntarioPeloEmail(email));
		
		if(membro.getMembro() == null)
			return "redirect:recursoHumano";
		
		membro.setAtividade(ativDao.AtividadePelaId(ativId));
		
		if(tipo == 33){
			membro.setCoordenador(true);
		} else {
			membro.setControle_novato(true);
		}
		
		membroDao.updateMembroAtividade(membro);
		
		return "redirect:recursoHumano";
		
	}
	
}
