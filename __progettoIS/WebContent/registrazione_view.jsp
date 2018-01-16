<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="errore" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>
<head>
	<title>Registrazione</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/registrazione_view.css" type="text/css">
</head>
<body>
<!--       ********************      NavBar      ********************           -->

<jsp:include page="includes/_header.jsp"/>


<!--       ********************      Container     ********************           -->
<div id="container">
	<% if(errore.equals("registrazione fallita (dati non validi e/o campi vuoti)")){ %>
	
		<div id="popUPErrore">
			<button id="close" OnClick="closePopUp()">X</button>
			<p>È stato riscontrato un errore nei dati immessi</p>
			<button id="ok" OnClick=" location.href='logout'">Torna alla pagina precedente</button>
		</div>
		
	<% }%>
	<% if(errore.equals("registrazione fallita (dati occupati)")){ %>
		
		<div id="popUPErrore">
			<button id="close" OnClick="closePopUp()">X</button>
			<p>Alcuni dei dati inseriti sono già presenti sul database</p>
			<button id="ok" OnClick=" location.href='logout'">Conferma</button>
		</div>
		
	<% }%>
	<form action="checkregistrazione" method="post">
		Nome <input type="text" name="nome"><br/>
		Cognome <input type="text" name="cognome"><br/>
		Indirizzo Email <input type="email" name="email"><br/>
		Nome Utente <input type="text" name="username"><br/>
		Password <input type="password" name="password"><br/>
		Conferma Password <input type="password" name="confermaPassword"><br/>
		<input type="submit" value="Registrati">
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