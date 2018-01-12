<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="errore" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link rel="stylesheet" href="css/login_view.css" type="text/css">
	<%@include file="includes/_import.jsp" %>
</head>

<body>

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
<% if(!errore.equals("")){ %>
	<p><%=errore%></p>
<% }%>
<form action="checklogin" method="post">
	Username <input type="text" name="username"><br/>
	Password <input type="password" name="password"><br/>
	<input type="submit" value="Accedi">
</form>
</div>


</body>
</html>