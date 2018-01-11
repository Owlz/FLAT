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
<title><%=film.getTitolo() %> (<%=film.getDataDiUscita().substring(0, 4) %>)</title>
<%@include file="includes/_import.jsp" %>
</head>
<body>
<jsp:include page="includes/_header.jsp"/>
<%=film.getTitolo() %>
<br>
<%=film.getDescrizione() %>
<img height=100px width=75px src="http://image.tmdb.org/t/p/w500<%=film.getLocandina() %>"/>
<br/>
<br/>
<br/>
<% if(utente.getRuolo().equals("visitatore")) { %>
accedi per utilizzare la watchlist
<% } else if (inWatchlist.equals("true")) { %>
<div id="watchlist" style="color: blue;" onClick="remove('<%=film.getId() %>')">Rimuovi dalla watchlist</div>
<% } else { %>
<div id="watchlist" style="color: red;" onClick="add('<%=film.getId() %>')">Aggiungi alla watchlist</div>
<% } %>

<br>
<br>
<% if(recUtente.getUtente() != null)/*se l'utente ha fatto una recensione la mostra*/ {%>
	<h2>La tua recensione:</h2>
	<div id="rec--<%=recUtente.getUtente().getUsername() %>-<%=recUtente.getFilm().getId() %>" style="background-color: #b77cf1">
			<h3>Titolo: <i><%=recUtente.getTitolo() %></i> (Voto: <i><%=recUtente.getVoto() %></i>)</h3>
			<p><%=recUtente.getRecensione() %></p>
		</div>
<% } else if(!utente.getRuolo().equals("visitatore")) /* altrimenti mostra la form per inserirla*/ { %>
	<form method="post" action="inseriscirecensione" style="background-color: #b77cf1">
		Voto (obbligatorio): 
			<select name="voto">
				<% int x = 11; while(x --> 1) { %>
					<option value="<%= x %>"><%= x %></option>
				<% } %>
  			</select>
  		<br/>
  		Titolo (opzionale):
  			<input type="text" name="titolo">
  		<br/>
  		Testo (opzionale):
  			 <textarea rows="10" cols="20" name="recensione"></textarea> 
  		<br/>
 		 <input type="hidden" name="idFilm" value="<%=film.getId() %>">
  		<input type="submit" value="invia la recensione">
	</form>
<% } else {%>
	<h2> Solo gli utenti registrati possono inserire le recensioni</h2>
<% } %>
<hr/>
<% if(listaRec.size() > 0){ %>
	<h2>Lista delle recensioni: </h2>
	<% for(Recensione x: listaRec){ %>
		<% if(x.getUtente().equals(utente)) continue; %>
		<div id="rec--<%=x.getUtente().getUsername() %>-<%=x.getFilm().getId() %>" style="background-color: #7daaf2">
			<h3>Titolo: <i><%=x.getTitolo() %></i> (Voto: <i><%=x.getVoto() %></i> - Autore: <i><%=x.getUtente().getUsername() %></i>)</h3>
			<p><%=x.getRecensione() %></p>
		</div>
	<% } %>
<% } %>

<script>
function add(id){
	let xml = new XMLHttpRequest();
	let url = "addwatchlist?id="+id;
	
	xml.open("get", url, true);
	xml.send();

	let x = document.getElementById("watchlist");
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				x.innerHTML = "rimuovi";
				x.setAttribute("onclick", "remove('"+id+"')");
				x.style.color = "blue";
			} else {
				x.innerHTML = "errore, riprova";
			} 
		}
	}
}

function remove(id){
	let xml = new XMLHttpRequest();
	let url = "removewatchlist?id="+id;
	
	xml.open("get", url, true);
	xml.send();

	let x = document.getElementById("watchlist");
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				x.innerHTML = "aggiungi";
				x.setAttribute("onclick", "add('"+id+"')");
				x.style.color = "red";
			} else {
				x.innerHTML = "errore, riprova";
			} 
		}
	}
}
</script>
</body>
</html>