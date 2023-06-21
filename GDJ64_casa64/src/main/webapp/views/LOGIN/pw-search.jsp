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
                <img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/08.jpg"  alt="user login">
            </div>
            <div class="login__forms">
            <form action="" class="login__register login-in">
            <h1 class="login__title">본인인증</h1>
            <p>본인인증을 위한 절차를 진행해 주세요</p>
            <div class="login__box">
                <i class='bx bx-lock login__icon'></i>
                <input type="text" placeholder="Email" class="login__input">
            </div>
            <div id="check-email" class="login__box check-num">
                <i class='bx bx-check login__icon'></i>
                <input type="text" placeholder="checkNum" class="login__input">
                <button type="button" class="btn btn-dark">중복확인</button>
            </div>
            <div class="text-alert">
                <!-- <p>*인증번호가 일치하지 않습니다.</p> -->
            </div>
            <div id="position-box"></div>
            <div id="login-sub-menu">
                <a href="<%=request.getContextPath()%>/views/LOGIN/login.jsp" class="login__forgot">로그인 하러가기</a>
            </div>
            <a href="<%=request.getContextPath()%>/views/LOGIN/pw-create.jsp" class="login__button">Next</a>
            
            <div>
                <span class="login__account login__account--account">가입된 계정이 없으십니까?</span>
                <a href="<%=request.getContextPath()%>/views/LOGIN/signup01-agreement.jsp" class="login__signin login__signin--signup" id="sign-up">회원가입</a>
            </div>
            </form>
    </section>

     <!-- 푸터 영역 -->
     <%@ include file="/views/common/footer.jsp" %>	
    <!-- <footer id="footer">
        <script>$('#footer').load('<%=request.getContextPath()%>/views/common/footer.jsp')</script>
    </footer> -->
<!-- 
    
</body>
</html> -->