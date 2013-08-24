<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User - ${user.firstName}</title>
</head>
<body>
	
	<form action="UserController" method="post">
		<input type="hidden" name="id" value="${user.id}"/>
		<input type="hidden" name="action" value="${empty user? 'create' : 'update'}"/>
		<ul>
			<li>First Name: <input type="text" name="firstName" value="${user.firstName}"/></li>
			<li>Last Name: <input type="text" name="lastName" value="${user.lastName}"/></li>
			<li>Email: <input type="text" name="email" value="${user.email}"/></li>
			<li>Gender: <input type="text" name="gender" value="${user.gender}"/></li>
		</ul>
		<input type="submit" value="Submit"/>
	</form>
	
</body>
</html>