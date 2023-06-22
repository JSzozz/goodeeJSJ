
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="card">
	<div class="card-body">
		<p class="card-title align-middle mb-0">
			<b>나라라라</b> 님의<br> MyPage입니다 :)
		</p>
	</div>
</div>
<ul class="mt-5 nav flex-column col-10 ">
	<li class="nav-item"><a class="nav-link"
		href="<%=request.getContextPath()%>/myPage/myPageInfo">회원정보/수정</a></li>
	<li class="nav-item"><a class="nav-link"
		href="<%=request.getContextPath()%>/myPage/myPageReservation">예약내역</a>
	</li>
	<li class="nav-item"><a class="nav-link"
		href="<%=request.getContextPath()%>/myPage/myPageReview">이용후기</a></li>
	<li class="nav-item"><a class="nav-link"
		href="<%=request.getContextPath()%>/myPage/myPageQnA">문의사항</a></li>
	<li class="nav-item"><a class="nav-link active"
		href="<%=request.getContextPath()%>/myPage/memberOut">회원탈퇴</a></li>
</ul>
<!-- Modal -->
<div class="modal fade" id="passwordcheckMyinfo"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">비밀번호 확인</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<input type="password" class="form-control" placeholder="password">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-dark"
					onclick="location.href='<%=request.getContextPath()%>/views/myPage/changeMyInfo.jsp'">확인</button>
			</div>
		</div>
	</div>
</div>


