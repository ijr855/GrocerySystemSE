<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Order</title>
</head>
<body>
	<p><a href="<s:url action='logOut'/>">Logout</a></p>
	<p><a href="<s:url action='viewCart'/>">Back to Cart</a></p>
	<p><a href="<s:url action='getGrocery'/>">Back to Shopping</a>
	<h1>Review Your Order</h1>
	<s:form action="confirmOrder">
		<s:select label="Pick the time of the delivery" list="deliveryTimes" name="selectedTime"/>
		<s:radio label="Delivery Options" list="deliveryOptions" name="selectedDelivery"/>
		<s:submit value="Confirm order"/>
	</s:form>
	<p><s:property value="#selectedDeliveryTime"/></p>
</body>
</html>