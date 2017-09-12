package com.lacosdaalegria.intra.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.PoloDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.Polo;
import com.lacosdaalegria.intra.hibernate.model.RegiaoAdministrativa;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.UserDetail;


@Controller
public class OngsController {

	@RequestMapping("EquipeOngs")
	public String equipeOngs( Model model, HttpSession session) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		PoloDAO daoPolo = new PoloDAO();
		VoluntarioDAO volDao = new VoluntarioDAO();
		RegiaoAdministrativaDAO raDao = new RegiaoAdministrativaDAO();
		
		model.addAttribute("polos", daoPolo.listaPolo());
		model.addAttribute("equipe", volDao.equipeOngs());
		model.addAttribute("ras", raDao.listaRADesviculadas());
		
		session.setAttribute("currentpage", "InstituicoesOngs");
		
		return "equipeOngs";
		
	}
	
	@RequestMapping("cadastrarRegional")
	public String cadastroInstituicao(Polo polo, HttpSession session,
			@RequestParam("ras") List<Integer> ras) {

		PoloDAO dao = new PoloDAO();
		
		polo = dao.addPolo(polo);
		
		polo.iniciaRas(ras);
		
		return "redirect:EquipeOngs";
		
	}
	
	@RequestMapping("adicionarEquipeOngs")
	public String adicionarEquipeOngs(@RequestParam("email") String email,
									  @RequestParam("polo_id") int polo_id) {

		VoluntarioDAO vDao = new VoluntarioDAO();
		
		Voluntario v = vDao.VoluntarioPeloEmail(email);
		PoloDAO pDao = new PoloDAO();
		
		Polo polo = pDao.PoloPelaId(polo_id);
		
		v.setPolo(polo);
		
		vDao.updateVoluntario(v);
		
		return "redirect:EquipeOngs";
		
	}
	
	@RequestMapping("liberarRA")
	public String liberarRA(@RequestParam("raId") int raId) {

		RegiaoAdministrativaDAO dao = new RegiaoAdministrativaDAO();
		
		RegiaoAdministrativa ra = dao.RegiaoAdministrativaPelaId(raId);
		
		ra.setPolo(null);
		
		dao.updateRegiaoAdministrativa(ra);
		
		return "redirect:EquipeOngs";
		
	}
	
	@RequestMapping("adcionarRegiao")
	public String adcionarRegiao(@RequestParam("polo_id") int poloId,
								 @RequestParam("ras") List<Integer> ras) {

		RegiaoAdministrativaDAO rDao = new RegiaoAdministrativaDAO();
		PoloDAO pDao = new PoloDAO();
		
		Polo p = pDao.PoloPelaId(poloId);
		
		for (Integer id : ras){
			
			RegiaoAdministrativa ra = rDao.RegiaoAdministrativaPelaId(id);
			ra.setPolo(p);
			
			rDao.updateRegiaoAdministrativa(ra);
			
		}
		
		return "redirect:EquipeOngs";
		
	}
	
}
