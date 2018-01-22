<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, applicationLogic.bean.Recensione, applicationLogic.bean.FilmLocal"%>
<jsp:useBean id="datiUtente" class="applicationLogic.bean.Utente" scope="request"/>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("recensioniUtente"); %>
<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session"/>


<!DOCTYPE html>
<html>
<head>
	<title>Area Utente</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/utente_view.css" type="text/css">
	<link rel="stylesheet" href="includes/fontawesome-5.0.4/web-fonts-with-css/css/fontawesome-all.css">
</head>
<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>

<!--       ********************      Container     ********************           -->
<div id="aside">
	
		<h4><%=datiUtente.getNome()%></h4>
		<h4><%=datiUtente.getCognome()%></h4>
		<h4><%=datiUtente.getUsername()%></h4>

	<% if(datiUtente.equals(utente)){%>
		<a href="cambiodati">Modifica Dati</a>
		<a href="watchlist">La tua watchlist</a>
	<% } %>
	<% if(datiUtente.equals(utente) && datiUtente.getRuolo().equals("moderatore")){ %>
		<a href="mostrasegnalate">Gestione Segnalazioni</a>
		<a href="mostrarecensioni">Gestione Recensioni</a>
	<% } %>
</div>


<div id="content">
	<% if(datiUtente.equals(utente)){ %>
		
		<% if(listaRec.size() == 0){ %>
			<p> Non hai ancora fatto recensioni</p> <br/>
		<% } else { %>
			<h4> La tua lista di recensioni </h4>
			<% for(Recensione x: listaRec){ %>
			
				<div class="boxRecensione" id="<%=x.getId() %>">
					<h3>Film: <i><%=((FilmLocal) x.getFilm()).getTitolo()%></i>
					<br/>
					Titolo: <i><%=x.getTitolo()%></i>
					<br/>
					( Voto: <i><%=x.getVoto()%></i> )</h3>
					<p id="testo"><%=x.getTesto()%></p>
				</div>
				<div id="pulsantiAzione">
					<button class="removeButton" onClick="remove(this,<%=x.getId() %>)"><i class="fa fa-times" aria-hidden="true"></i></button>
				</div>
				
<%-- 				Totale voti: <%=x.getVotiTotali() %> --%>
				
			<% } %>
		<% } %>
		
	<% } %>
</div>

<div id="popUPConfermaRimozione">

	<p>Recensione rimossa</p>

	<button id="ok" OnClick="closePopUpRimozione()">Conferma</button>

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
				openPopUpRimozione();
				elemento.remove();
				document.getElementById(id).remove();
			}
		}
	}
}

function openPopUpRimozione() {
	
	var div_conferma = document.getElementById('popUPConfermaRimozione');
	div_conferma.style.display='flex';
 	
};
function closePopUpRimozione() {
	
	var div_conferma = document.getElementById('popUPConfermaRimozione');
	div_conferma.style.display='none';
 	
};

</script>
<jsp:include page="includes/_import.jsp"/>
</body>
</html>