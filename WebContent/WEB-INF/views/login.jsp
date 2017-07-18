<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Intranet do Grupo da Alegria">
    <meta name="author" content="Laços da Alegria">

    <title>Laços da Alegria</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="assets/img/favicon.png">
  </head>

  <body>

      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->

	  <div id="login-page">
	  
	  	<div class="container" >
	  	
		      <form class="form-login" action = "efetuaLogin" method="post">
		        <h2 class="form-login-heading">Área do Voluntário</h2>
		        <div class="login-wrap">
		            <input name="login" type="text" class="form-control" placeholder="Login ou Email" autofocus required>
		            <br>
		            <input name="senha" type="password" class="form-control" placeholder="Senha" required>
		            <label class="checkbox">
		                <span class="pull-right">
		                    <a data-toggle="modal" href="login.html#myModal" class = "link-underline"> Esqueceu a Senha?</a>
		
		                </span>
		            </label>
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa fa-lock"></i> Iniciar Sessão</button>
					</form>			
				
					<form action = "cadastroVoluntario" method="post">
					
					<h2 class="lock-screen">Ainda não é Voluntário?</h2>
					
					<div>	
					<p>Basta preecher o cadastro e logar para obter mais informações.</p>
		         
									
		            <button class="btn btn-theme btn-block" type="submit"><i class="fa"></i> Fazer Cadastro</button>
		           	
					</div>
			    
					</form>
		            
		            
				</div>
			 
		
		          <!-- Modal -->
		         <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">Esqueceu a Senha?</h4>
		                      </div>
		                      <form action = "recuperaSenha" method="post">
		                      <div class="modal-body">
		                          <p>Entre com o e-mail abaixo para resetar sua senha.</p>
		                          <input type="text" name="email" placeholder="E-mail" autocomplete="off" class="form-control placeholder-no-fix">
		
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
		
		      </form>	  	
	  	
	  	</div>
		
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>
    <script>
        $.backstretch("assets/img/login-bg2.jpg", {speed: 500});
    </script>


  </body>
</html>
