<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, applicationLogic.bean.Recensione"%>
<jsp:useBean id="datiUtente" class="applicationLogic.bean.Utente" scope="request"/>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("recensioniUtente"); %>
<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session"/>

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
	<% if(datiUtente.equals(utente)){ %>
		<a href="cambiodati">Clicca per cambiare i dati dell'account</a>
		<a href="watchlist">Clicca per visualizzare la watchlist</a><br/>
		
		<% if(listaRec.size() == 0){ %>
			Non hai ancora fatto recensioni <br/>
		<% } else {%>
			La tua lista di recensioni
			<% for(Recensione x: listaRec){ %>
				<%=x.getFilm().getId() %> - <%=x.getTitolo() %> - <%=x.getVoto() %> <strong onClick="remove(this,<%=x.getFilm().getId() %>)">Rimuovi</strong> <br/>
			<% } %>
		<% } %>
	<% } %>
<script>
function remove(elemento, id){
	let xml = new XMLHttpRequest();
	let url = "removerecensione?id="+id;
	
	xml.open("get", url, true);
	xml.send();
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				elemento.innerHTML = "rimosso!";
			} else {
				x.innerHTML = "errore, riprova";
			} 
		}
	}
}
</script>
</body>
</html>