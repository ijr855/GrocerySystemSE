<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Order</title>
<style>
white-space: pre;
</style>
</head>
<body>
<p><a href="<s:url action='logOut'/>">Logout</a></p>
<p><a href="<s:url action='viewCart'/>">View Cart</a></p>
<p><a href="<s:url action='getGrocery'/>">Back to Shopping</a>
<p><a href="<s:url action='viewOrders'/>">Back to your orders</a></p>

<pre>
<p>Order ID:       <s:property value="selectedOrderID"/></p>
<p>Order Total:    <s:property value="selectedTotal"/></p>
<p>Delivery Speed: <s:property value="selectedDeliverSpeed"/></p>
<p>Delivery Date:  <s:property value="selectedDeliveryDate"/></p>
<p>Delivery Time:  <s:property value="selectedDeliveryTime"/></p>
<p>Category	Item ID		Item Name	Price	Quantity</p>
<s:iterator value="orderItems" status="status">	
<s:property value="category"/>		<s:property value="ID"/>		<s:property value="name"/>		<s:property value="price"/>	<s:property value="qt"/>
	<br>
</s:iterator>
</pre>
</body>
</html>