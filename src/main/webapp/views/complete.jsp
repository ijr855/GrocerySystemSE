<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Placed order successfully</h1>
	<p><a href="<s:url action='getGrocery'/>">Back to Shopping</a></p>
	<p><a href="<s:url action='logOut'/>">Logout</a></p>	
</body>
</html>