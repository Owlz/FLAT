<%@page import="applicationLogic.bean.FilmRemote"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="applicationLogic.bean.Voto, applicationLogic.bean.FilmLocal"%>
	<jsp:useBean id="recensione" scope="request" class="applicationLogic.bean.Recensione"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><%=recensione.getTitolo() %></title>
</head>
<body>
<%=((FilmLocal) recensione.getFilm()).getTitolo() %><br/>
<%=recensione.getTitolo() %> - <%=recensione.getVoto() %> - ID: <%=recensione.getId() %><br/>
TESTO : <%=recensione.getTesto() %><br/>
Voti <%= recensione.getVoti() %> <br/>
Segnala: <p onClick="segnala('<%=recensione.getId() %>')">Segnala recensione</p>

<script>
function segnala(id){
	let xml = new XMLHttpRequest();
	let url = "segnala?id=" + id;
	
	xml.open("get", url, true);
	xml.send();

	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ") {
				alert(xml.responseText);
			}else{
				alert(xml.responseText);	
			}
		}
	}
}
</script>
</body>
</html>