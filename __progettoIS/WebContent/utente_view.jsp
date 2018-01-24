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
	
		<h4><span style="font-size: 13px; color: grey">Nome:</span> <%=datiUtente.getNome()%> <%=datiUtente.getCognome()%></h4>
		
		<h4><span style="font-size: 13px; color: grey">Username:</span> <%=datiUtente.getUsername()%></h4>

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
			<h2> La tua lista di recensioni </h2>
			<% for(Recensione x: listaRec){ %>
			
				<div class="boxRecensione box effect7" id="<%=x.getId() %>">
				
					<h3 id="titoloRec"><span style="font-size: 13px; color: #777">Film:</span> <i><%=((FilmLocal) x.getFilm()).getTitolo()%></i></h3>
					<h3><span style="font-size: 13px; color: #777">Titolo: </span> <i><%=x.getTitolo()%></i> &emsp;&emsp;
						<span style="font-size: 13px; color: #777">Voto: </span> <i><%=x.getVoto()%>
						<span style="font-size: 13px; color: #777">/10</span></i></h3>
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