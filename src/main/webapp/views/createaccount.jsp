<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Creation Page</title>
</head>
<body>
	<h1>Create Account</h1>
	<s:form action="createAccount">
			<s:textfield type="text" name="firstName" label="First Name"/>
			<s:textfield type="text" name="lastName" label="Last Name"/>
			<s:textfield type="text" name="addr" label="Address"/>
			<s:textfield type="text" name="payment" label="Credit Card Number"/>
			<s:textfield type="text" name="crn" label="CVV"/>
			<s:textfield type="text" name="uname" label="Username"/>
			<s:textfield type="password" name="pword" label="Password"/>
			<s:submit value="Create Account"/>
	</s:form>
    <p><a href="<s:url action='selectlogin'/>">Login</a></p>	
</body>
</html>