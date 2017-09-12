<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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

  <body onload="pageInit()">

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
		    
		    <c:choose>
		    <c:when test="${instituicao.id == null}">
		    	<h3><a href="javascript:history.back()"><i class="fa fa-arrow-left" aria-hidden="true"></i></a> &nbsp&nbsp <i class="fa fa-university" aria-hidden="true"></i> Cadastrar Instituição </h3>
		  	<hr>
			<div class="row">
		      <form class="form-horizontal style-form" action="cadastrarInstituicao" method = "post">
		    </c:when>
		    <c:otherwise>
		    	<h3><a href="javascript:history.back()"><i class="fa fa-arrow-left" aria-hidden="true"></i></a> &nbsp&nbsp <i class="fa fa-university" aria-hidden="true"></i> Atualizar Instituição </h3>
		  	<hr>
			<div class="row">
			
		      <form class="form-horizontal style-form" action="atualizarInstituicao" method = "post">
		    </c:otherwise>
		    </c:choose>
		      <!-- edit form column -->
		      <div class="col-md-12 personal-info ">
					<p class="ml centered"><font color="red">${error}</font></p>
					
					<input type="hidden" class="form-control" name="id" value="${instituicao.id}" >
					
		         	<div class="form-group">
		           	<label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-building-o" aria-hidden="true"></i> Nome da Instituição</b></label>
		           		<div class="col-sm-10">
                           <input type="text" class="form-control" name="nome" value="${instituicao.nome}" required>
                        </div>		         
		           	</div>
		           	<div class="form-group">
		           	<label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-phone" aria-hidden="true"></i> Telefone</b></label>
		           		<div class="col-sm-10">
                           <input type="text" class="form-control" name="telefone"  value="${instituicao.telefone}" required>
                        </div>		         
		           	</div>
		          <div class="form-group">
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-envelope-o" aria-hidden="true"></i> E-mail</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" value="${instituicao.email}" type="text" name="email" >
		              </div>
		            </div>
		            
		            <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-question-circle-o" aria-hidden="true"></i> Região Administrativa</b></label>
		              <div class="col-sm-10">
		              <select class="form-control select2" name = "ra_id"  required>
								<c:forEach items="${ras}" var="ra">
									<option value="${ra.id}" >${ra.nome}</option>
								</c:forEach>
		                </select> 
		              </div>
		          </div>
		          
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-user-circle-o" aria-hidden="true"></i> Nome do Responsável</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" name="responsavel"  value="${instituicao.responsavel}" type="text" required>
		              </div>
		          </div>
		          
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-phone" aria-hidden="true"></i> Contato do Resposável</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" name="contato_resp"  value="${instituicao.contato_resp}" type="text" >
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-envelope-o" aria-hidden="true"></i> E-mail do Responsánvel</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" name = "email_resp" value="${instituicao.email_resp}" type="email" >
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-book" aria-hidden="true"></i> Descrição da Instituição</b></label>
		              <div class="col-sm-10">
		              	<textarea rows="6" class="form-control placeholder-no-fix" name="descricao_insti" required>${instituicao.descricao_insti}</textarea>
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-tags" aria-hidden="true"></i> Caracteristicas</b></label>
		              <div class="col-sm-10">
		              <select class="form-control select2 multiple1" multiple="multiple" name = "tag1" required style="width:100%;">
								<c:forEach items="${caract}" var="tag">
									<option value="${tag.id}" >${tag.nome}</option>
								</c:forEach>				
		                </select> 
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-question-circle-o" aria-hidden="true"></i> Já foi Realizada alguma atividade?</b></label>
		              <div class="col-sm-10">
		              <select class="form-control" id="ja_teve" name = "ja_fomos"  required>
								<option value="" >Escolha uma opção*</option>							
								<option value="1" >Sim</option>
								<option value="0" >Não</option>
		                </select> 
		              </div>
		          </div>
		          <div class="form-group ativ_relizada" hidden="true">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-bookmark" aria-hidden="true"></i> Descrição da Atividade Realizada</b></label>
		              <div class="col-sm-10">
		              	<textarea rows="6" class="form-control placeholder-no-fix" id="descricao_ativ" name="descricao_ativ">${instituicao.descricao_ativ}</textarea>
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-window-restore" aria-hidden="true"></i> Atividades Recomendadas</b></label>
		              <div class="col-sm-10">
		              <select class="form-control select2 multiple2" multiple="multiple" name = "tag1"  style="width:100%;">
							<c:forEach items="${ativs}" var="tag">
									<option value="${tag.id}" >${tag.nome}</option>
								</c:forEach>
					
		                </select> 
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-thermometer-full" aria-hidden="true"></i> Descrição de Pontos Críticos</b></label>
		              <div class="col-sm-10">
		              	<textarea rows="6" class="form-control placeholder-no-fix" name="descricao_p_criticos" >${instituicao.descricao_p_criticos}</textarea>
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-bomb" aria-hidden="true"></i> Pontos Críticos</b></label>
		              <div class="col-sm-10">
		              <select class="form-control select2 multiple3" multiple="multiple" name = "tag1"  style="width:100%;">
							<c:forEach items="${critcs}" var="tag">
									<option value="${tag.id}" >${tag.nome}</option>
								</c:forEach>
		                </select> 
		              </div>
		          </div>
		          
		          <div class="form-group">
		            <label class="col-md-2 control-label"></label>
		         	 <div class="modal-footer">
		         	 <c:choose>
		         	 <c:when test="${instituicao.id == null}">
		         		 <input class="btn btn-primary" value="Cadastrar Informações" type="submit">
		         	 </c:when>
		         	 <c:otherwise>
		         	 	<input class="btn btn-primary" value="Atualizar Informações" type="submit">
		         	 </c:otherwise>
		         	 </c:choose>
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
    
    	$('select[name^="ra_id"] option[value=${instituicao.ra.id}]').attr("selected","selected");
    	<c:forEach items="${instituicao.tags}" var="tag">
    		$('select[name^="tag1"] option[value=${tag.id}]').attr("selected","selected");
	 	</c:forEach>
    
    	$(".select2").select2();
    	
	</script>
    
	<script type="application/javascript">
	
	function pageInit() {
		if(${instituicao.jaFomosInt()==1}){
			$('select[name^="ja_fomos"] option[value=1]').attr("selected","selected");
			$('.ativ_relizada').show();
			$('#descricao_ativ').attr('required', true);
		} else {
			$('select[name^="ja_fomos"] option[value=0]').attr("selected","selected");
			$('.ativ_relizada').hide();
			$('#descricao_ativ').attr('required', false);
		}
	};
	
	$('#ja_teve').on('change', function() {
		if(this.value == 1){
			$('.ativ_relizada').show();
			$('#descricao_ativ').attr('required', true);
		} else {
			$('.ativ_relizada').hide();
			$('#descricao_ativ').attr('required', false);
		}
	});
	  
    </script>
    
  </body>
</html>