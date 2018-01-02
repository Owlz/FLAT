<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="film" scope="request" class="applicationLogic.models.Film"/>
<!DOCTYPE html>
<html>
<head>
	<title><%=film.getTitolo() %> (<%=film.getDataDiUscita().substring(0, 4) %>)</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
<%=film.getTitolo() %>
<img src="http://image.tmdb.org/t/p/w500<%=film.getLocandina() %>">
</body>
</html>