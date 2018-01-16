<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session" />

<!--       ********************      NavBar      ********************           -->
<nav id="nav">
	<div id="spazioLogo">
		<a onClick='href="home_view.jsp"'><img src="img/logo.jpg" height="50" alt="logo"></a>
	</div>
	
	<select class="js-example-basic-single" name="film" style="width: 300px;"></select>
	
	<ul>
		<% if(!utente.getRuolo().equals("visitatore")){ %>
			<li><a OnClick="openPopUp()" href="#">Logout</a></li>
			<li><a href="utente?id=<%= utente.getUsername() %>">Benvenuto <%= utente.getUsername() %></a></li>
		<% }else{  %>
			<li><a href="registrazione">Registrati</a></li>
			<li><a href="login">Login</a></li>
		<% } %>
	</ul>
</nav>

<div id="conferma">
	Vuoi veramente uscire?


</div>

<script>
function openPopUp() {
	
	var div_conferma = document.getElementById('conferma');

	div_conferma.style.display='block';
 	
};



</script>