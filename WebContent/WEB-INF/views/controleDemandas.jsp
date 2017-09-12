	<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="Laços da Alegria">
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="Laços da Alegria">
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <title>Laços da Alegria</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/to-do.css">
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">  

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=3xxlvr4x4mfh2h5wsc9vh3bdq5b2st3sxgv8iowhcxy83kbv"></script>
 	<script>tinymce.init({ selector:'textarea', menubar: false, height: 250 });</script>
    
    <link rel="shortcut icon" href="assets/img/favicon.png">
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <%@ include file="top_menu.jsp" %> 	
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
             	 <%@ include file="menu.jsp" %>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          	<h3><i class="fa fa-users hidden-phone" aria-hidden="true"></i> <b> ${equipe.nome}<b> 
          	<c:choose>
          		<c:when test="${lider}">
          			<button type="button" class="btn-round btn-info" data-toggle="modal" href="#ModalInfoLider"><i class="fa fa-info-circle" style="font-size:20px;" aria-hidden="true"></i></button>
          		</c:when>
          		<c:otherwise>
          			<button type="button" class="btn-round btn-info" data-toggle="modal" href="#ModalInfo"><i class="fa fa-info-circle" style="font-size:20px;" aria-hidden="true"></i></button>
          		</c:otherwise>
          	</c:choose>
          	
			</h3>
			<br>
			 <div>
			 	Legenda:
			 	<a style="font-size:13px;" class="btn btn-info btn-xs" ><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Capturar Demanda"><i class="fa fa-lock" aria-hidden="true"></i></li></a>
           		<a style="font-size:13px;" class="btn btn-primary btn-xs"><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Adicionar Nota"><i class="fa fa-plus" aria-hidden="true"></i></li></a>
	            <a style="font-size:13px;" class="btn btn-success btn-xs" ><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Concluir Demanda"><i class="fa fa-check" aria-hidden="true"></i></li></a>
	            <a style="font-size:13px;" class="btn btn-info btn-xs" ><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Adicionar Pendência"><i class="fa fa-flag" aria-hidden="true"></i></li></a>												                                                    
				<a style="font-size:13px;" class="btn btn-primary btn-xs"><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Designar Demanda"><i class="fa fa-share-square" aria-hidden="true"></i></li></a>
				<a style="font-size:13px;" class="btn btn-info btn-xs" ><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Resolve Pendência"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></li></a>
				<a style="font-size:13px;"  class="btn btn-warning btn-xs"> <li class="dropdown tooltips" style="list-style-type: none;" data-placement="left" data-original-title="Descapturar Demanda"><i class="fa fa-unlock-alt" aria-hidden="true"></i></li></a>
				<a style="font-size:13px;" class="btn btn-info btn-xs" ><li class="dropdown tooltips" style="list-style-type: none;" data-placement="right" data-original-title="Reabre Demanda"><i class="fa fa-undo" aria-hidden="true"></i></li></a>								
			  	<a style="font-size:13px;" class="btn btn-danger btn-xs"><li class="dropdown tooltips" style="list-style-type: none;" data-placement="left" data-original-title="Arquivar Demanda"><i class="fa fa-archive" aria-hidden="true"></i></li></a>
            </div>
          	<!-- SORTABLE TO DO LIST -->
			
              <div class="row mt mb">
                  <div class="col-md-6">
                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h5><i class="fa fa-tasks"></i> A Fazer - Demandas não Capturadas</h5></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">                              
							  <div class="task-content">
								<div style="height:248px;overflow:auto;">
                                 <ul id="sortable" class="task-list">	
                                 
                                 <c:forEach items="${todo}" var="demanda">
                                      <li class="list-${demanda.criticidade}">
                                          <a href="Notas?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}"><i class=" fa fa-ellipsis-v"></i></a>
                                          <div class="task-checkbox">
                                              <span class="badge bg-${demanda.criticidade}"><small>${demanda.dias_ate_fim_prazo}</small></span>
                                          </div>
                                          <div class="task-title">
                                              <span class="task-title-sp">${demanda.titulo}</span>
                                              <span class="label label-warning">Sem Responsánvel</span>
                                              <div class="pull-right hidden-phone">
                                                  <a onclick="clickAndDisable(this);" style="font-size:15px;" href="capturaDemanda?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}" class="btn btn-info btn-xs fa fa-lock"></a>
                                                  <c:if test="${lider}">
                              						<button onclick="modalDesignaDemanda(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-primary btn-xs fa fa-share-square"></button>
                              	                  </c:if>
                                              </div>
                                          </div>
                                      </li>
                                      </c:forEach>                                      
                                  </ul>
								</div>
                              </div>
                              <div class=" add-task-row">
                                  <a class="btn btn-success btn-sm pull-left" data-toggle="modal" href="#modalDemanda">Adicionar Demanda</a>
                                  <a class="btn btn-default btn-sm pull-right hidden-phone" >Ver Todo Histórico</a>
                              </div>
                          </div>
                      </section>
                  </div><!--/col-md-12 -->
				  
				   <div class="col-md-6">
                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h5><i class="fa fa-share"></i> Fazendo - Lista de Demandas Ativas</h5></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
							   <div style="height:278px;overflow:auto;">
                                  <ul id="sortable2" class="task-list">
                                     <c:forEach items="${doing}" var="demanda">
									  <li class="list-${demanda.criticidade}">
                                          <a href="Notas?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}"><i class=" fa fa-ellipsis-v"></i></a>
                                          <div class="task-checkbox">
                                              <span class="badge bg-${demanda.criticidade}"><small>${demanda.dias_ate_fim_prazo}</small></span>
                                          </div>
                                          <div class="task-title">
                                              <span class="task-title-sp">${demanda.titulo}</span>
											  	<span style="background-color:<c:out value="${demanda.responsavel.getHexCor()}"/>" class="label label-primary">${demanda.responsavel.getPrimerio_nome()}</span>
                                              <div class="pull-right hidden-phone">
                                              	  <button  onclick="modalAddNota(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-primary btn-xs fa fa-plus"></button>
                                                  <c:if test="${demanda.responsavel_id == voluntario.userid}">
	                                                  <button onclick="modalAddConclusao(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-success btn-xs fa fa-check" ></button>
	                                                  <button onclick="modalAddPendencia(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-info btn-xs fa fa-flag" ></button>												                                                    
												  </c:if>
												  <c:if test="${demanda.responsavel_id == voluntario.userid || lider}">
												  	<a style="font-size:13px;"  href="descapturaDemanda?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}" class="btn btn-warning btn-xs fa fa-unlock-alt" onclick="clickAndDisable(this);"></a>
												  </c:if>
                                              </div>
                                          </div>
                                      </li>
									 </c:forEach>
                                  </ul>
								 </div>
                              </div>
                          </div>
                      </section>
                  </div><!--/col-md-12 -->
                  
				  <div class="col-md-6">
                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h5><i class="fa fa-exclamation-triangle"></i> Pendentes - Lista de Demandas Com Pendências</h5></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
							   <div style="height:278px;overflow:auto;">
                                  <ul id="sortable3" class="task-list">
                                     <c:forEach items="${pending}" var="demanda">
									  <li class="list-${demanda.criticidade}">
                                          <a href="Notas?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}"><i class=" fa fa-ellipsis-v"></i></a>
                                          <div class="task-checkbox">
                                              <span class="badge bg-${demanda.criticidade}"><small>${demanda.dias_ate_fim_prazo}</small></span>
                                          </div>
                                          <div class="task-title">
                                              <span class="task-title-sp">${demanda.titulo}</span>
											  <span style="background-color:<c:out value="${demanda.responsavel.getHexCor()}"/>" class="label label-primary">${demanda.responsavel.getPrimerio_nome()}</span>
                                              <div class="pull-right hidden-phone">
                                              
                                              	  <button onclick="modalAddNota(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-primary btn-xs fa fa-plus"></button>
                                             <c:if test="${demanda.responsavel_id == voluntario.userid}">
	                                              <button onclick="modalAddConclusao(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-success btn-xs fa fa-check"></button>
												  <button onclick="modalAddSolucao(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-info btn-xs fa fa-thumbs-o-up"></button>												                                                    
												  <a style="font-size:13px;" href="descapturaDemanda?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}" class="btn btn-warning btn-xs fa fa-unlock-alt" onclick="clickAndDisable(this);"></a>
											 </c:if>
                                              </div>
                                          </div>
                                      </li>
									 </c:forEach> 
                                  </ul>
								 </div>
                              </div>
                          </div>
                      </section>
                  </div><!--/col-md-12 -->
				  
				  <div class="col-md-6">
                      <section class="task-panel tasks-widget">
	                	<div class="panel-heading">
	                        <div class="pull-left"><h5><i class="fa fa-check-square-o"></i> Concluídas - Lista de Demandas Concluídas</h5></div>
	                        <br>
	                 	</div>
                          <div class="panel-body">
                              <div class="task-content">
							   <div style="height:278px;overflow:auto;">
                                  <ul id="sortable4" class="task-list">
                                     <c:forEach items="${done}" var="demanda"> 
									  <li class="list-${demanda.criticidade}">
                                          <a href="Notas?demandaId=${demanda.demanda_id}&equipeId=${demanda.equipe_id}"><i class=" fa fa-ellipsis-v"></i></a>
                                          <div class="task-checkbox">
                                              <span class="badge bg-${demanda.criticidade}"><small>${demanda.dias_ate_fim_prazo}</small></span>
                                          </div>
                                          <div class="task-title">
                                              <span class="task-title-sp">${demanda.titulo}</span>
											  <span style="background-color:<c:out value="${demanda.responsavel.getHexCor()}"/>" class="label label-primary">${demanda.responsavel.getPrimerio_nome()}</span>
                                              <div class="pull-right hidden-phone">
                                              	<c:if test="${demanda.responsavel_id == voluntario.userid || lider}">
                                                  <button onclick="modalAddReabertura(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-info btn-xs fa fa-undo"></button>
                                                </c:if>
                                                <c:if test="${lider}">                                                   
                                                  <button onclick="modalAddArquivamento(${demanda.demanda_id});" style="font-size:13px;" class="btn btn-danger btn-xs fa fa-archive"></button>
                                                 </c:if>
                                              </div>
                                          </div>
                                      </li>
									 </c:forEach> 
                                  </ul>
								 </div>
                              </div>
                          </div>
                      </section>
                  </div><!--/col-md-12 -->
				  
              </div><!-- /row -->
              
              <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Indique Aqui seu Amigo</h4>
		                      </div>
		                      <div class="modal-body">
		                          <p>Entre com o e-mail abaixo para resetar sua senha.</p>
		                          <input type="text" name="email" placeholder="E-mail" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="button">Enviar</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		        <!-- modal -->
				  
			<!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalFeedBack" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Deixe Aqui seu Feedback</h4>
		                      </div>
		                      <form action="feedback"  method="post">
		                      <div class="modal-body">
		                          <p>Entre com o feedback abaixo. Depois é só enviar</p>
								<textarea rows="8" class="form-control placeholder-no-fix" name="feedback" required></textarea>								 
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="submit">Enviar</button>
		         
		                      </div>
		                     </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		           <c:if test="${lider}">
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalAddMembro" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Adicionar um membro</h4>
		                      </div>
		                      <form action="adicionarMembro"  method="post">
		                      <div class="modal-body">
		                          <p>Entre com o e-mail abaixo do Membro da Equipe que quer adicionar</p>
		                          <input type="text" name="email" placeholder="E-mail" autocomplete="off" class="form-control placeholder-no-fix" required>
								<input type="hidden" name="equipeId" value="${equipe.equipeID}" />
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="submit">Adicionar</button>
		                      </div>
		                     </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		         
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalRetirar" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-d">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Retirada de Recurso Humano</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>Você Realmente quer <b>Retirar</b> esse Membro da Equipe?</p>		                       	
		                      </div>
		                      <form action="retirarMembro" method="post">
		                      <div class="modal-footer">
		                       <input  type="hidden" name="userId" id="retirarMembro" value = "userId" >
		                        <input  type="hidden" name="equipeId" value = "${equipe.equipeID}" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Retirada</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalMembros" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Membros da Equipe</h4>
		                      </div>
		                      <div class="modal-body">
		                          <div style="height:465px;line-height:3em;overflow:auto;padding:5px;">
								
							<div class="custom-check goleft">
				             <table id="" class="table table-hover custom-check">
				              <tbody>
				              
				              <!-- Fazer for each para a lista de s os apoios da atividade-->
				              <c:forEach items="${membros}" var="membro">			
										 <tr>	
										<td>				                      
				                     	   <a>${membro.nome}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${membro.whatsapp}</a>
				      					</td>
				      					<td>				                      
				                     	   <button data-toggle="modal" href="#ModalRetirar" onclick="modalRetirar(${membro.userid});" class="btn btn-danger btn-xs" style="margin-left:10px;"><i class="fa fa-times "></i></button>
				      					</td>				      					
				      					</tr>
				      				</c:forEach>			              							 
								 
				              </tbody>
				          </table>
						</div><!-- /table-responsive -->
								
						</div><!-- scrollbar -->
		
		                      </div>
		                      <div class="modal-footer">
		                      <h5 class = "goleft floater" >Total de ${fn:length(membros)} Membros</h5>
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>		                          
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalInfoLider" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">${equipe.nome}</h4>
		                      </div>
		                      
		                      <form class="form-horizontal style-form" action="atualizarEquipe" method="post">
		                      <div class="modal-body">
		                      
		                      <div class="form-group">
	                              <label class="col-sm-2 col-sm-2 control-label">Objetivo</label>
	                              <div class="col-sm-10">
	                                  <input type="text" class="form-control" name="objetivo" value = "${equipe.objetivo}" required>
	                              </div>
	                          </div>

	                          <div class="form-group">
	                              <label class="col-sm-2 col-sm-2 control-label">Nº de Membros</label>
	                              <div class="col-sm-10">
	                                  <input type="text" class="form-control" name="num_membros" value = "${equipe.num_membros}" required>
	                              </div>
	                          </div>
	                          
	                          <div class="form-group">
	                              <label class="col-sm-2 col-sm-2 control-label">Lider</label>
	                              <div class="col-sm-10">
	                                  <input type="text" class="form-control" name="lider_email" value = "${equipe.lider.email}" required>
	                              </div>
	                          </div>
	                          
	                          <div class="form-group">
	                              <label class="col-sm-2 col-sm-2 control-label">Descrição</label>
	                              <div class="col-sm-10">
	                                 <textarea rows="8" class="form-control placeholder-no-fix" name="descricao" required>${equipe.descricao}</textarea>
	                              </div>
	                          </div>
	                          	<input type="hidden" name="equipeID" value = "${equipe.equipeID}" >
		                      </div>

		                      <div class="modal-footer">
		                      	  <button type="button" onclick="modalInfoRetirar()" class="btn btn-primary" data-toggle="modal" href="#modalMembros"><i class="fa fa-users" aria-hidden="true"></i> <span class="hidden-phone"> Membros</span></button>
								  <button type="button" onclick="modalInfoRetirar()" class="btn btn-primary" data-toggle="modal" href="#modalAddMembro"><i class="fa fa-plus" aria-hidden="true"></i> <span class="hidden-phone">Adcionar Membro</span></button>
								  <button class="btn btn-success" class="btn btn-default" type="submit"><i class="fa fa-pencil" aria-hidden="true"></i> Atualizar</button>	
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                      </div>
		                      </form>
		                  </div>
		                  
		              </div>
		          </div>
		          <!-- modal -->
		          
		          </c:if>
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalInfo" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">${equipe.nome}</h4>
		                      </div>
		                      <div class="modal-body">
		                        <p><b style="font-size:15px;">Objetivo:</b>${equipe.objetivo}</p>
		                          <p><b style="font-size:15px;">Nº de Membros:</b>${equipe.num_membros}</p>
		                          <p><b style="font-size:15px;">Responsável:</b>${equipe.lider.nome}</p>
		                          <p><b style="font-size:15px;">Contato:</b>${equipe.lider.whatsapp}</p>
		                          <p><b style="font-size:15px;">Equipe da:</b> ${equipe.diretoria}</p>
		                          <p><b style="font-size:15px;">Descrição:</b>${equipe.descricao}</p> 		                          
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalDemanda" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Cadastrar Demanda!</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="cadastrarDemanda" method = "post">
		                      
		                 <div class="modal-body">
		                 
		                 <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Título</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="titulo" placeholder="Texto curto e direto!" required>
                              </div>
                          </div>
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Dias para Conclusão</label>
                              <div class="col-sm-10">
                                  <input class="form-control" type="number" name="dias_para_atendimento"  required>
                              </div>
                          </div>  
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Responsável</label>
                              <div class="col-sm-10">  
                               	   <select class="form-control" name = "responsavel_id"  required>
                                    	<option value="0">Sem Definir</option>
                                    	<c:forEach items="${membros}" var="membro">
                                    		<option value="${membro.userid}">${membro.nome}</option>
                                    	</c:forEach>
                                    </select> 
                              </div>
                          </div> 
                          
						    <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Descrição</label>
                              <div class="col-sm-10">
                                 <textarea rows="8" class="form-control placeholder-no-fix" name="descricao" required></textarea>
                              </div>
                          </div>                               

						<input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
					</div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="submit">Enviar</button>
		                      </div>
		                      
		                      </form>
		                        	
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalDesignaDemanda" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Designar a Demanda!</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="designarDemanda" method = "post">
		                      
		                 <div class="modal-body">
		                 
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Responsável</label>
                              <div class="col-sm-10">  
                               	   <select class="form-control" name = "responsavelId"  required>
                                    	<option value="0">Sem Definir</option>
                                    	<c:forEach items="${membros}" var="membro">
                                    		<option value="${membro.userid}">${membro.nome}</option>
                                    	</c:forEach>
                                    </select> 
                              </div>
                          </div> 
                          
						<input  type="hidden" name="equipeId" value = "${equipe.equipeID}" >
						<input  type="hidden" name="demandaId" id="idDesigna" value = "demandaId" >
					</div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="submit">Designar</button>
		                      </div>
		                      
		                      </form>
		                        	
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          
		          <!-- Modal nota comum-->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAddNota" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Adicionar Nota</h4>
		                      </div>
		                      <form action="adicionarNota" method="post">
		                      <div class="modal-body">
		                          <p>Qual a nota que você quer adcionar?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="nota" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="demanda_id" id="idDemanda" value = "demandaId" >
		                        <input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Adicionar</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal conclusão -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAddConclusao" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Concluir Demanda</h4>
		                      </div>
		                      <form action="concluirDemanda" method="post">
		                      <div class="modal-body">
		                          <p>Nota pertinente a conclusão da Demana</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="nota" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="demanda_id" id="idDemandaC" value = "demandaId" >
		                        <input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Adicionar</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal pendencia-->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAddPendencia" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Criar Pendência</h4>
		                      </div>
		                      <form action="adicionarPendencia" method="post">
		                      <div class="modal-body">
		                          <p>Qual a pendência para a conclusão da demanda?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="nota" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="demanda_id" id="idDemandaP" value = "demandaId" >
		                        <input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Adicionar</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal solução de pendencia-->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAddSolucao" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Solução de Pendência</h4>
		                      </div>
		                      <form action="resolverPendencia" method="post">
		                      <div class="modal-body">
		                          <p>Qual a solução para a pendência foi encontrada?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="nota" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="demanda_id" id="idDemandaS" value = "demandaId" >
		                        <input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Adicionar</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal rabertura -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAddReabertura" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Reabrir Demanda</h4>
		                      </div>
		                      <form action="reabrirDemanda" method="post">
		                      <div class="modal-body">
		                          <p>Qual o motivo da reabertura?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="nota" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="demanda_id" id="idDemandaR" value = "demandaId" >
		                        <input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Adicionar</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal arquivamento -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAddArquivamento" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Arquivamento da Demanda</h4>
		                      </div>
		                      <form action="arquivarDemanda" method="post">
		                      <div class="modal-body">
		                          <p>A demanda foi concluida de forma satisfatória?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="nota" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="demanda_id" id="idDemandaA" value = "demandaId" >
		                        <input hidden type="number" name="equipeId" value = "${equipe.equipeID}" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Adicionar</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
			
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              2014 - Laços da Alegria
              <a class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page-->
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>    
    <script src="assets/js/tasks.js" type="text/javascript"></script>

    <script>
      jQuery(document).ready(function() {
          TaskList.initTaskWidget();
      });

      $(function() {
          $( "#sortable" ).sortable();
          $( "#sortable" ).disableSelection();
      });
	  
	        $(function() {
          $( "#sortable2" ).sortable();
          $( "#sortable2" ).disableSelection();
      });
	  
	  	        $(function() {
          $( "#sortable3" ).sortable();
          $( "#sortable3" ).disableSelection();
      });
	  
	  	        $(function() {
          $( "#sortable4" ).sortable();
          $( "#sortable4" ).disableSelection();
      });


    </script>
    
   <script type="text/javascript">
    function modalRetirar(userId) {
        // altera as informações do modal cancelar  
        $('#modalMembros').modal('toggle');
      	  document.getElementById("retirarMembro").value = userId;
        
      };
      function modalInfoRetirar() {
          // altera as informações do modal cancelar  
          $('#ModalInfoLider').modal('toggle'); 
        };
      function clickAndDisable(link) {
          // disable subsequent clicks
          link.onclick = function(event) {
             event.preventDefault();
          }
        };
    </script> 
    
    <script>
    
    function modalAddNota(demandaId) {
        // altera as informações do modal cancelar  
        $('#ModalAddNota').modal('toggle');
      	  document.getElementById("idDemanda").value = demandaId;
        
    };
    
    function modalAddConclusao(demandaId) {
        // altera as informações do modal cancelar  
        $('#ModalAddConclusao').modal('toggle');
      	  document.getElementById("idDemandaC").value = demandaId;
        
    };
    
    function modalAddPendencia(demandaId) {
        // altera as informações do modal cancelar  
        $('#ModalAddPendencia').modal('toggle');
      	  document.getElementById("idDemandaP").value = demandaId;
        
    };
    
    function modalAddSolucao(demandaId) {
        // altera as informações do modal cancelar  
        $('#ModalAddSolucao').modal('toggle');
      	  document.getElementById("idDemandaS").value = demandaId;
        
    };
    
    function modalAddReabertura(demandaId) {
        // altera as informações do modal cancelar  
        $('#ModalAddReabertura').modal('toggle');
      	  document.getElementById("idDemandaR").value = demandaId;
        
    };
    
    function modalAddArquivamento(demandaId) {
        // altera as informações do modal cancelar  
        $('#ModalAddArquivamento').modal('toggle');
      	  document.getElementById("idDemandaA").value = demandaId;
        
    };
    
    function modalDesignaDemanda(demandaId) {
        // altera as informações do modal cancelar  
        $('#modalDesignaDemanda').modal('toggle');
      	  document.getElementById("idDesigna").value = demandaId;
        
    };
    

    </script>
    
    
    
  <script>
      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

  </body>
</html>
