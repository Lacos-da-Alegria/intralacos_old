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

    <script src="assets/js/chart-master/Chart.js"></script>
    
    <script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=3xxlvr4x4mfh2h5wsc9vh3bdq5b2st3sxgv8iowhcxy83kbv"></script>
 	<script>tinymce.init({ selector:'textarea', menubar: false, height: 250 });</script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <link rel="shortcut icon" href="assets/img/favicon.png">
    
    <style>
		/* Dropdown Button */
		.dropbtn {
		    background-color: #4CAF50;
		    color: white;
		    padding: 16px;
		    font-size: 16px;
		    border: none;
		    cursor: pointer;
		}
		
		/* The container <div> - needed to position the dropdown content */
		.dropdown {
		    position: relative;
		    display: inline-block;
		}
		
		/* Dropdown Content (Hidden by Default) */
		.dropdown-content {
		    display: none;
		    position: absolute;
		    background-color: #f9f9f9;
		    min-width: 160px;
		    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		    z-index: 1;
		}
		
		/* Links inside the dropdown */
		.dropdown-content a {
		    color: black;
		    padding: 12px 16px;
		    text-decoration: none;
		    display: block;
		}
		
		/* Change color of dropdown links on hover */
		.dropdown-content a:hover {background-color: #f1f1f1}
		
		/* Show the dropdown menu on hover */
		.dropdown:hover .dropdown-content {
		    display: block;
		}
		
		/* Change the background color of the dropdown button when the dropdown content is shown */
		.dropdown:hover .dropbtn {
		    background-color: #3e8e41;
		}
    </style>
    
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
	                  	  	  <h3 style="margin-left:10px;"> <b> <i class="fa fa-university" aria-hidden="true"></i> ${atendimento.nome} </b> 
	                  	  	  </h3>	                  	      	                   	  
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th><i class="fa fa-star-half-o " aria-hidden="true"></i> <span class="hidden-phone">Data de Criação</span></th>
                                  <th><i class="fa fa-caret-square-o-down " aria-hidden="true"></i> <span class="hidden-phone">Categoria</span></th>
                                  <th><i class="fa fa-user-circle-o "></i> <span class="hidden-phone">Solicitante</span></th>
                                  <th><i class="fa fa-check-square "></i> <span class="hidden-phone">Ação</span></th>
                                  <th><i class="fa fa-paper-plane "></i> <span class="hidden-phone">Responder</span></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${ouvidorias}" var="ouvid">
                              <tr>
	                              <td>${ouvid.dt_criacao}</td>
	                              <td>${ouvid.categoria.nome}</td>
	                              <td>${ouvid.voluntario.getPrimerio_nome()}</td>
	                              <c:choose>
	                              	<c:when test="${ouvid.responsavel == null}">
	                              		<td><a href="capturarAtend?atendId=${ouvid.id}" class="btn btn-success btn-xs fa fa-lock" style="font-size:16px;"></a></td>
	                              		<td><a class="btn btn-danger btn-xs fa fa-times" style="font-size:16px;"></a></td>
	                              	</c:when>
	                              	<c:otherwise>
	                              		<c:choose>
		                              	<c:when test="${ouvid.responsavel.id == voluntario.id}">
		                              	<td><a href="descapturarAtend?atendId=${ouvid.id}" style="font-size:16px;" class="btn btn-warning btn-xs fa fa-unlock"></a></td>
		                              	<td><a onclick="apresentaDetalhe('${ouvid.mostraOuvidoria()}',${ouvid.id});" style="font-size:16px;"  class="btn btn-primary btn-xs fa fa-info-circle"></a></td>
		                              	</c:when>
		                              	<c:otherwise>
		                              		<td><a class="btn btn-danger btn-xs fa fa-times" style="font-size:16px;"></a></td>
		                              		<td><a class="btn btn-danger btn-xs fa fa-times" style="font-size:16px;"></a></td>
		                              	</c:otherwise>
		                              </c:choose>
	                              	</c:otherwise>
	                              </c:choose>
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalDetalhe" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Detalhe do Atendimento</h4>
		                      </div>
		                      <form action="reponderOuvidoria">
		                      <div class="modal-body">
		                      	<div class="content-panel showback">
		                      		<h4><i class="fa fa-envelope"></i> Mensagem</h4>
		                      		<hr style="border-color:#bababa;">
		                      		<div id="ouvidoria"></div>
		                      	</div>
		                      	<br>
		                      	<input type="hidden" id="ouvId" name="idOuvidoria">
		                      	<div class="content-panel showback">
		                      		<h4><i class="fa fa-check-square-o"></i> Resposta</h4>
		                      		<textarea rows="8" class="form-control placeholder-no-fix" name="resposta"></textarea>
		                      	</div>
		                      </div>
		                      <div class="modal-footer">  
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <input class="btn btn-primary" type="submit" value="Enviar">
		                     </div> 
		                     </form>  
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
    
        function clickAndDisable(link) {
            // disable subsequent clicks
            link.onclick = function(event) {
               event.preventDefault();
            }
          };
          
    </script> 
    
    <script type="text/javascript">
      
        function apresentaDetalhe(ouvidoria, id) {
        	
        	$("#ouvidoria").html(ouvidoria);
        	$("#ouvId").val(id);
        	  
        	$('#ModalDetalhe').modal('show');        	
          };
          
    </script> 

  </body>
</html>