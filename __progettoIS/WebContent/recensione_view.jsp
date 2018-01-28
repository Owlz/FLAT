<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="applicationLogic.bean.Voto, applicationLogic.bean.FilmLocal"%>
    <%@page import="applicationLogic.bean.FilmRemote"%>
    <jsp:useBean id="utente" scope="session" class="applicationLogic.bean.Utente"/>
	<jsp:useBean id="recensione" scope="request" class="applicationLogic.bean.Recensione"/>
	
<!DOCTYPE html>
<html>

<head>
	<title><%=recensione.getTitolo() %></title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/recensione_view.css" type="text/css">
	<link rel="stylesheet" href="includes/fontawesome-5.0.4/web-fonts-with-css/css/fontawesome-all.css">	
</head>

<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>

<!--       ********************      Container     ********************           -->
<div id="container">

	<h2>Recensione di  <%=recensione.getUtente().getUsername()%>:</h2>
	
	<div class="elemento" id="<%=recensione.getId() %>">
			
		<div class="corpo">
			<h4><i>Titolo:</i> "<%=recensione.getTitolo() %>"</h4>	
			<p id="scritte" style="fontSize: 16px;"> 
				<i style="font-weight: bold">Film:</i> <%=((FilmLocal) recensione.getFilm()).getTitolo()%> <br/>
				<i style="font-weight: bold">Autore:</i> <%=recensione.getUtente().getUsername()%> <br/>
				<i style="font-weight: bold">Testo:</i> <%=recensione.getTesto()%>
			</p>
		</div>
		<div class="voti">
			<p><i style="font-weight: bold">Voto del film:</i> <%=recensione.getVoto() %></p>
			<p><i style="font-weight: bold">Somma voti utenti:</i> <%=recensione.getVotiTotali() %></p>
		</div>
		<div class="bottoni" id="pulsantiAzione">
				<% if(!utente.getRuolo().equals("visitatore")) { %>
					<i onClick="segnala('<%=recensione.getId()%>')" class="fa fa-flag" aria-hidden="true"></i>
					
					<!-- Scorro la lista di chi ha votato per vedere se l'utente ha già votato la recensione -->
					<% boolean flag = false; %>
					<% for (Voto y: recensione.getVoti()) { %> 
					
						<% if (y.getUtente().equals(utente)) { %>	<!-- Se l'utente ha già votato -->
							<% flag = true; %>
							<% if (y.getVoto() == 1) { %>				<!-- Ha votato Up -->
								<i id="sopra<%=recensione.getId()%>" onClick="rimuoviVoto('<%=y.getId()%>', this)" class="fa fa-thumbs-up" aria-hidden="true" style="color:green"></i>
								<i id="sotto<%=recensione.getId()%>" onClick="aggiornaVoto(-1,'<%=y.getId()%>', this)" class="fa fa-thumbs-down" aria-hidden="true"></i>
							<% } else { %>								<!-- Ha votato Down -->
								<i id="sopra<%=recensione.getId()%>" onClick="aggiornaVoto(+1,'<%=y.getId()%>', this)" class="fa fa-thumbs-up" aria-hidden="true"></i>
								<i id="sotto<%=recensione.getId()%>" onClick="rimuoviVoto('<%=y.getId()%>', this)" class="fa fa-thumbs-down" aria-hidden="true" style="color:red"></i>
							<% } %>
											
						<% }  %> 								
						<!-- Se l'utente non ha mai votato li inserisco dopo -->	
						<% if (flag) break;%>
						
					<% } %>
					
					<% if (!flag) {%>
						<i id="sopra<%=recensione.getId()%>" onClick="inserisciVoto('<%=recensione.getId()%>', '+1', this)" class="fa fa-thumbs-up" aria-hidden="true"></i>
						<i id="sotto<%=recensione.getId()%>" onClick="inserisciVoto('<%=recensione.getId()%>', '-1', this)" class="fa fa-thumbs-down" aria-hidden="true"></i>
					<% } %>
				<% } %>
			</div>
	</div>
</div>

<div id="popUPConfermaSegnalazione">

	<p>Recensione Segnalata</p>
	<button id="ok" OnClick="closePopUpSegnalazione()">Conferma</button>
	
</div>

<jsp:include page="includes/_import.jsp"/>
<script>
function inserisciVoto(idRecensione, voto, bottone){
	let xml = new XMLHttpRequest();
	let url = "vota?flag=aggiungi&voto="+ voto +"&idRecensione=" + idRecensione;
	
	xml.open("get", url, true);
	xml.send();

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText !== "fall"){
				
				//Valido per entrambi
				bottone.setAttribute( "onClick", "rimuoviVoto(" + xml.responseText.substring(4) + ", this);" );
				//In base a quale hai cliccato
				if (voto == "+1") {
					bottone.style.color = 'green';
					document.getElementById('sotto' + idRecensione).setAttribute( "onClick", "aggiornaVoto(-1," + xml.responseText.substring(4) + ", this);" );
				} else {
					bottone.style.color = 'red';
					document.getElementById('sopra' + idRecensione).setAttribute( "onClick", "aggiornaVoto(+1," + xml.responseText.substring(4) + ", this);" );
				}
				
			} else {
				
			} 
		}
	}
}

function aggiornaVoto(voto, idVoto, bottone){
	let xml = new XMLHttpRequest();
	let url = "vota?flag=modifica&idVoto=" + idVoto + "&voto=" + voto;
	
	xml.open("get", url, true);
	xml.send();
	
	var idRecensione = bottone.id.substring(5);
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				
				if (voto == "+1") { //Se ha cliccato su sopra
					document.getElementById('sopra' + idRecensione).style.color = 'green';
					document.getElementById('sotto' + idRecensione).style.color = '#f5a015';
					document.getElementById('sopra' + idRecensione).setAttribute( "onClick", "rimuoviVoto(" + idVoto + ", this);" );
					document.getElementById('sotto' + idRecensione).setAttribute( "onClick", "aggiornaVoto(-1, " + idVoto + ", this);" );
				} else if (voto == "-1") { //Se ha cliccato su sotto
					document.getElementById('sopra' + idRecensione).style.color = '#f5a015';
					document.getElementById('sotto' + idRecensione).style.color = 'red';
					document.getElementById('sopra' + idRecensione).setAttribute( "onClick", "aggiornaVoto(+1, " + idVoto + ", this);" );
					document.getElementById('sotto' + idRecensione).setAttribute( "onClick", "rimuoviVoto(" + idVoto + ", this);" );
				}

			}
		}
	}
}

function rimuoviVoto(idVoto, bottone){
	let xml = new XMLHttpRequest();
	let url = "vota?flag=rimuovi&idVoto=" + idVoto;
	
	xml.open("get", url, true);
	xml.send();

	var idRecensione = bottone.id.substring(5);
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				bottone.style.color = '#f5a015';
				document.getElementById('sopra' + idRecensione).setAttribute( "onClick", "inserisciVoto("+ idRecensione +", +1, this);" );
				document.getElementById('sotto' + idRecensione).setAttribute( "onClick", "inserisciVoto("+ idRecensione +", -1, this);" );
			} else {
				
			} 
		}
	}
}


function segnala(id){
	let xml = new XMLHttpRequest();
	let url = "segnala?id="+id;
	
	xml.open("get", url, true);
	xml.send();

	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				var div_conferma = document.getElementById('popUPConfermaSegnalazione');
				div_conferma.style.display='flex';
			} else {
				
			} 
		}
	}
}

function closePopUpSegnalazione() {
	var div_conferma = document.getElementById('popUPConfermaSegnalazione');
	div_conferma.style.display='none';
}
</script>
</body>
</html>