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
		    <h1>Editar Perfil</h1>
		  	<hr>
			<div class="row">
			
			<form class="form-horizontal style-form" action="atualizaProfilePic" method = "post" enctype="multipart/form-data">
			      <!-- left column -->
			      <div class="col-md-3 mb">
			        <div class="text-center">
			          <img src="${voluntario.urlS3()}" class="avatar img-circle" alt="avatar" name="pic" height="152" width="152">
			          <h6>Altere sua Foto do Perfil</h6> 
			          
			          <input id="image-file" class="form-control" type="file" name="foto" required multiple>
			        </div><br>
				<input class="btn btn-primary" value="Atualizar Foto" type="submit" id="submitFoto" style="margin-left:30%;">
			      </div>
			  </form> 
			  
		      <form class="form-horizontal style-form" action="atualizaInfo" method = "post">
		      <!-- edit form column -->
		      <div class="col-md-8 personal-info ">
					<p class="ml centered"><font color="red">${error}</font></p>
		         	<div class="form-group">
		           	<label class="col-sm-2 col-sm-2 control-label"><b>Nome Completo</b></label>
		           		<div class="col-sm-10">
                           <input type="text" class="form-control" name="nome"  value="${voluntario.nome}" required>
                        </div>		         
		           	</div>
		           	<c:if test="${!voluntario.novato}">
		           	<div class="form-group">
		           	<label class="col-sm-2 col-sm-2 control-label"><b>Nome de Doutor</b></label>
		           		<div class="col-sm-10">
                           <input type="text" class="form-control" name="nome_doutor"  value="${voluntario.nome_doutor}">
                        </div>		         
		           	</div>
		         	</c:if>
		          <div class="form-group">
		            <label class="col-sm-2 col-sm-2 control-label"><b>Data de Nascimento</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" value="${voluntario.dt_Nascimento}" type="text" name="dt_Nascimento" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask required>
		              </div>
		            </div>
		          
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>DDD</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" name="ddd"  value="${voluntario.ddd}" type="text" required>
		              </div>
		          </div>
		          
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Whatsapp</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" name="whatsapp"  value="${voluntario.whatsapp}" type="text" required>
		              </div>
		          </div>
		           <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>CPF</b></label>
		              <div class="col-sm-10">
		              <input class="form-control" name = "cpf" value="${voluntario.cpf}" type="text" required>
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Email</b></label>
		              <div class="col-sm-10">
		              <input name = "email" class="form-control" value="${voluntario.email}" type="text" readonly>
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Endereço</b></label>
		              <div class="col-sm-10">
		              <input name = "endereco" class="form-control" value="${voluntario.endereco}" type="text" placeholder = "Endereço Vazio">
		              </div>
		          </div>  
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Região</b></label>
		              <div class="col-sm-10"s>
		              <select class="form-control" name = "regiaoId"  required>
								<option value="" >Cidade Satélite*</option>							
								<c:forEach items="${regioes}" var="regiao">
									<option value="${regiao.id}">${regiao.nome}</option>	
								</c:forEach>
		                </select> 
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Login</b></label>
		              <div class="col-sm-10">
		               <input class="form-control" value="${voluntario.login}" type="text" readonly >
		              </div>
		          </div>
		          <div class="form-group">
		            <label class="col-md-2 control-label"></label>
		         	 <div class="modal-footer">
		              <input class="btn btn-primary" value="Atualizar Informações" type="submit">
		              <span></span>
		              <input class="btn btn-default" value="Cancelar" type="reset">
		              </div>
		            
		          </div>
		          </div>
		        </form>
		        
		       <div class="form-group" >  
		         <div class="col-sm-7">
		          <input id="telegramId" class="form-control" value="${voluntario.telegram_id}" type="text" readonly >
		         </div>		      
		      </div>
		      <button class="btn btn-primary" style="margin-left: 13%;" onclick="copyToClipboard()"><i class="fa fa-telegram fa-lg" aria-hidden="true"></i>  Copiar Validador Telegram</button>
		      
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
		// When ready...
		window.addEventListener("load",function() {
			// Set a timeout...
			setTimeout(function(){
				// Hide the address bar!
				window.scrollTo(0, 1);
			}, 0);
		});
	</script>    
	
	<script type="application/javascript">
	
	 $('#image-file').bind('change', function() {
		 
         if((this.files[0].size/1024)>500){
        	 $('#submitFoto').attr('disabled','disabled');
        	 alert("O tamanho da foto deve ser inferior a 500Kbytes para ser utilizada, favor reduzir o tamanho da foto antes de envia-la");
         } else {
        	 $('#submitFoto').removeAttr('disabled');
         }

	 });	
	
	$('select[name^="regiao"] option[value="${voluntario.regiao.id}"]').attr("selected","selected");
	
	  $(function () {
	    //Datemask dd/mm/yyyy
	    $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
	  //Datemask dddd/yyyy
	    $("#datemask3").inputmask("dddd/yyyy", {"placeholder": "dddd/yyyy"});
	    //Datemask2 mm/dd/yyyy
	    $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});    
		//Money Euro
	    $("[data-mask]").inputmask();

	  });
	  
	  function copyToClipboard(elementId) {


		  var aux = document.createElement("input");
		  aux.setAttribute("value", $('#telegramId').val());
		  document.body.appendChild(aux);
		  aux.select();
		  document.execCommand("copy");

		  document.body.removeChild(aux);
		  alert("Validador Copiado!");

		}
    </script>

  </body>
</html>