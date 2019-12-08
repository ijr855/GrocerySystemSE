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
	<h1>Review Your Order</h1>
	<s:form action="pay">
		<p>Pick the time of the delivery</p>
		<select name="time">
  			<option value="10AM">10AM-11AM</option>
  			<option value="11AM">11AM-12PM</option>
  			<option value="12PM">12PM-1PM</option>
  			<option value="1PM">1PM-2PM</option>
  			<option value="2PM">2PM-3PM</option>
  			<option value="3PM">3PM-4PM</option>
  			<option value="4PM">4PM-5PM</option>
  			<option value="5PM">5PM-6PM</option>
  			<option value="6PM">6PM-7PM</option>
  			<option value="7PM">7PM-8PM</option>
  			<option value="8PM">8PM-9PM</option>
		</select>
		<p>Delivery Options</p>
		<select name="delivery">
			<option value="standard">Standard Delivery - 2 days - FREE shipping</option>
			<option value="express">Express Delivery - 1 day - S25.00 - Shipping</option>		
		</select>
		<p>Payment Methods</p>
		<select name="payment">
			<option value="card">Master Card</option>
			<option value="cash">Cash</option>
		</select>
		<p> </p>
		<s:submit value="Place This Order"/>
	</s:form>
</body>
</html>