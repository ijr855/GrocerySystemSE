<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grocery System Home</title>
</head>
<body>
	<h1>Soon you will be able to purchase items here!</h1>
	<p>Hello user ID <s:property value="#session.currentUserID" />!</p>
	<p><a href="<s:url action='logOut'/>">Logout</a></p>
	<p><a href="<s:url action='getGrocery'/>">Grocery List</a>
	<p><a href="<s:url action='viewOrders'/>">Track your orders</a></p>
</body>
</html>