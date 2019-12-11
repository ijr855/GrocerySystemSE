<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
<p><a href="<s:url action='logOut'/>">Logout</a></p>
<p><a href="<s:url action='goHome'/>">Return to homepage</a></p>
<p><a href="<s:url action='getGrocery'/>">Back to Shopping</a>
<p><a href="<s:url action='checkOut'/>">Proceed to checkout</a></p>

<s:iterator value="cart" status="status">
	<form action="subtractItem">
		<s:hidden name="selectedID" value="%{ID}"/><s:property value="ID"/> <s:property value="name"/>  <s:property value="category"/> <s:property value="price"/> <s:property value="qt"/> <s:submit value="-1 of This Item"/>
	<br>
	</form>
</s:iterator>
</body>
</html>