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
	  	
		      <form class="form-login" action = "resetarSenha" method="post">
		        <h2 class="form-login-heading">Resetar Senha</h2>
		        <div class="login-wrap">
		        	<p><font color="red">${error}</font></p>
		        	<small class="ml"><font color="red"><b> *Senha deve conter no mínimo 6 dígitos.</b></font></small>
		            <input name="senha" id="pass1" type="password" class="form-control" placeholder="Senha*" autofocus required>
		            <br>
		            <input name="dual_senha" id="pass2" type="password" class="form-control" placeholder="Repita a Senha*" required>
		            <input name="token" type="hidden" value="${token}">
		            <br><font color="red" id="mensagemPassword"></font>
					
		            <button class="btn btn-theme btn-block" type="submit" style="margin-top:15px;"><i class="fa fa-lock"></i> Alterar Senha</button>
				</div>
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
        
        function checkPasswordMatch() {
    	    var password = $("#pass1").val();
    	    var confirmPassword = $("#pass2").val();

    	    if (password != confirmPassword){
    	        $("#mensagemPassword").html("As senhas não coincidem!").css('color', 'red');
    	        return false;
    	    } else{
    	    	$("#mensagemPassword").html("Senhas coincidem!").css('color', 'green');
    	    	if(password.length>=6)
    	    		return true;
    	    	else {
    	    		$("#mensagemPassword").html("Senha menor que 6 dígitos!").css('color', 'red');
    	    		return false;
    	    	}
    	    		
    	    }    
    	}

    	$(document).ready(function () {
    	   $("#pass1, #pass2").keyup(checkPasswordMatch);
    	});
    	
    	$('#cadastro').submit(function(e){ 
    		   var bool = checkPasswordMatch();
    		   if(!bool){
    		    e.preventDefault(); // will stop the form from submitting
    		    alert("Favor corrigir os erros antes de submeter o cadastro!");
    		   } else {
    		    return true; //continue to submit form 
    		    }
    		});	

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
	
	<script>
	function delete_cookie( name ) {
		  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		};
	</script>


  </body>
</html>
