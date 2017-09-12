<header class="header black-bg">
          <div class="sidebar-toggle-box">
              <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Menu"></div>
          </div>
            
<a href="${voluntario.hrefVoluntario()}" class="logo hidden-phone"><b>¡ÅREA DO ${voluntario.designacao()}</b></a>

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
              
               <li class="dropdown tooltips" data-placement="left" data-original-title="Ir para Ouvidoria">
                  <a class="dropdown-toggle" href="Ouvidoria">
                  <% int atendidos = (Integer) session.getAttribute("atendidos"); %>
                 	<c:set var="atendidos" value=""/>
                      <i class="fa li_bubble" ></i>                     
                      <c:if test="<%=atendidos > 0 %>">    
                      	<span class="button__badge blink"><%=atendidos%></span>
                      </c:if>                               
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
