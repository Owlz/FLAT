<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, applicationLogic.bean.Recensione"%>
<jsp:useBean id="datiUtente" class="applicationLogic.bean.Utente" scope="request"/>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("recensioniUtente"); %>
<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Area Utente</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/utente_view.css" type="text/css">
</head>
<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>


<!--       ********************      Container     ********************           -->
<div id="aside">
	
		<h4><%=datiUtente.getNome()%></h4>
		<h4><%=datiUtente.getCognome()%></h4>
		<h4><%=datiUtente.getUsername()%></h4>
		<h4><%=datiUtente.getPassword()%> <span style="color:red">da togliere</span></h4>
		<h4><%=datiUtente.getEmail()%> <span style="color:red">da togliere</span></h4>
		<h4><%=datiUtente.getRuolo()%> <span style="color:red">da togliere</span></h4>

	<% if(datiUtente.equals(utente)){ %>
		<a href="cambiodati">Modifica Dati</a>
		<a href="watchlist">La tua watchlist</a>
	<% } %>
</div>


<div id="content">
	<% if(datiUtente.equals(utente)){ %>
		
		<% if(listaRec.size() == 0){ %>
			<p> Non hai ancora fatto recensioni</p> <br/>
		<% } else { %>
			<p> La tua lista di recensioni </p>
			<% for(Recensione x: listaRec){ %>
				<%=x.getFilm().getId() %> - <%=x.getTitolo() %> - <%=x.getVoto() %> <strong onClick="remove(this,<%=x.getId() %>)">Rimuovi</strong> <br/>
			<% } %>
		<% } %>
		
	<% } %>
</div>



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
<jsp:include page="includes/_import.jsp"/>
</body>
</html>