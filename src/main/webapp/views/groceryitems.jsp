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
<p>The items are displayed here</p>
<p> Name    Category    Price    Quantity </p>


<s:iterator value="products" status="status">
	<form action="addToCart">
	<s:hidden name="products" value="%{#products}"/> <s:hidden name="selectedID" value="%{ID}"/>
	<s:hidden name="selectedItem" value="%{Name}"/> <s:property value="Name"/>    <s:hidden name="selectedCategory" value="%{cate}"/> <s:property value="cate"/>   <s:hidden name="selectedPrice" value="%{price}"/>   <s:property value="price"/>  <s:hidden name="selectedQuantity" value="1"/>  <s:property value="quan"/>    <s:submit value="Add to Cart"/>
	<br>
	</form>
</s:iterator>


</body>
</html>