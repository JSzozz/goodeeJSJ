<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>


  <%
    String clientId = "ogN8S_IYucSd1upGMoFq";//애플리케이션 클라이언트 아이디값";
    String redirectURI = URLEncoder.encode("http://localhost:9090/GDJ64_casa64/naverLogin.do", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=" + clientId;
    apiURL += "&redirect_uri=" + redirectURI;
    apiURL += "&state=" + state;
    session.setAttribute("state", state);
 %>



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
                            <span><a href="<%=apiURL%>"><img src="<%=request.getContextPath()%>/images/icon/btnW_완성형.png" alt="" width="200px"></a></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

     <!-- 푸터 영역 -->
     <%@ include file="/views/common/footer.jsp" %>	