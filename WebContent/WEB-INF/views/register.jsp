<!DOCTYPE jsp>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="pt">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema Interno do Laços da Alegria">
    <meta name="author" content="Laços da Alegria">
    <meta name="keyword" content="Laços da Alegria, Palhaços de Hospital, Trabalho Voluntário">
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

	  <div id="register-page">
	  
	  	<div class="container" >
	  	
		      <form id="cadastro" class="form-regi" action="cadastrar" method="post">
			  
		        <h2 class="form-regi-heading">Cadastro do Voluntário</h2><br>
		        <p class="ml centered"><font color="red">${error}</font></p>
		        <small class="ml"><font color="red"><b> *Informações Obrigatórias</b></font></small>
				
		        <div class = "inlineblock centered">
				
					<div class = "inlineblock centered">
					
					<div class = "floater inlineblock regi-wrap centered">
						
						<!-- Enviando mensagem de erro -->
						
						<form:errors path="voluntario.login" cssStyle="color:red"/>
						
						<input name="login" type="text" class="form-control" placeholder="Login*" required>
							
						<br>
						<input name="senha" id="pass1" type="password" class="form-control" placeholder="Senha* - Min. de 6 Caracteres" required>
						<br>	
						<input name="dual_senha" type="password" id="pass2" class="form-control" placeholder="Confirmar Senha*" onChange="checkPasswordMatch();" required>
						<font color="red" id="mensagemPassword"></font>
						<br>
						<input name="email" type="email" class="form-control" placeholder="E-mail*" required >
						<br>									
						
							 <select name="regiaoId" class="form-control" required>
								<option value="">Cidade Satélite*</option>								
								<c:forEach items="${regioes}" var="regiao">
									<option value="${regiao.id}">${regiao.nome}</option>	
								</c:forEach>
							</select>
							<br>
							   
							<select name="preferenciaId" class="form-control" required>
								<option value="">Preferência de Atividade*</option>
								<c:forEach items="${atividades}" var="atividade">
									<option value="${atividade.id}">${atividade.nome}</option>	
								</c:forEach>
							</select>
							<br>			
								
							
						<div class = "goleft">
						<label>Sexo*</label><br> 
						<label>
							<input type="radio" name="sexo" value="masculino" required> Palhacinho
						</label>
						<label>
							<input type="radio" name="sexo" value="feminino" required> Palhacinha
						</label >
						</div>				
							</div>
							
					<div class = "floater inlineblock centered regi-wrap">							
						
						<input name="nome" type="text" class="form-control" placeholder="Nome Completo*" required>
						<br>
						<input name="cpf" type="text"  class="form-control" placeholder="CPF*" required>
						<br>	
						<input class="form-control" placeholder="Data de Nascimento*" type="text" name="dt_Nascimento" data-inputmask="'alias': 'dd/mm/yyyy'" data-mask required>
						<br>
                       	<input name="ddd" type="tel" class="form-control" placeholder="DDD*" required>
						<br>
                        <input name="whatsapp" type="tel" class="form-control" placeholder="WhatsApp*" required>
						<br>
						<input name="endereco" type="text" class="form-control" placeholder="Endereço" >
						<br>
						
							<select name="como_conheceu" class="form-control">
								<option value="">Como Conheceu o Laços?</option>
									<option value="amigo">Indicação de um Amigos</option>
								  <option value="social">Através de um Rede Social do Laços</option>
								  <option value="palestra">Através de uma Palestra</option>								  
								  <option value="televisao">Em uma Reportagem na Televisão</option>
								  <option value="outros">Outros</option>
							</select>
						<br>				
						
					</div>
					</div>		
							
					
					<input type="submit" class="regi-btn btn btn-theme centered" class="fa"><br><br>			
					
				</div>
				
		      </form>	  	
	  	
	  	</div>
		
	  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>	
    
    <!-- InputMask -->
	<script src="<c:url value="assets/js/input-mask/jquery.inputmask.js" />"></script>
	<script src="<c:url value="assets/js/input-mask/jquery.inputmask.date.extensions.js" />"></script>
	<script src="<c:url value="assets/js/input-mask/jquery.inputmask.extensions.js" />"></script>
	
   <script type="application/javascript">

	$.backstretch("assets/img/login-bg2.jpg",{speed:500});
   
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

  </body>
</html>
