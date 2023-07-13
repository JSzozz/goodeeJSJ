<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=" "/>
</jsp:include>
<section id="content">

	<c:if test="${empty demos }">
		조회된 값이 없습니다
	</c:if>
	<c:if test="${not empty demos }">
		<table class="table">
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">나이</th>
				<th scope="col">이메일</th>
				<th scope="col">성별</th>
				<th scope="col">개발가능언어</th>
				<th scope="col">수정</th>
			</tr>
			<c:forEach var="d" items="${demos }">
				<tr>
					<td>${d.devNo }</td>
					<td>${d.devName }</td>
					<td>${d.devAge }</td>
					<td>${d.devEmail }</td>
					<td>${d.devGender }</td>
					<td>${Arrays.toString(d.devLang) }</td>
					<td>
						<form id="devFrm" method="post">
							<button type="button" class="col-sm-6 btn btn-outline-warning"
							onclick="updateByDevNO('demo/devUpdate.do?devNo=${d.devNo}')">
								수정하기
							</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>	
	</c:if>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<script>
	const updateByDevNO=(url)=>{
		$("#devFrm").attr("action","${path}/"+url);
		$("#devFrm").submit();
	}

</script>


</body>
</html>