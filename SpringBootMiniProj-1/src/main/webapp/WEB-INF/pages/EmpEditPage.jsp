<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>EMPLOYEE EDIT PAGE</h3>
<form:form action="update" method="POST" modelAttribute="employee">
<pre>
ID  : <form:input path="eid" readonly="true"/>
NAME: <form:input path="ename"/>
MAIL: <form:input path="email"/>
SAL : <form:input path="esal"/>
DEPT: <form:select path="dept">
		<form:option value="">-SELECT-</form:option>
		<form:option value="DEV">DEV</form:option>
		<form:option value="QA">QA</form:option>
		<form:option value="BA">BA</form:option>
	</form:select>
	<input type="submit" value="Update"/><a href="/SpringBootMiniProj-1/all"><input type="button" value="Back"/></a><a href="/SpringBootMiniProj-1/"><input type="button" value="Home"/></a>
	
	
	
	
</pre>
</form:form>
</body>
</html>