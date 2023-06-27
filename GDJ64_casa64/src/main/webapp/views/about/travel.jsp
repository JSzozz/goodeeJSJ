<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/nara_publish/css/about.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/nara_publish/css/slidshowstyle.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/nara_publish/js/travel.js"></script>

<!-- 컨텐츠/내용 시작 -->
<section>
	<div id="wrap" class="container-fluid">
		<div class="container">
			<%@ include file="/views/about/about_tab.jsp"%>
			<div class="map">
				<div class="container">
					<div class="mySlides">
						<div class="numbertext">1 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/anmok.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">2 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/artemuseum.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">3 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/hasla.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">4 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/jungdongjin.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">5 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/gangmun.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">6 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/gyeongpo_lake.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">7 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/market.jpg"
							class="w-100">
					</div>

					<div class="mySlides">
						<div class="numbertext">8 / 8</div>
						<img
							src="<%=request.getContextPath()%>/nara_publish/images/travel/anbandegi.jpg"
							class="w-100">
					</div>

				
		            <a class="prev" onclick="plusSlides(-1)">❮</a>
		            <a class="next" onclick="plusSlides(1)">❯</a>

					<div class="caption-container">
						<p id="caption"></p>
					</div>

					<div class="row">
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/anmok.jpg"
								style="width: 100%" onclick="currentSlide(1)" alt="안목해변">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/artemuseum.jpg"
								style="width: 100%" onclick="currentSlide(2)" alt="아르떼 뮤지엄">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/hasla.jpg"
								style="width: 100%" onclick="currentSlide(3)" alt="하슬라 아트월드">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/jungdongjin.jpg"
								style="width: 100%" onclick="currentSlide(4)" alt="정동진">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/gangmun.jpg"
								style="width: 100%" onclick="currentSlide(5)" alt="강문해변">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/gyeongpo_lake.jpg"
								style="width: 100%" onclick="currentSlide(6)" alt="경포호">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/market.jpg"
								style="width: 100%" onclick="currentSlide(7)" alt="강릉 중앙시장">
						</div>
						<div class="column">
							<img class="demo cursor"
								src="<%=request.getContextPath()%>/nara_publish/images/travel/anbandegi.jpg"
								style="width: 100%" onclick="currentSlide(8)" alt="안반데기 은하수">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>