	package com.lacosdaalegria.intra.controller;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.comunication.JavaMail;
import com.lacosdaalegria.intra.dao.MaisLacosDAO;
import com.lacosdaalegria.intra.dao.RegistroAtividadeDAO;
import com.lacosdaalegria.intra.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.model.UserDetail;
import com.lacosdaalegria.intra.model.Voluntario;

@Controller
public class VoluntarioController {

	
	//Controller responsavel por abrir pagina de login da aplicacao.
	@RequestMapping("/")
	public String entrarSistema(HttpSession session){
		
		UserDetail Detail = new UserDetail();
		
		if (session.getAttribute("logado")!= null){
		
		if(Detail.user(session).getUser().getAcesso() == 0){
			
			return "redirect:area-novato";
			
			}else{
			
					return "redirect:area-voluntario";
				
			}
		}
	
		return "login";
	}
	
	//Controller responsavel por abrir pagina de cadastro de voluntario.
	@RequestMapping("cadastroVoluntario")
	public String mostrarPaginaCadastroVoluntario(){
		
		return "register";
	}
	
	//Controller responsavel por persistir o voluntario.
	@RequestMapping("cadastrar")
	public String cadastrarVoluntario (Voluntario voluntario,  HttpSession session) throws ClassNotFoundException{
		
		VoluntarioDAO dao = new VoluntarioDAO();
		UserDetail Detail = new UserDetail();
		MaisLacosDAO maisLacosDao = new MaisLacosDAO();

		if (dao.existeLogin(voluntario)){
			
			return "redirect:cadastroVoluntario";
			
		} else 
			
		dao.adicionaVoluntario(voluntario);	
		
		Detail.setUser(voluntario);		
		Detail.setMaisLacos(maisLacosDao.getMaisLacos(voluntario.getUserid()));
		Detail.setAtividadesAtuais(null);	
		session.setAttribute("logado", "true");
		session.setAttribute("userDetail", Detail);
		
		return "redirect:area-novato";
		
	}
	
	//Controller responsavel por chamar controller de resetar senha.
	@RequestMapping("resetSenha")
	public String resetarSenha(){
	
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
		
		
		MaisLacosDAO maisLacosDao = new MaisLacosDAO();
		VoluntarioDAO userDao = new VoluntarioDAO();
		UserDetail Detail = new UserDetail();
		RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
		
		if(Detail.setUser(voluntario)){
			
			Detail.setMaisLacos(maisLacosDao.getMaisLacos(Detail.getUser().userid));
			Detail.atividadesAtuais.putAll(regDao.atividadesAtuais(Detail.getUser().userid));		
			Detail.periodoInscritos.putAll(regDao.periodosAtuais(Detail.getUser().userid));
			Detail.setFaltante(userDao.faltou(Detail.getUser().userid));
			session.setMaxInactiveInterval(-1);
			session.setAttribute("userDetail", Detail);
			session.setAttribute("logado", hashCode());
			
			if (Detail.getUser().acesso == 0){
				
				return "redirect:area-novato";	
				
			} else {
				
				return "redirect:area-voluntario";	
				
			} 
			
		} 
			
			return "redirect:/";

		
	}
	
	@RequestMapping("promover")	
	public String promove(Model model) {
		
		VoluntarioDAO dao = new VoluntarioDAO();
		
		model.addAttribute("novatos", dao.listarNovatos());
		
		return "promotor";
	}	
	
	@RequestMapping("promoverNovato")
	public String promoverNovato(@RequestParam("userid") List<String> usersid){
		
		VoluntarioDAO dao = new VoluntarioDAO();
		
		dao.atualizaAcesso((usersid.toString().replace("[", "(").replace("]", ")")));		
		
		return "redirect:promover";
	}
	
	@Async
	@RequestMapping("recuperaSenha")
	public Callable<String> recuperaSenha(@RequestParam("email") String email){
		
		 return new Callable<String>() {
			    public String call() throws Exception {
			    	
			    	Voluntario user = new Voluntario(); 
					JavaMail mail = new JavaMail();
					VoluntarioDAO dao = new VoluntarioDAO();
					
					user = dao.infoVoluntario(email);
					
					if (user.nome == null)
						return "redirect:/";

					mail.emailRecuperaSenha(email, user.getNome(), user.getSenha());
					
					return "redirect:/";
			   
			    }
			  };
			
		
	}
	
	@RequestMapping("atualizaInfo")
	public String atualizaInfo(Voluntario voluntario, HttpSession session){
		
		UserDetail userDetail = new UserDetail();
		VoluntarioDAO dao = new VoluntarioDAO();
		
		userDetail.user(session).getUser().setNome(voluntario.getNome());
		userDetail.user(session).getUser().setNome_doutor(voluntario.getNome_doutor());
		userDetail.user(session).getUser().setDt_Nascimento(voluntario.getDt_Nascimento());
		userDetail.user(session).getUser().setWhatsapp(voluntario.getWhatsapp());
		userDetail.user(session).getUser().setEndereco(voluntario.getEndereco());
		userDetail.user(session).getUser().setRegiao(voluntario.getRegiao());
		
		dao.atualizaInformacoes(voluntario, userDetail.user(session).getUser().userid);
				
		return "redirect:profile";
			  
	}
	
}
		
	
