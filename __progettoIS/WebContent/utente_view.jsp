<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="datiUtente" class="applicationLogic.models.Utente" scope="request"/>
<!DOCTYPE html>
<html>
<head>
	<title>Area Utente</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
	<jsp:include page="includes/_header.jsp"/>
	<%= datiUtente.getUsername() %>
	<%= datiUtente.getRuolo() %>
	<%= datiUtente.getPassword() %>
</body>
</html>