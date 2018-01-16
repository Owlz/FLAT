<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione"%>

<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaRec"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Gestione Recensioni</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
	<%@include file="includes/_header.jsp" %>
	<select>
		<option>Per ora non funzionano, per provare vai su "/mostrarecensioni?ord=data|segn"</option>
		<option>Ordina per data (nuove prima, vecchie dopo)</option>
		<option>Ordina per segnalazioni (segnalate prima)</option>
	</select>
	<% for(Recensione x: listaRec) { %>
		<div id="<%=x.getId() %>" style="backgound-color: red;">
		<h2>Titolo: <%=x.getTitolo() %></h2>
		<p> Testo: <%=x.getTesto() %></p>
		<p style="color:green;" onClick="removeRecensione('<%=x.getId() %>')">Rimuovi recensione</p>
		</div>
	<% } %>
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