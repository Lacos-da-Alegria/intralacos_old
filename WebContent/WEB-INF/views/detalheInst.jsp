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
    <link href="assets/js/select2/css/select2.min.css" rel="stylesheet" />
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
		    <h3><a href="javascript:history.back()"><i class="fa fa-arrow-left" aria-hidden="true"></i></a> &nbsp&nbsp <i class="fa fa-university" aria-hidden="true"></i> Detalhes  <span class="hidden-phone">da Institui��o</span>
		    <a href="AtualizaInstituicao?inst_id=${instituicao.id}" type="button" class="btn btn-primary pull-right" style="margin-right:10px;"><i class="fa fa-pencil fa-lg" aria-hidden="true"></i> <span class="hidden-phone">&nbsp&nbsp&nbsp Atualizar Institui��o</span></a>
		    </h3>
		    
		  	<hr>
			<div class="row">
			
		      <form class="form-horizontal style-form">
		      <!-- edit form column -->
		      <div class="col-md-12 personal-info ">
		         	<div class="form-group">
		           	<label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-building-o" aria-hidden="true"></i> Nome da Institui��o</b></label>
		           		<div class="col-sm-10">
                           <p> ${instituicao.nome}</p>
                        </div>		         
		           	</div>
		           	<div class="form-group">
		           	<label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-phone" aria-hidden="true"></i> Telefone</b></label>
		           		<div class="col-sm-10">
                           <p>${instituicao.telefone}</p>
                        </div>		         
		           	</div>
		          <div class="form-group">
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-envelope-o" aria-hidden="true"></i> E-mail</b></label>
		              <div class="col-sm-10">
		              <p> ${instituicao.email}</p>
		              </div>
		            </div>
		          
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-user-circle-o" aria-hidden="true"></i> Nome do Respons�vel</b></label>
		              <div class="col-sm-10">
		              <p> ${instituicao.responsavel}</p>
		              </div>
		          </div>
		          
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-phone" aria-hidden="true"></i> Contato do Respos�vel</b></label>
		              <div class="col-sm-10">
		              <p> ${instituicao.contato_resp} </p>
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-envelope-o" aria-hidden="true"></i> E-mail do Respons�nvel</b></label>
		              <div class="col-sm-10">
		              <p> ${instituicao.email_resp} </p>
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-book" aria-hidden="true"></i> Descri��o da Institui��o</b></label>
		              <div class="col-sm-10">
		              	<p> ${instituicao.descricao_insti} <p> 
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-tags" aria-hidden="true"></i> Caracteristicas</b></label>
		              <div class="col-sm-10">
		              	<c:forEach items="${instituicao.caracteristicas()}" var="tag">
							<span class="label label-default">${tag.nome}</span>&nbsp&nbsp
						</c:forEach>
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-question-circle-o" aria-hidden="true"></i> J� foi Realizada alguma atividade?</b></label>
		              <div class="col-sm-10"s>
			              <p> ${instituicao.jaFomos()} </p>
		              </div>
		          </div>
		          <c:if test="${instituicao.ja_fomos}">
		          <div class="form-group ativ_relizada">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-bookmark" aria-hidden="true"></i> Descri��o da Atividade Realizada</b></label>
		              <div class="col-sm-10">
		              	<p>${instituicao.descricao_ativ}</p>
		              </div>
		          </div>
		          </c:if>
		           <div class="form-group ativ_relizada" >  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-window-restore" aria-hidden="true"></i> Atividades Realizadas</b></label>
		              <div class="col-sm-10"s>
		              	<c:forEach items="${instituicao.atividades()}" var="tag">
							<span class="label label-default">${tag.nome}</span>&nbsp&nbsp
						</c:forEach>
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-thermometer-full" aria-hidden="true"></i> Descri��o de Pontos Cr�ticos</b></label>
		              <div class="col-sm-10">
		              	<p> ${instituicao.descricao_p_criticos}</p>
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-bomb" aria-hidden="true"></i> Pontos Cr�ticos</b></label>
		             <div class="col-sm-10"s>
		              	<c:forEach items="${instituicao.pontosCriticos()}" var="tag">
							<span class="label label-default">${tag.nome}</span>&nbsp&nbsp
						</c:forEach>
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
    
    <script src="assets/js/select2/js/select2.min.js"></script>    
    
    <!-- InputMask -->
	<script src="<c:url value="assets/js/input-mask/jquery.inputmask.js" />"></script>
	<script src="<c:url value="assets/js/input-mask/jquery.inputmask.date.extensions.js" />"></script>
	<script src="<c:url value="assets/js/input-mask/jquery.inputmask.extensions.js" />"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>
    
    <script type="text/javascript">
    	$(".js-example-basic-multiple").select2();
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