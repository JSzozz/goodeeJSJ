<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="board"/>
</jsp:include>
<section>
	
	<section id="board-container" class="container">
		<c:if test="${empty boards }">
			<h2>조횐된 게시글잉 없습니다.</h2>
		</c:if>
		<c:if test="${not empty boards }">
	        <p>총 ${totalData }건의 게시물이 있습니다.</p>
	        
	        <table id="tbl-board" class="table table-striped table-hover">
	            <tr>
	                <th>번호</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>작성일</th>
	                <th>첨부파일</th>
	                <th>조회수</th>
	            </tr>
	            <c:forEach var="b" items="${boards }">
		            <tr>
		            	<td>
			            	${b.boardNo }
		            	</td>
		            	<td>
			            	<a href="${path }/board/boardView.do?no=${b.boardNo}">
			            	${b.boardTitle }
			            	</a>
		            	</td>
		            	<td>${b.boardWriter }</td>
		            	<td>${b.boardDate }</td>
		            	<td>첨부파일</td>
		            	<td>${b.boardReadCount }</td>
					</tr>
	            </c:forEach>
	        </table> 
	        <c:out value="${pageBar }" escapeXml="false"/>
		</c:if>
	</section>
	
<style>
	div#board-container{width:400px; margin:0 auto; text-align:center;}
	div#board-container input{margin-bottom:15px;}
</style>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>