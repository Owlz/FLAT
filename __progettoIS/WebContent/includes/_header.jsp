<jsp:useBean id="utente" class="applicationLogic.models.Utente" scope="session" />

<div style="background-color: red;">
<% if(!utente.getRuolo().equals("visitatore")){ %>
	Sei loggato come <a href="utente?id=<%= utente.getUsername() %>"><%= utente.getUsername() %></a>, <a href="logout">disconnettiti</a>
<% }else{  %>
	<a href="login">Va al login</a> - <a href="registrazione">Va alla registrazione</a>
<% } %>
</div>