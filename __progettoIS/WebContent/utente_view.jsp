<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="datiUtente" class="applicationLogic.models.Utente" scope="request"/>
<jsp:useBean id="utente" class="applicationLogic.models.Utente" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>Area Utente</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
	<jsp:include page="includes/_header.jsp"/>
	<ul>
		<li><%=datiUtente.getUsername()%></li>
		<li><%=datiUtente.getPassword()%></li>
		<li><%=datiUtente.getNome()%></li>
		<li><%=datiUtente.getCognome()%></li>
		<li><%=datiUtente.getEmail()%></li>
		<li><%=datiUtente.getRuolo()%></li>
	</ul>
	<% if(datiUtente.getUsername().equals(utente.getUsername())){ %>
		<a href="cambiodati">Clicca per cambiare i dati dell'account</a>
	<% } %>
</body>
</html>