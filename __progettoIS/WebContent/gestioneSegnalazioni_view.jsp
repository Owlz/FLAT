<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione"%>

<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaSegnalate"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Gestione Segnalazioni</title>
	<%@include file="includes/_import.jsp"%></head>
<body>
	<% for(Recensione x: listaRec){ %>
		<div>
			<p><%=x.getTesto() %></p>
		</div>
	<% } %>
</body>
</html>