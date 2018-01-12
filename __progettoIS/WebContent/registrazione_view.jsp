<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="errore" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>
<head>
	<title>Registrazione</title>
	<%@include file="includes/_import.jsp" %>
	<link rel="stylesheet" href="css/login_view.css" type="text/css">
</head>
<body>

<!--       ********************      NavBar      ********************           -->
<nav id="nav">
	<div id="spazioLogo">
		<img src="img/logo.jpg" height="50" alt="logo">
	</div>
	
	<select class="js-example-basic-single" name="film" style="width: 300px;"></select>
	
	<ul>
		<li><a href="registrazione">Registrati</a></li>
		<li><a href="login">Login</a></li>
	</ul>
</nav>


<!--       ********************      Container     ********************           -->
<div id="container">
	<% if(!errore.equals("")){ %>
		<p><%=errore%></p>
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


</body>

</html>