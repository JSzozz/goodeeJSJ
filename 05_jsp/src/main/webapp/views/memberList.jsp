<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.jsp.model.dto.MemberDTO"  %>   
<%
	List<MemberDTO> members=(List)request.getAttribute("members");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%-- <%=request.getAttribute("members") %> --%>
	<!-- 
		검색 ; 주소, 서블릿(마음대로)
		searchmember.jsp 조회된 데이터 출력
		이름을 기준으로 부분검색 가능하게
	 -->
	<h3>회원조회결과</h3>
	
	<div id="searchcontainer">
    	<form action="<%=request.getContextPath()%>/memberByName.do" ><!--method=post로 안했음!  -->
			<input type="text" name="keyword" placeholder="검색할 이름을 작성하세요">
			<input type="submit" value="검색">
		</form>
	</div>
	
	
	<div id="result-container">
		<table>
			<tr>
				<th>아이디</th>
				<th>패스워드</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>취미</th>
				<th>가입일</th>
			</tr>
			<% if(members.isEmpty()){%>
				<tr>
					<td colspan="10">조회된 회원이 없습니다.
				</tr>
			<%}else{
				for(MemberDTO m:members) {
			%>
				<tr>
					<td><%= m.getMemberId()%></td>
					<td><%= m.getMemberPwd()%></td>
					<td><%= m.getMemberName()%></td>
					<td><%= m.getAge()%></td>
					<td><%= m.getGender()%></td>
					<td><%= m.getEmail()%></td>
					<td><%= m.getPhone()%></td>
					<td><%= m.getAddress()%></td>
					<td><%= m.getHobby()%></td>
					<td><%= m.getEnrollDate()%></td>
				</tr>				
				<%}
			}%>
		</table>
	</div>
</body>
</html>