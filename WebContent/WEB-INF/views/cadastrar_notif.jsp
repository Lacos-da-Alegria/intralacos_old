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
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Menu"></div>
              </div>
              
						<!--logo start-->	
			            <a href="area-voluntario" class="logo hidden-phone"><b>��REA DO VOLUNT�RIO</b></a>
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
	
			
		<div class="form-panel">
		    <h1>Criar Notifica��o</h1>
		  	<hr>
			<div class="row">
			
			<form class="form-horizontal style-form" action="insertNotif" method = "post">	
			      <!-- left column -->
			      <div class="col-md-3">
			        <div class="text-center">
			          <img src="assets/img/notification.jpg" class="avatar img-circle" alt="avatar" name="pic" accept="asset/img/*">
			          <h6>Seja claro e direto!</h6> 			          
			        </div>
			      </div>
		      
		      <!-- edit form column -->
		      <div class="col-md-8 personal-info ">
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Cor do cone</b></label>
		              <div class="col-sm-10">
		              <select class="form-control" name = "tipo"  required>
							  <option value="1">Cinza</option>
							  <option value="2">Roxo</option>
							  <option value="3">Verde</option>
							  <option value="4">Vermelho</option>
		                </select> 
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Link</b></label>
		              <div class="col-sm-10">
		               <input name="link" class="form-control" type="text" required >
		              </div>
		          </div>
		              
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Tag</b></label>
		              <div class="col-sm-10">
		              <input name = "tag" class="form-control" type="text" required>
		              </div>
		          </div>
		          <div class="form-group">  
		            <label class="col-sm-2 col-sm-2 control-label"><b>Notifica��o</b></label>
		              <div class="col-sm-10">
		               <textarea rows="8" class="form-control placeholder-no-fix" name="mensagem" required></textarea>
		              </div>
		          </div>
		          
		          	<div class="form-group">
		            	<label class="col-md-2 control-label"></label>
		         	 	<div class="modal-footer">
		              	<input class="btn btn-primary" value="Criar Notificao" type="submit">
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