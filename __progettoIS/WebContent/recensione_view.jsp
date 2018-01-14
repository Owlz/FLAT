<%@page import="applicationLogic.bean.FilmRemote"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="applicationLogic.bean.Voto"%>
	<jsp:useBean id="recensione" scope="request" class="applicationLogic.bean.Recensione"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=recensione.getTitolo() %></title>
</head>
<body>
<%=((FilmRemote) recensione.getFilm()).getTitolo() %><br/>
<%=recensione.getTitolo() %> - <%=recensione.getVoto() %> - ID: <%=recensione.getId() %><br/>
TESTO : <%=recensione.getTesto() %><br/>
Voti <%= recensione.getVoti() %>

</body>
</html>