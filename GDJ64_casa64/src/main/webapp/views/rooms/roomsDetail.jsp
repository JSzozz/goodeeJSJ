<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<section>
	<div class="category-image">
        <!-- 이미지에 rooms새겨진 최상단 화면 -->
        <img src="<%=request.getContextPath() %>/images/rooms.png" width="100%" height="200px">
        <div>ROOMS</div><!-- 클릭된 객실의 이름 -->
    </div>
    <br><br><br><br>
	<h2 class="text-center"><strong>Ocean Terrace 101</strong></h2>
	<br><br>
	<div class="row">
		<!-- 이미지로 버튼만들어서 클릭하면 아래 사진위의버튼이 트리거 되도록 -->
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="0"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/01.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="1"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/02.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="2"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/03.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="3"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/04.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="4"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/05.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="5"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/06.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="6"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/07.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="7"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/08.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="8"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/09.jpg"></button>
		<button class="mini sm-btn" data-bs-target="#rooms-carousel" data-bs-slide-to="9"><img class="mini" alt="" src="<%=request.getContextPath() %>/images/ocean1/10.jpg"></button>
	</div>
	<style>
		.mini{
 			width:190px;
			height:100px;	
		}
		.sm-btn{
			border:none;
		}
	</style>
	<br>
	<div id="rooms-carousel" class="carousel slide">
		<div class="carousel-indicators row">
		<!-- 슬라이드 아래부분 버튼으로 사진이동 가능하도록 -->			
			<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="3" aria-label="Slide 4"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="4" aria-label="Slide 5"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="5" aria-label="Slide 6"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="6" aria-label="Slide 7"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="7" aria-label="Slide 8"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="8" aria-label="Slide 9"></button>
    		<button type="button" data-bs-target="#rooms-carousel" data-bs-slide-to="9" aria-label="Slide 10"></button>
		</div>
	
  		<div class="carousel-inner">
  		<!-- 어차피 이미지는 DB에서 for문 돌려서 불러올거잖아~? -->
    		<div class="carousel-item active">
      			<img src="<%=request.getContextPath() %>/images/ocean1/01.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/02.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/03.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/04.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/05.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/06.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/07.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/08.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/09.jpg" class="d-block w-100" alt="..." width="80%">
    		</div>
    		<div class="carousel-item">
      			<img src="<%=request.getContextPath() %>/images/ocean1/10.jpg" class="d-block w-100" alt="..." width="80%"> 
    		</div>
  		</div>
  		
  		<button class="carousel-control-prev" type="button" data-bs-target="#rooms-carousel" data-bs-slide="prev">
    		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    		<span class="visually-hidden">Previous</span>
  		</button>
  		<button class="carousel-control-next" type="button" data-bs-target="#rooms-carousel" data-bs-slide="next">
    		<span class="carousel-control-next-icon" aria-hidden="true"></span>
    		<span class="visually-hidden">Next</span>
  		</button>
	</div>
	<br><br><br><br>
	<div class="row">
		<div class="col-2"></div>
		<div class="col justify-content-md-center">
		<!-- 객실 상세 소개, 좌우 영역 박스. 왼쪽은 시설 설명 오른쪽은 룸 레이아웃 -->
				<dl class="row p-5">
				<!-- db의 ROOMS에서 가져와서 출력 -->
  					<dt class="col-sm-3">객실 안내</dt>
  					<dd class="col-sm-9">
    					<dl class="row">
      						<dd class="col-sm-4">기준 인원 (00)명</dt>
      						<dd class="col-sm-8">최대 인원 (00)명</dt>

    					</dl>
  					</dd>

  					<dt class="col-sm-3">기본 옵션</dt>
  					<dd class="col-sm-9">
    					<p>DB에 저장된 기본옵션 ","를 기준으로 가져온다</p>
  					</dd>

  					<dt class="col-sm-3">객실 정보</dt>
  					<dd class="col-sm-9">푸른 동해가 마주 보이는 탁 트인 전망에 도시에서의 복잡함은 잊힐거에요. 소중한 사람이 절로 생각나는 마음 따뜻해지는 곳에서 한 숨 쉬고나면 다시 에너지 충전 완료!</dd>


  					<dt class="col-sm-3">가격 안내</dt>
  					<dd class="col-sm-9">
    					<dl class="row">
      						<dt class="col-sm-4">비수기 평일</dt>
      						<dd class="col-sm-8">100,000</dd>
      						<dt class="col-sm-4">비수기 주말</dt>
      						<dd class="col-sm-8">150,000</dd>
      						<dt class="col-sm-4">성수기 평일</dt>
      						<dd class="col-sm-8">200,000</dd>
      						<dt class="col-sm-4">성수기 주말</dt>
      						<dd class="col-sm-8">250,000</dd>
    					</dl>
  					</dd>
				</dl>
				<div class="row">
					<div class="col-4"></div>
					<button class="col-5 btn btn-dark"><h4>이 객실 예약하러 가기</h4></button>
				</div>
				
			</div>
			<style>
				.btn-dark:hover{
					background-color:white;
					color:black;					
				}
			</style>

		<div class="col-6">
			<img class="z-0" src="<%=request.getContextPath() %>/images/ocean1/layout1.png">		
		</div>
	</div>
	<br><br><br><br>
	<!-- <div class="d-grid gap-2 btn-lg">
		<button class="btn btn-primary"><h4>이 객실 예약하러 가기</h4></button>
	</div>  -->
	<br><br><br><br>
</section>
<script>
	/* $(".btn").onclick(e=>{
		//해당 객실 이름 가지고
		//캘린더로 페이지 이동
	}) */
</script>
<%@ include file="/views/common/footer.jsp" %>