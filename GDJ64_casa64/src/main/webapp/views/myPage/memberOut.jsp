<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>회원탈퇴</h2>
		</div>

	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>
		<div class="mt-5 col-7 mx-auto">
			<div class="p-3">
				<div class="border-primary border-bottom border-2">
					<h4 class="mb-3"><b>회원탈퇴 안내</b></h4>

				</div>
				<p class="mt-5">회원탈퇴를 신청하기 전에 안내 사항을 확인해주세요.</p>
				<div class="mt-5">
					<p>사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능 합니다.</p>
					<p>탈퇴한 아이디는 복구가 불가능하오니 신중하게 선택하시기 바랍니다.</p>
				</div>
				<div class="">
					<p>※ 탈퇴 후에도 게시판에 등록한 게시물은 그대로 남아 있습니다.</p>
				</div>

				<div class="border text-center mt-5 my-3">회원탈퇴하려는 계정 :
					asdf@btc.com</div>



				<form action="" class="pt-3">
					<input class="form-check-input" type="checkbox" value=""
						id="flexCheckDefault"> <label class="form-check-label"
						for="flexCheckDefault"> 안내 사항을 모두 확인하였으며, 이에 동의합니다. </label>


					<div class="mt-3 text-center">
						<button type="button" class="btn btn-primary">확인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/common/footer.jsp"%>
