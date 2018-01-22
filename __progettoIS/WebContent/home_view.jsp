<%@ page language="java" contentType="text/html; charset=UTF-8" 
 pageEncoding="UTF-8" import="java.util.ArrayList, applicationLogic.bean.Recensione, applicationLogic.bean.FilmLocal, applicationLogic.bean.Utente, applicationLogic.bean.FilmLocal"%>
<% ArrayList<Recensione> listaRec = (ArrayList<Recensione>) request.getAttribute("listaRec"); %>

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

<!-- 		<main class="wrapper" id="wrapper"> -->
<!-- 			<div class="griglia"> -->
			
<%--  			<% for(Recensione x: listaRec){ %> --%>
<%-- 			<div class="cella" onClick=" location.href='Item?id='+'<%= x.getId() %>'"> --%>
<!-- 				<figure> -->
<%-- 					<img src="img/prodotti/<%=x.getImmagine() %>" alt="<%= x.getNome() %>"> --%>
<!-- 					<figcaption class="figcaption"> -->
<%-- 						<h3><%= x.getNome() %></h3> --%>
<%-- 						<a onClick="AggiungiAlCarrello('<%= x.getId() %>')">Aggiungi</a> --%>
<!-- 					</figcaption> -->
<!-- 				</figure> -->
<!-- 			</div> -->
<%-- 			<%} %> --%>
			
<!-- 			</div> -->
<!-- 		</main> -->
	
	<main class="wrapper" id="wrapper">
		<div class="griglia">
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
				
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
				
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
			<div class="cella" onClick="">
				<figure>
					<img src="img/esempio.jpg">
					<figcaption class="figcaption">
						<h3>Nome film</h3>
						<a onClick="">Watch</a>
					</figcaption>
				</figure>
			</div>
			
		</div>
	</main>
	
	
	
	<div id="aside">
		
		<div class="aside_title">
			<h4>Ultime recensioni</h4>
		</div>
		
		<div class="aside_box">
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Praesent aliquam sapien non congue lobortis. Sed est tellus,
				faucibus ac vestibulum in, posuere vel orci.</p>
			<img alt="esempio" src="img/esempio.jpg" id="esempio" width="100" height="100">
		</div>

		<div class="aside_box">
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Praesent aliquam sapien non congue lobortis. Sed est tellus,
				faucibus ac vestibulum in, posuere vel orci.</p>
			<img alt="esempio" src="img/esempio.jpg" id="esempio" width="100" height="100">
		</div>

		<div class="aside_box">
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
				Praesent aliquam sapien non congue lobortis. Sed est tellus,
				faucibus ac vestibulum in, posuere vel orci.</p>
			<img alt="esempio" src="img/esempio.jpg" id="esempio" width="100" height="100">
		</div>
	</div>
	
</div>

<jsp:include page="includes/_import.jsp"/>
</body>
</html>