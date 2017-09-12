<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
          
          	<h3>Controle de Recurso Humano</h3>
          	
          	
          	<div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">
	                  	  	  <h4 style="margin-left:10px;"> Cordenadores de Atividades Hospitalares</h4>
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th><i class="fa fa-star-o"></i>Atividade</th>
                                  <th><i class="fa fa-user"></i> Nome</th>
                                  <th><i class="fa fa-whatsapp"></i> WhatsApp</th>
                                  <th class="hidden-phone"><i class=" fa fa-envelope-o"></i> E-mail</th>
                                  <th class="hidden-phone"><i class="fa fa-globe"></i> Região</th>
                                  <th><i class="fa fa-times-circle-o"></i> Remover</th>
                                  <th></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${recursos.coordenadores()}" var="recurso" varStatus="loop" >
                              <tr>
                              	  <td>${recurso.atividade.tag}</td>
                                  <td>${recurso.membro.nome}</td>
                                  <td>${recurso.membro.whatsapp}</td>
                                  <td class="hidden-phone">${recurso.membro.email}</td>
                                  <td class="hidden-phone">${recurso.membro.regiao.nome}</td>
                                  <td>
                                      <button data-toggle="modal" href="#ModalRetirar" onclick="modalRetirar(${recurso.id},1);" class="btn btn-danger btn-xs" style="margin-left:10px;"><i class="fa fa-times "></i></button>
                                  </td>
                              </tr>   
                              </c:forEach>                          
                              </tbody>
                          </table>
                          
                    <label class="col-md-2 control-label"></label>
		         	 <div class="modal-footer">
		              <button class="btn btn-success" data-toggle="modal" href="#AddRecursoHumano"><i class="fa fa-check" ></i>Adicionar Coordenador</button>
		              </div>
		              
                      </div><!-- /content-panel -->
                  </div><!-- /col-md-12 -->
              </div><!-- /row -->		
              
              <div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">
	                  	  	  <h4 style="margin-left:10px;"> Equipe Controle de Novatos </h3>
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th><i class="fa fa-star-o"></i>Atividade</th>
                                  <th><i class="fa fa-user"></i> Nome</th>
                                  <th><i class="fa fa-whatsapp"></i> WhatsApp</th>
                                  <th class="hidden-phone"><i class=" fa fa-envelope-o"></i> E-mail</th>
                                  <th class="hidden-phone"><i class="fa fa-globe"></i> Região</th>
                                  <th><i class="fa fa-times-circle-o"></i> Remover</th>
                                  <th></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${recursos.controleNovatos()}" var="recurso" varStatus="loop" >
                              <tr>
                              	  <td>${recurso.atividade.tag}</td>
                                  <td>${recurso.membro.nome}</td>
                                  <td>${recurso.membro.whatsapp}</td>
                                  <td class="hidden-phone">${recurso.membro.email}</td>
                                  <td class="hidden-phone">${recurso.membro.regiao.nome}</td>
                                  <td>
                                      <button data-toggle="modal" href="#ModalRetirar" onclick="modalRetirar(${recurso.id},2);" class="btn btn-danger btn-xs" style="margin-left:10px;"><i class="fa fa-times "></i></button>
                                  </td>
                              </tr>   
                              </c:forEach>                          
                              </tbody>
                          </table>
                    <label class="col-md-2 control-label"></label>
		         	 <div class="modal-footer">
		              <button class="btn btn-success" data-toggle="modal" href="#AddRecursoHumano"><i class="fa fa-check"></i>Adicionar Membro</button>
		              </div>
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalRetirar" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-d">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Retirada de Recurso Humano</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>Você Realmente quer <b>Retirar</b> esse Recurso Humano?</p>		                       	
		                      </div>
		                      <form action="retirarRescurso" method="post">
		                      <div class="modal-footer">
		                       <input hidden type="number" name="userId" id="retirarCord" value = "coordId" >
		                       <input hidden type="number" name="tipoRecurso" id="tipoRecurso" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Retirada</button>
		                      </div>
		                      </form>
		                  </div>    
		              </div>
		          </div>
		          <!-- modal -->

		         <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="AddRecursoHumano" class="modal fade">
		              <div class="modal-dialog">
		              <form class="form-horizontal style-form" action="adicionarRecurso"  method="post">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Adicionar um Recurso Humano</h4>
		                      </div>
		                      <div class="modal-body">
		                      <div class="form-group" >
		                      	<p style="margin-left:15px;">Entra Abaixo com as infomações e clique em adiconar.</p>
		                      </div>
						          <div class="form-group">  
						            <label class="col-sm-2 col-sm-2 control-label"><b>Tipo de Recurso</b></label>
						              <div class="col-sm-10">
						                <select class="form-control" name = "tipo" required>
				                      	  <option value="" ></option>							
										  <option value="33" >Coordenador de Atividade</option>
										  <option value="11" >Membro Controle de Novatos</option>
										  <option value="66" >Assessor Controle de Novatos</option>
										</select>
						              </div>
						          </div>
						          <div class="form-group">  
						            <label class="col-sm-2 col-sm-2 control-label"><b>E-mail</b></label>
						              <div class="col-sm-10">
						              <input class="form-control" name = "email" placeholder="Email Cadastrado no IntraLaços" type="text" required>
						              </div>
						          </div>
						          <div class="form-group">  
						            <label class="col-sm-2 col-sm-2 control-label"><b>Atividade</b></label>
						              <div class="col-sm-10">
						              	<select class="form-control" name = "atividade" required>
				                      	  <option value="" ></option>			
				                      	  <c:forEach items="${atividades}" var="ativ" >				
										  <option value="${ativ.id}" >${ativ.tag}</option>
										  </c:forEach>
										</select>						             
						              </div>
						          </div>						          						      		                      
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-theme" type="submit">Adicionar</button>
		                      </div>
		                      </div>
		                     </form>
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
    
    function modalRetirar(coordId, tipo) {
        // altera as informações do modal cancelar             
      	  document.getElementById("retirarCord").value = coordId;
      	  document.getElementById("tipoRecurso").value = tipo;
        
      };
    
    </script> 
    
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
	

  </body>
</html>