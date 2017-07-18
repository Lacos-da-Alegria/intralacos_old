<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="LaÁos da Alegria">

    <title>LaÁos da Alegria</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    
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
		<c:when test="${faltante==true}">
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
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Menu"></div>
              </div>
            <!--logo start-->	
            <a href="area-voluntario" class="logo"><b>¡ÅREA DO VOLUNT¡RIO</b></a>
            <!--logo end-->
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- settings start -->
                    <li class="dropdown tooltips" data-placement="left" data-original-title="Indique Um Amigo">
                        <a data-toggle="modal" class="dropdown-toggle" href="volunteer-area.html#myModal">
                            <i class="fa li_megaphone" ></i>                            
                        </a>
                        					
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li class="dropdown tooltips" data-placement="left" data-original-title="Deixe Seu Feedback">
                        <a class="dropdown-toggle"  data-toggle="modal"  href="volunteer-area.html#ModalFeedBack">
                            <i class="fa fa-envelope-o"></i>                            
						</a>
                    </li>
                    <!-- inbox dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="logout">Logout</a></li>
            	</ul>
            </div>
        </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="profile"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered">${voluntario.nome}</h5>
              	  	
                  <li class="mt">
                      <a class="active" href="area-voluntario">
                          <i class="fa li_heart"></i>
                          <span>¡Årea do Volunt·rio</span>
                      </a>
                  </li>
				  
				  <li class="sub-menu">
                      <a href="profile">
                          <i class="fa li_user"></i>
                          <span>Minha Conta</span>
                      </a>
                  </li>

                   <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_tag"></i>
                          <span>InformaÁıes Importantes</span>
                      </a>
                       <ul class="sub">                
                          <li><a  href="construcao">Volunt·rio Nota 10</a></li>
                      </ul>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-list-ol"></i>
                          <span>Lista de Atividades</span>
                      </a>
                       <ul class="sub">
                       <c:forEach items="${Atividade}" var="ativ" >
                         <li><a  href="listaAtividade?atividade=${ativ.ativid}">${ativ.tag}</a></li>
                        </c:forEach>
                      </ul>
                  </li>
                  
				<c:if test="${(voluntario.acesso == 69) || (voluntario.acesso != 1)}">
					
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-desktop"></i>
                          <span>Painel da Atividade</span>
                      </a>
                      <ul class="sub">
                      <c:forEach items="${todas_Ativs}" var="ativ" >
                      <c:if test="${(voluntario.acesso == 69) || (voluntario.acesso == ativ.key + 1)}">
                          <li><a  href="detalheAtividade?atividade=${ativ.key}">${ativ.value}</a></li>
                       </c:if>
					</c:forEach>
                      </ul>
                  </li>
                  
                 </c:if>
                  <c:if test="${voluntario.acesso == 69}">
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>Admin Page</span>
                      </a>
                      
                      <ul class="sub">
                      
                          <li><a  href="promover">Promover Usuarios</a></li>
                          <li><a  href="cadastrarNotif">Cadastrar Notificacao</a></li>
						
                      </ul>
                      
                  </li>
                  </c:if>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_news"></i>
                          <span>Sobre</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="construcao">LaÁos da Alegria</a></li>
                          <li><a  href="construcao">Trabalho Volunt·rio</a></li>
                          <li><a  href="construcao">Como Posso Ajudar</a></li>
                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_phone"></i>
                          <span>Contato</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="construcao">LaÁos Da Alegria</a></li>
                          <li><a  href="construcao">Parceiros</a></li>
					</ul>
                  </li>
                 

              </ul>
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
                  
                    <div class="row mt">
                    
         <!-- Comeca a logica chosse when dos botoens de participacao das atividades -->
         
				<!-- Inicio Botao HUB -->	
				
				 <c:forEach items="${Atividade}" var="ativ" >
				 
				<div class="col-lg-4 col-md-4 col-sm-4 mb">
					<div class="content-panel pn">
						<div class="col-xs-4 col-xs-offset-8">
							<button class="btn btn-clear-g with-shadow" data-toggle="modal"  href="volunteer-area.html#Modal${ativ.tag}">INFO</button>
						</div>
						
						<div id="profile-01" class="profile-${fn:toLowerCase(ativ.tag)}">
								<br><h3 class="with-shadow">${ativ.tag}</h3>
						</div>
							
				<c:choose>
					<c:when test="${aberto == false}">
						<div class="profile-01b centered" data-toggle="modal" href="#Modalfechada">									
							<p>InscriÁıes Fechadas</p>
						</div>
					</c:when>
				<c:otherwise>
				
					<c:choose>
					
						<c:when test="${ativsPeriodo[ativ.periodo]==null || ativsPeriodo[ativ.periodo]==false}">						
							<c:choose>
								<c:when test="${lotada[ativ.ativid] == true}">
									<a href="inscrever?atividade=${ativ.ativid}" onclick="clickAndDisable(this);">									
										<div class="profile-01l centered">									
											<p>Atividade Lotada, entrar na Fila!</p>
										</div>
									</a>	
						  		</c:when>
						  <c:otherwise>								
								<a href="inscrever?atividade=${ativ.ativid}" onclick="clickAndDisable(this);">
										<div class="profile-01 centered">								
											<p>Participar</p>
										</div>
									</a>										
							</c:otherwise>								
							</c:choose>			
				  		</c:when>
				  	<c:otherwise>
						<c:choose>			
						<c:when test="${ativAtual[ativ.ativid]==true}">
							<c:choose>
								<c:when test="${lotada[ativ.ativid] == true}">
									<a data-toggle="modal" href="#ModalCancelar" onclick="modalCancelar(${ativ.ativid});">									
									<div class="profile-01c centered">									
										<p>Sair da Atividade Lotada</p>
									</div>
								</a>	
						  		</c:when>
						  	<c:otherwise>
						  	
						  		<a data-toggle="modal" href="#ModalCancelar" onclick="modalCancelar(${ativ.ativid});">									
										<div class="profile-01c centered">									
											<p>Cancelar ParticipaÁ„o</p>
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
							<c:when test="${posicoes[ativ.ativid] > 0}">
							<h6>
							<i class="fa fa-hand-o-right"  style="color: #5373e7;font-size: 1.2em;">&nbsp&nbsp${posicoes[ativ.ativid]}∫ na Fila de Espera</i>
							<br>${ativ.nome}</h6>
							</c:when>
							<c:otherwise>
								<c:choose >
									<c:when test="${ativAtual[ativ.ativid] == true && fase_randomica_acabou == true}">
										<h6>
									        <i class="fa fa-thumbs-o-up"  style="color: #14c10e; font-size: 1.2em;">&nbsp&nbspEsperamos vocÍ l·</i>
									    <br>${ativ.nome}</h6>
									</c:when>
									<c:otherwise>
										<h6><i class="fa li_paperplane"></i><br>${ativ.nome}</h6>
									</c:otherwise>	
								</c:choose>
							</c:otherwise>
						</c:choose>
						</div>
					</div><!--/content-panel -->
				</div><!--/col-md-4-->
				
				</c:forEach>
				
				<!-- Fim Botao HUB -->   
                      	
         <!-- Acaba aqui a logica chosse when dos botoens de participacao das atividades -->

                    </div><!-- /row -->                    
                    				
					
					
					<div class="row">
						
						<h1 class= "centered">Mais LaÁos!</h1><br>
				
                  		<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
                  			<div class="box1">
					  			<span class="li_heart"></span>
					  			<h3>${maislacos.total_voluntarios}</h3>
                  			</div>
					  			<p>Hoje o LaÁos da Alegria conta com ${maislacos.total_voluntarios} lindos volunt·rios!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_star"></span>
					  			<h3>${maislacos.atividade_participadas}</h3>
                  			</div>
					  			<p>Que incrÌvel vocÍ j· participou de ${maislacos.atividade_participadas} atividades!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_diamond"></span>
					  			<h3>${maislacos.total_novatos}</h3>
                  			</div>
					  			<p>Nos temos ${maislacos.total_novatos} lindos novatos esperando para levar amor!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_fire"></span>
					  			<h3>${maislacos.atividades_distintas}</h3>
                  			</div>
					  			<p>Ainda faltam ${maislacos.atividades_distintas} Atividades para vocÍ conhecer!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_megaphone"></span>
					  			<h3>${maislacos.indicacoes}</h3>
                  			</div>
					  			<p>Espalhando o amor! VocÍ indicou ${maislacos.indicacoes} amigos atÈ agora.</p>
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
		                          <p>Entre com o e-mail do amigo que vocÍ quer indicar.</p>
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
		                          <p>Entre com o feedback abaixo. Depois È sÛ enviar</p>
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
				  
				  <c:forEach items="${Atividade}" var="ativ" >
				  
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="Modal${ativ.tag}" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-t">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">${ativ.nome}</h4>
		                      </div>
		                      <div class="modal-body">
		                        <p><b>DescriÁ„o:</b>${ativ.descricao}</p>
		                          <p><b>Local:</b>${ativ.endereco}</p> 
		                          <p><b>Hor·rio:</b>${ativ.horario}</p>		                          
		                          <p><b>Preparo:</b>${ativ.local_preparo}</p>
		                          <p><b>N∫ de Volunt·rios:</b>${ativ.limite_voluntario}</p>	
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
		                          <h4 class="modal-title centered">InscriÁıes Fechadas</h4>
		                      </div> 
		                      <div class="modal-body centered">
		                          <p>InscriÁıes acontecem de <b>Segunda</b> ‡ <b>S·bado</b> ‡s 12h</p>		                       	
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
		                          <h4 class="modal-title centered">InscriÁ„o Bloqueada</h4>
		                      </div> 
		                      <div class="modal-body centered">
		                          <p>VocÍ j· est· inscrito em outra atividade que acorrer· nesse mesmo hor·rio. Caso queira participar dessa, favor cancelar primeiramente a outra</p>		                       	
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
		                          <h4 class="modal-title centered">Cancelar ParticipaÁ„o</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>VocÍ Realmente quer <b>Cancelar</b> sua participaÁ„o nessa atividade?</p>		                       	
		                      </div>
		                      <form action="cancelar" method="post">
		                      <div class="modal-footer">
		                       <input hidden type="number" name="atividade" id="ativCancelar" value = "" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Cancelamento</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
					
								
                  </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">
                  
                  <c:if test="${serapoio[0] != 0}">
                  
						<c:choose>
							<c:when test="${serapoio[1] == 1}">
							
								<h3>Apoio Ativo</h3>
								
								   <div class="desc box2 centered">
                      				<div class="btn-group" >
									  <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
									    AÁıes Possiveis <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" role="menu">
									    <li><a href="desativarApoio">Desativar Apoio</a></li>
									    <li><a href="#">Deixar de Ser Apoio</a></li>
										
									  </ul>
									</div>						
			                      </div> 
											
							</c:when>
						<c:otherwise>								
								<h3>Apoio Desativado</h3>	
								
								 <div class="desc box2 centered">
                      				<div class="btn-group" >
									  <button type="button" class="btn btn-theme04 dropdown-toggle" data-toggle="dropdown">
									    AÁıes Possiveis <span class="caret"></span>
									  </button>
									  <ul class="dropdown-menu" role="menu">
									    <li><a href="ativarApoio">Ativar Apoio</a></li>
									    <li><a href="#">Deixar de Ser Apoio</a></li>

										
									  </ul>
									</div>						
			                      </div> 
																				
						</c:otherwise>								
						</c:choose>                      
                  
                  </c:if>

						<h3>NotificaÁıes Importantes</h3>
                                        
                      <!-- First Action -->
                      <c:forEach items="${feeds}" var="feed">
                      
	                      <div class="desc">
	                      	<div class="thumb">
	                      		<span class="badge bg-theme0${feed.tipo}"><i class="fa fa-clock-o"></i></span>
	                      	</div>
	                      	<div class="details">   
	                      		<p><muted>${feed.tempo}</muted><br/>                 		
	                      		   <a target="_blank"  href="${feed.link}">${feed.tag}</a> ${feed.mensagem} <br/>
	                      		</p>
	                      	</div>
	                      </div>
                      
                      </c:forEach>     
                   
						<h3>Calend·rio de Atividades</h3><br>
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
              LaÁos da Alegria - 2016
              <a href="area-voluntario#" class="go-top">
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
	
	<c:if test="${faltante==true}">
		<script type="text/javascript">
	        $(document).ready(function () {
	        var unique_id = $.gritter.add({
	            // (string | mandatory) the heading of the notification
	            title: '<a style="color:#ffd777">O LaÁos Conta Com VocÍ!</a>',
	            // (string | mandatory) the text inside the notification
	            text: 'Quando assumimos o compromisso de participar de uma atividade do LaÁos, estamos assumindo o compromisso de levar alegria a quem precisa, a quem est· a nossa espera. O LaÁos depende de cada um de nÛs para funcionar, e precisamos de seriedade na hora de confirmar a participaÁ„o. Caso algo aconteÁa simplesmente cancele a participaÁ„o com um clique, que outro volunt·rio ir· assumir esse compromisso.<a style="color:#ffd777"> <br><br> Att, <br> LaÁos da Alegria </a>',
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
                    url: "",
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
              // altera as informaÁıes do modal cancelar
              
            	  document.getElementById("ativCancelar").value = ativ;
              
            };
            
    </script>
  

  </body>
</html>
