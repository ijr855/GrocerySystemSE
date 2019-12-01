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
	<form action="process.php" method="POST">
		<div>
			<label>First Name</label> <input type="text" name="firstName"
				placeholder="Enter first name">
		</div>
		<br>
		<div>
			<label>Last Name</label> <input type="text" name="lastName">
		</div>
		<br>
		<div>
			<label>Username</label> <input type="text" name="username">
		</div>
		<br>
		<div>
			<label>Email</label> <input type="email" name="email">
		</div>
		<br>
		<div>
			<label>Gender</label> <select name="gender">
				<option value="male">Male</option>
				<option value="female">Female</option>
				<option value="other">Other</option> </select>
		</div>
		<br>
		<div>
			<label>Birthday</label> <input type="date" name="birthday">
		</div>
		<br> <input type="submit" name="submit" value="Submit">
	</form>

</body>
</html>