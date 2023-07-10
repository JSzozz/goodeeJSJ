<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 조회</title>
</head>
<body>
	
	<h2>31번 게시글 출력</h2>
	
	<c:if test="${empty boards }">
		<h2>조회된 게시글이 없습니다</h2>
	</c:if>
	
	<c:if test="${not empty boards }">
		<c:forEach var="b" items="${boards }">
			<ul>
				<li>게시글 번호 : ${b.boardNo }</li>
				<li>게시글 제목 : ${b.boardTitle }</li>
				<li>게시글 작성자 : ${b.boardWriter.username }</li>
				<li>작성자 이메일 : ${b.boardWriter.email }</li>
				<li>게시글 내용 : ${b.boardContent }</li>
			</ul>
			<c:if test="${not empty b.boardComments }">
				<c:forEach var="bc" items="${b.boardComments }">
					<table>
						<tr>
						<td>${bc.boardCommentNo }</td>
						<td>${bc.boardCommentWriter }</td>
						<td>${bc.boardCommentContent }</td>
						</tr>
					</table>
				</c:forEach>
			</c:if>
		</c:forEach>
	</c:if>
	


</body>
</html>