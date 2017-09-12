<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="Laços da Alegria">
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <title>Laços da Alegria</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    <link rel="stylesheet" href="assets/css/to-do.css">
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <script src="assets/js/chart-master/Chart.js"></script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    
    <c:choose>
		<c:when test="${faltante}">
			<link rel="shortcut icon" href="assets/img/sad.png">
		</c:when>
		<c:otherwise>
			<link rel="shortcut icon" href="assets/img/favicon.png">
		</c:otherwise>
	</c:choose>
    
    
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

              <div class="row">
                  <div class="col-lg-9">
				  
				  <h1 class= "centered">Atividades dessa Semana!</h1>
				  
				  <c:if test="${AtividadesOngs.size() > 0}">
				  <ul class="nav nav-pills">
				    <li class="active"><a data-toggle="pill" href="#home">Hospitais</a></li>
				    <li><a data-toggle="pill" href="#menu1">Ong's <span class="badge bg-important">${AtividadesOngs.size()}</span></a></li>
				  </ul>
				  </c:if>
				  
		 <div class="tab-content">
          <div id="home" class="tab-pane fade in active row mt">        
                    
         <!-- Comeca a logica chosse when dos botoens de participacao das atividades -->
         
				<!-- Inicio Botao HUB -->	
				
				 <c:forEach items="${atividades}" var="ativ" >
				 
				<div class="col-lg-4 col-md-4 col-sm-4 mb">
					<div class="content-panel pn">
						<div class="col-xs-4 col-xs-offset-8">
							<button class="btn btn-clear-g with-shadow" data-toggle="modal"  href="#Modal${ativ.tag}">INFO</button>
						</div>
						
						<div id="profile-01" style="background-image: url(${ativ.urlS3()});background-size: cover; background-repeat: no-repeat; background-position: 50% 50%;">
								<br><h3 class="with-shadow">${ativ.tag}</h3>
						</div>
						
						
				<c:if test="${fase_randomica_acabou}">
					<c:set var="posicaoAtiv" value="${voluntario.posicaoFila(ativ)}"/>
					<c:set var="ativLotada" value="${ativ.atividadeLotada()}"/>
				</c:if>
						
				<c:set var="ativInscrito" value="${voluntario.estaInscrito(ativ)}"/>
							
				<c:choose>
					<c:when test="${!ativ.podeIncrever()}">
						<div class="profile-01b centered" data-toggle="modal" href="#Modalfechada">									
							<p>Inscrições Fechadas</p>
						</div>
					</c:when>
				<c:otherwise>
				
					<c:choose>
					
						<c:when test="${voluntario.podeSeInscrever(ativ)}">						
							<c:choose>
								<c:when test="${fase_randomica_acabou && ativLotada}">
									<a href="inscrever?atividade=${ativ.id}" onclick="clickAndDisable(this);">									
										<div class="profile-01l centered">									
											<p>Atividade Lotada, entrar na Fila!</p>
										</div>
									</a>	
						  		</c:when>
						  <c:otherwise>								
								<a href="inscrever?atividade=${ativ.id}" onclick="clickAndDisable(this);">
										<div class="profile-01 centered">								
											<p>Participar</p>
										</div>
									</a>										
							</c:otherwise>								
							</c:choose>			
				  		</c:when>
				  	<c:otherwise>
						<c:choose>			
						<c:when test="${ativInscrito}">
							<c:choose>
								<c:when test="${fase_randomica_acabou && ativLotada}">
									<a data-toggle="modal" href="#ModalCancelar" onclick="modalCancelar(${voluntario.registroAtividade(ativ).id});">									
									<div class="profile-01c centered">									
										<p>Sair da Atividade Lotada</p>
									</div>
								</a>	
						  		</c:when>
						  	<c:otherwise>
						  	
						  		<a data-toggle="modal" href="#ModalCancelar" onclick="modalCancelar(${voluntario.registroAtividade(ativ).id});">									
										<div class="profile-01c centered">									
											<p>Cancelar Participação</p>
										</div>
									</a>								
																	
							</c:otherwise>								
							</c:choose>	
						</c:when>
						<c:otherwise>
						
							<a data-toggle="modal" href="#Modalbloqueada" >
								<div class="profile-01b centered">								
									<p>Atividade Bloqueada</p>
								</div>
							</a>
						
						</c:otherwise>
						</c:choose>
					</c:otherwise>
					</c:choose>
					
				</c:otherwise>
				</c:choose>
				
						<div class="centered">
						<c:choose >
						
							<c:when test="${fase_randomica_acabou && !ativInscrito && !ativLotada}">
								<h6>
									<i class="fa fa-heart"  style="color: #9a62ce; font-size: 1.2em;"> O ${ativ.tag} precisa de você</i>
								<br>${ativ.nome}</h6>
							</c:when>
							
							<c:when test="${fase_randomica_acabou && posicaoAtiv > 0}">
							<h6>
							<i class="fa fa-hand-o-right"  style="color: #5373e7;font-size: 1.2em;">&nbsp&nbsp${posicaoAtiv}º na Fila de Espera</i>
							<br>${ativ.nome}</h6>
							</c:when>
							
							<c:when test="${fase_randomica_acabou && ativInscrito}">
								<h6>
									<i class="fa fa-thumbs-o-up"  style="color: #14c10e; font-size: 1.2em;">&nbsp&nbspEsperamos você lá</i>
								<br>${ativ.nome}</h6>
							</c:when>
							
							<c:otherwise>
								<h6><i class="fa li_paperplane"></i><br>${ativ.nome}</h6>
							</c:otherwise>	
						</c:choose>
						</div>
					</div><!--/content-panel -->
				</div><!--/col-md-4-->
				
				</c:forEach>
				
				<!-- Fim Botao HUB -->   
                      	
         <!-- Acaba aqui a logica chosse when dos botoens de participacao das atividades -->

                   </div>
                   
                   <div id="menu1" class="tab-pane fade row mt" >
                    
         <!-- Comeca a logica chosse when dos botoens de participacao das atividades -->
         
				<!-- Inicio Botao HUB -->	
				
				 <c:forEach items="${AtividadesOngs}" var="ativ" >
				 
				<div class="col-lg-4 col-md-4 col-sm-4 mb">
					<div class="content-panel pn">
						<div class="col-xs-4 col-xs-offset-8">
							<button class="btn btn-clear-g with-shadow" data-toggle="modal"  href="#Modal${ativ.tag}">INFO</button>
						</div>
						
						<div id="profile-01" class="profile-${fn:toLowerCase(ativ.tag)}">
								<br><h3 class="with-shadow">${ativ.tag}</h3>
						</div>
							
				<c:choose>
					<c:when test="${aberto || ativ.chamada_liberada}">
						<div class="profile-01b centered" data-toggle="modal" href="#Modalfechada">									
							<p>Inscrições Fechadas</p>
						</div>
					</c:when>
				<c:otherwise>
				
					<c:choose>
					
						<c:when test="${voluntario.podeSeInscrever(ativ)}">						
							<c:choose>
								<c:when test="${fase_randomica_acabou && ativ.atividadeLotada()}">
									<a href="inscrever?atividade=${ativ.id}" onclick="clickAndDisable(this);">									
										<div class="profile-01l centered">									
											<p>Atividade Lotada, entrar na Fila!</p>
										</div>
									</a>	
						  		</c:when>
						  <c:otherwise>							
								<a href="inscrever?atividade=${ativ.id}" onclick="clickAndDisable(this);">
										<div class="profile-01 centered">								
											<p>Participar</p>
										</div>
									</a>										
							</c:otherwise>								
							</c:choose>			
				  		</c:when>
				  	<c:otherwise>
						<c:choose>			
						<c:when test="${voluntario.estaInscrito(ativ)}">
							<c:choose>
								<c:when test="${fase_randomica_acabou && ativ.atividadeLotada()}">
									<a data-toggle="modal" href="#ModalCancelar" onclick="modalCancelar(${ativ.id});">									
									<div class="profile-01c centered">									
										<p>Sair da Atividade Lotada</p>
									</div>
								</a>	
						  		</c:when>
						  	<c:otherwise>
						  	
						  		<a data-toggle="modal" href="#ModalCancelar" onclick="modalCancelar(${voluntario.registroAtividade(ativ)});">									
										<div class="profile-01c centered">									
											<p>Cancelar Participação</p>
										</div>
									</a>								
																	
							</c:otherwise>								
							</c:choose>	
						</c:when>
						<c:otherwise>
						
							<a data-toggle="modal" href="#Modalbloqueada" >
								<div class="profile-01b centered">								
									<p>Atividade Bloqueada</p>
								</div>
							</a>
						
						</c:otherwise>
						</c:choose>
					</c:otherwise>
					</c:choose>
					
				</c:otherwise>
				</c:choose>
					
						<div class="centered">
						<c:choose >
							<c:when test="${voluntario.posicaoFila(ativ) > 0}">
							<h6>
							<i class="fa fa-hand-o-right"  style="color: #5373e7;font-size: 1.2em;">&nbsp&nbsp${voluntario.posicaoFila(ativ)}º na Fila de Espera</i>
							<br>${ativ.nome}</h6>
							</c:when>
							
							<c:when test="${voluntario.estaInscrito(ativ) && fase_randomica_acabou}">
								<h6>
									<i class="fa fa-thumbs-o-up"  style="color: #14c10e; font-size: 1.2em;">&nbsp&nbspEsperamos você lá</i>
								<br>${ativ.nome}</h6>
							</c:when>
							
							<c:when test="${!ativ.atividadeLotada() && fase_randomica_acabou}">
								<h6>
									<i class="fa fa-heart"  style="color: #9a62ce; font-size: 1.2em;"> O ${ativ.tag} precisa de você</i>
								<br>${ativ.nome}</h6>
							</c:when>
							
							<c:otherwise>
								<h6><i class="fa li_paperplane"></i><br>${ativ.nome}</h6>
							</c:otherwise>	
						</c:choose>
						</div>
					</div><!--/content-panel -->
				</div><!--/col-md-4-->
				
				</c:forEach>
				
				<!-- Fim Botao HUB -->   
                      	
         <!-- Acaba aqui a logica chosse when dos botoens de participacao das atividades -->

                   </div>	
                   </div>		
					
					
					<div class="row">
						
						<h1 class= "centered">Mais Laços!</h1><br>
				
                  		<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
                  			<div class="box1">
					  			<span class="li_heart"></span>
					  			<h3>${voluntario.maisLacos.total_voluntarios}</h3>
                  			</div>
					  			<p>Hoje o Laços da Alegria conta com ${voluntario.maisLacos.total_voluntarios} lindos voluntários!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_star"></span>
					  			<h3>${voluntario.maisLacos.atividade_participadas}</h3>
                  			</div>
					  			<p>Que incrível você já participou de ${voluntario.maisLacos.atividade_participadas} atividades!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_diamond"></span>
					  			<h3>${voluntario.maisLacos.total_novatos}</h3>
                  			</div>
					  			<p>Nos temos ${voluntario.maisLacos.total_novatos} lindos novatos esperando para levar amor!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_fire"></span>
					  			<h3>${voluntario.maisLacos.atividades_distintas}</h3>
                  			</div>
					  			<p>Ainda faltam ${voluntario.maisLacos.atividades_distintas} Atividades para você conhecer!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_megaphone"></span>
					  			<h3>${maislacos.indicacoes}</h3>
                  			</div>
					  			<p>Espalhando o amor! Você indicou ${voluntario.maisLacos.indicacoes} amigos até agora.</p>
                  		</div>
                  	
                  	</div><!-- /row mt -->	
					
					<!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Indique Aqui seu Amigo</h4>
		                      </div>
		                      <div class="modal-body">
		                          <p>Entre com o e-mail do amigo que você quer indicar.</p>
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
				  
				  <c:forEach items="${atividades}" var="ativ" >
				  
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="Modal${ativ.tag}" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-t">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">${ativ.nome}</h4>
		                      </div>
		                      <div class="modal-body">
		                        <p><b>Descrição:</b>${ativ.descricao}</p>
		                          <p><b>Local:</b>${ativ.endereco}</p> 
		                          <p><b>Horário:</b>${ativ.horario}</p>		                          
		                          <p><b>Preparo:</b>${ativ.local_preparo}</p>
		                          <p><b>Nº de Voluntários:</b>${ativ.limite_voluntario}</p>	
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->			
															
				  </c:forEach>
		
		            <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="Modalfechada" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-t">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Inscrições Fechadas</h4>
		                      </div> 
		                      <div class="modal-body centered">
		                          <p>Inscrições acontecem de <b>Segunda</b> à <b>Sábado</b> às 12h! </p>
		                          <small style="color:red;">Caso esteja em périodo de escrição entre em contato com o devido Coordenador da atividade, informando-o que a chamada da atividade anterior precisa ser realizada para liberação das inscrições!</small>		                       	
		                      </div>
		                       <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                      </div>                  
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="Modalbloqueada" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-t">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Inscrição Bloqueada</h4>
		                      </div> 
		                      <div class="modal-body centered">
		                          <p>Você já está inscrito em outra atividade que acorrerá nesse mesmo horário. Caso queira participar dessa, favor cancelar primeiramente a outra</p>		                       	
		                      </div>
		                       <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                      </div>                  
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
				  	
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalCancelar" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-d">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Cancelar Participação</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>Você Realmente quer <b>Cancelar</b> sua participação nessa atividade?</p>		                       	
		                      </div>
		                      <form action="cancelar" method="post">
		                      <div class="modal-footer">
		                       <input  type="hidden" name="atividade" id="ativCancelar" value = "ativid" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Cancelamento</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <c:if test="${voluntario.realizarFeedBack()}">
		          	<%@ include file="feedbackNovato.jsp" %>
		          </c:if>
		          
		          <c:if test="${voluntario.temDemanda()}">
		          	<%@ include file="demandas.jsp" %>
		          </c:if>
		          
		          
					
								
                  </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">
                   <c:if test="${voluntario.acessoDiretorHosp() || voluntario.acessoCoordenador()}">
                  	<h3>Código de Promoo de Novato</h3>               
                      <div class="desc box2 centered">
						<button type="button" class="btn btn-round btn-primary"><span class="fa fa-code"></span>&nbsp&nbspCdigo da Semana - <b><font size="3">${codigoSemana}</font></b></button>
                      </div>
                  </c:if>
                  
						<h3>Notificações Importantes</h3>
                                        
                      <!-- First Action -->
                      <c:forEach items="${feeds}" var="feed">
                      
	                      <div class="desc">
	                      	<div class="thumb">
	                      		<span class="badge bg-theme0${feed.tipo}"><i class="fa fa-clock-o"></i></span>
	                      	</div>
	                      	<div class="details">   
	                      		<p><muted>${feed.dt_cricao}</muted><br/>                 		
	                      		   <a target="_blank"  href="${feed.link}">${feed.tag}</a> ${feed.mensagem} <br/>
	                      		</p>
	                      	</div>
	                      </div>
                      
                      </c:forEach>     
                   
						<h3>Calendário de Atividades</h3><br>
                       <!-- CALENDAR-->
                        <div id="calendar" class="mb">
                            <div class="panel green-panel no-margin">
                                <div class="panel-body">
                                    <div id="date-popover" class="popover top" style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
                                        <div class="arrow"></div>
                                        <h3 class="popover-title" style="disadding: none;"></h3>
                                        <div id="date-popover-content" class="popover-content"></div>
                                    </div>
                                    <div id="my-calendar"></div>
                                </div>
                            </div>
                        </div><!-- / calendar -->
                      
                  </div><!-- /col-lg-3 -->
              </div><!--/row -->
          </section>
      </section>

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              Laços da Alegria - 2016
              <a href="area-voluntario" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>	
	
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
      
     </script>
	
	<c:if test="${faltante}">
		<script type="text/javascript">
	        $(document).ready(function () {
	        var unique_id = $.gritter.add({
	            // (string | mandatory) the heading of the notification
	            title: '<a style="color:#ffd777">O Laços Conta Com Você!</a>',
	            // (string | mandatory) the text inside the notification
	            text: 'Quando assumimos o compromisso de participar de uma atividade do Laços, estamos assumindo o compromisso de levar alegria a quem precisa, a quem está a nossa espera. O Laços depende de cada um de nós para funcionar, e precisamos de seriedade na hora de confirmar a participação. Caso algo aconteça simplesmente cancele a participação com um clique, que outro voluntário irá assumir esse compromisso.<a style="color:#ffd777"> <br><br> Att, <br> Laços da Alegria </a>',
	            // (string | optional) the image to display on the left
	            image: 'assets/img/sad.png',
	            // (bool | optional) if you want it to fade out on its own or just sit there
	            sticky: true,
	            // (int | optional) the time you want it to be alive for before fading out
	            time: '',
	            // (string | optional) the class name you want to apply to that specific message
	            class_name: 'my-sticky-class'
	        });
	        return false;
	        });
		</script>
	</c:if>
	
	<script type="text/javascript">
		// When ready...
		window.addEventListener("load",function() {
			// Set a timeout...
			setTimeout(function(){
				// Hide the address bar!
				window.scrollTo(0, 1);
			}, 0);
		});
	</script>	
	
	<c:if test="${voluntario.realizarFeedBack()}">
	  	<%@ include file="feedbackJs.jsp" %>
	</c:if>
	
	<script>
	$( document ).ready(function() {
		$('#ModalLembreteDem').modal('show');
	});
	</script>

<script>
	$( document ).ready(function() {
		$('#ModalLembreteDem').modal('show');
	});
</script>

	<script type="application/javascript">
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "nothing",
                    modal: true
                },
               
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        };
        
        function clickAndDisable(link) {
            // disable subsequent clicks
            link.onclick = function(event) {
               event.preventDefault();
            }
          };
          
          function modalCancelar(ativ) {
              // altera as informações do modal cancelar
              
            	  document.getElementById("ativCancelar").value = ativ;
              
            };
            
    </script>
  

  </body>
</html>
