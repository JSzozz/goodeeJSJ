<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <!-- 헤더 영역 시작 -->
    <%@ include file="/views/common/header.jsp" %>
    <!-- 헤더 영역 종료 -->

    <section>
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- <link href='../test.scss' rel='stylesheet'> -->
        <div class="login">
            <div class="login__content">
            <div class="login__img">
                <!-- <img src="../images/객실사진/10번 객실/05.jpg" alt="user login"> -->
                <img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/09.jpg"  alt="user login">
            </div>
            <div class="login__forms">
                <form action="<%=request.getContextPath() %>/member/enrollMailMember.do" class="login__create" id="login-up">
                    <h1 class="login__title">2. 정보입력</h1>
                    <div id="signin-input">
                        <div class="login__box">
                            <i class='bx bx-user login__icon'></i>
                            <input type="text" placeholder="Username" class="login__input" name="userName" required>
                        </div>
                        
                        <!-- <div class="login__box">
                            <i class='bx bx-at login__icon'></i>
                            <input type="text" placeholder="Email" class="login__input">
                            <button type="button" class="btn btn-dark">중복확인</button>
                        </div> -->
                        <div class="login__box">
                        <i class='bx bx-at login__icon'></i>
                        <input type="text" placeholder="Email" class="login__input" name="email">
                        <button type="button" class="btn btn-primary" onclick="checkEmail();">중복확인</button>
                    </div>
                        <div class="login__box">
                            <i class='bx bx-user login__icon'></i>
                            <input type="text" placeholder="NickName" class="login__input" name="nickName" required>
                        </div>
                        <div class="login__box">
                            <i class='bx bx-phone login__icon'></i>
                            <input type="text" placeholder="phone" class="login__input" name="phone" required>
                        </div>
                        
                        <div id="pw-box">
                            <div class="login__box">
                                <i class='bx bx-lock login__icon'></i>
                                <input type="password" placeholder="password" id="ck-pw1" class="login__input" name="password" required>
                            </div>

                            <div class="login__box">
                                <i class='bx bx-lock login__icon'></i>
                                <input type="password" placeholder="passwordCK" id="ck-pw2" class="login__input fn-password-check" required>
                            </div>
                        </div>
                    </div>
                    <div class="emailcheck">
                        <!-- <p>*비밀번호가 일치하지 않습니다.</p> -->
                    </div>
                   
                    <div class="text-alert pwcheck">
                        <!-- <p>*비밀번호가 일치하지 않습니다.</p> -->
                    </div>
                    <div style="height: 5%;"></div>
                    
<!--                     <div class="login__box">
                            
                            <input type="text" placeholder="인증번호입력" class="login__input" name="verifyEmail">
                            <button type="button" class="btn btn-primary" onclick="verifyEmail();">인증하기</button>
                        </div> -->
                    <button type="submit" class="login__button" disabled>인증하기</button>
                    
                    <!-- <div>
                        <span class="login__account login__account--account">?</span>
                        <a href="../LOGIN/login.html" class="login__signup login__signup--signup" id="sign-in">Log In</a>
                    </div>   -->
                </form>
            </div>
    </section>
	<script>
	const checkEmail=()=>{
		const email=$("input[name=email]").val();
		const fn_emailChk=(email)=>{
			 var regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
					if(!regExp.test(email)){
					    return false;
					  }
					  return true;
					};
		$.ajax({
			url:"<%=request.getContextPath()%>/member/duplicatedEmail",
			type:"post",
			data:{email:email},
			dataType:"json",
			success:function(result){
				if(result==0){
					$(".emailcheck").html("이미 가입된 이메일입니다.");
					
					
				}else if(fn_emailChk(email)==true){
					$(".emailcheck").html("사용가능한 이메일입니다.").attr("color","green");
					$(".login__button").attr("disabled",false);
					
					
				}else{
					$(".emailcheck").html("이메일 형식이 아닙니다.");
					
				}
			},
				error:function(){
					alert("error");
				}
			
		});
	};
	$(".fn-password-check").keyup(()=>{
	    const i = $("#ck-pw1").val();
	    const j = $("#ck-pw2").val();
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
	    $(".pwcheck").html("");
	    $(".pwcheck").append($("<p>").text(msg).css("color",color));
	});
	

		
	</script>



    <!-- 푸터 영역 -->
    <%@ include file="/views/common/footer.jsp" %>	