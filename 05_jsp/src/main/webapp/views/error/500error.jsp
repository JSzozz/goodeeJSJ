<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500error.jsp</title>
</head>
<body>
	<h3 style="color:red"> 500error 발생</h3>
	<p><%=exception.getMessage() %></p>
	<!-- isErrorPage="true" 속성값 변경 필요! -->
	<!-- Cannot invoke "String.length()" because "name" is null -->
</body>
</html>