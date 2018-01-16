<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="errore" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/login_view.css" type="text/css">
</head>

<body>
<!--       ********************      NavBar      ********************           -->

<jsp:include page="includes/_header.jsp"/>

<!--       ********************      Container     ********************           -->
<div id="container">
	<% if(errore.equals("login fallito, dati non presenti (username errato)")){ %>
	
		<div id="popUPErrore">
			<p>Non è stato trovato nessun utente</p>
			<button id="ok" OnClick="closePopUp()">Conferma</button>
		</div>
		
	<% }%>
	<% if(errore.equals("login fallito, dati non validi (username azzeccato ma password errata)")){ %>
		
		<div id="popUPErrore">
			<p>È stato riscontrato un errore nei dati immessi</p>
			<button id="ok" OnClick="closePopUp()">Torna alla pagina precedente</button>
		</div>
		
	<% }%>
<form action="checklogin" method="post">
	Username <input type="text" name="username"><br/>
	Password <input type="password" name="password"><br/>
	<input type="submit" value="Accedi">
</form>
</div>


<jsp:include page="includes/_import.jsp"/>
<script>
function closePopUp() {
	var div_errore = document.getElementById('popUPErrore');
	div_errore.style.display='none';
};
</script>
</body>
</html>