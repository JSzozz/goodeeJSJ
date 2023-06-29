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
                <img src="<%=request.getContextPath()%>/images/room/09.jpg"  alt="user login">
            </div>
            <div class="login__forms">
                <form action="" class="login__create" id="login-up">
                    <h1 class="login__title">1. 약관동의</h1>
                    <!-- Slider main container -->
                    <div class="swiper">
                        <!-- Additional required wrapper -->
                        <div class="swiper-wrapper">
                            <!-- Slides -->
                            <div class="swiper-slide">
                                <h4>가. 개인정보 수집동의</h4>
                                <textarea name="service01" id="first-agree" cols="400" rows="10" readonly>여러분을 환영합니다.BTC 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 네이버 서비스의 이용과 관련하여 네이버 서비스를 제공하는 네이버 주식회사(이하 ‘네이버’)와 이를 이용하는 네이버 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.네이버 서비스를 이용하시거나 네이버 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.
                                </textarea>
                                <div>
                                    <input type="checkbox" id="signup-agree01" class="signup-agreement">
                                    <label for="signup-agree01"> 위약관을 충분히 숙지하였으며, 위에 동의합니다.</label>
                                </div>
                            </div>
                            
                            <div class="swiper-slide">
                                <h4>나. 수집된 데이터 처리방침</h4>
                                <textarea name="service02" id="second-agree" cols="400" rows="10" readonly>다양한 BTC 서비스를 즐겨보세요.BTC는 BTC.com을 비롯한 네이버 도메인의 웹사이트 및 응용프로그램(어플리케이션, 앱)을 통해 정보 검색, 다른 이용자와의 커뮤니케이션, 콘텐츠 제공, 상품 쇼핑 등 여러분의 생활에 편리함을 더할 수 있는 다양한 서비스를 제공하고 있습니다.여러분은 PC, 휴대폰 등 인터넷 이용이 가능한 각종 단말기를 통해 각양각색의 네이버 서비스를 자유롭게 이용하실 수 있으며, 개별 서비스들의 구체적인 내용은 각 서비스 상의 안내, 공지사항, 네이버 웹고객센터(이하 ‘고객센터’) 도움말 등에서 쉽게 확인하실 수 있습니다.네이버는 기본적으로 여러분 모두에게 동일한 내용의 서비스를 제공합니다. 다만, '청소년보호법' 등 관련 법령이나 기타 개별 서비스 제공에서의 특별한 필요에 의해서 연령 또는 일정한 등급을 기준으로 이용자를 구분하여 제공하는 서비스의 내용, 이용 시간, 이용 횟수 등을 다르게 하는 등 일부 이용을 제한하는 경우가 있습니다. 자세한 내용은 역시 각 서비스 상의 안내, 공지사항, 고객센터 도움말 등에서 확인하실 수 있습니다.
                                </textarea>
                                <div>
                                    <input type="checkbox" id="signup-agree02" class="signup-agreement">
                                    <label for="signup-agree02"> 위약관을 충분히 숙지하였으며, 위에 동의합니다.</label>
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <h4>다. 광고성 수신메일 동의</h4>
                                <textarea name="service03" id="third-agree" cols="400" rows="10" readonly>BTC 서비스에는 기본적으로 본 약관이 적용됩니다만 네이버가 다양한 서비스를 제공하는 과정에서 부득이 본 약관 외 별도의 약관, 운영정책 등을 적용하는 경우(예, 네이버페이, V LIVE 등)가 있습니다. 그리고 네이버 계열사가 제공하는 특정 서비스의 경우에도(예, LINE, SNOW등) 해당 운영 회사가 정한 고유의 약관, 운영정책 등이 적용될 수 있습니다. 이러한 내용은 각각의 해당 서비스 초기 화면에서 확인해 주시기 바랍니다.회원으로 가입하시면 네이버 서비스를 보다 편리하게 이용할 수 있습니다.여러분은 본 약관을 읽고 동의하신 후 회원 가입을 신청하실 수 있으며, 네이버는 이에 대한 승낙을 통해 회원 가입 절차를 완료하고 여러분께 네이버 서비스 이용 계정(이하 ‘계정’)을 부여합니다. 계정이
                                </textarea>
                                <div>
                                    <input type="checkbox" id="signup-agree03" class="signup-agreement">
                                    <label for="signup-agree03"> 위약관을 충분히 숙지하였으며, 위에 동의합니다.</label>
                                </div>
                            </div>
                        </div>
                        <!-- If we need pagination -->
                        <div class="swiper-pagination"></div>
                        
                        <div class="signup-nextbtn">
                            <a class="login__button">Next</a>
                        </div>
                        <div>
                            <input type="checkbox" id="signup-agree04">
                            <label for="signup-agree04" class="login__signin login__signin--signup"> 전체 동의</label>
                        </div>
                    </div>
                </form>
            </div>
    </section>

    <!-- js파일 -->
    <!-- 한파일에 두기엔 변수명이 중복되는데 부트스트랩은 이름을 변경하면 안되는걸로 알아서 일단 합치지 않음 -->
      <script>
      	$("#signup-agree04").click(function(){
      		const checked1=$("#signup-agree01").is(":checked");
      		const checked2=$("#signup-agree02").is(":checked");
      		const checked3=$("#signup-agree03").is(":checked");
      		if(checked1==false||checked2==false||checked3==false){
      			$("input:checkbox").prop("checked",true);
      		}else{
      			$("input:checkbox").prop("checked",false);
      		}
      		
      	});
      
      	$(".login__button").click(function(){
      		const checked1=$("#signup-agree01").is(":checked");
      		const checked2=$("#signup-agree02").is(":checked");
      		const checked3=$("#signup-agree03").is(":checked");
      		if(checked1==true&&checked2==true&&checked3==true){
      			location.href="<%=request.getContextPath()%>/member/enrollMemberInput.do";
      		}else{
      			alert("약관에 모두 동의해주세요");
      		}
      	});
      	
     	
        const swiper = new Swiper('.swiper', {
        // Optional parameters
        direction: 'horizontal',
        mousewheel: true,
        loop: false,
        // If we need pagination
        pagination: {
            el: '.swiper-pagination',
            clickable : true,
        },
        // Navigation arrows
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        }); 
    </script>
     <!-- 푸터 영역 -->
     <%@ include file="/views/common/footer.jsp" %>	