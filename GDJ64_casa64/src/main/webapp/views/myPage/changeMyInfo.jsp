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

				<form action="<%=request.getContextPath() %>/member/changemyinfo.do" class="form-floating">
					<div class="input-group mb-3">

						<input type="text" class="form-control"
							 value="<%=m.getEmail() %>" name="email" readonly>

					</div>
					<div class="input-group mb-3">
						<span class="input-group-text" >닉네임</span> <input
							type="text" class="form-control" name="nick"
							value="<%=m.getNickName() %>">
					</div>



					<div class="input-group mb-3">
						<span class="input-group-text">전화번호</span> <input type="text"
							class="form-control" name="phone" value="<%=m.getPhone() %>">
					</div>

					<div class="input-group mb-3">
						<span class="input-group-text">이름</span> <input type="text"
							class="form-control" name="name" value="<%=m.getMemberName() %>">
					</div>
					<div class="d-grid gap-2">
						<button class="btn btn-primary" type="submit">확인</button>
						<button class="btn btn-secondary" type="reset" onclick="location.href='<%=request.getContextPath()%>/views/myPage/myPageInfo.jsp'">취소</button>
					</div>

				</form>
			</div>
		</div>

	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>
