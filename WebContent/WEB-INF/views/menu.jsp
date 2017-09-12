			<ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="profile"><img src="${voluntario.urlS3()}" class="img-circle" height="60" width="60"></a></p>
              	  <h5 class="centered">${voluntario.nome}</h5>
              	  
                  <li class="mt">
                      <a  href="${voluntario.hrefVoluntario()}">
                          <i class="fa li_heart"></i>
                          <span>¡Årea do ${voluntario.designacao()}</span>
                      </a>
                  </li>
				  
				  <li class="sub-menu">
                      <a href="profile">
                          <i class="fa li_user"></i>
                          <span>Minha Conta</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="Ouvidoria">
                          <i class="fa fa-ticket"></i>
                          <span>Ouvidoria</span>
                      </a>
                  </li>
                  
                  <c:if test="${!voluntario.novato}">  
		          <li class="sub-menu">
                      <a href="javascript:;" >
   					  <i class="fa fa-plus" aria-hidden="true"></i>
                          <span>Mais LaÁos</span>
                      </a>
                       <ul class="sub">      
                          <!--  <li><a  href="construcao">Volunt·rio Nota 10</a></li> -->
                          <li><a class="typeform-share" href="https://ricardo249.typeform.com/to/CHaHvE" data-mode="1" target="_blank">Quiz LaÁos</a></li>
	                      <li><a  href="AniversariantesDoMes">Aniversariantes do MÍs</a></li>   
                      </ul>
                  	</li>
			    	</c:if>
			        <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_tag"></i>
                          <span>InformaÁıes Importantes</span>
                      </a>
                       <ul class="sub">
                       <c:if test="${voluntario.novato}">       
                       	  <li><a data-toggle="modal" class="dropdown-toggle" href="#myModaAtivar">N„o Sou Novato</a></li>
                       </c:if>
                       	  <li><a  href="https://drive.google.com/file/d/0BwsuXGjVO_-veGl0blhfR1RVWDQ/view" target="_blank">Diretrizes</a></li>          
                          <li><a  href="assets/docs/estatuto.pdf" target="blank">Estatuto</a></li>
                          <li><a  href="FAQ">Perguntas Frequentes</a></li>
                          <li><a href="https://lacosinforma.wordpress.com/">LaÁos Informa</a></li>
                          <li><a  href="LeituraTermo">Termo de Ades„o</a></li>
                      </ul>
                  	</li>
              <c:if test="${!voluntario.novato}">    
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-list-ol"></i>
                          <span>Lista de Atividades</span>
                      </a>
                       <ul class="sub">
                       <c:forEach items="${atividades}" var="ativ" >
                         <li><a  href="listaAtividade?atividade=${ativ.id}">${ativ.tag}</a></li>
                        </c:forEach>
                      </ul>
                  </li>
                
				<c:if test="${voluntario.acessoCoordenador() || voluntario.acessoDiretorHosp()|| voluntario.admin}">
					
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-desktop"></i>
                          <span>Painel da Atividade</span>
                      </a>
                      <ul class="sub">
                      
						<li><a href="cadastrarNotificacao">Cadastrar NotificaÁ„o</a></li>
                      
                      <c:forEach items="${atividades}" var="ativ" >
                          <li><a  href="detalheAtividade?ativId=${ativ.id}">${ativ.tag}</a></li>
					</c:forEach>
					
                      </ul>
                  </li>
                  
                 </c:if>
                 
                 <c:if test="${voluntario.acessoControleNovatos() || voluntario.acessoDiretorHosp() || voluntario.admin}">
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-gamepad"></i>
                          <span>Controle de Novatos</span>
                      </a>
                      <ul class="sub">                     			 		
                      <c:forEach items="${atividades}" var="ativ" >
                          <li><a  href="controleNovatos?ativId=${ativ.id}">	${ativ.tag}</a></li>
					</c:forEach>
					
                      </ul>
                  </li>
                 </c:if>
                
                <c:if test="${voluntario.atendimento != null}">
                
                <li class="sub-menu">
                      <a href="Atendimento?atendId=${voluntario.atendimento.id}" >
                          <i class="fa fa-comments"></i>
                          <span>${voluntario.atendimento.nome}</span>
                      </a>
                  </li>
                
                </c:if>
                
                 <c:if test="${voluntario.acessoPolo() || voluntario.acessoDiretorOngs() || voluntario.admin}">
                 <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-university"></i>
                          <span>Registro Ongs</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="InstituicoesOngs">InstituiÁıes</a></li>
                          <li><a  href="CalendarioOngs">Calend·rio de AÁıes</a></li>
                          <c:if test="${voluntario.acessoDiretorOngs() || voluntario.admin}">
							 <li><a  href="EquipeOngs">Equipe Ong's</a></li>
                         	 <li><a  href="ControleTags">Controle de Tags</a></li>
                          </c:if>
                      </ul>
                  </li>
                 </c:if>
                 <c:if test="${voluntario.pertenceEquipe() || voluntario.admin}">
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-briefcase"></i>
                          <span>Controle de Demandas</span>
                      </a>
                      <ul class="sub">                     					
                      <c:forEach items="${voluntario.equipes}" var="equipe" >
                          <li><a  href="ControleDemandas?equipeId=${equipe.id}">${equipe.nome}</a></li>
					</c:forEach>
					
                      </ul>
                  </li>
                  
                 </c:if>
                 
                  <c:if test="${voluntario.acessoExecutivo() || voluntario.admin}">
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>Admin Page</span>
                      </a>
                      <ul class="sub">
                          <li><a href="recursoHumano"> Recurso Humano</a></li>
                          <li><a href="CadastroEquipe"> Cadastro de Equipe</a></li>
                          <li><a href="controleDiretores"> Controle de Diretores</a></li>
                      </ul>
                      
                  </li>
                  </c:if>
                  
                  </c:if>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_news"></i>
                          <span>Sobre</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="historiaLacos">LaÁos da Alegria</a></li>
                          <!-- <li><a  href="construcao">Trabalho Volunt·rio</a></li> -->
                          <li><a  href="comoPossoAjudar">Como Posso Ajudar</a></li>
                      </ul>
                  </li>

                  <li class="sub-menu">
                      <a href="Contatos" >
                          <i class="fa li_phone"></i>
                          <span>Contatos</span>
                      </a>
                  </li>
                 

              </ul>
         <!-- sidebar menu end-->
         
<script>(function(){var qs,js,q,s,d=document,gi=d.getElementById,ce=d.createElement,gt=d.getElementsByTagName,id='typef_orm_share',b='https://s3-eu-west-1.amazonaws.com/share.typeform.com/';if(!gi.call(d,id)){js=ce.call(d,'script');js.id=id;js.src=b+'share.js';q=gt.call(d,'script')[0];q.parentNode.insertBefore(js,q)}id=id+'_';if(!gi.call(d,id)){qs=ce.call(d,'link');qs.rel='stylesheet';qs.id=id;qs.href=b+'share-button.css';s=gt.call(d,'head')[0];s.appendChild(qs,s)}})()</script>       
         
