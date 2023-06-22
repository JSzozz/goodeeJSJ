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
			<a href="<%=request.getContextPath()%>/notice/insertNotice.do?title=COMMUNITY&name=이용 후기"
			class="category-tab-a category-tab-review">이용후기</a>
		</li>
	</ul>
</div>

<div class="cummunity-title">
	<h3 class="text-center"><%=request.getAttribute("communityTitle") %></h3>
</div>

<script>
	const current_url = window.location.pathname;
	console.log($(".text-center").text());
	$(function() {
		$('.category-tab-a').removeClass('active');
		if (current_url.indexOf('공지사항') > -1) {
			$('.category-tab-notice').addClass('active');
		} else if (current_url.indexOf('QnA') > -1) {
			$('.category-tab-qna').addClass('active');
		} else {
			$('.category-tab-review').addClass('active');
		}
	}) 
</script>