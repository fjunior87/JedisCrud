<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User - ${user.firstName}</title>
</head>
<body>
	<ul>
		<li>${user.firstName }</li>
		<li>${user.lastName }</li>
		<li>${user.email }</li>
	</ul>

	<a href="UserController?action=edit&id=${user.id}">Edit</a>
	<a href="UserController?action=delete&id=${user.id}">Delete</a>
</body>
</html>