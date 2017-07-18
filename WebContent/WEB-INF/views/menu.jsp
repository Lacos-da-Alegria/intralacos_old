<ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href="profile"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <h5 class="centered">${voluntario.nome}</h5>
              	  	
                  <li class="mt">
                      <a href="area-voluntario">
                          <i class="fa li_heart"></i>
                          <span>¡Årea do Volunt·rio</span>
                      </a>
                  </li>
				  
				  <li class="sub-menu">
                      <a href="profile">
                          <i class="fa li_user"></i>
                          <span>Minha Conta</span>
                      </a>
                  </li>

                   <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa li_tag"></i>
                          <span>InformaÁıes Importantes</span>
                      </a>
                       <ul class="sub">                
                          <li><a  href="construcao">Volunt·rio Nota 10</a></li>
                      </ul>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-list-ol"></i>
                          <span>Lista de Atividades</span>
                      </a>
                       <ul class="sub">
                       <c:forEach items="${Atividade}" var="ativ" >
                         <li><a  href="listaAtividade?atividade=${ativ.ativid}">${ativ.tag}</a></li>
                        </c:forEach>
                      </ul>
                  </li>
                  
				<c:if test="${(voluntario.acesso == 69) || (voluntario.acesso != 1)}">
					
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-desktop"></i>
                          <span>Painel da Atividade</span>
                      </a>
                      <ul class="sub">
                      <c:forEach items="${todas_Ativs}" var="ativ" >
                      <c:if test="${(voluntario.acesso == 69) || (voluntario.acesso == ativ.key + 1)}">
                          <li><a  href="detalheAtividade?atividade=${ativ.key}">${ativ.value}</a></li>
                       </c:if>
					</c:forEach>
                      </ul>
                  </li>
                  
                 </c:if>
                  <c:if test="${voluntario.acesso == 69}">
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-cogs"></i>
                          <span>Admin Page</span>
                      </a>
                      
                      <ul class="sub">
                      
                          <li><a  href="promover">Promover Usuarios</a></li>
                          <li><a  href="cadastrarNotif">Cadastrar Notificacao</a></li>
						
                      </ul>
                      
                  </li>
                  </c:if>
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