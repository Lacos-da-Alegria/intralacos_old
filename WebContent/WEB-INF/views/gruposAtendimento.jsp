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
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Menu"></div>
              </div>
              
						<!--logo start-->	
			            <a href="area-voluntario" class="logo hidden-phone"><b>ÁREA DO VOLUNTÁRIO</b></a>
			            <!--logo end-->            

				  
            <div class="nav notify-row" id="top_menu">
                <!--  notification start -->
                <ul class="nav top-menu">
                    <!-- settings start -->
                    <li class="dropdown tooltips" data-placement="left" data-original-title="Indique Um Amigo">
                        <a data-toggle="modal" class="dropdown-toggle" href="volunteer-area.html#myModal">
                            <i class="fa li_megaphone" ></i>                            
                        </a>
                        					
                    </li>
                    <!-- settings end -->
                    <!-- inbox dropdown start-->
                    <li class="dropdown tooltips" data-placement="left" data-original-title="Deixe Seu Feedback">
                        <a class="dropdown-toggle"  data-toggle="modal"  href="volunteer-area.html#ModalFeedBack">
                            <i class="fa fa-envelope-o"></i>                            
						</a>
                    </li>
                    <!-- inbox dropdown end -->
                </ul>
                <!--  notification end -->
            </div>
            <div class="top-menu">
            	<ul class="nav pull-right top-menu">
                    <li><a class="logout" href="logout">Logout</a></li>
            	</ul>
            </div>
        </header>
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
          
          	<h3><i class="fa fa-fort-awesome" aria-hidden="true"></i> <span class="hidden-phone">Grupos de Atendimento</span>
				  <button data-toggle="modal" href="#modalCategoria" type="button" class="btn btn-primary pull-right" style="margin-right:10px;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> <span class="hidden-phone">  Nova Categoria</span></button>
	    	  	  <button data-toggle="modal" href="#modalGrupo" type="button" class="btn btn-success pull-right" style="margin-right:10px;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> <span class="hidden-phone">  Novo Grupo</span></button>
	    	  	  <button data-toggle="modal" href="#AddRecursoHumano" type="button" class="btn btn-default pull-right" style="margin-right:10px;"><i class="fa fa-check fa-lg" aria-hidden="true"></i> <span class="hidden-phone">  Novo Atendente</span></button>
          	</h3>
          	
          	
              <c:forEach items="${grupos}" var="grupo" >
              <div class="row mt">
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">

	                  	  	  <div class="dropdown"><h4 style="margin-left:10px;"> ${grupo.nome}
	                  	  	  </h4>
								  <div class="dropdown-content" style="width:230px;">
								  	<c:forEach items="${grupo.categorias}" var="cat">
								  	   <a><span class="label label-default">${cat.nome}</span> <button data-toggle="modal" href="#ModalLiberar" onclick="retirarRegiao(${cat.id});" class="btn btn-danger btn-xs" style="margin-left:10px;"><i class="fa fa-times "></i></button></a>
								    </c:forEach>
								  </div>
								</div>
								<button data-toggle="modal" href="#addRegiao" onclick="addRegiao(${grupo.id});" type="button" class="btn btn-primary pull-right" style="margin-right:10px;"><i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> <span class="hidden-phone">  Adcionar Categoria</span></button>
	                  	  	  <hr>
                              <thead>
                              <tr>
                                  <th><i class="fa fa-user"></i> Nome</th>
                                  <th><i class="fa fa-whatsapp"></i> WhatsApp</th>
                                  <th class="hidden-phone"><i class=" fa fa-envelope-o"></i> E-mail</th>
                                  <th class="hidden-phone"><i class="fa fa-globe"></i> Região</th>
                                  <th><i class="fa fa-times-circle-o"></i> Remover</th>
                                  <th></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${grupo.atendentes}" var="atend" >
                              <tr>
                                  <td>${atend.nome}</td>
                                  <td>${atend.whatsapp}</td>
                                  <td class="hidden-phone">${atend.email}</td>
                                  <td class="hidden-phone">${atend.regiao.nome}</td>
                                  <td>
                                      <button data-toggle="modal" href="#ModalRetirar" onclick="modalRetirar(${atend.id});" class="btn btn-danger btn-xs" style="margin-left:10px;"><i class="fa fa-times "></i></button>
                                  </td>
                              </tr>   
                              </c:forEach>                          
                              </tbody>
                          </table>
                    <label class="col-md-2 control-label"></label>
		         	 <div class="modal-footer">
		              </div>
                      </div><!-- /content-panel -->
                  </div><!-- /col-md-12 -->
              </div><!-- /row -->		
			</c:forEach>
					
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
		                      <form action="retirarCoordenador" method="post">
		                      <div class="modal-footer">
		                       <input hidden type="number" name="userId" id="retirarCord" value = "coordId" >
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-danger" type="submit">Confirmar Retirada</button>
		                      </div>
		                      </form>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		           <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="ModalLiberar" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-d">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered">Retirar Categoria</h4>
		                      </div>
		                      <div class="modal-body centered">
		                          <p>Você Realmente quer <b>Retirar</b> esse Categoria?</p>		                       	
		                      </div>
		                      <form action="retirarCategoria" method="post">
		                      <div class="modal-footer">
		                       <input hidden type="number" name="idCat" id="retirarRegiao" value = "coordId" >
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
		              <form class="form-horizontal style-form" action="vincularAtendente"  method="post">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Adicionar Atendente</h4>
		                      </div>
		                      <div class="modal-body">
		                      <div class="form-group" >
		                      	<p style="margin-left:15px;">Entra Abaixo com as infomações e clique em adiconar.</p>
		                      </div>
						          <div class="form-group">  
						            <label class="col-sm-2 col-sm-2 control-label"><b>E-mail</b></label>
						              <div class="col-sm-10">
						              <input class="form-control" name = "email" placeholder="Email Cadastrado no IntraLaços" type="text" required>
						              </div>
						          </div>
						          <div class="form-group">  
						            <label class="col-sm-2 col-sm-2 control-label"><b>Grupo de Atendimento</b></label>
						              <div class="col-sm-10">
						              	<select class="form-control" name = "idGrupo" required>
				                      	  <option></option>	
				                      	  <c:forEach items="${grupos}" var="grupo" >				
										  <option value="${grupo.id}" >${grupo.nome}</option>
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
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalCategoria" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered"><i class="fa fa-book" aria-hidden="true"></i> Cadastrar Categoria</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="cadastrarCategoria" method = "post" >
		                      
		                 <div class="modal-body">
		                 
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Categoria </label> 
                              <div class="col-sm-10">
                                 <input class="form-control" type="text" name="nome" required>
                              </div>
                          </div> 
						  <div class="form-group">  
				            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-question-circle-o" aria-hidden="true"></i> Região Administrativa</b></label>
				              <div class="col-sm-10">
				              <select class="form-control select2"  name = "atendimento"  required style="width:100%;">
										<c:forEach items="${grupos}" var="grupo">
											<option value="${grupo.id}" >${grupo.nome}</option>
										</c:forEach>
				                </select> 
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
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="modalGrupo" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered"><i class="fa fa-book" aria-hidden="true"></i> Cadastrar Grupo de Atendimento</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="cadastrarGrupo" method = "post" >
		                      
		                 <div class="modal-body">
		                 
                           <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">Nome do Grupo </label> 
                              <div class="col-sm-10">
                                 <input class="form-control" type="text" name="nome" required>
                              </div>
                          </div> 
						</div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-success" type="submit">Cadastrar</button>
		                      </div>
		                      
		                      </form>
		                        	
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		          
		          
		          <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="addRegiao" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header-b">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title centered"><i class="fa fa-book" aria-hidden="true"></i> Adcionar Categoria</h4>
		                      </div>
		                      
		              <form class="form-horizontal style-form" action="vincularCategoria" method = "post" >
		                  
		                  <input type="hidden" name="idGrupo" id="poloId" value = "poloId" >     
		                  
		                 <div class="modal-body">
						  <div class="form-group">  
				            <label class="col-sm-2 col-sm-2 control-label"><b><i class="fa fa-question-circle-o" aria-hidden="true"></i> Região Administrativa</b></label>
				              <div class="col-sm-10">
				              <select class="form-control select2" name = "cat"  required style="width:100%;">
										<c:forEach items="${categorias}" var="cat">
											<option value="${cat.id}" >${cat.nome}</option>
										</c:forEach>
				                </select> 
				              </div>
				          </div> 
						</div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
		                          <button class="btn btn-primary" type="submit">Adcionar</button>
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

    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>   
    
    <script type="text/javascript">
    
    function modalRetirar(coordId) {
        // altera as informações do modal cancelar             
      	  document.getElementById("retirarCord").value = coordId;
        
      };
      
    function retirarRegiao(coordId) {
          // altera as informações do modal cancelar             
        	  document.getElementById("retirarRegiao").value = coordId;
          
        };
        
  	function addRegiao(poloId) {
            // altera as informações do modal cancelar             
          	  document.getElementById("poloId").value = poloId;
            
          };
        
    </script> 
    
    <script type="text/javascript">

	    $(".select2").select2();
	    
	</script>
	

  </body>
</html>