<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>회원정보</h2>
		</div>

	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>

		<div class="mt-5 col-9 mx-auto">

			<div class="tb">
				<table class="table table-hover text-center align-middle" style="height: 500px;">
					<colgroup>
						<col width="30%">
						<col width="40%">
						<col width="30%">
					</colgroup>
					<tbody>
						<tr>
							<th>성명</th>
							<td>***</td>
							<td></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td>유저닉네임</td>
							<td><button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#passwordcheckMyinfo">수정</button></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td>asdf@gmail.com</td>
							<td></td>
						</tr>
						<tr>
							<th>비밀번호 변경</th>
							<td></td>
							<td>
								<button type="button" class="btn btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#staticBackdrop">변경</button>
							</td>
							<!-- Modal -->
							<div class="modal fade" id="staticBackdrop"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="staticBackdropLabel">비밀번호 확인</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<input type="text" class="form-control"
												placeholder="password">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
											<button type="button" class="btn btn-dark"
												onclick="location.href='<%=request.getContextPath()%>/views/myPage/changePassword.jsp'">확인</button>
										</div>

									</div>
								</div>
							</div>

						</tr>
						<tr>
							<th>전화번호</th>
							<td>010-1234-5678</td>
							<td><button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#passwordcheckMyinfo">수정</button></td>
						</tr>
						<tr>
							<th>회원탈퇴</th>
							<td></td>
							<td><button type="button" class="btn btn-danger btn-sm"
									onclick="location.href='<%=request.getContextPath()%>/views/myPage/memberOut.jsp'">탈퇴</button></td>

						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<%@ include file="/views/common/footer.jsp"%>
