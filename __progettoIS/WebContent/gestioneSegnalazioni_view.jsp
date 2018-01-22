<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione, applicationLogic.bean.FilmLocal, applicationLogic.bean.Utente"%>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaSegnalate"); %>

<!DOCTYPE html>
<html>

<head>
	<title>Gestione Segnalazioni</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/gestioneSegnalazioni_view.css" type="text/css">
</head>

<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>

<!--       ********************      Container     ********************           -->
<div id="container">
	<h2>Recensioni Segnalate:</h2>

	<% for(Recensione x: listaRec){ %>
		<div class="elemento" id="<%=x.getId() %>">
			
			<div class="corpo">
				<h4><i>Titolo:</i> "<%=x.getTitolo() %>"</h4>
				<p> 
					<i style="font-weight: bold">Film:</i> <%=((FilmLocal) x.getFilm()).getTitolo()%> <br/>
					<i style="font-weight: bold">Autore:</i> <%=x.getUtente().getUsername()%> <br/>
					<i style="font-weight: bold">Testo:</i> <%=x.getTesto()%>
				</p>
			</div>
			<div class="voti">
				<p><i style="font-weight: bold">Voto del film:</i> <%=x.getVoto() %></p>
				<p><i style="font-weight: bold">Somma voti utenti:</i> <%=x.getVotiTotali() %></p>
			</div>
			<div class="bottoni">
				<button onClick="removeRecensione('<%=x.getId() %>')">Rimuovi recensione</button>
				<button onClick="removeSegnalazione('<%=x.getId() %>')">Ignora segnalazione</button>
			</div>
		</div>
	<% } %>


</div>

<div id="popUpRimuoviRecensione">

	<p>Recensione rimossa</p>
	<button id="ok" OnClick="closePopUpRecensione()">Conferma</button>

</div>

<div id="popUpIgnoraSegnalazione">

	<p>Segnalazione ignorata</p>
	<button id="ok" OnClick="closePopUpSegnalazione()">Conferma</button>

</div>

<jsp:include page="includes/_import.jsp"/>
<script>
function removeRecensione(id){
	let xml = new XMLHttpRequest();
	let url = "removerecensioneadmin?id="+id;
	
	xml.open("get", url, true);
	xml.send();
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				let x = document.getElementById(id);
				x.remove();
				openPopUpRecensione();
			}else{
				let x = document.getElementById(id);
				x.innerHTML = "errore";
			}
		}
	}
}
function removeSegnalazione(id){
	let xml = new XMLHttpRequest();
	let url = "removesegnalazione?id="+id;
	
	xml.open("get", url, true);
	xml.send();
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				let x = document.getElementById(id);
				x.remove();
				openPopUpSegnalazione();
			}else{
				let x = document.getElementById(id);
				x.innerHTML = "errore";
			}
		}
	}
}

function openPopUpRecensione() {
	var div_confermaR = document.getElementById('popUpRimuoviRecensione');
	div_confermaR.style.display='flex';
};
function closePopUpRecensione() {
	var div_confermaR = document.getElementById('popUpRimuoviRecensione');
	div_confermaR.style.display='none';
};
function openPopUpSegnalazione() {
	var div_confermaS = document.getElementById('popUpIgnoraSegnalazione');
	div_confermaS.style.display='flex';
};
function closePopUpSegnalazione() {
	var div_confermaS = document.getElementById('popUpIgnoraSegnalazione');
	div_confermaS.style.display='none';
};
</script>
</body>
</html>