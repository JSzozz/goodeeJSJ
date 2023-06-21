<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="category-tab">
	<ul>
		<li>
			<a href="<%=request.getContextPath()%>/about/about.do" 
			class="category-tab-a category-tab-about">오시는 길</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/about/travel.do"
			class="category-tab-a category-tab-travel">주변 여행지</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/about/teamBTC.do"
			class="category-tab-a category-tab-teamBTC">팀원 소개</a>
		</li>
	</ul>
</div>

<div class="about-title">
	<h3 class="text-center"><%=request.getAttribute("aboutTitle") %></h3>
</div>

<script>
	const current_url = window.location.pathname;
	$(function() {
		$('.category-tab-a').removeClass('active');
		if (current_url.indexOf('travel') > -1) {
			$('.category-tab-travel').addClass('active');
		} else if (current_url.indexOf('teamBTC') > -1) {
			$('.category-tab-teamBTC').addClass('active');
		} else {
			$('.category-tab-about').addClass('active');
		}
	})
</script>