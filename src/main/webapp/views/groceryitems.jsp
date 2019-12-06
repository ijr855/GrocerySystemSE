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
	<s:property value="Name"/>          <s:property value="cate"/>          <s:property value="price"/>          <s:property value="quan"/>
	<br>
	
</s:iterator>
</body>
</html>