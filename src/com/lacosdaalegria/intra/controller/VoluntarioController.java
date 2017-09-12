package com.lacosdaalegria.intra.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lacosdaalegria.intra.comunication.JavaMail;
import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.OuvidoriaDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegiaoAdministrativaDAO;
import com.lacosdaalegria.intra.hibernate.dao.ResetSenhaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.ResetSenha;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.CodigoAtivacao;
import com.lacosdaalegria.intra.model.UserDetail;
import com.lacosdaalegria.intra.s3.S3;


@Controller
public class VoluntarioController {

	
	//Controller responsavel por abrir pagina de login da aplicacao.
	@RequestMapping("/")
	public String entrarSistema(Model model, HttpSession session){
		
		UserDetail Detail = new UserDetail();
		
		if (session.getAttribute("logado")!= null){
		
			if(Detail.user(session).getUser().isNovato()){
				
				return "redirect:area-novato";
				
				}else{
				
				 return "redirect:area-voluntario";
					
				}
		}
		
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("sucess", session.getAttribute("sucess"));
		
		session.invalidate();
	
		return "login";
	}
	
	//Controller responsavel por abrir pagina de cadastro de voluntario.
	@RequestMapping("cadastroVoluntario")
	public String mostrarPaginaCadastroVoluntario(Model model, HttpSession session){
		
		RegiaoAdministrativaDAO raDao = new RegiaoAdministrativaDAO();
		
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		model.addAttribute("regioes", raDao.listaRegiaoAdministrativa());
		
		session.invalidate();
		
		return "register";
	}
	
	//Controller responsavel por persistir o voluntario.
	@RequestMapping("cadastrar")
	public String cadastrarVoluntario (Voluntario voluntario, @RequestParam("preferenciaId") int preferencia,
			@RequestParam("regiaoId") int regiao, HttpSession session) {
		
		voluntario.hashSenhas();
		
		AtividadeDAO aDao = new AtividadeDAO();
		
		voluntario.setPreferencia(aDao.AtividadePelaId(preferencia));
		
		RegiaoAdministrativaDAO regDao = new RegiaoAdministrativaDAO();
		
		voluntario.setRegiao(regDao.RegiaoAdministrativaPelaId(regiao));
		
		if (voluntario.validaCadastro()){
			
			VoluntarioDAO dao = new VoluntarioDAO();
			UserDetail Detail = new UserDetail();
//			MaisLacosDAO maisLacosDao = new MaisLacosDAO();
			
			voluntario = dao.addVoluntario(voluntario);	
			
			Detail.setUser(voluntario);		
//			Detail.setMaisLacos(maisLacosDao.getMaisLacos(voluntario.getId()));
			Detail.setPrimeiro_acesso(true);
			session.setAttribute("logado", "true");
			session.setAttribute("userDetail", Detail);
			session.setAttribute("atendidos", 0);

//			maisLacosDao.close();
			
			return "redirect:area-novato";
			
			
		} else {
		
			session.setAttribute("error", voluntario.getErro());
			
			return "redirect:cadastroVoluntario";
			
		}
		
	}
	
	//Controller responsavel por chamar controller de resetar senha.
	@RequestMapping("resetarSenha")
	public ModelAndView resetarSenha(@RequestParam("token") String token, Voluntario voluntario, HttpSession session){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		ResetSenhaDAO daoR = new ResetSenhaDAO();
		
		ResetSenha reset = daoR.validaToken(token);
		
		if(reset.getVoluntario() == null){
			
			session.setAttribute("error", "Token fornecido está Expirado!");
			
			return new ModelAndView("redirect:/resetSenha?token="+token);
			
		} else {
			
			if(voluntario.validaSenha()){
				
				Voluntario vol = reset.getVoluntario();
				
				vol.atualizaSenha(voluntario);
				
				dao.updateVoluntario(vol);
				
				daoR.invalidaToken(token);
				
				vol.setSenha(voluntario.getSenha());
				
				this.efetuaLogin(vol, session);
			
			} else {
				
				session.setAttribute("error", voluntario.getErro());
				
				return new ModelAndView("redirect:/resetSenha?token="+token);
			}
			
			return new ModelAndView("redirect:/");
			
		}
	
	}
	
	@RequestMapping("resetSenha")
	public String resetarSenha(@RequestParam("token") String token, Model model, HttpSession session){
		
		model.addAttribute("error", session.getAttribute("error"));
		model.addAttribute("token", token);
		
		session.invalidate();
		
		return "resetSenha";
	
	}
	
	//Controller responsavel por invalidar a sessão e fazer logout do usuario.
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		
		request.getSession().invalidate();
	
	return "redirect:/";
	
	}
	
	//Controller responsavel validar e efetuar login.
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Voluntario voluntario, HttpSession session){
		
		VoluntarioDAO userDao = new VoluntarioDAO();

		voluntario = userDao.login(voluntario);
		
		if(voluntario != null){
			
//			MaisLacosDAO maisLacosDao = new MaisLacosDAO();
			UserDetail detail = new UserDetail();
//			
//			detail.setMaisLacos(maisLacosDao.getMaisLacos(voluntario.getId()));
			//Detail.setFaltante(userDao.faltou(Detail.getUser().getId()));
			
			if(voluntario.estaDesativado() && !voluntario.isNovato()){
				voluntario.setStatus(1);
				userDao.updateVoluntario(voluntario);
			}
			
			voluntario.iniciaDemandas();
			voluntario.iniciaMaisLacos();
			
			detail.setUser(voluntario);
			
			OuvidoriaDAO ouviDao = new OuvidoriaDAO();
			
			session.setMaxInactiveInterval(3600);
			session.setAttribute("userDetail", detail);
			session.setAttribute("logado", true);
			session.setAttribute("atendidos", ouviDao.meusAtendimentosAtendidos(voluntario));
			
			if (voluntario.isNovato()){
				return "redirect:area-novato";	
			} else {
				return "redirect:area-voluntario";	
			} 
			
		} 
		
		session.setAttribute("error", "A combinação Login, Senha esta Errada!");
		
		return "redirect:/";
		
	}	
	
	@RequestMapping("promoverNovato")
	public String promoverNovato(@RequestParam("codigo") String codigo, HttpSession session){
		
		if(codigo.equals(CodigoAtivacao.codigoAtivacao)){
			
			VoluntarioDAO dao = new VoluntarioDAO();
			UserDetail userDetail = new UserDetail();
			
			Voluntario voluntario = userDetail.user(session).getUser();
			
			voluntario.setNovato(false);
		
			dao.updateVoluntario(voluntario);
			
			session.invalidate();
			
			this.efetuaLogin(userDetail.user(session).getUser(), session);
			
			return "redirect:area-voluntario";
		
		}
		
		return "redirect:area-novato";
	}
	
	@RequestMapping("recuperaSenha")
	public String recuperaSenha(@RequestParam("email") String email, HttpSession session){

		Voluntario voluntario = new Voluntario(); 
		JavaMail mail = new JavaMail();
		VoluntarioDAO dao = new VoluntarioDAO();
		ResetSenhaDAO daoR = new ResetSenhaDAO();
		
		voluntario = dao.VoluntarioPeloEmail(email);
		
		if (voluntario == null) {
			
			return "redirect:/"; 
		}
		
		session.setAttribute("sucess", "E-mail enviado com sucesso!");
		
		mail.emailRecuperaSenha(voluntario, daoR.criaTokenResetSenha(voluntario));
		
		return "redirect:/";
	   
	}
	
	
	@RequestMapping("atualizaInfo")
	public String atualizaInfo(Voluntario voluntario, @RequestParam("regiaoId") int regiao, HttpSession session){
		
		UserDetail userDetail = new UserDetail();
		
		RegiaoAdministrativaDAO regDao = new RegiaoAdministrativaDAO();
		
		voluntario.setRegiao(regDao.RegiaoAdministrativaPelaId(regiao));
		
		voluntario.setId(userDetail.user(session).getUser().getId());
		
		if(voluntario.validaAtualizacao()){
		
			VoluntarioDAO dao = new VoluntarioDAO();
			
			userDetail.user(session).getUser().atualizaInformacao(voluntario);
			
			dao.updateVoluntario(userDetail.user(session).getUser());
			
			return "redirect:profile";
		
		} else {
			
			session.setAttribute("error", voluntario.getErro());
			
			return "redirect:profile";
		}
			  
	}
	
	@RequestMapping("desativaNovato")
	public String desativaNovato(@RequestParam("userId") int novatoId,
			@RequestParam("observacao") String observacao, 
			HttpSession session){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		UserDetail userDetail = new UserDetail();
		
		Voluntario novato = dao.VoluntarioPelaId(novatoId);
		
		if(userDetail.user(session).getUser().getId().equals(novato.getResponsavel().getId())){
		
			novato.setStatus(2);
			
			dao.updateVoluntario(novato);
		
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
			  
	}
	
	@RequestMapping("ativaNovato")
	public String ativaNovato(HttpSession session){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		UserDetail userDetail = new UserDetail();
		
		Voluntario novato = dao.VoluntarioPelaId(userDetail.user(session).getUser());
		
		novato.setStatus(1);
		
		dao.updateVoluntario(novato);
		
		return "redirect:area-novato";
			  
	}
	
	@RequestMapping("atualizaPreferencia")
	public String atualizaPreferencia(@RequestParam("preferencia") int id, HttpSession session){
		
		if(id != 0){
			
			VoluntarioDAO dao = new VoluntarioDAO();
			UserDetail userDetail = new UserDetail();
			AtividadeDAO ativDao = new AtividadeDAO();
			
			userDetail.user(session).getUser().setPreferencia(ativDao.AtividadePelaId(id));
			
			dao.updateVoluntario(userDetail.user(session).getUser());
			
		}
		
		return "redirect:area-novato";
			  
	}
	
	@RequestMapping("editaNovato")
	public String editaNovato(@RequestParam("userId") int novatoId,
			@RequestParam("observacao") String observacao, 
			HttpSession session){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		UserDetail userDetail = new UserDetail();
		
		Voluntario novato = dao.VoluntarioPelaId(novatoId);
		
		if( userDetail.user(session).getUser().getId().equals(novato.getResponsavel().getId())){
		
			novato.setObservacao(observacao);
			
			dao.updateVoluntario(novato);
		
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
			  
	}
	
	@RequestMapping("adicionarResponsavel")
	public String adicionarResponsavel(@RequestParam("novatoId") int novatoId, HttpSession session){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		UserDetail userDetail = new UserDetail();
		
		Voluntario novato = dao.VoluntarioPelaId(novatoId);
		
		if(novato.getResponsavel() == null){
			
			novato.setResponsavel(userDetail.user(session).getUser());
			
			dao.updateVoluntario(novato);
			
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
			  
	}
	
	@RequestMapping("retirarResponsavel")
	public String retirarResponsavel(@RequestParam("novatoId") int novatoId, HttpSession session){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		UserDetail userDetail = new UserDetail();
		
		Voluntario novato = dao.VoluntarioPelaId(novatoId);
		
		if(userDetail.user(session).getUser().getId().equals(novato.getResponsavel().getId())){
			
			novato.setResponsavel(null);
			
			dao.updateVoluntario(novato);
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
			  
	}
	
	@RequestMapping(value = "atualizaProfilePic", method = RequestMethod.POST)
	public String atualizaProfilePic(@RequestParam("foto") MultipartFile foto, HttpSession session, Model model) throws IOException{
		
		if((foto.getSize()/1024) > 500 || foto.isEmpty()){
			return "redirect:profile";
		}
		
		UserDetail userDetail = new UserDetail();
		VoluntarioDAO dao = new VoluntarioDAO();
		S3 s3 = new S3();
		
		String nomeFoto = null;
		
		try {

			nomeFoto = s3.carregaImagem("pic", userDetail.getUserId(session), foto);
			
		} catch (Exception e) {
			
			System.out.println(e);

			return "redirect:profile";
		}
        
		Voluntario voluntario = userDetail.user(session).getUser();
		
		voluntario.setProfile(nomeFoto);
		
		dao.updateVoluntario(voluntario);
		
		return "redirect:profile";
			  
	}
	
	@RequestMapping("aceitarTermo")
	public String aceitarTermo(@RequestParam("aceite") boolean aceite, HttpSession session){
		
		UserDetail userDetail = new UserDetail();
		
		Voluntario voluntario = userDetail.user(session).getUser();
		
		if(aceite && !voluntario.isAceitou_termo()){
			
			voluntario.aceitaTermo();
			
			VoluntarioDAO dao = new VoluntarioDAO();
			
			dao.updateVoluntario(voluntario);
			
			userDetail.user(session).setUser(voluntario);
		}
		
		
		
			if (voluntario.isNovato()){
				return "redirect:area-novato";	
			} else {
				return "redirect:area-voluntario";	
			} 
			  
	}
	
}
		
	
