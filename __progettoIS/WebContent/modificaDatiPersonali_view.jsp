<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="utente" class="applicationLogic.models.Utente" scope="session" />
<jsp:useBean id="errore" class="java.lang.String" scope="session"/>

<!DOCTYPE html>
<html>
<head>
	<title>Modifica i Dati Personali</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>
	<jsp:include page="includes/_header.jsp"/>
	
	<% if(errore != null){ %>
		<p><%=errore%></p>
	<% } errore = null; %>
	<form action="controllocambiodati" method="post">
		Nome: <input type="text" value="<%=utente.getNome()%>" onfocus="if (this.value=='<%=utente.getNome()%>') this.value = ''" name="nome"> <br/>
		Cognome: <input type="text" value="<%=utente.getCognome()%>" onfocus="if (this.value=='<%=utente.getCognome()%>') this.value = ''" name="cognome"> <br/>
		Username: <input type="text" value="<%=utente.getUsername()%>" onfocus="if (this.value=='<%=utente.getUsername()%>') this.value = ''" name="username"> <br/>
		Password: <input type="text" value="<%=utente.getPassword()%>" onfocus="if (this.value=='<%=utente.getPassword()%>') this.value = ''" name="password"> <br/>
		Email: <input type="text" value="<%=utente.getEmail()%>" onfocus="if (this.value=='<%=utente.getEmail()%>') this.value = ''" name="email"> <br/>
		<input type="submit" value="invia i dati">
	</form>
	
</body>
</html>