<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.*, applicationLogic.bean.Recensione, applicationLogic.bean.Voto"%>

<jsp:useBean id="film" scope="request" class="applicationLogic.bean.FilmRemote"/>
<jsp:useBean id="utente" scope="session" class="applicationLogic.bean.Utente"/>
<jsp:useBean id="inWatchlist" scope="request" class="java.lang.String"/>
<jsp:useBean id="recUtente" scope="request" class="applicationLogic.bean.Recensione"/>
<%ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaRecensioni");%>

<!DOCTYPE html>
<html>
<head>
	<title><%=film.getTitolo() %> (<%=(film.getDataDiUscita()!= null) ? film.getDataDiUscita().substring(0, 4) : "" %>)</title>
	<%@include file="includes/_import.jsp" %>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/film_view.css" type="text/css">
	<link rel="stylesheet" href="includes/fontawesome-5.0.4/web-fonts-with-css/css/fontawesome-all.css">	
</head>

<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>


<!--       ********************      Container     ********************           -->

<div id="sfondo">
	<img src="http://image.tmdb.org/t/p/w1280<%=film.getBackdrop() %>"/>
</div>

<div id="container">
	
	<div id="aside">
	
		<h2><%=film.getTitolo()%></h2>
		<h6>Titolo originale: <i><%=film.getTitoloOriginale()%></i></h6>
		<h6>Generi: <i><%=film.getGeneri()%></i></h6>
		
		<img src="http://image.tmdb.org/t/p/w500<%=film.getLocandina()%>"/>
		
		<h3 id="voto">Voto FLAT: <%=film.getVoto()%><span style="font-size: 13px; color: grey">/10</span></h3>
		
		<% if(utente.getRuolo().equals("visitatore")) { %>
			<h4 class="watchlist">Accedi per utilizzare la watchlist!</h4>
			<% } else if (inWatchlist.equals("true")) { %>
			<div id="in" class="inWatchlist"><i class="fa fa-check" aria-hidden="true"></i> Già aggiunto alla Watchlist</div>
			<% } else { %>
			<div id="add" class="addWatchlist" onClick="add('<%=film.getId() %>')">Aggiungi alla watchlist</div>
		<% } %>
		
	</div>

	<div id="content">
	
		<% if(listaRec.size() > 0){ %>
			<h2>Recensioni: </h2>
			<% for(Recensione x: listaRec){ %>
				<% if(x.getUtente().equals(utente)) continue; %>
				<div class="boxRecensione" id="rec--<%=x.getUtente().getUsername() %>">
					<h3>Titolo: <i><%=x.getTitolo() %></i> 
					<br/>
					( Voto: <i><%=x.getVoto() %></i> - Autore: <i><%=x.getUtente().getUsername() %></i> )</h3>
					<p id="testo"><%=x.getTesto() %></p>
				</div>
				<div id="pulsantiAzione">
					<i onClick="segnala('<%=x.getId()%>')" class="fa fa-flag" aria-hidden="true"></i>
					
					<!-- Scorro la lista di chi ha votato per vedere se l'utente ha già votato la recensione -->
					<% boolean flag = false; %>
					<% for (Voto y: x.getVoti()) { %> 
					
						<% if (y.getUtente().equals(utente)) { %>	<!-- Se l'utente ha già votato -->
							<% flag = true; %>
							<% if (y.getVoto() == 1) { %>				<!-- Ha votato Up -->
								<i id="sopra<%=x.getId()%>" onClick="rimuoviVoto('<%=y.getId()%>', this)" class="fa fa-thumbs-up" aria-hidden="true" style="color:green"></i>
								<i id="sotto<%=x.getId()%>" onClick="aggiornaVoto(-1,'<%=y.getId()%>', this)" class="fa fa-thumbs-down" aria-hidden="true"></i>
							<% } else { %>								<!-- Ha votato Down -->
								<i id="sopra<%=x.getId()%>" onClick="aggiornaVoto(+1,'<%=y.getId()%>', this)" class="fa fa-thumbs-up" aria-hidden="true"></i>
								<i id="sotto<%=x.getId()%>" onClick="rimuoviVoto('<%=y.getId()%>', this)" class="fa fa-thumbs-down" aria-hidden="true" style="color:red"></i>
							<% } %>
											
						<% }  %> 								
						<!-- Se l'utente non ha mai votato li inserisco dopo -->	
						<% if (flag) break;%>
						
					<% } %>
					
					<% if (!flag) {%>
						<i id="sopra<%=x.getId()%>" onClick="inserisciVoto('<%=x.getId()%>', '+1', this)" class="fa fa-thumbs-up" aria-hidden="true"></i>
						<i id="sotto<%=x.getId()%>" onClick="inserisciVoto('<%=x.getId()%>', '-1', this)" class="fa fa-thumbs-down" aria-hidden="true"></i>
					<% } %>
				</div>
			<% } %>
		<% } %>
	
		<% if(recUtente.getUtente() != null)/*se l'utente ha fatto una recensione la mostra*/ {%>
			<h2>La tua recensione:</h2>
			<div class="boxRecensione personale" id="rec--<%=recUtente.getUtente().getUsername() %>" style="background-color: #b77cf1">
				<h3>Titolo: <i><%=recUtente.getTitolo() %></i>
				<br/>
				( Voto: <i><%=recUtente.getVoto() %></i> )</h3>
				<p id="testo"><%=recUtente.getTesto() %></p>
			</div>
		<% } else if(!utente.getRuolo().equals("visitatore")) /* altrimenti mostra il bottone per inserirla*/ { %>
		
			<button id="newRecensione" OnClick="openForm()">Scrivi recensione</button>
			
		<% } else {%>
			<h2> Solo gli utenti registrati possono inserire le recensioni</h2>
	<% } %>
	<hr/>
	
	</div>

</div>

<div id="spazioFormRecensione">
	<button id="close" OnClick="closeForm()">X</button>
	
	<form name="formRecensione" id="formRecensione" method="post" onSubmit="return controlloInserimento(this, '<%=film.getId() %>');">
	  	<span id="descTitolo">Titolo: </span>
  			<input type="text" name="titolo">
		<span id="descVoto">Voto: </span>
			<select name="voto">
				<option selected="selected" value="0">--</option>
				<% int x = 11; while(x --> 1) { %>
					<option value="<%= x %>"><%= x %></option>
				<% } %>
  			</select>
  		<br/>
  		<br/>
  		<span id="descTesto">Testo: </span>
  			 <textarea rows="10" cols="60" name="recensione"></textarea> 
  		<br/>
 		<input type="hidden" name="idFilm" value="<%=film.getId() %>">
 		<button type="submit" id="submit">Invia la recensione</button>
	</form>
</div>

<div id="footer">
	<h2>Trama:</h2>
	<p><%=film.getDescrizione() %></p>
</div>

<div id="popUPConfermaRecensione">

	<p>Recensione Aggiunta</p>
	<button id="ok" OnClick="closePopUpRecensione()">Conferma</button>

</div>

<div id="popUPConfermaSegnalazione">

	<p>Recensione Segnalata</p>
	<button id="ok" OnClick="closePopUpSegnalazione()">Conferma</button>
	
</div>

<div id="popUPErrore">

	<p id="stringaErrore">Errore</p>
	<button id="ok" OnClick="closePopUpErrore()">Conferma</button>

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
				if (voto == "1") {
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
					document.getElementById('sotto' + idRecensione).style.color = 'blue';
					document.getElementById('sopra' + idRecensione).setAttribute( "onClick", "rimuoviVoto(" + idVoto + ", this);" );
					document.getElementById('sotto' + idRecensione).setAttribute( "onClick", "aggiornaVoto(-1, " + idVoto + ", this);" );
				} else if (voto == "-1") { //Se ha cliccato su sotto
					document.getElementById('sopra' + idRecensione).style.color = 'blue';
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
				bottone.style.color = 'blue';
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





function closeForm() {
	var bottone = document.getElementById('newRecensione');
	bottone.style.display='block';
	var div_form = document.getElementById('spazioFormRecensione');
	div_form.style.display='none';
};
function openForm() {
	var bottone = document.getElementById('newRecensione');
	bottone.style.display='none';
	var div_form = document.getElementById('spazioFormRecensione');
	div_form.style.display='block';
}

function controlloInserimento(form, idFilm) {
	//Controlli da fare prima di chiamare la servlet
	
	//Azzero eventuali errori precedenti
	document.getElementById("descTitolo").style.color= "black";
	document.getElementsByName("titolo")[0].style.color= "black";
	document.getElementsByName("titolo")[0].style.borderColor="";
	document.getElementById("descVoto").style.color= "black";
	document.getElementsByName("voto")[0].style.color= "black";
	document.getElementsByName("voto")[0].style.borderColor="black";
	document.getElementById("descTesto").style.color= "black";
	document.getElementsByName("recensione")[0].style.color= "black";
	document.getElementsByName("recensione")[0].style.borderColor="black";
	
	//Titolo breve e/o mancante
	if(document.getElementsByName("titolo")[0].value.length < 5) {
		document.getElementById("descTitolo").style.color= "red";
		document.getElementsByName("titolo")[0].style.color= "red";
		document.getElementsByName("titolo")[0].style.borderColor="red";
		openPopUpErrore("Titolo troppo breve o mancante.");
		return false;
	}
	
	//Voto mancante
	if(document.getElementsByName("voto")[0].value === "0") {
		document.getElementById("descVoto").style.color= "red";
		document.getElementsByName("voto")[0].style.color= "red";
		document.getElementsByName("voto")[0].style.borderColor="red";
		openPopUpErrore("Voto mancante");
		return false;
	}
	
	//Recensione breve e/o mancante
	if(document.getElementsByName("recensione")[0].value.length < 20) {
		document.getElementById("descTesto").style.color= "red";
		document.getElementsByName("recensione")[0].style.color= "red";
		document.getElementsByName("recensione")[0].style.borderColor="red";
		openPopUpErrore("Recensione troppo breve o mancante.");
		return false;
	}
	
	openPopUpRecensione();
	addRecensione(idFilm);
	return true;
}


function add(id){
	let xml = new XMLHttpRequest();
	let url = "addwatchlist?id="+id;
	
	xml.open("get", url, true);
	xml.send();

	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				document.getElementById("add").classList.remove('addWatchlist');
				document.getElementById("add").classList.add('inWatchlist');
				document.getElementById("add").innerHTML= '<i class="fa fa-check" aria-hidden="true"></i> Già aggiunto alla Watchlist';
			} else {
				
			} 
		}
	}
}

function addRecensione(idFilm){
	let xml = new XMLHttpRequest();
	//Prendo i dati dalla form
    var x = document.getElementById("voto").selectedIndex;
	voto = document.getElementsByTagName("option")[x].value;
    var titolo = document.getElementsByName("titolo")[0].value;
    var recensione = document.getElementsByName("recensione")[0].value;
	
	let url = "addrecensione?voto=" + voto + "&titolo=" + titolo + "&recensione=" + recensione + "&idFilm=" + idFilm;
	
	xml.open("get", url, true);
	xml.send();

	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				openPopUpRecensione();
			} else {
				
			} 
		}
	}
}



function openPopUpErrore(string) {
	var div_errore = document.getElementById('popUPErrore');
	document.getElementById("stringaErrore").innerHTML= string;
	div_errore.style.display='flex';
};
function closePopUpErrore() {
	var div_conferma = document.getElementById('popUPErrore');
	div_conferma.style.display='none';
};

function openPopUpRecensione() {
	var div_conferma = document.getElementById('popUPConfermaRecensione');
	div_conferma.style.display='flex';
};
function closePopUpRecensione() {
	var div_conferma = document.getElementById('popUPConfermaRecensione');
	div_conferma.style.display='none';
};
function closePopUpSegnalazione() {
	var div_conferma = document.getElementById('popUPConfermaSegnalazione');
	div_conferma.style.display='none';
};
</script>
</body>
</html>