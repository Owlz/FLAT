<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="errore" class="java.lang.String" scope="request"/>

<!DOCTYPE html>
<html>

<head>
	<title>Login</title>
	<link rel="stylesheet" href="css/nav.css" type="text/css">
	<link rel="stylesheet" href="css/login_view.css" type="text/css">
</head>

<body>
<!--       ********************      NavBar      ********************           -->

<jsp:include page="includes/_header.jsp"/>

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


<jsp:include page="includes/_import.jsp"/>
</body>
</html>