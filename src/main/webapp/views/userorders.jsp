<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Your Orders</title>
</head>
<body>
<p><a href="<s:url action='logOut'/>">Logout</a></p>
<p><a href="<s:url action='viewCart'/>">View Cart</a></p>
<p><a href="<s:url action='getGrocery'/>">Back to Shopping</a></p>
<p><a href="<s:url action='viewOrderDetails'/>">Order Details</a></p>

<s:iterator value="userOrders" status="status">
	<s:property value="orderID"/> <s:property value="total"/>  <s:property value="deliverSpeed"/> <s:property value="deliveryDate"/> <s:property value="deliveryTime"/> <a href="<s:url action='viewOrderDetails'/>">View Details</a>
</s:iterator>

</body>
</html>