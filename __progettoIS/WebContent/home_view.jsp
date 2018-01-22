<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione, applicationLogic.bean.FilmLocal, applicationLogic.bean.Utente"%>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaRec"); %>
<% ArrayList<FilmLocal> listaLoc = (ArrayList<FilmLocal>) request.getAttribute("listaLoc");%>

<!DOCTYPE html>
<html>
<head>
	<title>Homepage</title>
	<%@include file="includes/_import.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/home_view.css" type="text/css">
</head>
<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>


<!--       ********************      Container     ********************           -->

<div id="container">

	<main class="wrapper" id="wrapper">
		<div class="griglia">
		
		<% for(FilmLocal x: listaLoc){ %>
			<div class="cella" onClick=" location.href='film?id=<%=x.getId()%>'">
				<figure>
					<img src="http://image.tmdb.org/t/p/w500<%=x.getLocandina()%>" alt="<%=x.getTitolo()%>">
					<figcaption class="figcaption">
						<a href="film?id=<%= x.getId() %>"><%=x.getTitolo()%></a>
					</figcaption>
				</figure>
			</div>
		<%} %>
		
		</div>
	</main>
	
	
	
	<div id="aside">
		
		<div id="aside_title">
			<h4>Ultime recensioni inserite: </h4>
		</div>
		
		<% for(Recensione y: listaRec){ %>
			<div class="boxRecensione" id="rec--<%=y.getUtente().getUsername() %>">
				<h3>Film: <i><%=((FilmLocal) y.getFilm()).getTitolo()%></i></h3>
				<h3>Titolo: <i><%=y.getTitolo() %></i>
				<br/>
				( Voto: <i><%=y.getVoto() %></i> - Autore: <i><%=y.getUtente().getUsername() %></i> )</h3>
				<p id="testo">
					<% if (y.getTesto().length() > 250) {
						out.print(y.getTesto().substring(0,250)+ " ...");
					} else {
						out.print(y.getTesto());
					}
					%>
				</p>
				<button class="moreButton" onClick="location.href='recensione?id=<%=y.getId()%>'">[Visualizza altro...]</button>
			</div>
		<%} %>
	</div>
	
</div>

<jsp:include page="includes/_import.jsp"/>
</body>
</html>