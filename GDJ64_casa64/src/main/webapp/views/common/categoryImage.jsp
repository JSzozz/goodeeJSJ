<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="category-image">
	<img src="<%=request.getContextPath()%>/nara_publish/images/community.png"
		width="100%" height="200px">
	<!-- 파라미터로 받으면 사용자가 값을 수정할 수 있어서 어트리뷰트로 사용(수정금지)-->
	<div><%=request.getAttribute("categoryName") %></div>
</div>