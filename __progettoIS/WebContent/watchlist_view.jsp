<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="applicationLogic.models.Film, java.util.*"%>
    
	<% ArrayList<Film> watchlist = (ArrayList<Film>) request.getAttribute("watchlist"); %>

<!DOCTYPE html>
<html>
<head>
	<title>La tua Watchlist</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
	<%@include file="includes/_header.jsp" %>
	<ul>
	<% for(Film x: watchlist){ %>
		<li><img height="50px" width="50px" src="http://image.tmdb.org/t/p/w500<%=x.getLocandina() %>"> <%=x.getId()%> - <%=x.getTitolo()%> - <%=x.getTitoloOriginale()%> </li>
	<%} %>		
	</ul>
</body>
</html>