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

<jsp:include page="includes/_import.jsp"/>
</body>
</html>