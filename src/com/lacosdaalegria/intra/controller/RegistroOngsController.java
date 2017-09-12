package com.lacosdaalegria.intra.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.InstituicaoDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;
import com.lacosdaalegria.intra.hibernate.dao.TagDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Instituicao;
import com.lacosdaalegria.intra.hibernate.model.Tag;
import com.lacosdaalegria.intra.model.UserDetail;


@Controller
public class RegistroOngsController {
	
	@RequestMapping("CadastroInstituicao")
	public String cadastroInstituicao( Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		TagDAO dao = new TagDAO();
		RegiaoAdministrativaDAO raDao = new RegiaoAdministrativaDAO();
		
		
		List<Tag> tags = dao.listaTagsAtivas();
		
		model.addAttribute("caract", tags.stream().filter(tag -> tag.getTipo() == 1).
				collect(Collectors.toList()));
		model.addAttribute("ativs", tags.stream().filter(tag -> tag.getTipo() == 2).
				collect(Collectors.toList()));
		model.addAttribute("critcs", tags.stream().filter(tag -> tag.getTipo() == 3).
				collect(Collectors.toList()));
		
		if(userDetail.user(session).getUser().getDiretoria() == null)
			model.addAttribute("ras", raDao.listaRegiaoAdminPolo(userDetail.user(session).getUser().getPolo().getId()));
		else {
			if(userDetail.user(session).getUser().getDiretoria().ehOngs())
				model.addAttribute("ras", raDao.listaRegiaoAdministrativa());
		}
		
		session.setAttribute("currentpage", "CadastroInstituicao");
		
		return "cadastro_instituicoes";
		
	}
	
	@RequestMapping("cadastrarInstituicao")
	public String cadastroInstituicao(Instituicao instituicao, HttpSession session,
			@RequestParam("tag1") List<String> tag1,
			@RequestParam("ra_id") int ra_id) {

		InstituicaoDAO dao = new InstituicaoDAO();
		
		instituicao.iniciaTags(tag1);
		
		instituicao.setRa(ra_id);
		
		dao.addInstituicao(instituicao);
		
		return "redirect:InstituicoesOngs";
		
	}
	
	@RequestMapping("AtualizaInstituicao")
	public String atualizaInstituicao(@RequestParam("inst_id") int inst_id, Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail detail = new UserDetail();
		
		model.addAttribute("voluntario", detail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		TagDAO dao = new TagDAO();
		InstituicaoDAO daoInst = new InstituicaoDAO();
		RegiaoAdministrativaDAO raDao = new RegiaoAdministrativaDAO();
		
		List<Tag> tags = dao.listaTagsAtivas();
		
		model.addAttribute("caract", tags.stream().filter(tag -> tag.getTipo() == 1).
				collect(Collectors.toList()));
		model.addAttribute("ativs", tags.stream().filter(tag -> tag.getTipo() == 2).
				collect(Collectors.toList()));
		model.addAttribute("critcs", tags.stream().filter(tag -> tag.getTipo() == 3).
				collect(Collectors.toList()));
		
		if(detail.user(session).getUser().getDiretoria() == null)
			model.addAttribute("ras", raDao.listaRegiaoAdminPolo(detail.user(session).getUser().getPolo().getId()));
		else {
			if(detail.user(session).getUser().getDiretoria().ehOngs())
				model.addAttribute("ras", raDao.listaRegiaoAdministrativa());
		}	
		
		model.addAttribute("instituicao", daoInst.InstituicaoPelaId(inst_id));
		
		session.setAttribute("currentpage", "AtualizaInstituicao");
		
		return "cadastro_instituicoes";
		
	}
	
	@RequestMapping("atualizarInstituicao")
	public String atualizarInstituicao(Instituicao instituicao, HttpSession session,
			@RequestParam("tag1") List<String> tag1,
			@RequestParam("ra_id") int ra_id) {

		InstituicaoDAO dao = new InstituicaoDAO();
		
		instituicao.iniciaTags(tag1);
		
		instituicao.setRa(ra_id);
		
		dao.updateInstituicao(instituicao);
		
		return "redirect:AtualizaInstituicao?inst_id="+instituicao.getId();
		
	}
	
	@RequestMapping("InstituicoesOngs")
	public String instituicoesOngs( Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail detail = new UserDetail();
		
		model.addAttribute("voluntario", detail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		InstituicaoDAO dao = new InstituicaoDAO();
		
		if(detail.user(session).getUser().getDiretoria() == null)
			model.addAttribute("instituicoes", dao.listaInstituicaosRegional(detail.user(session).getUser().getPolo()));
		else {
			if(detail.user(session).getUser().getDiretoria().ehOngs())
				model.addAttribute("instituicoes", dao.listaInstituicaos());
		}
		
		session.setAttribute("currentpage", "InstituicoesOngs");
		
		return "instituicoes";
		
	}
	
	@RequestMapping("DetalheInstituicao")
	public String detalheInstituicoes(@RequestParam("inst_id") int inst_id, Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail detail = new UserDetail();
		
		model.addAttribute("voluntario", detail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		InstituicaoDAO dao = new InstituicaoDAO();
		
		if(detail.user(session).getUser().getDiretoria() == null){
			
			Instituicao inst = dao.InstituicaoPelaId(inst_id);	
		
			if(inst.getRa().getPolo().getId() == detail.user(session).getUser().getPolo().getId())
				model.addAttribute("instituicao", inst);
			
		} else {
			if(detail.user(session).getUser().getDiretoria().ehOngs())
				model.addAttribute("instituicao", dao.InstituicaoPelaId(inst_id));
		}
		
		session.setAttribute("currentpage", "DetalheInstituicoes");
		
		return "detalheInst";
		
	}
	
	@RequestMapping("ControleTags")
	public String CadastroTag( Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail detail = new UserDetail();
		
		model.addAttribute("voluntario", detail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		TagDAO dao = new TagDAO();
		
		model.addAttribute("tags", dao.listaTags());
		
		session.setAttribute("currentpage", "ControleTags");
		
		return "tags";
		
	}
	
	@RequestMapping("cadastrarTag")
	public String cadastrarTag( Tag tag, HttpSession session) {
		
		TagDAO dao = new TagDAO();
		
		dao.addTag(tag);
		
		return "redirect:ControleTags";
		
	}
	
	@RequestMapping("atualizaTag")
	public String atualizaTag(@RequestParam("tag_id") int tag_id , HttpSession session) {
		
		TagDAO dao = new TagDAO();
		
		Tag tag = dao.TagPelaId(tag_id);
		
		tag.inverteStatus();
		
		dao.updateTag(tag);
		
		return "redirect:ControleTags";
		
	}
	
}
