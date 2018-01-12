<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session" />

<div style="background-color: red;"><a href="<%= request.getContextPath() %>">homepage</a> - 
<% if(!utente.getRuolo().equals("visitatore")){ %>
	Sei loggato come <a href="utente?id=<%= utente.getUsername() %>"><%= utente.getUsername() %></a>, <a href="logout">disconnettiti</a>
<% }else{  %>
	<a href="login">Va al login</a> - <a href="registrazione">Va alla registrazione</a>
<% } %>
</div>

<!--       ********************      NavBar      ********************           -->
<nav id="nav">
	<div id="spazioLogo">
		<a onClick='href="home_view.jsp"'><img src="img/logo.jpg" height="50" alt="logo"></a>
	</div>
	
	<select class="js-example-basic-single" name="film" style="width: 300px;"></select>
	
	<ul>
		<li><a href="registrazione">Registrati</a></li>
		<li><a href="login">Login</a></li>
	</ul>
</nav>
