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
                    <img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/06.jpg"  alt="user login">
                </div>
                <div class="login__forms">
                    <form action="<%=request.getContextPath()%>/LoginToHeaderServlet.do" class="login__register login-in" id="frm-login">
                        <h1 class="login__title">Log In</h1>
                        <div class="login__box">
                            <i class='bx bx-user login__icon'></i>
                            <input type="email" placeholder="Email" class="login__input flag" name="email" id="id-null-check">
                        </div>
                        <div class="login__box">
                            <i class='bx bx-lock login__icon'></i>
                            <input type="password" placeholder="Password" class="login__input" name="password">
                        </div>
                        <div id="position-box"></div>
                        <a href="<%=request.getContextPath()%>/views/LOGIN/email-search.jsp" class="login__forgot">가입정보조회 </a>
                        <input type="submit" class="login__button" id="login-submit-button" value="Log in">
                        <div>
                            <span class="login__account login__account--account">가입된 계정이 없으십니까?</span>
                            <a href="<%=request.getContextPath()%>/member/enrollMember.do" class="login__signin login__signin--signup" id="sign-up">회원가입</a>
                        </div>
                        <div id="sns-login">
                            <span><img src="<%=request.getContextPath()%>/images/icon/kakao-talk-icon.png" alt="" width="50px"></span>
                            <span><img src="<%=request.getContextPath()%>/images/icon/btnG_아이콘사각.png" alt="" width="50px"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    
     <!-- 푸터 영역 -->
     <%@ include file="/views/common/footer.jsp" %>	
     
     
     
     
     
     