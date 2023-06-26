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
					<%=m.getEmail() %></div>



				<div class="pt-3">
					<input class="form-check-input" type="checkbox" value=""
						id="outCheck"> <label class="form-check-label"
						for="flexCheckDefault"> 안내 사항을 모두 확인하였으며, 이에 동의합니다. </label>


					<div class="mt-3 text-center">
						<button type="button" class="btn btn-primary" id="outBtn" onclick="outCheck();">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="passwordcheckMemberOut"
								data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
								aria-labelledby="staticBackdropLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title">비밀번호 확인</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<input type="password" class="form-control" id="outPasswordCK"
												placeholder="password">
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">닫기</button>
											<button type="button" class="btn btn-dark"
												onclick="outPasswordCheck();">확인</button>
										</div>

									</div>
								</div>
							</div>
	
	
</div>
<script>
	const outCheck=()=>{
		if($("#outCheck").is(":checked")==false){
			
			alert("안내사항에 동의해주세요");
			
		}else{
			
			$("#passwordcheckMemberOut").modal("show");
		}
	};
	
	const outPasswordCheck=()=>{
		let pw=$("#outPasswordCK").val();
		let memberno=<%=m.getMemberNo()%>
		$.ajax({
			url:"<%=request.getContextPath()%>/member/passwordcheck.do",
			type:"POST",
			data:{password:pw,
				  memberNo:memberno},
			success:function(data){
				if(data>0){
					
					location.replace("<%=request.getContextPath()%>/member/cancelMember.do");
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
