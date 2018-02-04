<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session" />

<nav id="nav">
	<div id="spazioLogo">
		<a href="home_view.jsp"><img src="img/logo.png" height="50" alt="logo"></a>
	</div>
	
	<select class="js-example-basic-single" name="film" style="width: 620px;"></select>
	
	<ul>
		<% if(!utente.getRuolo().equals("visitatore")){ %>
			<li><a OnClick="openPopUp()">Logout</a></li>
			<li><a href="utente?id=<%= utente.getUsername() %>">Benvenuto <%= utente.getUsername() %></a></li>
		<% }else{  %>
			<li><a href="registrazione">Registrati</a></li>
			<li><a href="login">Login</a></li>
		<% } %>
	</ul>
</nav>

<div id="popUPConferma">
	<button id="close" OnClick="closePopUp()">X</button>
	
	<p>Stai per effettuare il Logout</p>

	<button id="ok" OnClick=" location.href='logout'">Conferma</button>

</div>

<script>
function openPopUp() {
	
	var div_conferma = document.getElementById('popUPConferma');
	div_conferma.style.display='flex';
 	
};

function closePopUp() {
	
	var div_conferma = document.getElementById('popUPConferma');
	div_conferma.style.display='none';
 	
};



</script>