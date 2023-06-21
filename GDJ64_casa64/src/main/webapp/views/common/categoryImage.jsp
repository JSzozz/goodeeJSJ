<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="category-image">
	<img src="<%=request.getContextPath()%>/nara_publish/images/community.png"
		width="100%" height="200px">
	<div><%=request.getAttribute("categoryName") %></div>
</div>