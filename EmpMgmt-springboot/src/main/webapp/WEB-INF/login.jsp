<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

</head>
<body>
<form action="login" method="post">
<input type="hidden" name="action" value="LOGIN" />

	<h2><%= request.getAttribute("msg") == null ? "" : request.getAttribute("msg") %></h2>
	User:   <input type="text" name="userName" value="mohit" /><br>
	Password: <input type="text" name="password"  value="mohitp" /><br>
	<br>
	<button type="submit" >LOGIN</button>
	
</form>

</body>
</html>