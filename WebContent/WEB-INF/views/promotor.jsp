<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="LaÁos da Alegria">

    <title>IntraLaÁos</title>

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
            <a href="area-voluntario" class="logo"><b>¡ÅREA DO VOLUNT¡RIO</b></a>
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
                        <a class="dropdown-toggle"  data-toggle="modal"  href="volunteer-area.html#myModal2">
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
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="construcao"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered">${voluntario}</h5>
              	  	
                  <li class="mt">
                      <a href="area-voluntario">
                          <i class="fa li_heart"></i>
                          <span>¡Årea do Volunt·rio</span>
                      </a>
                  </li>
				  
				  <li class="sub-menu">
                      <a href="construcao">
                          <i class="fa li_user"></i>
                          <span>Minha Conta</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                      <a href="construcao" >
                          <i class="fa li_tag"></i>
                          <span>InformaÁıes Importantes</span>
                      </a>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-desktop"></i>
                          <span>Painel da Atividade</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="detalhe-hub">HUB</a></li>
                          <li><a  href="detalhe-hrg">HRG</a></li>                     
						  <li><a  href="detalhe-hfa">HFA</a></li>
                      </ul>
                  </li>
                  <li class="sub-menu">
                      <a href="construcao" >
                          <i class="fa fa-cogs"></i>
                          <span>Admin Page</span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_news"></i>
                          <span>Sobre</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="construcao">LaÁos da Alegria</a></li>
                          <li><a  href="construcao">Trabalho Volunt·rio</a></li>
                          <li><a  href="construcao">Como Posso Ajudar</a></li>
                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_phone"></i>
                          <span>Contato</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="construcao">LaÁos Da Alegria</a></li>
                          <li><a  href="construcao">Parceiros</a></li>
					</ul>
                  </li>
                 

              </ul>
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

                                  
                    				
					
					
					<div class="row">
						
						<h1 class= "centered">P·gina para PromoÁ„o de Volunt·rios</h1><br>
						
						
						<form class = "centered" action="promoverNovato" method="post">

						 <c:forEach items="${novatos}" var="novato">
                      
						  <label><input type="checkbox" name="userid" value="${novato.userid}"><b>Nome:</b>${novato.nome}  <b>Telefone:</b> ${novato.whatsapp}</label><br>
                      
                     	 </c:forEach> 

						  <input type="submit" value="Submit">
						</form>
				
                  		
                  	
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal2" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Deixe Aqui seu Feedback</h4>
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal3" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">InformaÁıes da Atividade</h4>
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
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal4" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">ConfirmaÁ„o de ParticipaÁ„o</h4>
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
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>	
	
		
	<script type="application/javascript">
        $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
                $(this).hide();
            });
        
            $("#my-calendar").zabuto_calendar({
                action: function () {
                    return myDateFunction(this.id, false);
                },
                action_nav: function () {
                    return myNavFunction(this.id);
                },
                ajax: {
                    url: "show_data.php?action=1",
                    modal: true
                },
               
            });
        });
        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        }
    </script>
  

  </body>
</html>
