<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이징처리</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<center><h1>학생조회결과</h1></center>
	<br>
	<c:if test="${empty students }">
		<h3>조회된 학생이 없습니다</h3>
	</c:if>
	<c:if test="${not empty students }">
		<table class="table">
			<tr>
				<th>학생번호</th>
				<th>학생이름</th>
				<th>학생전화번호</th>
				<th>학생주소</th>
				<th>학생이메일</th>
				<th>학생등록일</th>
			</tr>
			<c:forEach var="s" items="${students }">
				<tr>
				
					<td><c:out value="${s.studentNo }"></c:out></td>
					<td><c:out value="${s.studentName }"></c:out></td>
					<td><c:out value="${s.studentTel }"></c:out></td>
					<td><c:out value="${s.studentAddress }"></c:out></td>
					<td><c:out value="${s.studentEmail }"></c:out></td>
					<td><c:out value="${s.reg_date }"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		<center><c:out value="${pageBar }" escapeXml="false"></c:out></center>
	</c:if>
	
	
</body>
</html>