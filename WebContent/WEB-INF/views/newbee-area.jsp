<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
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
	<link href="assets/js/fancybox/jquery.fancybox.css" rel="stylesheet" />	
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <script src="assets/js/chart-master/Chart.js"></script>
	<script src="assets/js/jquery.js"></script>
    
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
              <div class="row">
				<div class="col-lg-9 main-chart">
				
		         	  <h1 class="centered">Seja Bem Vindo ao Laços da Alegria!</h1><br>
					  
                  	<div class="row">
                  		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb">
			                <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="more" href="https://www.youtube.com/watch?v=SW1I5sfWnsg?autoplay=1"><img class="img-responsive" src="assets/img/portfolio/import-info.jpg" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
						</div><!-- col-lg-4 -->
						
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a href="https://drive.google.com/file/d/0BwsuXGjVO_-veGl0blhfR1RVWDQ/view" target="_blank"><img class="img-responsive" src="assets/img/portfolio/diret-lacos.jpg" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 mb">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="more" href="https://www.youtube.com/watch?v=fxbDkhc4IeI?autoplay=1"><img class="img-responsive" src="assets/img/portfolio/como-funfa.jpg" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
                      
					</div><!-- /row -->	
					
					 <div class="row">
					 
					 <br><h1 class= "centered">Mais Laços!</h1><br>
					 
                  		<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
                  			<div class="box1">
					  			<span class="li_heart"></span>
					  			<h3>${maislacos.total_voluntarios}</h3>
                  			</div>
					  			<p>Hoje o Laços da Alegria conta com ${maislacos.total_voluntarios} lindos voluntários!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_star"></span>
					  			<h3>0</h3>
                  			</div>
					  			<p>Como é bom começar, ansioso para sua primeira atividade?</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_fire"></span>
					  			<h3>0</h3>
                  			</div>
					  			<p>Muitas visita seguidas virão tenho certeza!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_diamond"></span>
					  			<h3>${maislacos.atividades_distintas}</h3>
                  			</div>
					  			<p>Ainda faltam ${maislacos.atividades_distintas} Atividades para você conhecer, que ótimo!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_megaphone"></span>
					  			<h3>0</h3>
                  			</div>
					  			<p>Quando você virar doutor vai poder indicar seus amigos.</p>
                  		</div>
                  	
                  	</div><!-- /row mt -->	
                  	
                  	
                  <!-- Modal -->
					<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModaAtivar" class="modal fade">
		              <div class="modal-dialog">		           
		                  <div class="modal-content">
		                  <form action="promoverNovato"  method="post">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Você não é mais Novato?</h4>
		                      </div>
		                      <div class="modal-body">
		                      
		                          <p>É so colocar o código de promoção abaixo e enviar que será promovido. <br>
		                          Você pode obter esse código com o coordenador da sua atividade</p>
		                          
		                          <small><font color="red">*Lembrando da importância de realmente ter ido na primeira atividade, contamos com a sua honestidade</font></small>
		                          <input type="text" name="codigo" placeholder="Codigo da Semana" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">Fechar</button>
		                          <button class="btn btn-theme" type="submit">Enviar</button>
		                      </div>
		                   </form> 
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
					
                 </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">                  
                    <!--Inicio Side Bar  -->
                   <c:if test="${voluntario.status == 1}">
					<h3>Posição na Fila de Espera</h3>               
                      <div class="desc box2 centered">
						<button type="button" class="btn btn-round btn-primary"><span class="li_heart"></span>&nbsp&nbspVocê é o <b><font size="3">${posicao}º</font></b> na Fila</button>
                      </div>
           
                       <!-- USERS ONLINE SECTION -->
						<h3>Preferência de Atividade</h3>
						
                      <!-- First Member -->
                      <form action="atualizaPreferencia" method="post">
                      <div class="desc">
                      	<select name="preferencia" class="form-control" required>	
                      	  <option value="">Preferência de Atividade*</option>
						  <c:forEach items="${hospitais}" var="atividade">
									<option value="${atividade.id}">${atividade.nome}</option>	
								</c:forEach>
						</select><br>						
						  
						<div class="centered">
                      	
                      	<div class="btn-group ">
						<button type="submit" class="btn btn-theme" name = "Alterar"><i class="fa fa-check"></i> Alterar</button>
						</div>
						<div class="btn-group ">
						<button type="reset" class="btn btn-theme04" ><i class="fa fa-adjust"></i> Resetar</button>
						</div>
                  		
                      </div>
					</div>
					</form>       
					</c:if>                           
                 <c:if test="${voluntario.status == 2}">	
					<h3>Conta Desativada</h3>
                                                              
                      <div class="desc centered" >
                      
						<div class="btn-group" >
						  <button type="button" class="btn btn-theme04 dropdown-toggle" data-toggle="dropdown">
						    Reativar Minha Conta <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" role="menu">
						    <li><a href="ativaNovato">Agora</a></li>
						  </ul>
						</div>						
                      </div>                                          
				</c:if>
					<!-- USERS ONLINE SECTION -->
						<h3>Calendario de Atividades</h3><br>

                        <!-- CALENDAR-->
                        <div id="calendar" class="mb">
                            <div class="panel green-panel no-margin">
                                <div class="panel-body">
                                    <div id="date-popover" class="popover top" style="cursor: pointer; disadding: block; margin-left: 33%; margin-top: -50px; width: 175px;">
                                        <div class="arrow"></div>
                                        <h3 class="popover-title" style="disadding: none;"></h3>
                                        <div id="date-popover-content" class="popover-content"></div>
                                    </div>
                                    <div id="my-calendar"></div>
                                </div>
                            </div>
                        </div><!-- / calendar -->
                      
                  </div><!-- /col-lg-3 -->
              </div><!--/row -->
          </section>
      </section>

      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center">
              Lacos da Alegria - 2016
              <a href="#" class="go-top">
                  <i class="fa fa-angle-up"></i>
              </a>
          </div>
      </footer>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/jquery.sparkline.js"></script>
	<script src="assets/js/fancybox/jquery.fancybox.js"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>	
	
	<c:if test="${userDetail.primeiro_acesso}">
	
	<a class="more" id="hidden_link" href="https://www.youtube.com/watch?v=LkwAybdLPBQ?autoplay=1"></a>
	
    <script type="text/javascript">
    	$(document).ready(function() {
        	$("#hidden_link").fancybox().trigger('click');
    	});
    	
    	window.addEventListener("load",function() {
    		// Set a timeout...
    		setTimeout(function(){
    			// Hide the address bar!
    			window.scrollTo(0, 1);
    		}, 0);
    	});
    	
	</script>
  	</c:if>	

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
                legend: [
                    {type: "text", label: "Special event", badge: "00"},
                    {type: "block", label: "Regular event", }
                ]
            });
        });
		
		$("a.more").click(function() {
	$.fancybox({
			'padding'		: 0,
			'autoScale'		: false,
			'transitionIn'	: 'none',
			'transitionOut'	: 'none',
			'title'			: this.title,
			'width'			: 680,
			'height'		: 495,
			'href'			: this.href.replace(new RegExp("watch\\?v=", "i"), 'v/'),
			'type'			: 'swf',
			'swf'			: {
			'wmode'		: 'transparent',
			'allowfullscreen'	: true
			}
		});

	return false;
});
		
		 $(function() {
        //    fancybox
          jQuery(".fancybox").fancybox();
      });

        
        
        function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
        };
        
    	
    	$('select[name^="preferencia"] option[value="${voluntario.preferencia.id}"]').attr("selected","selected");
    	
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
