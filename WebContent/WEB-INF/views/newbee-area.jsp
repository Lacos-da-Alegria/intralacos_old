<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="LaÁos da Alegria">
    
    <title>LaÁos da Alegria</title>

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
      <header class="header black-bg">
              <div class="sidebar-toggle-box">
                  <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Menu"></div>
              </div>
            <!--logo start-->
            <a href="area-novato" class="logo"><b>·rea do Novato</b></a>
            <!--logo end-->
           
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
              
              	  <p class="centered"><a href="profile"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered">${usuario.nome}</h5>
              	  	
                  <li class="mt">
                      <a class="active" href="area-novato">
                          <i class="fa li_heart"></i>
                          <span>¡Årea do Novato</span>
                      </a>
                  </li>
				  
				  <li class="sub-menu">
                      <a href="profile">
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
				<div class="col-lg-9 main-chart">
				
		         	  <h1 class="centered">Seja Bem Vindo ao LaÁos da Alegria!</h1><br>
					  
                  	<div class="row">
                  		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc">
			                <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="more" href="https://www.youtube.com/watch?v=Nj-IbZ3Yz3E&feature=player_embedded#at=41"><img class="img-responsive" src="assets/img/portfolio/import-info.jpg" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
						</div><!-- col-lg-4 -->
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc">
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
					
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 desc">
						<div class="project-wrapper">
		                    <div class="project">
		                        <div class="photo-wrapper">
		                            <div class="photo">
		                            	<a class="more" href="https://www.youtube.com/watch?v=SW1I5sfWnsg&feature=player_embedded#at=41"><img class="img-responsive" src="assets/img/portfolio/como-funfa.jpg" alt=""></a>
		                            </div>
		                            <div class="overlay"></div>
		                        </div>
		                    </div>
		                </div>
					</div><!-- col-lg-4 -->
                      
					</div><!-- /row -->	
					
					 <div class="row">
					 
					 <br><h1 class= "centered">Mais LaÁos!</h1><br>
					 
                  		<div class="col-md-2 col-sm-2 col-md-offset-1 box0">
                  			<div class="box1">
					  			<span class="li_heart"></span>
					  			<h3>${quantVolutarios}</h3>
                  			</div>
					  			<p>Hoje o LaÁos da Alegria conta com ${quantVolutarios} lindos volunt·rios!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_star"></span>
					  			<h3>0</h3>
                  			</div>
					  			<p>Como È bom comeÁar, ansioso para sua primeira atividade?</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_fire"></span>
					  			<h3>0</h3>
                  			</div>
					  			<p>Muitas visita seguidas vir„o tenho certeza!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_diamond"></span>
					  			<h3>5</h3>
                  			</div>
					  			<p>Ainda faltam 5 Atividades para vocÍ conhecer, que Ûtimo!</p>
                  		</div>
                  		<div class="col-md-2 col-sm-2 box0">
                  			<div class="box1">
					  			<span class="li_megaphone"></span>
					  			<h3>0</h3>
                  			</div>
					  			<p>Quando vocÍ virar doutor vai poder indicar seus amigos.</p>
                  		</div>
                  	
                  	</div><!-- /row mt -->	
					
                 </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
                  
      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 ds">
                    <!--Inicio Side Bar-->
					<h3>PosiÁ„o na Fila de Espera</h3>
                                        
              
                      <div class="desc box2 centered">
                      
						<button type="button" class="btn btn-round btn-primary"><span class="li_heart"></span>&nbsp&nbspVocÍ È o <b><font size="3">37∫</font></b> na Fila</button>
                
                      </div>
             
                       <!-- USERS ONLINE SECTION -->
						<h3>PreferÍncia de Atividade</h3>
						
                      <!-- First Member -->
                      <form action="preferencia">
                      <div class="desc">
                      	<select class="form-control" name = "preferencia">
						  <option>Hospital Regional do Gama</option>
						  <option>Hospital Regional de Taguatinga</option>
						  <option>Hospital das ForÁas Armadas</option>
						  <option>Hospital Universit·rio de BrasÌlia</option>
						  <option>Atividades Extra-Hospitalares</option>
						</select><br>						
						  
						<div class="centered">
                      	
                      	<div class="btn-group ">
						<button type="submit" class="btn btn-theme" name = "Alterar"><i class="fa fa-check"></i> Alterar</button>
						</div>
						<div class="btn-group ">
						<button type="button" class="btn btn-theme04" Resetar = "Resetar"><i class="fa fa-adjust"></i> Resetar</button>
						</div>
                  		
                      </div>
					</div>
					</form>
                                   
                 					
					<h3>Conta Desativada</h3>
                                        
                      <!-- First Action -->
                      <div class="desc centered" >
                      
						<div class="btn-group" >
						  <button type="button" class="btn btn-theme04 dropdown-toggle" data-toggle="dropdown">
						    Reativar Minha Conta <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" role="menu">
						    <li><a href="area-novato">Agora</a></li>
						    <li><a href="#">Daqui a 01 Semana</a></li>
						    <li><a href="#">Daqui a 02 Semana</a></li>						    
						    <li><a href="#">Daqui a 01 M√™s</a></li>
							<li><a href="#">N√£o Sei Quando</a></li>
							
						  </ul>
						</div>						
                      </div>

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
				'allowfullscreen'	: 'true'
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
        }
    </script>
  

  </body>
</html>
