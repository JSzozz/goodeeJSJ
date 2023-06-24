<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>회원정보변경</h2>
		</div>

	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>
		<div class="mt-5 col-5 mx-auto">
			<span class="text-center text-black-100"></span>
			<div
				class="p-5 mt-4 bg-white border border-secondary rounded text-center">

				<form action="" class="form-floating">
					<div class="input-group mb-3">

						<input type="text" class="form-control" id="email"
							placeholder="기존이메일" disabled>

					</div>
					<div class="input-group mb-3">
						<span class="input-group-text" id="nick">닉네임</span> <input
							type="text" class="form-control" id="password1"
							placeholder="기존닉네임">
					</div>



					<div class="input-group mb-3">
						<span class="input-group-text">전화번호</span> <input type="text"
							class="form-control" id="phone" placeholder="기존전화번호">
					</div>

					<div class="input-group mb-3">
						<span class="input-group-text">이름</span> <input type="text"
							class="form-control" id="phone" placeholder="">
					</div>
					<div class="d-grid gap-2">
						<button class="btn btn-primary" type="button">확인</button>
						<button class="btn btn-secondary" type="button">취소</button>
					</div>

				</form>
			</div>
		</div>

	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>
