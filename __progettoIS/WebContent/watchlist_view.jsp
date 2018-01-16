<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="applicationLogic.bean.FilmLocal, java.util.*"%>
    
    <jsp:useBean id="film" scope="request" class="applicationLogic.bean.FilmRemote"/>
	<% ArrayList<FilmLocal> watchlist = (ArrayList<FilmLocal>) request.getAttribute("watchlist"); %>

<!DOCTYPE html>
<html>

<head>
	<title>La Tua Watchlist</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/watchlist_view.css" type="text/css">
</head>

<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>


<!--       ********************      Container     ********************           -->
<div id="container">
	<h2>La Tua Watchlist:</h2>
	
	<ul>
		<% for(FilmLocal x: watchlist){ %>
			<li><div class="elemento">
				<img width="150px" src="http://image.tmdb.org/t/p/w500<%=x.getLocandina() %>">
				<h4><%=x.getTitolo()%></h4>
				<button onClick="remove('<%=x.getId() %>',this)">Rimuovi dalla watchlist</button>
			</div></li>
		<%} %>		
	</ul>
</div>
	
	
<jsp:include page="includes/_import.jsp"/>
<script>
function remove(id, elemento){
	let xml = new XMLHttpRequest();
	let url = "removewatchlist?id="+id;
	
	xml.open("get", url, true);
	xml.send();
	
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			if(xml.responseText === "succ"){
				elemento.parentNode.parentNode.remove();
			} else {
				elemento.innerHTML = "errore, riprova";
			} 
		}
	}
}
</script>
</body>
</html>