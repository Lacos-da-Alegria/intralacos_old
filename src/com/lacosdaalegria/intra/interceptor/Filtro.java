package com.lacosdaalegria.intra.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class Filtro {
	
	private static List<String> novato = new ArrayList<String>();
	private static List<String> voluntario = new ArrayList<String>();
	private static List<String> coordenador = new ArrayList<String>();
	private static List<String> controleNovato = new ArrayList<String>();
	private static List<String> controleDemandas = new ArrayList<String>();
	private static List<String> controleDemandasLider = new ArrayList<String>();
	private static List<String> diretores = new ArrayList<String>();
	private static List<String> equipeOng = new ArrayList<String>();
	private static List<String> diretorOngs = new ArrayList<String>();
	private static List<String> AtivIdImporta = new ArrayList<String>();
	
	public static List<String> todos = new ArrayList<String>();
	
	private static HashMap<Integer, List<String>> mapaFiltro = new HashMap<Integer, List<String>>();
	
	public static List<String> listaAcesso(Voluntario vol, int equipeId){
		
		if(vol.isNovato()){
			return novato;
		}
		
		if(equipeId != 0){
			if(vol.lideraEquipe(equipeId)){
				return controleDemandasLider;
			} else {
				return controleDemandas;
			}
		}
		
		if(vol.getMembro_atividade() != null && vol.getMembro_atividade().isCoordenador()){
			return coordenador;
		}
		
		if(vol.acessoPolo()){
			return equipeOng;
		}
		
		if(vol.acessoControleNovatos()){
			return controleNovato;
		} 
		
		if(vol.getDiretoria()!= null){
			if(vol.getDiretoria().ehOngs()){
				return diretorOngs;
			} else {
				return diretores;
			}
		}
		
		return voluntario;
	}
	
	
	public static List<String> getMapaFiltro(int acesso) {
		return mapaFiltro.get(acesso);
	}


	public Filtro(){
		
		List<String> logado = new ArrayList<String>();
		
		//Paginas que todos podem acessar mesmo não logado
		todos.add("/");
		todos.add("cadastroVoluntario");
		todos.add("cadastrar");
		todos.add("recuperaSenha");
		todos.add("efetuaLogin");
		todos.add("logout");
		todos.add("resetSenha");
		todos.add("resetarSenha");
		
		//Paginas que todos podem acessar porem somente se estiver logado
		
		logado.add("profile");
		logado.add("atualizaInfo");
		logado.add("historiaLacos");
		logado.add("FAQ");
		logado.add("atualizaProfilePic");
		logado.add("Contatos");
		logado.add("LojinhaLacos");
		logado.add("comoPossoAjudar");
		logado.add("Inventario");
		logado.add("TermoAdesao");
		logado.add("aceitarTermo");
		logado.add("LeituraTermo");
		logado.add("feedback");
		logado.add("Ouvidoria");
		logado.add("abrirAtendimeto");
						
		//Paginas que novatos podem acessar
		
		novato.add("construcaonovato");
		novato.add("area-novato");
		novato.add("promoverNovato");
		novato.add("ativaNovato");
		novato.add("atualizaPreferencia");
		novato.addAll(logado);
				
		//Paginas que voluntarios que não são mais novatos podem acessar

		voluntario.add("area-voluntario");
		voluntario.add("construcao");
		voluntario.add("inscrever");
		voluntario.add("cancelar");
		voluntario.add("mudar");
		voluntario.add("desativarApoio");
		voluntario.add("ativarApoio");
		voluntario.add("listaAtividade");
		voluntario.add("AniversariantesDoMes");
		voluntario.add("feedbackNovato");
		
		voluntario.add("Atendimento");
		voluntario.add("capturarAtend");
		voluntario.add("descapturarAtend");
		voluntario.add("reponderOuvidoria");
		
		voluntario.addAll(logado);
		
		//Paginas que voluntarios que somente coordenadores podem acessar
		
		coordenador.add("fazerChamada");
		coordenador.add("atualizaAtividade");
		coordenador.add("statusAtividade");
		coordenador.add("detalheAtividade");
		coordenador.add("adicionarApoio");
		coordenador.add("cadastrarNotificacao");
		coordenador.add("insertNotif");
		coordenador.add("retirarApoio");
		coordenador.add("fazerChamadaNovato");
		coordenador.addAll(voluntario);
		
		//Paginas que voluntarios que somente a equipe de Controle de Novatos podem acessar
		
		controleNovato.add("controleNovatos");
		controleNovato.add("inscreverNovato");
		controleNovato.add("cancelarNovato");
		controleNovato.add("editaNovato");
		controleNovato.add("desativaNovato");
		controleNovato.add("adicionarResponsavel");
		controleNovato.add("retirarResponsavel");
		controleNovato.addAll(voluntario);
		
		//Paginas que voluntarios que somente a equipe de Controle de Novatos podem acessar
		
		controleDemandas.add("ControleDemandas");
		controleDemandas.add("cadastrarDemanda");
		controleDemandas.add("capturaDemanda");
		controleDemandas.add("descapturaDemanda");
		controleDemandas.add("adicionarPendencia");
		controleDemandas.add("resolverPendencia");
		controleDemandas.add("concluirDemanda");
		controleDemandas.add("adicionarNota");
		controleDemandas.add("reabrirDemanda");
		controleDemandas.add("Notas");
		
		
		//Filtro demanda lider
		
		controleDemandasLider.add("adicionarMembro");
		controleDemandasLider.add("retirarMembro");
		controleDemandasLider.add("arquivarDemanda");
		controleDemandasLider.add("atualizarEquipe");
		controleDemandasLider.add("designarDemanda");
		controleDemandasLider.addAll(controleDemandas);
		
		AtivIdImporta.addAll(controleNovato);
		AtivIdImporta.addAll(coordenador);
		
		diretores.addAll(AtivIdImporta);
		diretores.addAll(controleDemandasLider);
		diretores.add("hospitais");
		
		equipeOng.add("CadastroInstituicao");
		equipeOng.add("cadastrarInstituicao");
		equipeOng.add("AtualizaInstituicao");
		equipeOng.add("atualizarInstituicao");
		equipeOng.add("InstituicoesOngs");
		equipeOng.add("DetalheInstituicao");
		equipeOng.add("CalendarioOngs");
		equipeOng.add("pesquisaAgenda");
		equipeOng.add("cancelarAcao");
		equipeOng.add("agendarAcao");
		equipeOng.addAll(voluntario);
		
		diretorOngs.add("EquipeOngs");
		diretorOngs.add("cadastrarRegional");
		diretorOngs.add("adicionarEquipeOngs");
		diretorOngs.add("liberarRA");
		diretorOngs.add("adcionarRegiao");
		diretorOngs.addAll(equipeOng);
		diretorOngs.addAll(coordenador);
		diretorOngs.addAll(controleDemandasLider);
		
		//Mapeamento de Lista com Acessos
		mapaFiltro.put(0, novato);
		mapaFiltro.put(1, voluntario);
		mapaFiltro.put(11, controleNovato);
		mapaFiltro.put(12, equipeOng);
		mapaFiltro.put(22, controleDemandas);
		mapaFiltro.put(23, controleDemandasLider);
		mapaFiltro.put(24, AtivIdImporta);
		mapaFiltro.put(33, coordenador);
		mapaFiltro.put(42, diretores);
		mapaFiltro.put(423, diretorOngs);
		
	}
	
}
