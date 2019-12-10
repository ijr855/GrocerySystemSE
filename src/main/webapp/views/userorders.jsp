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

<s:iterator value="userOrders" status="status">
	<s:form action="viewOrderDetails">
		<s:hidden name="selectedOrderID" value="%{orderID}"/> <s:property value="orderID"/> <s:hidden name="selectedTotal" value="%{total}"/> <s:property value="total"/> <s:hidden name="selectedDeliverSpeed" value="%{deliverSpeed}"/> <s:property value="deliverSpeed"/> <s:hidden name="selectedDeliveryDate" value="%{deliveryDate}"/> <s:property value="deliveryDate"/> <s:hidden name="selectedDeliveryTime" value="%{deliveryTime}"/> <s:property value="deliveryTime"/>     <s:submit value="Track this order"/>
	</s:form>
</s:iterator>

</body>
</html>