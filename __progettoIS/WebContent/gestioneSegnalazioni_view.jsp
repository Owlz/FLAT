<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione"%>

<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaSegnalate"); %>
<!DOCTYPE html>
<html>
<head>
	<title>Gestione Segnalazioni</title>
	<%@include file="includes/_import.jsp"%></head>
<body>
	<%@include file="includes/_header.jsp"%></head>
	<% for(Recensione x: listaRec){ %>
		<div id="<%=x.getId() %>" style="background-color: red">
			<p><%=x.getTesto() %></p>
			<p>Voto del film: <%=x.getVoto() %></p>
			<p>Voti totali: <%=x.getVotiTotali() %></p>
			<p style="background-color: blue;" onClick="removeRecensione('<%=x.getId() %>')"> Rimuovi questa recensione </p>
			<p style="background-color: green;" onClick="removeSegnalazione('<%=x.getId() %>')"> Rimuovi questa segnalazione </p>
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