<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>마이페이지</h2>
		</div>
	</div>
	<div class="row min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>
		<div class="mt-5 col">

			<div class="mx-auto mt-5 card shadow"
				style="width: 60rem; height: 15rem;">
				<div class="row">
					<div class="col-md-4">
						<img src="./images/room.png" class="rounded-start m-4"
							style="width: 15rem; height: 12rem">
					</div>

					<div class="col-md-8">
						<div class="card-body">
							<h5 class="card-title">Room1</h5>
							<p class="card-text">
								예약자:*** <br>옵션: 바베큐, 조식
							</p>
							<a
								href="<%=request.getContextPath()%>/views/myPage/myPageReservation.jsp"
								class="btn btn-primary">예약내역보기</a>
						</div>

					</div>

					<!-- 예약내역이 없을때
				<div class="mx-auto mt-5 card shadow"
					style="width: 60rem; height: 15rem;">
					<div class="">
							<div class="card-body">
								<div class="text-center">
								<i class="bi bi-info-circle-fill fs-2 align-middle"></i>
								</div>
								<h5 class="card-title text-center mt-5">예약내역이 없습니다</h5>
								<div class="text-center mt-5">
								<a
									href=""
									class="btn btn-dark">예약하기</a>
								</div>
						</div>



					</div>
				</div> -->

					<div class="mx-auto mt-5 card shadow"
						style="width: 60rem; height: 15rem;">
						<div class="row">
							<div class="col-md-4">
								<img src="./images/5.jpeg" class="rounded-start m-4"
									style="width: 15rem; height: 12rem">
							</div>

							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">Room1</h5>
									<p class="card-text">
										제목: <br>내용:
									</p>
									<a
										href="<%=request.getContextPath()%>/views/myPage/myPageReview.jsp"
										class="btn btn-dark">리뷰내역보기</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>
