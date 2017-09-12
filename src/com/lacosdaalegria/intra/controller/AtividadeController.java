package com.lacosdaalegria.intra.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lacosdaalegria.intra.hibernate.dao.AtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.RegistroAtividadeDAO;
import com.lacosdaalegria.intra.hibernate.dao.SemanaDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Atividade;
import com.lacosdaalegria.intra.hibernate.model.RegistroAtividade;
import com.lacosdaalegria.intra.hibernate.model.Semana;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.FilaAtividade;
import com.lacosdaalegria.intra.model.UserDetail;
import com.lacosdaalegria.intra.s3.S3;


@Controller
public class AtividadeController {

	@RequestMapping("inscrever")
	public String inscrever(@RequestParam("atividade") int id, HttpSession session) {
		
		SemanaDAO semanaDao = new SemanaDAO();
		
		Semana semana = semanaDao.ultimaSemana();
		
		RegistroAtividadeDAO registroDao = new RegistroAtividadeDAO();
		
		UserDetail detail = new UserDetail();
		
		AtividadeDAO atividadeDao = new AtividadeDAO();
		Atividade atividade = atividadeDao.AtividadePelaId(id);
		
		if(atividade.podeIncrever())
			return "redirect:area-voluntario";
		
		Voluntario voluntario = detail.user(session).getUser();
		
		RegistroAtividade registro = new RegistroAtividade(atividade, voluntario, semana);
		
		//Verifica se o usuario pode se inscrever
		if(registro.podeInscrever(registroDao.listaRegistroAtividade(voluntario, semana))){
			
			if(detail.user(session).isFaltante()){
				registro.setPosicao(10001);
			}
			registroDao.addRegistroAtividade(registro);
		}
		
		return "redirect:area-voluntario";
		
	}
	
	@RequestMapping("cancelar")
	public String cancelar(@RequestParam("atividade") int registroId, HttpSession session) {
		
		RegistroAtividadeDAO registroDao = new RegistroAtividadeDAO();
		
		RegistroAtividade registro = registroDao.RegistroAtividadePelaId(registroId);
		
		if(registro.podeCancelar()){
			
			registro.cancelarRegistro();
			
			registroDao.updateRegistroAtividade(registro);
			
			//===================== Falta implementar =====================
			//Enviar mensagem para voluntario que entrar na atividade caso
			//isso aconteça
			
			//bot.enviaMensagemFila();
			
		}
		
		return "redirect:area-voluntario";

	}
	
	
	@RequestMapping("detalheAtividade")
	public String detalheAtividade(Model model, HttpSession session, @RequestParam("ativId") int id) {
		
		/*----------------------------------------------------------------------------
		 * Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		UserDetail userDetail = new UserDetail();
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("atividades", Atividade.atividadeAtivas());
		
		/*----------------------------------------------------------------------------
		 * Fim Model comuns a toda request que renderizar uma JSP
		------------------------------------------------------------------------------*/
		
		AtividadeDAO atividadeDao = new AtividadeDAO();
		Atividade atividade = atividadeDao.AtividadePelaId(id);
		FilaAtividade fila = new FilaAtividade(atividade);
		
		model.addAttribute("fila", fila);
		model.addAttribute("atividade", atividade);
		
		atividade.inicializaMembros();
		
		//Informações detalhes da atividae
		session.setAttribute("currentpage", "detalheAtividade?ativId="+id);
		
		return "activity-area";
		
	}
	
	@RequestMapping("fazerChamada")
	public String fazerChamada(@RequestParam(value = "foram", required=false) List<Integer> foram, 
			@RequestParam(value = "naoforam", required=false) List<Integer> naoforam, 
			@RequestParam("ativId") int id, HttpSession session) {
		
		AtividadeDAO atividadeDao = new AtividadeDAO();
		Atividade atividade = atividadeDao.AtividadePelaId(id);
		
		if(atividade.getChamada_liberada()){
		
			RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
			
			if(foram != null){
				for(int registroId : foram){
					
					RegistroAtividade registro = regDao.RegistroAtividadePelaId(registroId);
					
					registro.voluntarioFoi();
					
					regDao.updateRegistroAtividade(registro);
					
		
				}
			}
			
			if(naoforam != null){
				for(int registroId : naoforam){
					
					RegistroAtividade registro = regDao.RegistroAtividadePelaId(registroId);
					
					registro.voluntarioFaltou();
					
					regDao.updateRegistroAtividade(registro);
		
				}
			}
			
			FilaAtividade fila = new FilaAtividade(atividade);
			
			if(fila.chamadaRealizada()){
				
				atividade.setChamada_liberada(false);
				
				if(atividade.ehAcao())
					atividade.setStatus(0);
				
				atividadeDao.updateAtividade(atividade);
				
			}
			

		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");

		
	}
	
	@RequestMapping("fazerChamadaNovato")
	public String fazerChamadaNovato(@RequestParam(value = "foram", required=false) List<Integer> foram, 
			@RequestParam(value = "naoforam", required=false) List<Integer> naoforam, 
			@RequestParam("ativId") int id, HttpSession session) {
		
		AtividadeDAO atividadeDao = new AtividadeDAO();
		Atividade atividade = atividadeDao.AtividadePelaId(id);
		
		if(atividade.getChamada_liberada()){
			
			RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
			VoluntarioDAO voluntDao = new VoluntarioDAO();
			
			if(foram != null){
				for(int registroId : foram){
					
					RegistroAtividade registro = regDao.RegistroAtividadePelaId(registroId);
					
					registro.voluntarioFoi();
					
					registro.getVoluntario().promoverNovato();
					
					regDao.updateRegistroAtividade(registro);
						
					voluntDao.updateVoluntario(registro.getVoluntario());
					
				}
			}
			
			if(naoforam != null){
				for(int registroId : naoforam){
					
					RegistroAtividade registro = regDao.RegistroAtividadePelaId(registroId);
					
					registro.voluntarioFoi();
					
					registro.getVoluntario().novatoFaltou();
					
					regDao.updateRegistroAtividade(registro);
						
					voluntDao.updateVoluntario(registro.getVoluntario());
		
				}
			}
			
			FilaAtividade fila = new FilaAtividade(atividade);
			
			if(fila.chamadaRealizada()){
				
				atividade.setChamada_liberada(false);
				
				if(atividade.ehAcao())
					atividade.setStatus(0);
				
				atividadeDao.updateAtividade(atividade);
				
			}

		}

		return "redirect:"+(String)session.getAttribute("currentpage");

		
	}

	
	@RequestMapping("atualizaAtividade")
	public String atualizaAtividade(Atividade atividade, @RequestParam("ativId") int id, HttpSession session) {

		AtividadeDAO dao = new AtividadeDAO();
		
		dao.updateAtividade(dao.AtividadePelaId(id).atualizaInfo(atividade));
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
		
	}
	
	@RequestMapping("listaAtividade")
	public String listaAtividade(@RequestParam("atividade") int id, Model model, HttpSession session) {
		
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
		Atividade atividade = ativDao.AtividadePelaId(id);
		
		model.addAttribute("atividade", atividade);
			
			if(atividade.rodadaRandomica()){
				
				model.addAttribute("titulo", "A lista ficará disponível a partir de Quarta-Feira");
				
			} else {
					
				FilaAtividade fila = new FilaAtividade(atividade);
				
				model.addAttribute("titulo", "Lista de Voluntários - " + atividade.getTag());
				model.addAttribute("fila", fila);
				
			}
			
		session.setAttribute("currentpage", "ListaAtividade?atividade="+id);
		
		return "lista_atividade";
		
	}
	
	@RequestMapping("statusAtividade")
	public String statusAtividade(@RequestParam("ativId") int ativid, @RequestParam("senha") String senha,
			HttpSession session, @RequestParam("acao") int acao) {

		AtividadeDAO dao = new AtividadeDAO();
		UserDetail userDetail = new UserDetail();
		VoluntarioDAO voluntDao = new VoluntarioDAO();
		
		userDetail.user(session).getUser().setSenha(senha);
		
		if(voluntDao.login(userDetail.user(session).getUser()) != null){
		
			Atividade atividade = dao.AtividadePelaId(ativid);
			
			atividade.setStatus(acao);
			
			dao.updateAtividade(atividade);
		
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
		
	}
	
	@RequestMapping("inscreverNovato")
	public String inscreverNovato(@RequestParam("ativId") int id, HttpSession session, 
			@RequestParam("novatoId") int novatoId, HttpSession sesion) {
		
		AtividadeDAO ativDao = new AtividadeDAO();
		Atividade atividade = ativDao.AtividadePelaId(id);
		UserDetail userDetail = new UserDetail();
		
		RegistroAtividadeDAO regDao = new RegistroAtividadeDAO();
		Semana semana = Semana.getInstance();
		
		int novatos_inscritos = (regDao.novatosAtividade(atividade, semana) == null ? 0 : regDao.novatosAtividade(atividade, semana).size());
		
		if(atividade.getChamada_liberada() ||  novatos_inscritos >= atividade.getLimite_novato()){
			
			return "redirect:"+(String)session.getAttribute("currentpage");			
		}
		
		
		VoluntarioDAO volDao = new VoluntarioDAO();
		Voluntario voluntario = volDao.VoluntarioPelaId(novatoId);
		
		if( userDetail.user(session).getUser().getId().equals(voluntario.getResponsavel().getId())){			
			
			RegistroAtividade registro = new RegistroAtividade(atividade, voluntario, semana);
			regDao.addRegistroAtividade(registro);
			
		}
			
			return "redirect:"+(String)session.getAttribute("currentpage");		
		
	}
	
	@RequestMapping("cancelarNovato")
	public String cancelarNovato(@RequestParam("novatoId") int novatoId, HttpSession session) {
		
		RegistroAtividadeDAO registroDao = new RegistroAtividadeDAO();
		
		RegistroAtividade registro = registroDao.RegistroNovato(novatoId);
		
		if(!registro.podeCancelar()){
			
			return "redirect:"+(String)session.getAttribute("currentpage");
		
		}
		
		registro.cancelarRegistro();
		
		registroDao.updateRegistroAtividade(registro);
		
		return "redirect:"+(String)session.getAttribute("currentpage");				
		
	}
	
	
	@RequestMapping("hospitais")
	public String listaHospitais(Model model, HttpSession session) {
		
		AtividadeDAO dao = new AtividadeDAO();
		
		model.addAttribute("hospitais", dao.listaAtividades());
		
		return "hospitais";				
		
	}
	
	@RequestMapping(value = "atualizaImagemAtiv", method = RequestMethod.POST)
	public String atualizaProfilePic(@RequestParam("foto") MultipartFile foto, HttpSession session, @RequestParam("ativ_id") Integer id) throws IOException{
		
		if((foto.getSize()/1024) > 500 || foto.isEmpty()){
			return "redirect:profile";
		}
		
		AtividadeDAO dao = new AtividadeDAO();
		S3 s3 = new S3();
		
		Atividade atividade = dao.AtividadePelaId(id);
		
		String nomeFoto = null;
		
		try {

			nomeFoto = s3.carregaImagem("pic", atividade.getTag(), foto);
			
		} catch (Exception e) {
			
			System.out.println(e);

			return "redirect:hospitais";
		}
        
		atividade.setImagem(nomeFoto);
		
		dao.updateAtividade(atividade);
		
		return "redirect:hospitais";
			  
	}
	
	
	
	
}
