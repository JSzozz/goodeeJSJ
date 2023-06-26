<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>비밀번호변경</h2>
		</div>
	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>
		<div class="mt-5 col-5 mx-auto">
			<span class="text-center text-black-10"><h4>비밀번호변경</h4></span>
			<div class="p-5 bg-white border border-secondary rounded">
				<form action="<%=request.getContextPath() %>/member/changePassword.do">
<!-- 					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">현재
							비밀번호</label> <input type="password" class="form-control col-lg-12" id="">
					</div> -->
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">새
							비밀번호</label> <input type="password" class="form-control col-lg-12 fn-password" name="password">
					</div>
					<div class="mb-3">
						<label for="exampleInputPassword1" class="form-label">비밀번호
							확인</label> <input type="password" class="form-control col-lg-12 fn-password-check">
					</div>
					<div class="text-alert">
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
<script>
$(".fn-password-check").keyup(()=>{
	    const i = $(".fn-password").val();
	    const j = $(".fn-password-check").val();
	    let msg,color;
	    if(i!=0&&j!=0){
	        if(i.length==j.length&&i==j){
	            msg='*비밀번호가 일치합니다.';
	            color='green';
	            $(".login__button").attr("disabled",false);
	        }else{
	            msg='*비밀번호가 일치하지 않습니다.';
	            color='red';
	            $(".login__button").attr("disabled",true);
	        }
	    }
	    $(".text-alert").html("");
	    $(".text-alert").append($("<p>").text(msg).css("color",color));
	});
</script>	
<%@ include file="/views/common/footer.jsp"%>
