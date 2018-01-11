<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Homepage</title>
	<%@include file="includes/_import.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>
	
	<link rel="stylesheet" href="home_view.css" type="text/css">
</head>
<body>
<jsp:include page="includes/_header.jsp"/>

<!--       ********************      NavBar      ********************           -->
<nav id="nav">
	<div id="spazioLogo">
		<img src="img/logo.jpg" height="50" alt="logo">
	</div>
	
	<select class="js-example-basic-single" name="film" style="width: 300px;"></select>
	
	<ul>
		<li><a href="registrazione">Registrati</a></li>
		<li><a href="login">Login</a></li>
	</ul>
</nav>

<!--       ********************      Container     ********************           -->

<div id="container">

<!-- 		<main class="wrapper" id="wrapper"> -->
<!-- 			<div class="griglia"> -->
			
<%--  			<% for(Prodotto x: prodotti){ %> --%>
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

<script type="text/javascript">
function formatFilm (film) {
    var baseUrl = "http://image.tmdb.org/t/p/w45";
    var $film = $(
      '<span><img src="' + baseUrl +  film.locandina + '" class="img-flag" /> ' + film.titolo + '</span>'
    );
    return $film;
  };

  $(".js-example-basic-single")
	.select2({
		ajax: {
			url: "ricerca",
			dataType: 'json',
			delay: 250,
			data: function(params) {
				return {
					query: params.term
				};
			},
			processResults: function(data, params) {
				return {
					results: data.results
				};
			},
			cache: true
		},
		placeholder: 'Cerca un film',
		escapeMarkup: function(markup) {
			return markup;
		},
		minimumInputLength: 2,
		templateResult: formatFilm
	})
	.on("select2:selecting", function(e) {
		window.location.href = "film?id=" + e.params.args.data.id;
	});
</script>
</body>
</html>