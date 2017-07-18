<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
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
    <link rel="shortcut icon" href="assets/img/favicon.png">
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
                        <a data-toggle="modal" class="dropdown-toggle" href="#myModal">
                            <i class="fa li_megaphone" ></i>                            
                        </a>
                        					
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li class="dropdown tooltips" data-placement="left" data-original-title="Deixe Seu Feedback">
                        <a class="dropdown-toggle"  data-toggle="modal"  href="#ModalFeedBack">
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
                      <a href="area-voluntario">
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
                       <c:forEach items="${AtividadeAtivas}" var="ativ" >
                         <li><a  href="listaAtividade?atividade=${ativ.ativid}">${ativ.tag}</a></li>
                        </c:forEach>
                      </ul>
                  </li>
                  
				<c:if test="${(voluntario.acesso == 69) || (voluntario.acesso != 1)}">
					
                  <li class="sub-menu">
                      <a class="active"  href="javascript:;" >
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
				  
				  <h1 class= "centered">${titulo}</h1>
                  
                    <div class="row mt">
                      <div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-2 pn">
							<div class="weather-2-header">
									<div class="row centered mt-2">										
										<p><i class="fa fa-tasks"></i>&nbsp&nbspLista de Volunt·rios</p>	
									</div>
								</div><!-- /weather-2 header -->
								
						<div style="height:209px;line-height:3em;overflow:auto;padding:5px;">
								
							<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				               
				                	
				                <!-- Fazer for each para volut·rios -->	
				                	
				                	<c:forEach items="${voluntarios}" var="voluntario" varStatus="loop">			
										 <tr>	
										<td>				                      
				                     	   <a>${loop.index+1}∫</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${voluntario.nome}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${voluntario.whatsapp}</a>
				      					</td>
				      					</tr>
				      				</c:forEach>
				               	
				                
				              </tbody>
				          </table>	
						</div><!-- /table-responsive -->
								
						</div><!-- scrollbar -->
					
					</div><!--/content-panel -->
				</div><!--/col-md-4 -->
                      	

                      	<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-2 pn">
							<div class="weather-2-header">
									<div class="row">
										<div class="centered mt-2">	
											<p><i class="fa li_star"></i>&nbsp&nbspLista de Apoios</p>
										</div>
										
									</div>
								</div><!-- /weather-2 header -->
						<div style="height:209px;line-height:3em;overflow:auto;padding:5px;">

								
							<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				                
				                <!-- Fazer for each para apoios -->
				              
				               <c:forEach items="${apoiosAtiv}" var="apoioAtiv" varStatus="loop">			
										 <tr>	
										<td>				                      
				                     	   <a>${loop.index+1}∫</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${apoioAtiv.nome}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${apoioAtiv.whatsapp}</a>
				      					</td>
				      					</tr>
				      				</c:forEach>  
				                
				              </tbody>
				          </table>
						</div><!-- /table-responsive -->
								
						</div><!-- scrollbar -->
					
					</div><!--/content-panel -->
				</div><!--/col-md-4 -->
                      	
						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-2 pn">
							<div class="weather-2-header">
									<div class="row">
										<div class="centered mt-2">	
											<p><i class="fa fa-heart"></i>&nbsp&nbspLista de Novatos</p>
										</div>
									</div>
								</div><!-- /weather-2 header -->
						<div style="height:209px;line-height:3em;overflow:auto;padding:5px;">

								
						<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				             
				              <!-- Fazer for each para novatos -->
				                <tr>
				           			<td>				 
				                        <a>Controle Ainda N„o Implementado <i class="fa fa-heart"></i></a>
				                        
									</td>
				                </tr>
				                 
				              </tbody>
				          </table>
						</div><!-- /table-responsive -->
								
						</div><!-- scrollbar -->
					
					</div><!--/content-panel -->
				</div><!--/col-md-4 -->
                      	

                    </div><!-- /row -->
					
					 <h3 class= "centered">Est·ticas da Atividade</h3>
                    
                    				
					<div class="row mt">
					
						<div class="col-md-4 col-sm-4 mb">
                      		<div class="green-panel pn">
                      			<div class="green-header">
						  			<h5>Taxa de Retorno</h5>
                      			</div>
								<canvas id="taxaderetorno" height="120" width="120"></canvas>
								<script>
									var doughnutData = [
											{
												value: ${retorno.taxa_retorno},
												color:"#1c9ca7"
											},
											{
												value : ${100-retorno.taxa_retorno},
												color : "#f68275"
											}
										];
										var myDoughnut = new Chart(document.getElementById("taxaderetorno").getContext("2d")).Doughnut(doughnutData);
								</script>
								<p>No EspaÁo de 3 MÍs</p>
								<footer>
									<div class="ml pull-left">
										<h5><i class="fa li_like"></i> ${retorno.total_retorno} no Total</h5>
									</div>
									<div class="mr pull-right">
										<h5>${retorno.taxa_retorno}% Retornaram</h5>
									</div>
								</footer>
                      		</div><!-- /darkblue panel -->
                      	</div><!-- /col-md-4 -->					
									
						<div class="col-md-4 col-sm-4 mb">
							<!-- REVENUE PANEL -->
							<div class="green-panel pn">
								<div class="green-header">
									<h5>Cadastro Mensal de Novatos</h5>
								</div>
								<div class="chart mt">
									<div class="sparkline" data-type="line" data-resize="true" data-height="75" data-width="90%" data-line-width="1" data-line-color="#fff" data-spot-color="#fff" data-fill-color="" data-highlight-line-color="#fff" data-spot-radius="4" data-data="${cadastros}"></div>
								</div>
								<p class="mt"><b>${maislacos.total_novatos}</b><br/>Total de Novatos</p>
							</div>
						</div><!-- /col-md-4 -->	

						<div class="col-md-4 col-sm-4 mb">
                      		<div class="green-panel pn">
                      			<div class="green-header">
						  			<h5>Quem Conhece o ${atividade.tag}?</h5>
                      			</div>
								<canvas id="serverstatus03" height="120" width="120"></canvas>
								<script>
									var doughnutData2 = [
											{
												value: ${dist_ativ*100/maislacos.total_voluntarios},
												color:"#1c9ca7"
											},
											{
												value : ${100-(dist_ativ*100/maislacos.total_voluntarios)},	
												color : "#f68275"
											}
										];
										var myDoughnut2 = new Chart(document.getElementById("serverstatus03").getContext("2d")).Doughnut(doughnutData2);
								</script>
								<p>AtÈ o Momento<p>
								<footer>
									<div class="ml pull-left">
										<h5><i class="fa li_like"></i> ${maislacos.total_voluntarios} no Total</h5>
									</div>
									<div class="mr pull-right">
										<h5><fmt:formatNumber value="${dist_ativ*100/maislacos.total_voluntarios}" minFractionDigits="0" maxFractionDigits="2"/>% do LaÁos</h5>
									</div>
								</footer>
                      		</div><!-- /darkblue panel -->
                      	</div><!-- /col-md-4 -->								
						
												
					</div><!-- /row -->
					
					<div class="row mt">
					
						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-2 pn">
							<div class="weather-2-header">
									<div class="row">
										<div class="centered mt-2">	
											<p><i class="fa li_diamond"></i>&nbsp&nbspN∫ de Visitas All Times</p>
										</div>
									</div>
								</div><!-- /weather-2 header -->
						<div style="height:209px;line-height:3em;overflow:auto;padding:5px;">

								
						<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				              <!-- Fazer for each para N∫ de Visitas All Times Top 10 -->
				               	<c:forEach items="${top10all}" var="top">
				               	<tr>
				           			<td>${top.posicao}∫</td>
									<td>${top.nome}</td>
									<td>${top.quantidade}<td>
				                </tr>				                
								</c:forEach>
								</tbody>
								</table>
							</div><!-- /table-responsive -->
								
							</div><!-- scrollbar -->
					
						</div><!--/content-panel -->
					</div><!--/col-md-4 -->					
									
					<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-2 pn">
							<div class="weather-2-header">
									<div class="row">
										<div class="centered mt-2">	
											<p><i class="fa li_fire"></i>&nbsp&nbspN∫ de Visitas ⁄ltimos 3 Meses</p>
										</div>
									</div>
								</div><!-- /weather-2 header -->
						<div style="height:209px;line-height:3em;overflow:auto;padding:5px;">

								
						<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				               	
				               	<!-- Fazer for each para N∫ de Visitas ⁄ltimos 3 Meses Top 10 -->
				               	<c:forEach items="${top10month}" var="tops">
				               	<tr>
				           			<td>${tops.posicao}∫</td>
									<td>${tops.nome}</td>
									<td>${tops.quantidade}<td>
				                </tr>
				                </c:forEach>
								
								</tbody>
								</table>
							</div><!-- /table-responsive -->
								
							</div><!-- scrollbar -->
					
						</div><!--/content-panel -->
					</div><!--/col-md-4 -->	

						<div class="col-lg-4 col-md-4 col-sm-4 mb">
							<div class="weather-3 pn centered">
								<i class="fa fa-pied-piper-alt"></i>
								<h1>${frequencia_ativ[0]}%</h1>
								<div class="info">
									<div class="row">
											<h3 class="centered">FrequÍncia MÈdia</h3>
										<div class="col-sm-6 col-xs-6 pull-left">
											<p class="goleft"><i class="fa li_world"></i> ${frequencia_ativ[1]}%</p>
										</div>
										<div class="col-sm-6 col-xs-6 pull-right">
											<p class="goright"><i class="fa li_calendar"></i>&nbsp&nbsp3 Meses</p>
										</div>
									</div>
								</div>
							</div>
						</div><!--/col-md-4 -->	
						
					</div><!-- /row -->
					
					<div class="row mt">
                      <!--CUSTOM CHART START -->
                      <div class="border-head">
                          <h3>DistribuiÁ„o do Volunt·rio por Cidade SatÈlite</h3>
                      </div>
                      <div class="custom-bar-chart">
                          <ul class="y-axis">
                              <li><span>100</span></li>
                              <li><span>80</span></li>
                              <li><span>60</span></li>
                              <li><span>40</span></li>
                              <li><span>20</span></li>
                              <li><span>0</span></li>
                          </ul>
                          
                            <!-- Fazer for each para da distribuiÁ„o dos Voluntarios Top 7 cidades satelites-->
                          <c:forEach items="${distribuicao}" var="dist">
                          <div class="bar">
                              <div class="title">${dist.nome}</div>
                              <div class="value tooltips" data-original-title="${dist.quantidade}" data-toggle="tooltip" data-placement="top">${dist.quantidade}%</div>
                          </div>
						 </c:forEach>
                      </div>
                      <!--custom chart end-->
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
		                          <p>Entre com o e-mail ou Login abaixo do Amigo que voce quer indicar</p>
		                          <input type="text" name="indicar" placeholder="E-mail ou Login" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="button">Indicar</button>
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
				  
				  
				  
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal3" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Adicionar um Apoio</h4>
		                      </div>
		                      <form action="adicionarApoio"  method="post">
		                      <div class="modal-body">
		                          <p>Entre com o e-mail abaixo do Apoio que quer adicionar</p>
		                          <input type="text" name="email" placeholder="E-mail" autocomplete="off" class="form-control placeholder-no-fix" required>
								<input type="hidden" name="atividade" value="${ativid}" />
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal4" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Chamada da Atividade</h4>
		                      </div>
		                      
				            <c:choose>
				            
							<c:when test="${chamadaaberta == false}">
							
							<h3 class="centered">Chamada Fechada!</h3><br>
							<h5 class="centered">Hor·rio da chamada È das 13h ‡s 20h do S·bado</h5>
												
							</c:when>
							
						<c:otherwise>																				
		                       
		                  <form action="fazerChamada" method = "post">  
		                       
		                   <div class="modal-body">
		                       	
							<div style="height:465px;line-height:3em;overflow:auto;padding:5px;">
								
							<div class="custom-check goleft">
				           
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				              
				              <!-- Fazer for each para a chamada-->
				                
				                <c:forEach items="${chamada}" var="voluntario">
				                
				                <c:if test="${voluntario.status == 0}">
				                
				                <tr>
				           			
				           			<td>
									    <div id="${voluntario.userid}c" style="display: block; color: #000; text-shadow: 1px 1px 1px #ccc; font-size: 1.2em;">
									       <a>  <i class="fa fa-question"></i></a>
									    </div>									    
									    <div id="${voluntario.userid}a" style="display:none; color: #14c10e; text-shadow: 1px 1px 1px #ccc; font-size: 1.2em;">
									        <i class="fa fa-thumbs-o-up"></i>
									    </div>
									    <div id="${voluntario.userid}b" style="display:none; color: #f22e2e; text-shadow: 1px 1px 1px #ccc; font-size: 1.2em;">   
									       <i class="fa fa-thumbs-o-down"></i>
							           	</div>	
				           			</td>

				                    <td> <a style="text-shadow: 1px 1px 1px #ccc; font-size: 1.2em;">${voluntario.nome}</a></td>
									
									<td><a style="text-shadow: 1px 1px 1px #ccc; font-size: 1.2em;">${voluntario.whatsapp}</a></td>
									
										
				           			<td>
				           			<label><div class="btn btn-success btn-xs"><i class="fa fa-check"></i></div><input type="checkbox" onclick="javascript:yesnoCheck('${voluntario.userid}y', '${voluntario.userid}n', '${voluntario.userid}a', '${voluntario.userid}b', '${voluntario.userid}c');" name="foram" value="${voluntario.userid}" id="${voluntario.userid}y" hidden></label>
				                     </td> 
									
									<td>				 
									<label><div class="btn btn-danger btn-xs"><i class="fa fa-times"></i></div><input type="checkbox" onclick="javascript:noyesCheck('${voluntario.userid}y', '${voluntario.userid}n', '${voluntario.userid}a', '${voluntario.userid}b', '${voluntario.userid}c');" name="naoforam" value="${voluntario.userid}" id="${voluntario.userid}n" hidden></label>
									</td>
				                </tr>
				                
				                </c:if>
				                
				                </c:forEach>								
								 
				              </tbody>
				          </table> 
				         	
						</div><!-- /table-responsive -->
						</div><!-- scrollbar -->
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>		                        
		                       <button class="btn btn-theme" type="submit">Enviar</button>
		                      </div>
		                     </form>
		                     
		                     </c:otherwise>								
		                   </c:choose>	
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal5" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Lista de Apoios</h4>
		                      </div>
		                      <div class="modal-body">
		                          <div style="height:465px;line-height:3em;overflow:auto;padding:5px;">
								
							<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				              
				              <!-- Fazer for each para a lista de todos os apoios da atividade-->
				              <c:forEach items="${apoios}" var="apoio">			
										 <tr>	
										<td>				                      
				                     	   <a>${apoio.nome}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${apoio.whatsapp}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${apoio.regiao}</a>
				      					</td>
				      					</tr>
				      				</c:forEach>			              							 
								 
				              </tbody>
				          </table>
						</div><!-- /table-responsive -->
								
						</div><!-- scrollbar -->
		
		                      </div>
		                      <div class="modal-footer">
		                      <h5 class = "goleft floater" >Total de ${fn:length(apoios)} Apoios</h5>
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>		                          
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModaDesativar" class="modal fade">
		              <div class="modal-dialog">		           
		                  <div class="modal-content">
		                  <form action="statusAtividade?atividade=${ativid}&acao=0"  method="post">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Confirmar de DesativaÁ„o</h4>
		                      </div>
		                      <div class="modal-body">
		                      
		                          <p>VocÍ tem certeza que n„o haver· atividade essa semana do LaÁos no ${atividade.tag}? Entre com sua senha para confimar.</p>
		                          <input type="password" name="senha" placeholder="Senha" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-theme" type="submit">Enviar</button>
		                      </div>
		                   </form> 
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
				  
				  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModaAtivar" class="modal fade">
		              <div class="modal-dialog">		           
		                  <div class="modal-content">
		                  <form action="statusAtividade?atividade=${ativid}&acao=1"  method="post">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Confirmar de AtivaÁ„o</h4>
		                      </div>
		                      <div class="modal-body">
		                      
		                          <p>VocÍ tem certeza que ir· acontecer atividade essa semana do LaÁos no ${atividade.tag}? Entre com sua senha para confimar.</p>
		                          <input type="password" name="senha" placeholder="Senha" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-theme" type="submit">Enviar</button>
		                      </div>
		                   </form> 
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
				  
				   <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal7" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Atualizar InformaÁıes da Atividade</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="atualizaAtividade" method = "post">
		                      
		                 <div class="modal-body">
		                 
		                 <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Nome da Atividade</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="nome" value = "${atividade.nome}" required>
                              </div>
                          </div>
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">EndereÁo</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="endereco" value = "${atividade.endereco}" required>
                              </div>
                          </div>
                            <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Local de Preparo</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="local_preparo" value = "${atividade.local_preparo}" required>
                              </div>
                          </div>	
                            <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Hor·rio</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="horario" value = "${atividade.horario}" required>
                              </div>
                          </div>	
                          
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">N˙mero de Volunt·rios (6 ‡ 22)</label>
                              <div class="col-sm-10">
                                  <input class="form-control" type="number" name="limite_voluntario" min="6" max="22" value = "${atividade.limite_voluntario}" required>
                              </div>
                          </div>	 	
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">N˙mero de Apoios (4 ‡ 8)</label>
                              <div class="col-sm-10">
                                  <input class="form-control" type="number" name="limite_apoio" min="4" max="8" value = "${atividade.limite_apoio}" required>
                              </div>
                          </div>	   
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">N˙mero de Novatos (0 ‡ 8)</label>
                              <div class="col-sm-10">
                                  <input class="form-control" type="number" name="limite_novato" min="0" max="8" value = "${atividade.limite_novato}" required>
                              </div>
                          </div>  
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">PerÌodo da Atividade</label>
                              <div class="col-sm-10">  
                               	   <select class="form-control" name = "dia"  required>
                                    	<option value="1">Domingo</option>
                                    	<option value="2">Segunda</option>
                                    	<option value="3">TerÁa</option>
                                    	<option value="4">Quarta</option>
                                    	<option value="5">Quinta</option>
                                    	<option value="6">Sexta</option>
                                    	<option value="7">S·bado</option>
                                    </select> 
                            			<br>
                                    <select class="form-control" name = "periodo"  required>
                                    	<option value="1">Manh„</option>
                                    	<option value="2">Tarde</option>
                                    	<option value="3">Noturno</option>
                                    </select>
                              </div>
                          </div> 
                          
						    <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">DescriÁ„o</label>
                              <div class="col-sm-10">
                                 <textarea rows="8" class="form-control placeholder-no-fix" name="descricao" required>${atividade.descricao}</textarea>
                              </div>
                          </div>                               

						<input type="hidden" name="ativid" value="${ativid}" />
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
					
								
                  </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">
                    <!--Inicio Side Bar-->
						<h3>Volunt·rios da Atividade</h3>
                                        
                      <!-- First Action -->
                      <div class="desc box2 centered">
                      
						<button type="button" class="btn btn-theme05 btn-primary" data-toggle="modal" href="#myModal4"><span class="li_like"></span>&nbsp&nbspFazer Chamada</button>
                
                      </div>
					  
					  <h3>InformaÁıes da Atividade</h3>
                                        
                      <!-- First Action -->
                      <div class="desc box2 centered">
                      
						<button type="button" class="btn btn-theme05 btn-primary" data-toggle="modal" href="#myModal7"><span class="li_note"></span>&nbsp&nbspAtualizar as InformaÁıes</button>
                
                      </div>
             
                       <!-- USERS ONLINE SECTION -->
						<h3>Apoios da Atividade</h3>
						
                      <!-- First Member -->
                      <div class="desc">
                      		
						  
						<div class="centered">
                      	
                      	<div class="btn-group ">
						<button type="button" class="btn btn-theme" data-toggle="modal" href="#myModal3"><i class="fa fa-check"></i> Adicionar</button>
						</div>
						<div class="btn-group ">
						<button type="button" class="btn btn-theme02" data-toggle="modal" href="#myModal5"><i class="fa li_star"></i>&nbsp&nbspVer Lista&nbsp&nbsp</button>
						</div>
                  
                      </div>
					</div>
                                   
                 					
					<h3>Status da Atividade</h3>
                                        
                      <!-- First Action -->
                      <div class="desc centered">
                      
                      <c:choose>
					<c:when test="${atividade.status == 1}">
					
						<div class="btn-group">
						  <button type="button" class="btn btn-theme04 btn-primary" data-toggle="modal" href="#myModaDesativar">&nbsp&nbspDesativar Atividade Essa Semana</button>
						</div>	
						
					</c:when>
				<c:otherwise>
				
						<div class="btn-group">
						  <button type="button" class="btn btn-theme btn-primary" data-toggle="modal" href="#myModaAtivar">&nbsp&nbspAtivar Atividade Essa Semana</button>
						</div>	
						
                </c:otherwise>
                </c:choose>
						
						
											
                      </div>

					<!-- USERS ONLINE SECTION -->
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
              </div> <!--/row -->
          </section>
      </section>
</section>

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              LaÁos da Alegria - 2016
              <a href="volunteer-area" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->

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
	
		
	<script type="application/javascript">
	
	$('select[name^="dia"] option[value="<fmt:formatNumber type="number" 
			pattern="#" value="${atividade.periodo / 10}" />"]').attr("selected","selected");
	
	$('select[name^="periodo"] option[value="${atividade.periodo % 10}"]').attr("selected","selected");
	
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
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        };
        
			function yesnoCheck(param1, param2, param3, param4,param5) {
				
			    if (document.getElementById(param1).checked) {
			        document.getElementById(param3).style.display = 'block';
			        document.getElementById(param4).style.display = 'none';
			        document.getElementById(param5).style.display = 'none';
			        document.getElementById(param2).checked = false; 
			    } else {
			    	
			    	 document.getElementById(param3).style.display = 'none';
				     document.getElementById(param4).style.display = 'none';
				     document.getElementById(param5).style.display = 'block';
			    	
			    }
			    	
			    
			};
			
		function noyesCheck(param1, param2, param3, param4,param5) {
		
			if (document.getElementById(param2).checked){
			    document.getElementById(param3).style.display = 'none';
			    document.getElementById(param4).style.display = 'block';
			    document.getElementById(param5).style.display = 'none';
			    document.getElementById(param1).checked = false;
			} else {
				
				 document.getElementById(param3).style.display = 'none';
				 document.getElementById(param4).style.display = 'none';
				 document.getElementById(param5).style.display = 'block';

			}

		}

    </script>
  

  </body>
</html>
