<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.*, applicationLogic.bean.Recensione"%>

<jsp:useBean id="film" scope="request" class="applicationLogic.bean.FilmRemote"/>
<jsp:useBean id="utente" scope="session" class="applicationLogic.bean.Utente"/>
<jsp:useBean id="inWatchlist" scope="request" class="java.lang.String"/>
<jsp:useBean id="recUtente" scope="request" class="applicationLogic.bean.Recensione"/>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaRecensioni"); %>

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
<div id="container">
	
	<div id="aside">
	
		<h4><%=film.getTitolo() %></h4>
		
		<img src="http://image.tmdb.org/t/p/w500<%=film.getLocandina() %>"/>
		
		<% if(utente.getRuolo().equals("visitatore")) { %>
			accedi per utilizzare la watchlist
			<% } else if (inWatchlist.equals("true")) { %>
			<div id="in" class="inWatchlist">Già aggiunto alla watchlist</div>
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
					<h3>Titolo: <i><%=x.getTitolo() %></i> ( Voto: <i><%=x.getVoto() %></i> - Autore: <i><%=x.getUtente().getUsername() %></i> )</h3>
					<p id="testo"><%=x.getTesto() %></p>
				</div>
					<div id="pulsantiAzione">
						<i class="fa fa-flag" aria-hidden="true"></i>
						<i class="fa fa-thumbs-up" aria-hidden="true"></i>
						<i class="fa fa-thumbs-down" aria-hidden="true"></i>
					</div>
			<% } %>
		<% } %>
	
		<% if(recUtente.getUtente() != null)/*se l'utente ha fatto una recensione la mostra*/ {%>
			<h2>La tua recensione:</h2>
			<div id="rec--<%=recUtente.getUtente().getUsername() %>-<%=recUtente.getFilm().getId() %>" style="background-color: #b77cf1">
				<h3>Titolo: <i><%=recUtente.getTitolo() %></i> (Voto: <i><%=recUtente.getVoto() %></i>)</h3>
				<p><%=recUtente.getTesto() %></p>
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
	<form method="post" action="addrecensione">
	  	Titolo (opzionale):
  			<input type="text" name="titolo">
		Voto (obbligatorio): 
			<select name="voto">
				<option selected="selected" value="0">--</option>
				<% int x = 11; while(x --> 1) { %>
					<option value="<%= x %>"><%= x %></option>
				<% } %>
  			</select>
  		<br/>
  		<br/>
  		Testo (opzionale):
  			 <textarea rows="10" cols="60" name="recensione"></textarea> 
  		<br/>
 		<input type="hidden" name="idFilm" value="<%=film.getId() %>">
  		<input id="submit" type="submit" value="invia la recensione">
	</form>
</div>

<div id="footer">
	<h2>Trama:</h2>
	<p><%=film.getDescrizione() %></p>
</div>


<jsp:include page="includes/_import.jsp"/>
<script>
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
			} else {
				
			} 
		}
	}
}
</script>
</body>
</html>