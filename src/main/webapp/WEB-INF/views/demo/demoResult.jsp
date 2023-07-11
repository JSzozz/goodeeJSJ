<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과페이지</title>

<style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}
</style>

</head>
<body>

<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<section id="content">
	<c:if test="${empty demo }">
		<h3>조회된 demo가 없습니다</h3>
	</c:if>
	<c:if test="${not empty demo }">
		<table class="table" id="tbl-dev">
			<tr>
				<th scope="col">이름</th>
				<td><c:out value="${demo.devName }"/> </td>
			<tr>
			<tr>
				<th>나이</th>
				<td><c:out value="${demo.devAge }"/></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><c:out value="${demo.devEmail }"/></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><c:out value="${demo.devGender }"/></td>
			</tr>
			<tr>
				<th>개발가능언어</th>
				<td>
					<ul>
						<c:forEach var="d" items="${demo.devLang }" >
							<li>
								${d }
							</li>
						</c:forEach>
					</ul>
				</td>
			</tr>
		</table>
	</c:if>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>