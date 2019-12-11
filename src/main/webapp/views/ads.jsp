<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Promotions</title>
</head>
<body>
	<p><a href="<s:url action='logOut'/>">Logout</a></p>
	<p><a href="<s:url action='goHome'/>">Return to homepage</a></p>
	<p><a href="<s:url action='getGrocery'/>">Grocery List</a>
	<p><a href="<s:url action='viewOrders'/>">Track your orders</a></p>
<pre>
<p>Disc. Code	ItemID	Name	Type	Amount</p>
<s:iterator value="promoList" status="status">
<s:property value="code"/>	<s:property value="itemID"/>	<s:property value="itemName"/>	 <s:property value="type"/> 	<s:property value="discount"/>
</s:iterator>
</pre>
</body>
</html>