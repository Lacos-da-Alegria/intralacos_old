<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
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
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
      
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    
    <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=3xxlvr4x4mfh2h5wsc9vh3bdq5b2st3sxgv8iowhcxy83kbv"></script>
 	<script>tinymce.init({ selector:'textarea', menubar: false, height: 250 });</script>
   
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
          
          	
          	<div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">
	                  	  	  <h3 style="margin-left:10px;"> <b> <i class="fa fa-microphone" aria-hidden="true"></i> <span class="hidden-phone">Ouvidoria do Laços da Alegria</span> </b> 
	                  	  	  	<a href="#" type="button" class="btn btn-default pull-right" style="margin-right:10px;"><i class="fa fa-ticket fa-lg" aria-hidden="true"></i> <span class="hidden-phone">&nbsp&nbsp&nbsp Meus Atendimentos</span></a>
	                  	  	  	<a data-toggle="modal"  href="#ModalFeedBack" type="button" class="btn btn-primary pull-right" style="margin-right:10px;"><i class="fa fa-user-secret fa-lg" aria-hidden="true"></i> <span class="hidden-phone">&nbsp&nbsp&nbsp Feedback Anônimo</span></a>
								<a data-toggle="modal"  href="#ModalAtendimento" type="button" class="btn btn-success pull-right" style="margin-right:10px;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> <span class="hidden-phone">&nbsp&nbsp&nbsp Abrir Atendimento</span></a>	                  	  	  	
	                  	  	  </h3>	                  	      	                   	  
	                  	  	  <hr>    
                              <thead>
                              <tr>
                                  <th><i class="fa fa-star-half-o " aria-hidden="true"></i> <span class="hidden-phone">Situação</span></th>
                                  <th><i class="fa fa-hourglass-half " aria-hidden="true"></i> <span class="hidden-phone">Abertura</span></th>
                                  <th><i class="fa  fa-check-square-o"></i> <span class="hidden-phone">Tipo</span></th>
                                  <th><i class="fa fa-user-circle-o "></i> <span class="hidden-phone">Atendente</span></th>
                                  <th><i class="fa fa-question-circle "></i> <span class="hidden-phone">Ver Detalhes</span></th>
                              </tr>  
                              </thead>
                              <tbody>
                              <c:forEach items="${atendimentos}" var="atend">
                              <tr>   
	                              <td>&nbsp;&nbsp;&nbsp;<c:choose><c:when test="${atend.status == 1}"><i class="fa fa-check fa-2x" style="color:#11bf25;" aria-hidden="true"></i></c:when><c:otherwise><i class="fa fa-exclamation fa-2x" style="color:#fdb507;" aria-hidden="true"></i></c:otherwise> </c:choose></td>
	                              <td>${atend.dt_criacao}</td>
	                              <td>${atend.categoria.nome}</td>
	                              <td>${atend.nomeAtendente()}</td>
	                              <td><a onclick="apresentaDetalhe('${atend.mostraOuvidoria()}','${atend.mostraResposta()}',${atend.id});" style="font-size:16px;"  class="btn btn-primary btn-xs fa fa-info-circle"></a></td>
                              </tr>   
                              </c:forEach>                              
                              </tbody>
                          </table>
                      </div><!-- /content-panel -->
                  </div><!-- /col-md-12 -->
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
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Deixe Aqui seu Feedback Anônimo</h4>
		                      </div>
		                      <form action="feedback" method="post">
		                      <div class="modal-body">
		                          <p>Entre com o feedback abaixo. Depois é só enviar</p> 
								<textarea rows="8" class="form-control placeholder-no-fix" name="feedback"></textarea>			
								<br>
								<small>*prezamos por um ambiente onde todos possam falar o que sentem no coração, todos feedback são lidos e trabalhados</small> 					 
		                      </div>
		                      <div class="modal-footer">  
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <input class="btn btn-primary" type="submit" value="Enviar">
		                     </div>   
		                     </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalAtendimento" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Abertura de Atendimento</h4>
		                      </div>
		                      <form action="abrirAtendimeto" method="post">
		                      <div class="modal-body">
		                          <p>Escolha o atendimento, escreva o seu questionamento que iremos responder o mais rápido possível</p> 
		                        <select name="catId" class="form-control" required>
									<option value="">Escolha uma Opção*</option>								
									<c:forEach items="${tipos}" var="tipo">
										<option value="${tipo.id}">${tipo.nome}</option>	
									</c:forEach>
								</select>
								<br>							 
								<textarea rows="8" class="form-control placeholder-no-fix" name="ouvidoria"></textarea>			
								<br>
								<small style="color:red;">*Esse atendimento não é anônimo</small> 					 
		                      </div>
		                      <div class="modal-footer">  
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <input class="btn btn-success" type="submit" value="Enviar">
		                     </div>   
		                     </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalDetalhe" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-x">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Detalhe do Atendimento</h4>
		                      </div>
		                      <div class="modal-body">
		                      	<div class="content-panel showback">
		                      		<h4><i class="fa fa-envelope"></i> Mensagem</h4>
		                      		<hr style="border-color:#bababa;">
		                      		<div id="ouvidoria"></div>
		                      	</div>
		                      	<br>
		                      	<div class="content-panel showback">
		                      		<h4><i class="fa fa-check-square-o"></i> Resposta</h4>
		                      		<hr style="border-color:#bababa;">
		                      		<div id="resposta"></div>
		                      	</div>
		                      </div>
		                      <div class="modal-footer">  
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <a  class="btn btn-danger" id="concluirBtn" type="hidden" >Concluir Atendimento</a>
		                     </div>   
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
  		           
          </section>
      </section>

      <!--main content end-->

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

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	
	
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
 
	<script type="text/javascript">
      
        function apresentaDetalhe(ouvidoria, resposta, id) {
        	
        	$("#ouvidoria").html(ouvidoria);
        	$("#resposta").html(resposta);

        	if(!(resposta === "Ouvidoria ainda <b>não</b> foi respondida!")){
        		console.log("Entrou!")
        		$("#concluirBtn").show();
        		$("#concluirBtn").attr("href", "concluirOuvidoria?idOuvidoria="+id);
        	} else {
        		$("#concluirBtn").hide();
        	}
        	
        	$('#ModalDetalhe').modal('show');        	
          };
          
    </script> 

  </body>
</html>