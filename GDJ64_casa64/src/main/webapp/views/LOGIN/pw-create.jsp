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
                <img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/07.jpg"  alt="user login">
            </div>
            <div class="login__forms">
            <form action="<%=request.getContextPath() %>/member/changePassword.do" class="login__register login-in">
            <h1 class="login__title">새로운 비밀번호 생성</h1>
            <p>비밀번호 생성을 위한 절차를 <br> 진행해 주세요</p>
            <div class="login__box">
                <i class='bx bx-lock login__icon'></i>
                <input type="password" placeholder="Password" class="login__input" name="password" id="pw">
            </div>
            <div id="check-num" class="login__box">
                <i class='bx bx-lock login__icon'></i>
                <input type="password" placeholder="PasswordCheck" class="login__input fn-password-check" id="ckpw">
            </div>
            <div class="text-alert">
                <!-- <p>*인증번호가 일치하지 않습니다.</p> -->
            </div>
            <div id="position-box"></div>
            <button type="submit" class="login__button" disabled>비밀번호 변경</button>
            </form>
    </section>
    <script>
	$(".fn-password-check").keyup(()=>{
	    const i = $("#pw").val();
	    const j = $("#ckpw").val();
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
    

     <!-- 푸터 영역 -->
     <%@ include file="/views/common/footer.jsp" %>	
     
     
     
     
     
     
     
     
     
     
     
     