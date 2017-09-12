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
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <link rel="shortcut icon" href="assets/img/favicon.png">
    
    <style>
	    #fileselector {
	    margin: 10px; 
		}
		#upload-file-selector {
		    display:none;   
		}
		.margin-correction {
		    margin-right: 10px;   
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
	                  	  	  <h3 style="margin-left:10px;"> <b> <i class="fa fa-tags" aria-hidden="true"></i> Controle de Tags </b> 
	                  	  	  	<button data-toggle="modal" href="#modalTag" type="button" class="btn btn-primary pull-right" style="margin-right:10px;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> <span class="hidden-phone">  Nova Tag</span></button>
	                  	  	  </h3>	                  	      	                   	  
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th><i class="fa fa-tag " aria-hidden="true"></i> <span class="hidden-phone"> Tag</span></th>
                                  <th><i class="fa fa-file-code-o " aria-hidden="true"></i> <span class="hidden-phone"> Tipo</span></th>
                                  <th><i class="fa fa-question-circle "></i> <span class="hidden-phone">Status</span></th>
                                  <th><i class="fa fa-commenting-o "></i> <span class="hidden-phone">Descricao</span></th>
                                  <th><i class="fa fa-star-half-o" aria-hidden="true"></i> <span class="hidden-phone">Alterar Status</span></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${tags}" var="tag">
                              <tr>
	                              <td>${tag.nome}</td>
	                              <td>${tag.textoTipo()}</td>
	                              <td>${tag.textoStatus()}</td>
	                              <td>${tag.descricao}</td>
	                             <c:choose>
	                             	<c:when test="${tag.status == 1}">
	                             		<td><a onclick="clickAndDisable(this);" href="atualizaTag?tag_id=${tag.id}" style="font-size:13px;" class="btn btn-danger btn-xs fa fa-history"></a></td>
	                             	</c:when>
	                             	<c:otherwise>
	                             		<td><a onclick="clickAndDisable(this);" href="atualizaTag?tag_id=${tag.id}" style="font-size:13px;" class="btn btn-success btn-xs fa fa-history"></a></td>
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
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalTag" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered"><i class="fa fa-book" aria-hidden="true"></i> Cadastrar Tag</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="cadastrarTag" method = "post" enctype="multipart/form-data">
		                      
		                 <div class="modal-body">
		                 
		                 <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tipo</label>
                              <div class="col-sm-10">  
                               	   <select class="form-control" name = "tipo"  required>
                                    	<option value="">Escolha um Tipo</option>
                                    	<option value="1">Caracteristica</option>
                                    	<option value="2">Atividade</option>
                                    	<option value="3">Ponto Crítico</option>
                                    </select> 
                              </div>
                          </div> 
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Tag </label> 
                              <div class="col-sm-10">
                                 <input class="form-control" type="text" name="nome" required>
                              </div>
                          </div> 
						    <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Descrição</label>
                              <div class="col-sm-10">
                                 <textarea rows="8" class="form-control placeholder-no-fix" name="descricao" required></textarea>
                              </div>
                          </div> 
						</div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-primary" type="submit">Cadastrar</button>
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

  </body>
</html>