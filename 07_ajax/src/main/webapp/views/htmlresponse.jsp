<%@page import="com.ajax.model.vo.Actor"%>
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
	List<Actor> actors=(List<Actor>)request.getAttribute("actors");
%>
<table>
	<tr>
		<th>이름</th>
		<th>전화번호</th>
		<th>프로필</th>
	</tr>
	<% if(actors.isEmpty()){ %>
		<tr>
			<td colspan="3">조회된 배우가 없습니다</td>
		</tr>
	<%} else{
		for(Actor a:actors){%>
		<tr>
			<th><%=a.getName() %></th>
			<th><%=a.getPhone() %></th>
			<th>
				<img src="<%=request.getContextPath()%>/images/<%=a.getProfile() %>" width="100">
			</th>
		</tr>
		
	<%	} 
	}%>
</table>