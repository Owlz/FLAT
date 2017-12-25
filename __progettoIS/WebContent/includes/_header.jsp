<jsp:useBean id="utente" class="applicationLogic.models.Utente" scope="session" />

<div style="background-color: red;">
<% if(!utente.getRuolo().equals("visitatore")){ %>
	Sei loggato, <a href="logout">disconnettiti</a>
<% }else{  %>
	<a href="login">Va al login</a> - <a href="registrazione">Va alla registrazione</a>
<% } %>
</div>