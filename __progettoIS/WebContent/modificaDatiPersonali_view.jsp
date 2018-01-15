<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="utente" class="applicationLogic.bean.Utente" scope="session" />
<jsp:useBean id="errore" class="java.lang.String" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Modifica Dati</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/modificaDatiPersonali_view.css" type="text/css">
</head>

<body>
<!--       ********************      NavBar      ********************           -->
<jsp:include page="includes/_header.jsp"/>
	
<!--       ********************      Container     ********************           -->
<div id="container">
	<% if(errore != null){ %>
		<p><%=errore%></p>
	<% } errore = null; %>
	<form action="controllocambiodati" method="post">
		Nome: <input type="text" value="<%=utente.getNome()%>" onfocus="if (this.value=='<%=utente.getNome()%>') this.value = ''" name="nome"> <br/>
		Cognome: <input type="text" value="<%=utente.getCognome()%>" onfocus="if (this.value=='<%=utente.getCognome()%>') this.value = ''" name="cognome"> <br/>
		Password: <input type="text" value="<%=utente.getPassword()%>" onfocus="if (this.value=='<%=utente.getPassword()%>') this.value = ''" name="password"> <br/>
		Email: <input type="text" value="<%=utente.getEmail()%>" onfocus="if (this.value=='<%=utente.getEmail()%>') this.value = ''" name="email"> <br/>
		<p> Modifica le informazioni che vorresti cambiare e clicca su "Conferma", altrimenti su "Annulla". </p>
		<input type="submit" value="Annulla">
		<input type="submit" value="Conferma">
	</form>
</div>
	
</body>
</html>