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
				<table class="table table-hover text-center align-middle"
					style="height: 500px;">
					<colgroup>
						<col width="30%">
						<col width="40%">
						<col width="30%">
					</colgroup>
					<tbody>
						<tr>
							<th>성명</th>
							<td><%=m.getMemberName()%></td>
							<td></td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td><%=m.getNickName()%></td>
							<td><button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#passwordcheckMyinfo">수정</button></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><%=m.getEmail()%></td>
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
											<input type="password" class="form-control" id="passwordCK"
												placeholder="password">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
											<button type="button" class="btn btn-dark"
												onclick="passwordCheck();">확인</button>
										</div>

									</div>
								</div>
							</div>

							<div class="modal fade" id="passwordcheckMyinfo"
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
											<input type="password" class="form-control" id="passwordCK2"
												placeholder="password">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
											<button type="button" class="btn btn-dark"
												onclick="passwordCheck2();">확인</button>
										</div>

									</div>
								</div>
							</div>

						</tr>
						<tr>
							<th>전화번호</th>
							<td><%=m.getPhone()%></td>
							<td><button type="button" class="btn btn-primary btn-sm"
									data-bs-toggle="modal" data-bs-target="#passwordcheckMyinfo">수정</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script>
	const passwordCheck=()=>{
		let pw=$("#passwordCK").val();
		let memberno=<%=m.getMemberNo()%>
		$.ajax({
			url:"<%=request.getContextPath()%>/member/passwordcheck.do",
			type:"POST",
			data:{password:pw,
				  memberNo:memberno},
			success:function(data){
				if(data>0){
					location.replace("<%=request.getContextPath()%>/views/myPage/changePassword.jsp");
				}else{
					alert("비밀번호가 일치하지 않습니다");
				}
			},error:function(){
				alert("서버오류");
			}
		});
	};
	const passwordCheck2=()=>{
		let pw=$("#passwordCK2").val();
		let memberno=<%=m.getMemberNo()%>
		$.ajax({
			url:"<%=request.getContextPath()%>/member/passwordcheck.do",
			type:"POST",
			data:{password:pw,
				  memberNo:memberno},
			success:function(data){
				if(data>0){
					location.replace("<%=request.getContextPath()%>/views/myPage/changeMyInfo.jsp");
				}else{
					alert("비밀번호가 일치하지 않습니다");
				}
			},error:function(){
				alert("서버오류");
			}
		});
	};
</script>




<%@ include file="/views/common/footer.jsp"%>















