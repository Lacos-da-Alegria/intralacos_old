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
          
          	
          	<div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">
	                  	  	   <span class="label label-info" style="margin-left:10px;">Temos ${vagasNovatos} vagas</span>
	                  	  	  <h3 style="margin-left:10px;"> <b> Controle de Novatos do ${tag} </b> <button data-toggle="modal" href="#modalNovatosInscrito" type="button" class="btn btn-primary pull-right" style="margin-right:10px;">Novatos Inscritos</button></h3>	                  	      	                  	  	  
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th>N�</th>
                                  <th class="centered hidden-phone"><i class="fa fa-star-half-o" aria-hidden="true"></i></th>
                                  <th><i class="fa fa-user"></i> Nome</th>
                                  <th><i class="fa fa-whatsapp"></i> WhatsApp</th>
                                  <th><i class="fa fa-question-circle"></i> Idade</th>                                  
                                  <th class="hidden-phone"><i class=" fa fa-envelope-o"></i> E-mail</th>
                                  <th class="hidden-phone"><i class="fa fa-commenting-o"></i> Observa��es</th>
                                  <th><i class="fa fa-check-square-o"></i> A��es</th>
                                  <th></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${novatos}" var="novato" varStatus="loop" >
                              <tr>
                              <c:set var="myParam" value="${novato.responsavel.id}"/>
                              	  <td>${loop.index+1}</td>
                              	  <td class="centered hidden-phone"><span style="background-color:<c:out value="${cores[myParam]}"/>" class="label label-info">${novato.responsavel.getPrimerio_nome()}</span>                              	  </td>
                                  <td>${novato.nome}</td>
									  <c:choose>
									  	<c:when test="${empty novato.responsavel}">
									  		<td>* * * * * * * *</td>
		                                  	<td <c:if test="${novato.idade<16}">style="color:red;"</c:if>>* * </td>
		                                  	<td class="hidden-phone">* * * * * * * * * * * * * * * *</td>
		                                  	<td class="hidden-phone">${novato.observacao}</td>
									  		<td><a style="font-size:13px;" class="btn btn-info btn-xs" href="adicionarResponsavel?novatoId=${novato.id}" onclick="clickAndDisable(this);"><i style="margin:2px" class="fa fa-lock" aria-hidden="true"></i></a></td>
									  	</c:when>
									  	<c:when test="${voluntario.id != novato.responsavel.id}">
									 		<td>* * * * * * * *</td>
		                                  	<td <c:if test="${novato.idade<16}">style="color:red;"</c:if>>${novato.idade}</td>
		                                  	<td class="hidden-phone">* * * * * * * * * * * * * * * *</td>
		                                  	<td class="hidden-phone">${novato.observacao}</td>
									  	</c:when>
									  	<c:otherwise>
										  	<td>${novato.getTelefone()}</td>
		                                  	<td <c:if test="${novato.idade<16}">style="color:red;"</c:if>>${novato.idade}</td>
		                                  	<td class="hidden-phone">${novato.email}</td>
		                                  	<td class="hidden-phone">${novato.observacao}</td>
									  	
									  		<td><c:if test="${vagasNovatos>0}">
                                      			<a class="btn btn-success btn-xs" href="inscreverNovato?ativId=${atividId}&novatoId=${novato.id}" onclick="clickAndDisable(this);"><i class="fa fa-check"></i></a>
                                    		</c:if>  
                                      		<button data-toggle="modal" href="#ModalEdicao" onclick="modalEdicao(${novato.id});" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></button>
                                      		<button data-toggle="modal" href="#ModalDesativar" onclick="modalDesativar(${novato.id});" class="btn btn-danger btn-xs"><i class="fa fa-times "></i></button>
									  		<a style="font-size:13px;" class="btn btn-warning btn-xs" href="retirarResponsavel?novatoId=${novato.id}" onclick="clickAndDisable(this);"><i class="fa fa-unlock" aria-hidden="true"></i></a></td>	
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
		        <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalDesativar" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-d">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Desativa��o de Novato</h4>
		                      </div>
		                      <form action="desativaNovato" method="post">
		                      <div class="modal-body">
		                          <p>Qual � o motivo para a desativa��o desse novato?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="observacao" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="userId" id="desativaNovato" value = "novatoId" >
		                          
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Desativa��o</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          		        <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalEdicao" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Atualizar Observa��o</h4>
		                      </div>
		                      <form action="editaNovato" method="post">
		                      <div class="modal-body">
		                          <p>Qual � a observa��o que voc� quer deixar?</p>	
		                          
		                          <textarea rows="4" class="form-control placeholder-no-fix" name="observacao" required></textarea>	                       	
		                      </div>
		                      
		                      <div class="modal-footer">
		                       <input hidden type="number" name="userId" id="EditaNovato" value = "novatoId" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-primary" type="submit">Atualizar Observa��o</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalNovatosInscrito" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Lista de Novatos Inscritos</h4>
		                      </div>
		                      <div class="modal-body">
		                          <div style="height:300px;line-height:3em;overflow:auto;padding:5px;">
							<div class="custom-check goleft">
				             <table id="todo" class="table table-hover custom-check">
				              <tbody>
				              
				              <!-- Fazer for each para a lista de todos os apoios da atividade-->
				              <c:forEach items="${novatosInscritos}" var="reg">			
										 <tr>	
										<td>				                      
				                     	   <a>${reg.voluntario.nome}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${reg.voluntario.getTelefone()}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${reg.voluntario.regiao.nome}</a>
				      					</td>
				      					<td>				                      
				                     	   <a>${reg.voluntario.observacao}</a>
				      					</td>  
				      					<td>				                      
				                     	   <button data-toggle="modal" href="#ModalRetirar" onclick="modalRetirar(${reg.voluntario.id});" class="btn btn-danger btn-xs" style="margin-left:10px;"><i class="fa fa-times "></i></button>
				      					</td>				      					
				      					</tr>
				      				</c:forEach>			              							 
								 
				              </tbody>
				          </table>
						</div><!-- /table-responsive -->
								
						</div><!-- scrollbar -->
		
		                      </div>
		                      <div class="modal-footer">
		                      <h5 class = "goleft floater" >Total de ${fn:length(novatosInscritos)} Novatos Inscritos</h5>
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>		                          
		                      </div>
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
		                          <h4 class="modal-title centered">Cancelar Participa��o de Novato</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>Voc� Realmente quer <b>Retirar</b> esse Novato da Atividade dessa Semana?</p>		                       	
		                      </div>
		                      <form action="cancelarNovato" method="post">
		                      <div class="modal-footer">
		                       <input hidden type="number" name="novatoId" id="retirarNovato" value = "novatoId" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Retirada</button>
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
    
    function modalDesativar(novatoId) {
        // altera as informa��es do modal cancelar             
      	  document.getElementById("desativaNovato").value = novatoId;
        
      };
      
      function modalEdicao(novatoId) {
          // altera as informa��es do modal cancelar             
        	  document.getElementById("EditaNovato").value = novatoId;
          
        };
        
        function clickAndDisable(link) {
            // disable subsequent clicks
            link.onclick = function(event) {
               event.preventDefault();
            }
          };
          
          function modalRetirar(novatoId) {
              // altera as informa��es do modal cancelar  
              $('#modalNovatosInscrito').modal('toggle');
            	  document.getElementById("retirarNovato").value = novatoId;
              
            };
    
    </script> 

  </body>
</html>