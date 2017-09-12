package com.lacosdaalegria.intra.model;

public class InicializaDados {
	
	/*public static void main(String[] args) {
		VoluntarioDAO dao = new VoluntarioDAO();
		
		for(Voluntario vol : dao.listaVoluntario()){
			
			DiretoriaDAO daoD = new DiretoriaDAO();
			
			vol.setDiretoria(daoD.DiretoriaPeloTipo(1));
			dao.updateVoluntario(vol);
		}
		
		System.out.println("Fim");
	}*/
	
	/*
	public static void main(String[] args) {
		
		AtendimentoDAO adao = new AtendimentoDAO();
		Atendimento atend =  new Atendimento();
		
		atend.setNome("Genérico");
		
		atend = adao.addAtendimento(atend);
		
		CategoriaDAO cDao = new CategoriaDAO();
		
		Categoria cat = new Categoria();
		
		cat.setAtendimento(atend);
		
		cat.setNome("Dúvida sobre as Atividades");
		cDao.addCategoria(cat);
		
		cat.setNome("Dúvida sobre as Diretrizes");
		cDao.addCategoria(cat);
		
		cat.setNome("Dúvida sobre o IntraLaços");
		cDao.addCategoria(cat);
		
		cat.setNome("Problema no IntraLaços");
		cDao.addCategoria(cat);
		
		cat.setNome("Problema Geral");
		cDao.addCategoria(cat);
		
		SemanaDAO daoS = new SemanaDAO();
		Semana semana = new Semana();
		
		daoS.addSemana(semana);
		
		Diretoria diretoria = new Diretoria();
		DiretoriaDAO dao = new DiretoriaDAO();
		
		diretoria.setNome("Diretoria Executiva");
		diretoria.setTipo(1);
		
		dao.addDiretoria(diretoria);
		
		diretoria.setNome("Diretoria de Comunicação");
		diretoria.setTipo(2);
		
		dao.addDiretoria(diretoria);
		
		diretoria.setNome("Diretoria de ONG's");
		diretoria.setTipo(3);
		
		dao.addDiretoria(diretoria);
		
		diretoria.setNome("Diretoria de Hospitais");
		diretoria.setTipo(4);
		
		dao.addDiretoria(diretoria);
		
		RegiaoAdministrativaDAO raDao = new RegiaoAdministrativaDAO();
		List<String> ras = new ArrayList<>();
		 
		 //Primeira carga
		 ras.add("Águas Claras");
		 ras.add("Asa Norte");
		 ras.add("Asa Sul");
		 ras.add("Brazlándia"); 
		 ras.add("Candangolândia");
		 ras.add("Ceilândia");
		 ras.add("Cruzeiro");
		 ras.add("Estrutural");
		 ras.add("Fercal");
		 ras.add("Gama");
		 ras.add("Guará");
		 ras.add("Jardim Botânico"); 
		 ras.add("Itapoã");
		 ras.add("Lago Norte");
		 ras.add("Lagoa Sul");
		 ras.add("Núcleo Bandeirante");
		 ras.add("Paranoá");
		 ras.add("Park Way");
		 ras.add("Planaltina");
		 ras.add("Recanto das Emas");
		 ras.add("Riacho Fundo");
		 ras.add("Riacho Fundo II");
		 ras.add("Samambaia");
		 ras.add("Santa Maria");
		 ras.add("São Sebastião");
		 ras.add("Sobradinho");
		 ras.add("Sobradinho II");
		 ras.add("Taguatinga");
		 ras.add("Varjão");
		 ras.add("Vicente Pires");
		 ras.add("Entorno Saida Norte");
		 ras.add("Entorno Saida Sul");
	
		 for (String ra : ras){
			 
			 RegiaoAdministrativa ra1 = new RegiaoAdministrativa();
			 
			 ra1.setNome(ra);
			 
			 raDao.addRegiaoAdministrativa(ra1);
			 
		 }
		
		Atividade ativ = new Atividade();
		ativ.setStatus(1);
		ativ.setDescricao("Descrição Atividade");
		ativ.setEndereco("Endereço Atividade");
		ativ.setLocal_preparo("Local de Preparo");
		ativ.setHorario("Horario da Atividade");
		ativ.setDia(7);
		ativ.setPeriodo(2);
		ativ.setLimite_voluntario(22);
		ativ.setLimite_apoio(8);
		ativ.setLimite_novato(8);
		ativ.setTipo(1);
		ativ.setChamada_liberada(false);
		
		DiretoriaDAO daoDir = new DiretoriaDAO();
		
		ativ.setDiretoria(daoDir.DiretoriaPeloTipo(4));
		
		AtividadeDAO ativDao = new AtividadeDAO();
		
		ativ.setNome("Hospital Regional do Gama");
		ativ.setTag("HRG");
		
		ativDao.addAtividade(ativ);

		ativ.setNome("Hospital Regional de Taguatinga");
		ativ.setTag("HRT");
		
		ativDao.addAtividade(ativ);
		
		ativ.setNome("Hospital Universitário de Brasília");
		ativ.setTag("HUB");
		
		ativDao.addAtividade(ativ);
		
		ativ.setNome("Hospital das Forças Armadas");
		ativ.setTag("HFA");
		
		ativDao.addAtividade(ativ);
		
		ativ.setNome("Hospital Regional de Planaltina");
		ativ.setTag("HRP");
		
		ativDao.addAtividade(ativ);
		
		ativ.setNome("Hospital Regional de Sobradinho");
		ativ.setTag("HRS");
		
		ativDao.addAtividade(ativ);
		
		ativ.setNome("Hospital Regional da Asa Norte");
		ativ.setTag("HRAN");
		
		ativDao.addAtividade(ativ);

		System.out.println("Fim");

	}*/

	/*public static void main(String[] args) {
	
		
		AtendimentoDAO adao = new AtendimentoDAO();
		Atendimento atend =  new Atendimento();
		
		atend.setNome("Genérico");
		
		adao.addAtendimento(atend);
		
		CategoriaDAO cDao = new CategoriaDAO();
		
		Categoria cat = new Categoria();
		
		cat.setAtendimento(adao.listaAtendimento().get(0));
		
		cat.setNome("Dúvida sobre as Atividades");
		cDao.addCategoria(cat);
		
		cat.setNome("Dúvida sobre as Diretrizes");
		cDao.addCategoria(cat);
		
		cat.setNome("Dúvida sobre o IntraLaços");
		cDao.addCategoria(cat);
		
		cat.setNome("Problema no IntraLaços");
		cDao.addCategoria(cat);
		
		cat.setNome("Problema Geral");
		cDao.addCategoria(cat);
	
	}*/
	
	/*
	public static void main(String[] args) {
		Atividade ativ = new Atividade();
		ativ.setStatus(1);
		ativ.setDescricao("Descrição Atividade");
		ativ.setEndereco("Endereço Atividade");
		ativ.setLocal_preparo("Local de Preparo");
		ativ.setHorario("Horario da Atividade");
		ativ.setDia(7);
		ativ.setPeriodo(2);
		ativ.setLimite_voluntario(22);
		ativ.setLimite_apoio(8);
		ativ.setLimite_novato(8);
		ativ.setTipo(1);
		ativ.setChamada_liberada(false);
		
		DiretoriaDAO daoDir = new DiretoriaDAO();
		
		ativ.setDiretoria(daoDir.DiretoriaPeloTipo(4));
		
		AtividadeDAO ativDao = new AtividadeDAO();
		
		ativ.setNome("Lar Francisco de Assis");
		ativ.setTag("ACAO");
		
		ativDao.addAtividade(ativ);
		
	} */
}
