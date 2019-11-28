<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
  </head>
  <body>
    <h1>Welcome to the grocery system</h1>
    <p><a href="<s:url action='selectlogin'/>">Login</a></p>
    <p><a href="<s:url action='selectcreate'/>">Create Account</a></p>
  </body>
</html>