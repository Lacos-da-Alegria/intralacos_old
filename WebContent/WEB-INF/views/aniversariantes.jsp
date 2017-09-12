<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
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
          <section class="wrapper site-min-height">
          	<h2 class= "centered">Aniversariantes do Mês</h2>
          	<div class="row mt">
          		<div class="col-lg-12">
          			<c:forEach items="${aniversariantes}" var="voluntario">
						<div class="col-lg-2 col-md-2 col-sm-2 mb">
							<!-- WHITE PANEL - TOP USER -->
							<div class="white-panel pn" style="height: 185px;">
								<div class="white-header" style="background-color: #f6f3f3;">
									<h5 style="color:#585858;"><b>${voluntario.nome}</b></h5>
								</div>
								<p><img src="${voluntario.urlS3()}" class="img-circle" height="80" width="80" style="margin-left:-15%;"></p>
								<p style="color:#585858; margin-left:-5%;"><b>${voluntario.dt_Nascimento}</b></p>							
							</div>
						</div><!-- /col-md-4 -->
					</c:forEach>	
				</div><!--/END 1ST ROW OF PANELS -->
			</div>
					
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
	
	
	<script language="JavaScript1.2">
	<!-- Begin -->
	/*
	Float Up Drifter - JavaScript
	Visit www.rainbow.arch.scriptmania.com/scripts/index.html
	  for this script and many more
	*/
		// Configure below - change number of images to render
	var no = 8; 
		// Configure speed below
	var speed = 20;   // The smaller the number, the faster the movement

	var floatr = new Array();
	   //  Your image location
	floatr[0] = "assets/img/ballons/balloon0.gif"
	floatr[1] = "assets/img/ballons/balloon1.gif"
	floatr[2] = "assets/img/ballons/balloon2.gif"
	floatr[3] = "assets/img/ballons/balloon3.gif"
	floatr[4] = "assets/img/ballons/balloon4.gif"
	floatr[5] = "assets/img/ballons/balloon5.gif"
	floatr[6] = "assets/img/ballons/balloon6.gif"
	floatr[7] = "assets/img/ballons/balloon7.gif"


	var ns4up = (document.layers) ? 1 : 0;  // browser sniffer
	var ie4up = (document.all) ? 1 : 0;
	var ns6up = (document.getElementById&&!document.all) ? 1 : 0;
	var dx, xp, yp;    // coordinate and position variables
	var am, stx, sty;  // amplitude and step variables
	var i, doc_width = 800, doc_height = 1800;

	if (ns4up||ns6up) {
			doc_width = self.innerWidth;
			doc_height = self.innerHeight;
	} else if (ie4up) {
			doc_width = document.body.clientWidth;
			doc_height = document.body.clientHeight;
	}

	dx = new Array();
	xp = new Array();
	yp = new Array();
	am = new Array();
	stx = new Array();
	sty = new Array();
	j = 0;

	for (i = 0; i < no; ++ i) {
			dx[i] = 0;                        // set coordinate variables
			xp[i] = Math.random()*(doc_width-50);  // set position variables
			yp[i] = Math.random()*doc_height;
			am[i] = Math.random()*20;         // set amplitude variables
			stx[i] = 0.02 + Math.random()/10; // set step variables
			sty[i] = 0.7 + Math.random();     // set step variables
			if (ns4up) {                      // set layers
					if (i == 0) {
							document.write("<layer name=\"dot"+ i +"\" left=\"15\" top=\"15\" visibility=\"show\"><img src=\""+ floatr[j] + "\" border=\"0\"></layer>");
					} else {
							document.write("<layer name=\"dot"+ i +"\" left=\"15\" top=\"15\" visibility=\"show\"><img src=\""+ floatr[j] + "\" border=\"0\"></layer>");
					}        } else if (ie4up||ns6up) {                if (i == 0) 
	{
							document.write("<div id=\"dot"+ i +"\" style=\"POSITION: absolute; Z-INDEX: "+ i +"VISIBILITY: visible; TOP: 15px; LEFT: 15px; width:1;\"><img src=\"" + floatr[j] + "\" border=\"0\"></div>");
					} else {
							document.write("<div id=\"dot"+ i +"\" style=\"POSITION: absolute; Z-INDEX: "+ i +"VISIBILITY: visible; TOP: 15px; LEFT: 15px; width:1;\"><img src=\"" + floatr[j] + "\" border=\"0\"></div>");
					}
			}
			if (j == (floatr.length-1)) { j = 0; } else { j += 1; }
	}

	function floatrNS() {  // Netscape main animation function
			for (i = 0; i < no; ++ i) {  // iterate for every dot
					yp[i] -= sty[i];                if (yp[i] < -50) {
							xp[i] = Math.random()*(doc_width-am[i]-30);
							yp[i] = doc_height;
							stx[i] = 0.02 + Math.random()/10;
							sty[i] = 0.7 + Math.random();
							doc_width = self.innerWidth;
							doc_height = self.innerHeight;                }
					dx[i] += stx[i];
					document.layers["dot"+i].top = yp[i]+pageYOffset;
					document.layers["dot"+i].left = xp[i] + 
	am[i]*Math.sin(dx[i]);
			}
			setTimeout("floatrNS()", speed);
	}

	function floatrIE_NS6() {  // IE main animation function
			for (i = 0; i < no; ++ i) {  // iterate for every dot
					yp[i] -= sty[i];
					if (yp[i] < -50) {
							xp[i] = Math.random()*(doc_width-am[i]-30);
							yp[i] = doc_height;
							stx[i] = 0.02 + Math.random()/10;
							sty[i] = 0.7 + Math.random();
							doc_width = ns6up?window.innerWidth-5:document.body.clientWidth;
							doc_height = ns6up?window.innerHeight-5:document.body.clientHeight;
					}
					dx[i] += stx[i];
					if (ie4up){
					document.all["dot"+i].style.pixelTop = yp[i]+document.body.scrollTop;
					document.all["dot"+i].style.pixelLeft = xp[i] + am[i]*Math.sin(dx[i]);
					}
					else if (ns6up){
					document.getElementById("dot"+i).style.top=yp[i]+pageYOffset;
					document.getElementById("dot"+i).style.left=xp[i] + am[i]*Math.sin(dx[i]);
					}
			}
			setTimeout("floatrIE_NS6()", speed);
	}

	if (ns4up) {
			floatrNS();
	} else if (ie4up||ns6up) {
			floatrIE_NS6();
	}
	<!-- End -->
	</script>


  </body>
</html>
