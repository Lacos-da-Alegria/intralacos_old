<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
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
    <link href="assets/js/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">  
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    <link href="assets/js/select2/css/select2.min.css" rel="stylesheet" />
    
    <link rel="stylesheet" media="all" type="text/css" href="http://code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css" />
    <link rel="stylesheet" media="all" type="text/css" href="assets/js/datePicker/jquery-ui-timepicker-addon.css" />

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
          	<h3><i class="fa fa-calendar" aria-hidden="true"></i> Calendário de Ações 
          	<a data-toggle="modal" href="#modalAgendar" type="button" class="btn btn-primary pull-right" style="margin-right:10px;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> <span class="hidden-phone">&nbsp&nbsp&nbsp Nova Ação</span></a>
          	</h3>
          	<br>
              <!-- page start-->
              <div class="row mt">
                      <section class="panel">
                          <div class="panel-body">
                              <div id="calendar" class="has-toolbar"></div>
                          </div>
                      </section>
              </div>
              <!-- page end-->
              
              
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalAgendar" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered"><i class="fa fa-calendar" aria-hidden="true"></i> Agendar Ação</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="agendarAcao" method = "post" enctype="multipart/form-data">
		                      
		                 <div class="modal-body">
		                 
		                 <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Instituição</label>
                              <div class="col-sm-10">  
                               	   <select class="form-control" id="instituicoes" name = "instituicao.id"  required  style="width:100%;">
                                    	<c:forEach items="${instituicoes}" var="instituicao">
                                    		<option value="${instituicao.id}">${instituicao.nome}</option>
                                    	</c:forEach>
                                    </select> 
                              </div>
                          </div> 
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Nº de Voluntários</label>
                              <div class="col-sm-10">
                                  <input class="form-control" type="number" name="voluntarios"  required>
                              </div>
                          </div>
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Horário de Início </label> 
                              <div class="col-sm-10">
                                 <input class="form-control" name="horario" id="datePicker" value="" required/>
                              </div>
                          </div> 
						   <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Duração da Ação</label>
                              <div class="col-sm-10">
                                  <input class="form-control" type="number" name="duracao" placeholder="Duração em Horas" required>
                              </div>
                          </div> 
						</div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-primary" type="submit">Agendar</button>
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
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered"><i class="fa fa-calendar" aria-hidden="true"></i> Ação na <span id="nomeInstSpan"></span> </h4>
		                      </div>
		                <form class="form-horizontal"> 
		                 <div class="modal-body">
							      <!-- edit form column -->
							         	<div class="form-group">
							           	<label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-building-o" aria-hidden="true"></i> Nome da Instituição</b></label>
							           		<div class="col-sm-9">
					                           <p id="nomeInst"></p>
					                        </div>		         
							           	</div>
							           	<div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-user-circle-o" aria-hidden="true"></i> Criador da Ação</b></label>
							              <div class="col-sm-9">
							              <p id="CriadorLacos"></p>
							              </div>
							         	 </div>
							         	 <div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-calendar-check-o" aria-hidden="true"></i> Horário de Início</b></label>
							              <div class="col-sm-9">
							              <p id="HararioAcao"></p>
							              </div>
							         	 </div>
							         	 <div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-clock-o" aria-hidden="true"></i> Duração</b></label>
							              <div class="col-sm-9">
							              <p id="Duracao"></p>
							              </div>
							         	 </div>
							         	 <div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-thermometer-quarter" aria-hidden="true"></i> Quantidade</b></label>
							              <div class="col-sm-9">
							              <p id="QuantVolunt"></p>
							              </div>
							         	 </div>
							           	<div class="form-group">
							           	<label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-phone" aria-hidden="true"></i> Telefone</b></label>
							           		<div class="col-sm-9">
					                           <p id="InstTel"></p>
					                        </div>		         
							           	</div>
							           	
							          <div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-user-circle-o" aria-hidden="true"></i> Nome do Responsável</b></label>
							              <div class="col-sm-9">
							              <p id="RespNome"></p>
							              </div>
							          </div>
							          
							          <div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-phone" aria-hidden="true"></i> Contato do Resposável</b></label>
							              <div class="col-sm-9">
							              <p id="RespTel"></p>
							              </div>
							          </div>
							          <div class="form-group">  
							            <label class="col-sm-3 col-sm-3 control-label"><b><i class="fa fa-book" aria-hidden="true"></i> Descrição da Instituição</b></label>
							              <div class="col-sm-9">
							              	<p id="InstDesc"></p>
							              </div>
							          </div>
							    </div>
								
		                      <div class="modal-footer">
		                      	  <a id="hrefInst" type="button" class="btn btn-primary"><i class="fa fa-info-circle fa-lg" aria-hidden="true"></i> <span class="hidden-phone">&nbsp&nbsp&nbsp Detalhes Instituição</span></a>
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" onclick="cancelaAgenda()">Cancelar Ação</button>
		                      </div>
		                      </form>
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
		                          <h4 class="modal-title centered">Cancelar Ação</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>Você Realmente quer <b>Cancelar</b> sua Essa Ação?</p>		                       	
		                      </div>
		                      <form action="cancelarAcao" method="post">
		                      <div class="modal-footer">
		                       <input  type="hidden" name="agenda_id" id="acaoCancelar" value = "" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Cancelamento</button>
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
              2017 - Laços da Alegira
              <a class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    
	<script src="assets/js/fullcalendar/fullcalendar.min.js"></script>    
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/select2/js/select2.min.js"></script> 
  
	<script type="text/javascript" src="assets/js/datePicker/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="assets/js/datePicker/lang/jquery-ui-timepicker-pt-BR.js"></script>
	<script type="text/javascript" src="assets/js/datePicker/jquery-ui-sliderAccess.js"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page
	<script src="assets/js/calendar-conf-events.js"></script> -->    
  
 
 <script type="text/javascript">
 	
 	var agendaId;
 	
 	function cancelaAgenda(){
 		
 		document.getElementById("acaoCancelar").value = agendaId;
 		
 		 $('#ModalDetalhe').modal('hide');
 		 $('#ModalCancelar').modal('show');
 		
 	};
 
 
 	var Script = function () {

	    /* initialize the calendar
	     -----------------------------------------------------------------*/

	     $('#calendar').fullCalendar({
	        header: {
	            left: 'prev,next today',
	            center: 'title',
	            right: 'month,basicWeek,basicDay'
	        },
	        
	        eventClick: function(calEvent, jsEvent, view) {

	            $.ajax({
	          	    url: "pesquisaAgenda",	  	       
	          	    data: {'idAgenda' : calEvent.allDay},
	          	    success: function(data) {
	          	    	
	          	    	var agenda = jQuery.parseJSON(data);
	          	    	
	          	    	console.log(agenda);
	          	    	
	          	    	agendaId = agenda.id;
	          	    	
	          	    	$("#nomeInst").text(agenda.instituicao.nome);
	          	    	$("#nomeInstSpan").text(agenda.instituicao.nome);
	          	    	$("#InstTel").text(agenda.instituicao.telefone);
	          	    	$("#RespNome").text(agenda.instituicao.responsavel);
	          	    	$("#RespTel").text(agenda.instituicao.contato_resp);
	          	    	$("#InstDesc").text(agenda.instituicao.descricao_insti);
	          	    	$("#CriadorLacos").text(agenda.nomeCriador);
	          	    	$("#HararioAcao").text(agenda.horarioFormat);
	          	    	$("#Duracao").text(agenda.duracao + " horas");
	          	    	$("#QuantVolunt").text(agenda.voluntarios + " voluntários");
	          	    	$("#hrefInst").attr('href', 'DetalheInstituicao?inst_id='+agenda.instituicao.id);
	          	    	
	          	    	//Preenche o modal Detalhe com dados retornados pelo ajax
	          	    	
	                }, 
	          	    error: function(e) {
	          	    	console.log(e);
	                } 
	              }); 
	            
	            //Apresenta modal preenchido com os dados
	            $('#ModalDetalhe').modal('show');

	            // change the border color just for fun
	            $(this).css('border-color', '#2d38d0');

	        },  
	        
	        editable: true,
	        droppable: false,
	        defaultView: 'month',
	        
	        events: [
			<c:forEach items="${acoes}" var="acao">
				 {
	                title: '${acao.instituicao.nome}',
	                start: new Date(${acao.getAno()}, ${acao.getMes()}, ${acao.getDia()}),
	                allDay: ${acao.id}
	           	 },
			</c:forEach>
	        ]
	    });
	}();
	
 </script>
 
 
  <script>
      //custom select box
      
      $('#datePicker').datetimepicker($.timepicker.regional['pt-BR']);
      
      $("#instituicoes").select2();

  </script>

  </body>
</html>
