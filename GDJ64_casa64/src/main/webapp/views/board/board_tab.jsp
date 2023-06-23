<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="category-tab">
	<ul>
		<li>
			<a href="<%=request.getContextPath()%>/notice/insertNotice.do?title=COMMUNITY&name=공지사항"
			class="category-tab-a category-tab-notice">공지사항</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/notice/insertNotice.do?title=COMMUNITY&name=QnA"
			class="category-tab-a category-tab-qna">QnA</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/review/reviewList"
			class="category-tab-a category-tab-review">이용후기</a>
		</li>
	</ul>
</div>

<div class="cummunity-title">
	<!-- 파라미터로 받으면 사용자가 값을 수정할 수 있어서 어트리뷰트로 사용!!! (수정금지)-->
	<h3 class="text-center"><%=request.getAttribute("communityTitle") %></h3> 
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/nara_publish/js/nara.js"></script>
<script>
$(function(){
	board_tab();
})
</script>