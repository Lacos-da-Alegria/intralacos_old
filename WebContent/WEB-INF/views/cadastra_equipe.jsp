<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="La�os da Alegria">
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <title>La�os da Alegria</title>

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
	
			
		<div class="form-panel">
		    <h1>Cadastrar Equipe Administrativa</h1>
		  	<hr>
			<div class="row">
			
			<form class="form-horizontal style-form" action="cadastrarEquipe" method = "post">	
			      <!-- left column -->
			      <div class="col-md-3">
			        <div class="text-center">
			          <img src="assets/img/equipe.jpg" class="avatar img-circle" alt="avatar" name="pic" accept="asset/img/*">
			          <h6>Preencha o m�ximo poss�vel!</h6> 			          
			        </div>
			      </div>
		      
		      <!-- edit form column -->
		      <div class="col-md-8 personal-info ">
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Diretoria Respons�vel</b></label>
		              <div class="col-sm-10">
		              <select class="form-control" name = "diretoria"  required>
							  <option>Diretoria Executiva</option>
							  <option>Diretoria de ONG's</option>
							  <option>Diretoria de Hospitais</option>
							  <option>Diretoria de Comunica��o</option>
		                </select> 
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Nome</b></label>
		              <div class="col-sm-10">
		               <input name="nome" class="form-control" type="text" required >
		              </div>
		          </div>
		              
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>E-mail do Lider</b></label>
		              <div class="col-sm-10">
		              <input name = "lider_email" class="form-control" type="email">
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>N�meto de Membros</b></label>
		              <div class="col-sm-10">
		              <input name = "num_membros" class="form-control" type="number">
		              </div>
		          </div>
				<div class="form-group">  
					<label class="col-sm-2 col-sm-2 control-label"><b>Atividade</b></label>
					<div class="col-sm-10">
						 <select class="form-control" name = "ativ_id">
				                <option value="0" >Nenhuma Atividade Especifica</option>			
				             <c:forEach items="${Atividade}" var="ativ" >				
								<option value="${ativ.ativid}" >${ativ.nome}</option>
							</c:forEach>
						</select>						             
					</div>
				</div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Objetivo</b></label>
		              <div class="col-sm-10">
		               <input name="objetivo" class="form-control" type="text" required >
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Descri��o</b></label>
		              <div class="col-sm-10">
		               <textarea rows="8" class="form-control placeholder-no-fix" name="descricao"></textarea>
		              </div>
		          </div>
		          
		          	<div class="form-group">
		            	<label class="col-md-2 control-label"></label>
		         	 	<div class="modal-footer">
		              	<input class="btn btn-primary" value="Criar Equipe" type="submit">
		             		 <span></span>
		              	<input class="btn btn-default" value="Cancelar" type="reset">
		             </div>
		            
		          </div>
		          </div>
		        </form>
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
		                          <p>Entre com o feedback abaixo. Depois � s� enviar</p>
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
	

  </body>
</html>