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
<form action="checklogin" method="post" onSubmit="return controllo(this)">
	<label>Username: <input type="text" name="username"></label>
	<label>Password: <input type="password" name="password"></label>
	<button type="submit" id="submit">Accedi</button>
</form>
</div>


<jsp:include page="includes/_import.jsp"/>
<script>
function controllo(form){
	if(!(form["username"].value.trim().match(/^[a-zA-Z-\.\d]{3,15}$/))){
		form["user"].setAttribute("title", "L'username deve avere fra i 3 ed i 15 caratteri alfanumerici");
		form["user"].classList.add("error");
		console.log("niente nome utente");
		return false;
	}
	if(!(form["pswd"].value.match(/^[\S]{5,}$/))){
		form["pswd"].classList.add("error");
		form["pswd"].setAttribute("title", "La password deve avere fra i 3 ed i 10 caratteri e non può avere white space");
		return false;
	}
}

function closePopUp() {
	var div_errore = document.getElementById('popUPErrore');
	div_errore.style.display='none';
};
</script>
</body>
</html>