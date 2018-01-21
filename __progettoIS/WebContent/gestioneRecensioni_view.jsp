<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione"%>

<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaRec"); %>
<!DOCTYPE html>
<html>

<head>
	<title>Gestione Recensioni</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/gestioneRecensioni_view.css" type="text/css">
</head>

<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>

<!--       ********************      Container     ********************           -->
<div id="container">
	<h2>Lista Recensioni:</h2>
	
	<div id="ordina">
		<h4>Ordina: </h4>
		<select>
			<option>Per ora non funzionano, per provare vai su "/mostrarecensioni?ord=data|segn"</option>
			<option>Ordina per data (nuove prima, vecchie dopo)</option>
			<option>Ordina per segnalazioni (segnalate prima)</option>
		</select>
	</div>
	<% for(Recensione x: listaRec) { %>
		<div class="elemento" id="<%=x.getId()%>">
			<div class="corpo">
				<h4><i>Titolo:</i> "<%=x.getTitolo() %>"</h4>
				<p><i style="font-weight: bold">Testo:</i> <%=x.getTesto() %></p>
			</div>
			<div class="voti">
				<p><i style="font-weight: bold">Voto del film:</i> <%=x.getVoto() %></p>
				<p><i style="font-weight: bold">Somma voti utenti:</i> <%=x.getVotiTotali() %></p>
			</div>
			<div class="bottoni">
				<button onClick="removeRecensione('<%=x.getId() %>')">Rimuovi Recensione</button>
			</div>
		</div>
	<% } %>
	
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
			}else{
				let x = document.getElementById(id);
				x.innerHTML = "errore";
			}
		}
	}
}
</script>
</body>
</html>