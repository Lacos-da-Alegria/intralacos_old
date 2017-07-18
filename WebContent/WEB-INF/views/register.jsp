<!DOCTYPE jsp>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="pt">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Sistema Interno do La�os da Alegria">
    <meta name="author" content="La�os da Alegria">
    <meta name="keyword" content="La�os da Alegria, Palha�os de Hospital, Trabalho Volunt�rio">

    <title>La�os da Alegria</title>

   
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
	  	
		      <form class="form-regi" action="cadastrar" method="post">
			  
		        <h2 class="form-regi-heading">Cadastro do Volunt�rio</h2><br><small class="ml">
				<font color="red"> Informa��es Obrigat�rias</font></small>
				
		        <div class = "inlineblock centered">
				
					<div class = "inlineblock centered">
					
					<div class = "floater inlineblock regi-wrap centered">
						
						<!-- Enviando mensagem de erro -->
						
						<form:errors path="voluntario.login" cssStyle="color:red"/>
						
						<input name="login" type="text" class="form-control" placeholder="Login*" required>
							
						<br>
						<input name="senha" id="pass1" type="password" class="form-control" placeholder="Senha* - Min. de 6 Caracteres" required>
						<br>	
						<input type="password" class="form-control" placeholder="Confirmar Senha*" required>
						<br>
						<input name="email" type="email" class="form-control" placeholder="E-mail*" required >
						<br>									
						
							<select name="regiao" class="form-control" required>
								<option value="">Cidade Sat�lite*</option>								
								<option >�guas Claras</option>
								<option >Asa Norte</option>
								<option >Asa Sul</option>
								<option >Brazl�ndia</option> 
								<option >Candangol�ndia</option>
								<option >Ceil�ndia</option>
								<option >Cruzeiro</option>
								<option >Gama</option>
								<option >Guar�</option>
								<option >Jardim Bot�nico</option> 
								<option >Lago Norte</option>
								<option >Lagoa Sul</option>
								<option >N�cleo Bandeirante</option>
								<option >Parano�</option>
								<option >Planaltina</option>
								<option >Recanto das Emas</option>
								<option >Riacho Fundo</option>
								<option >Samambaia</option>
								<option >Santa Maria</option>
								<option >S�o Sebasti�o</option>
								<option >Sobradinho</option>
								<option >Taguatinga</option>
								<option >Vicente Pires</option>
								<option >Entorno Saida Norte</option>
								<option >Entorno Saida Sul</option>
							</select>
							<br>
							
							<select name="preferencia" class="form-control" required>
								<option value="">Prefer�ncia de Atividade*</option>
								<option value="1">Hospital Regional do Gama</option>
								  <option value="2">Hospital Regional de Taguatinga</option>
								  <option value="3">Hospital das For�as Armadas</option>
								  <option value="4">Hospital Universit�rio de Bras�lia</option>
								  <option value="5">Atividades Extra-Hospitalares</option>
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
						<input name="dt_Nascimento" type="date" placeholder="Data de Nascimento*" class="form-control" onfocus="(this.type='date')" required>
						<br>
						<input name="whatsapp" type="tel" class="form-control" placeholder="WhatsApp*" required	>
						<br>
						<input name="endereco" type="text" class="form-control" placeholder="Endere�o" >
						<br>
						
							<select name="como_conheceu" class="form-control">
								<option value="">Como Conheceu o La�os?</option>
									<option value="amigo">Indica��o de um Amigos</option>
								  <option value="social">Atrav�s de um Rede Social do La�os</option>
								  <option value="palestra">Atrav�s de uma Palestra</option>								  
								  <option value="televisao">Em uma Reportagem na Televis�o</option>
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

   </script>

  </body>
</html>
