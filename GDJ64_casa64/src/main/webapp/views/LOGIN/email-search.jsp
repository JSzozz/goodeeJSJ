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
                    <img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/05.jpg"  alt="user login">
                </div>
                <div class="login__forms">
                    <form action="" class="login__register login-in">
                        <h1 class="login__title">가입정보 조회</h1>
                        <div class="login__box">
                            <i class='bx bx-user login__icon'></i>
                            <input type="email" placeholder="Email" class="login__input" id="email-search">
                        </div>
                        <div class="text-alert">
                            <br><!-- <p>*가입된 이메일이 아닙니다.</p> -->
                        </div>
                        <div id="position-box"></div>
                        <div id="login-sub-menu">
                            <a href="<%=request.getContextPath()%>/views/LOGIN/login.jsp" class="login__forgot">로그인 하러가기</a>
                            <a href="<%=request.getContextPath()%>/views/LOGIN/pw-search.jsp" class="login__forgot">비밀번호 찾기</a>
                        </div>
                        <button type="button" class="login__button" onclick="emailCheck();">이메일 조회</button>
                        <div>
                            <span class="login__account login__account--account">가입된 계정이 없으십니까?</span>
                            <a href="<%=request.getContextPath()%>/views/LOGIN/signup01-agreement.jsp" class="login__signin login__signin--signup" id="sign-up">회원가입</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>        
    </section>
    
    <script>
    	const emailCheck=()=>{
    		const email=$("#email-search").val();
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
   				console.log(result);
   				if(fn_emailChk(email)==false){
   					$(".text-alert").html("");
   					$(".text-alert").append($("<p>").text("이메일형식이 아닙니다").css("color","red"));
   					
   				}else if(result==1){
   					$(".text-alert").html("");
   					$(".text-alert").append($("<p>").text("가입정보가 없습니다").css("color","red"));
   					
   				}else if(result==0){
   					$(".text-alert").html("");
   					$(".text-alert").append($("<p>").text("가입된 이메일입니다").css("color","green"));
   				}
   			
   			},
   				error:function(){
   					alert("error");
   				}
   			
   		});
    	}
    	
    </script>

    <!-- 푸터 영역 -->
    <%@ include file="/views/common/footer.jsp" %>	
    
    
    
    
    
    
    
    
    
    
    