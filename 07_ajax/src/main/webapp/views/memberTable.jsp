<%@page import="java.util.Arrays"%>
<%@page import="com.web.member.model.dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html> -->
<%
	List<Member> members=(List<Member>)request.getAttribute("members");
%>
<table>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>성별</th>
		<th>나이</th>
		<th>주소</th>
		<th>이메일</th>
		<th>휴대폰</th>
		<th>취미</th>
		<th>가입일</th>
	</tr>
	<% if(members.isEmpty()){ %>
		<tr>
			<td colspan="9">조회된 회원이 없습니다</td>
		</tr>
	<%} else{
		for(Member m:members){%>
		<tr>
			<th><%=m.getUserId() %></th>
			<th><%=m.getUserName() %></th>
			<th><%=m.getGender() %></th>
			<th><%=m.getAge() %></th>
			<th><%=m.getAddress() %></th>
			<th><%=m.getEmail() %></th>
			<th><%=m.getPhone() %></th>
			<th><%=Arrays.toString(m.getHobby()) %></th>
			<th><%=m.getEnrollDate() %></th>
		</tr>
		
	<%} 
	}%>
</table>
<style>
	table, th{border: 1px ridge black; border-color:gray}
	
</style>