<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<h1>Please Enter Your Username and Password</h1>
	<p>I've said hello to you <s:property value="#session.helloCount" /> times!</p>
	<s:form action="attemptLogin">
		<s:textfield name="username" label="Username"/>
		<s:password name="password" label="Password"/>
		<s:submit value="Login"/>
	</s:form>
</body>
</html>