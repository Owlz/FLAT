<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session" />

<div style="background-color: red;"><a href="<%= request.getContextPath() %>">homepage</a> - 
<% if(!utente.getRuolo().equals("visitatore")){ %>
	Sei loggato come <a href="utente?id=<%= utente.getUsername() %>"><%= utente.getUsername() %></a>, <a href="logout">disconnettiti</a>
<% }else{  %>
	<a href="login">Va al login</a> - <a href="registrazione">Va alla registrazione</a>
<% } %>
</div>