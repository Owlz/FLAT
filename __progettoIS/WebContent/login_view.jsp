<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<%@include file="includes/_import.jsp" %>
</head>
<body>

<form action="checklogin" method="post">
	Username <input type="text" name="username"><br/>
	Password <input type="password" name="password">
	<input type="submit" value="invia i dati">
</form>

</body>
</html>