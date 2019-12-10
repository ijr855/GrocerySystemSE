<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Grocery Items</title>
</head>
<body>

<s:set var="hasCart" value="hasCart"/>

<p><a href="<s:url action='logOut'/>">Logout</a></p>
<s:if test="%{#hasCart==true}">
	<p><a href="<s:url action='viewCart'/>">View Cart</a></p>
</s:if>
<p><a href="<s:url action='viewOrders'/>">Track your orders</a></p>
<s:form action="getGrocery">
	<s:textfield type="text" name="searchVar" label="Search Items by Name"/> <s:submit value="Search"/>
</s:form>
<p> ID    Name    Category    Price    Quantity </p>


<s:iterator value="products" status="status">
	<form action="addToCart">
		<s:hidden name="selectedID" value="%{ID}"/><s:property value="ID"/> <s:hidden name="selectedItem" value="%{Name}"/> <s:property value="Name"/>    <s:hidden name="selectedCategory" value="%{cate}"/> <s:property value="cate"/>   <s:hidden name="selectedPrice" value="%{price}"/>   <s:property value="price"/>  <s:hidden name="selectedQuantity" value="1"/>  <s:property value="qt"/>    <s:submit value="Add to Cart"/>
	<br>
	</form>
</s:iterator>


</body>
</html>