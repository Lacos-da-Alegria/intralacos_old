package com.lacosdaalegria.intra.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.dao.AtividadeDAO;
import com.lacosdaalegria.intra.dao.EquipeDAO;
import com.lacosdaalegria.intra.dao.RegistroAtividadeDAO;
import com.lacosdaalegria.intra.dao.ResumoAtivDAO;
import com.lacosdaalegria.intra.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.model.Atividade;
import com.lacosdaalegria.intra.model.DateHandler;
import com.lacosdaalegria.intra.model.HashMapAtividade;
import com.lacosdaalegria.intra.model.UserDetail;
import com.lacosdaalegria.intra.model.Voluntario;

@Controller
public class AtividadeController {

	@RequestMapping("inscrever")
	public String inscrever(@RequestParam("atividade") int atividade, HttpSession session) {
		
		RegistroAtividadeDAO dao = new RegistroAtividadeDAO();
		UserDetail userDetail = new UserDetail();
		DateHandler date = new DateHandler();
		boolean teste;
		
		if(userDetail.periodoInscritos.get(HashMapAtividade.periodos.get(atividade)) == null)
			teste = false;
		else
			teste = userDetail.atividadesAtuais.get(HashMapAtividade.periodos.get(atividade)) == null;
		
		if(teste||!dao.inscricaoAberta()){
			
			return "redirect:area-voluntario";
			
		}
			
		if(date.rodadaRandomica()) {
			dao.inscreverComPosicao(atividade, session);
		} else {
			dao.inscreverSemPosicao(atividade, session);
		}
		
		return "redirect:area-voluntario";
		
		
	}
	
	@RequestMapping("cancelar")
	public String cancelar(@RequestParam("atividade") int atividade, HttpSession session) {
		
		RegistroAtividadeDAO dao = new RegistroAtividadeDAO();
		
		if(!dao.calcelarAberta()){
			
			return "redirect:area-voluntario";
		
		}
		
		dao.cancelar(atividade, session);
		
		return "redirect:area-voluntario";
		
		
	}
	
	
	@RequestMapping("detalheAtividade")
	public String detalheAtividade(Model model, HttpSession session, @RequestParam("atividade") int atividade) {
		
		AtividadeDAO dao = new AtividadeDAO();
		UserDetail userDetail = new UserDetail();
		EquipeDAO apoios = new EquipeDAO();
		DateHandler data = new DateHandler();
		ResumoAtivDAO resumo = new ResumoAtivDAO();
		Atividade ativ = new Atividade();
		
		List<Voluntario> chamada = new ArrayList<Voluntario>();
		List<Voluntario> lista_voluntarios = new ArrayList<Voluntario>();
		List<Voluntario> lista_apoios = new ArrayList<Voluntario>();
		
		dao.filaAtividade(atividade, lista_voluntarios, lista_apoios);
		
		chamada.addAll(lista_voluntarios);
		chamada.addAll(lista_apoios);
		
		model.addAttribute("ativid", atividade);
		
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("voluntarios", lista_voluntarios);
		
		if(data.chamadaAberta())
			model.addAttribute("chamada", chamada);
		
		model.addAttribute("apoiosAtiv", lista_apoios);
		model.addAttribute("chamadaaberta", data.chamadaAberta());
		model.addAttribute("titulo", "Resumo da Atividade " + HashMapAtividade.tags.get(atividade));
		model.addAttribute("atividade", ativ.getAtividade(atividade));
		model.addAttribute("apoios", apoios.listaApoios(atividade));
		model.addAttribute("AtividadeAtivas", ativ.listaAtividade());
		model.addAttribute("todas_Ativs", HashMapAtividade.tags);
		
		session.setAttribute("currentpage", "detalheAtividade?atividade="+atividade);

		model.addAttribute("retorno", resumo.taxaRetorno(atividade));
		model.addAttribute("top10all", resumo.top10AllTimes(atividade));
		model.addAttribute("top10month", resumo.top10ThreeMonths(atividade));
		model.addAttribute("cadastros", resumo.cadastroMes());
		model.addAttribute("maislacos", userDetail.user(session).getMaisLacos());
		model.addAttribute("distribuicao", resumo.distruicaoVolunt(atividade));
		model.addAttribute("dist_ativ", resumo.distruicaoAtivid(atividade));
		model.addAttribute("frequencia_ativ", resumo.frequenciaVolunt(atividade));
		
		return "activity-area";
		
	}
	
	@RequestMapping("fazerChamada")
	public String fazerChamada(@RequestParam(value = "foram", required=false) List<Integer> foram, 
			@RequestParam(value = "naoforam", required=false) List<Integer> naoforam, HttpSession session) {
		
		RegistroAtividadeDAO dao = new RegistroAtividadeDAO();
		
		if(foram != null){
			for(int voluntario : foram){
					
				dao.chamadaUsuario(voluntario, 1);
	
			}
		}
		
		if(naoforam != null){
			for(int voluntario : naoforam){
				
				dao.chamadaUsuario(voluntario, 3);
	
			}
		}
		
		dao.close();


		return "redirect:"+(String)session.getAttribute("currentpage");

		
	}
	
	@RequestMapping("atualizaAtividade")
	public String atualizaAtividade(Atividade atividade, @RequestParam("ativid") int id, 
			@RequestParam("dia") int dia , HttpSession session) {

		AtividadeDAO dao = new AtividadeDAO();
		
		HashMapAtividade.nomes.put(id, atividade.getNome());
		HashMapAtividade.descricoes.put(id, atividade.getDescricao());
		HashMapAtividade.enderecos.put(id, atividade.getEndereco());
		HashMapAtividade.locais_preparos.put(id, atividade.getLocal_preparo());
		HashMapAtividade.horarios.put(id, atividade.getHorario());
		HashMapAtividade.periodos.put(id, atividade.getPeriodo()+(dia*10));
		
		HashMapAtividade.limites_voluntarios.put(id, atividade.getLimite_voluntario());
		HashMapAtividade.limites_apoios.put(id, atividade.getLimite_apoio());
		HashMapAtividade.limites_novatos.put(id, atividade.getLimite_novato());
		
		dao.atualizaAtividade(id);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
		
	}
	
	@RequestMapping("listaAtividade")
	public String listaAtividade(@RequestParam("atividade") int id, Model model, HttpSession session) {
		
		AtividadeDAO ativDao = new AtividadeDAO();
		UserDetail userDetail = new UserDetail();
		List<Voluntario> lista_voluntarios = new ArrayList<Voluntario>();
		List<Voluntario> lista_apoios = new ArrayList<Voluntario>();
		List<Voluntario> fila_espera = new ArrayList<Voluntario>();
		Atividade atividade = new Atividade();
		
		DateHandler date = new DateHandler();
		
		model.addAttribute("Atividade", atividade.listaAtividade());
		model.addAttribute("todas_Ativs", HashMapAtividade.tags);
			
			if(date.rodadaRandomica()){
				
				model.addAttribute("titulo", "A lista ficará disponível a partir de Quarta-Feira");
				
				model.addAttribute("voluntario", userDetail.user(session).getUser());
				
			} else {
		
		ativDao.filaAtividade(id, lista_voluntarios, lista_apoios, fila_espera);
		
		model.addAttribute("titulo", "Lista de Voluntários - " + HashMapAtividade.tags.get(id));
		model.addAttribute("voluntario", userDetail.user(session).getUser());
		model.addAttribute("voluntarios", lista_voluntarios);
		model.addAttribute("apoiosAtiv", lista_apoios);
		model.addAttribute("fila_espera", fila_espera);
		
			}
			
		session.setAttribute("currentpage", "ListaAtividade?atividade="+id);
		
		return "lista_atividade";
		
	}
	
	@RequestMapping("statusAtividade")
	public String statusAtividade(@RequestParam("atividade") int ativid, @RequestParam("senha") String senha,
			HttpSession session, @RequestParam("acao") int acao) {

		AtividadeDAO dao = new AtividadeDAO();
		UserDetail userDetail = new UserDetail();
		VoluntarioDAO voluntDao = new VoluntarioDAO();
		
		userDetail.user(session).getUser().setSenha(senha);
		
		if(voluntDao.login(userDetail.user(session).getUser())){
		
		dao.atualizaStatus(ativid, acao);
		
		HashMapAtividade.status_atividades.put(ativid, acao);
		}
		
		return "redirect:"+(String)session.getAttribute("currentpage");
		
		
	}
	
	
	
	
}
