<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="errore" class="java.lang.String" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Registrazione</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
	<jsp:include page="includes/_header.jsp"/>
	<% if(errore != null){ %>
		<p><%=errore%></p>
	<% } errore = null; %>
	<form action="checkregistrazione" method="post">
		Username <input type="text" name="username"><br/>
		Password <input type="password" name="password"><br/>
		Nome <input type="text" name="nome"><br/>
		Cognome <input type="text" name="cognome"><br/>
		Email <input type="email" name="email"><br/>
		<input type="submit" value="invia i dati">
	</form>
</body>
</html>