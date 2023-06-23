<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="category-tab">
	<ul>
		<li>
			<a href="<%=request.getContextPath()%>/about/about" 
			class="category-tab-a category-tab-about">오시는 길</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/about/travel"
			class="category-tab-a category-tab-travel">주변 여행지</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/about/teamBTC"
			class="category-tab-a category-tab-teamBTC">팀원 소개</a>
		</li>
	</ul>
</div>

<div class="about-title">
	<h3 class="text-center"><%=request.getAttribute("aboutTitle") %></h3>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/nara_publish/js/nara.js"></script>
<script>
$(function(){
	board_tab();
})
</script>