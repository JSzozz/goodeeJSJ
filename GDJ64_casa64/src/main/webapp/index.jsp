<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <!-- 헤더 영역 시작 -->
    <%@ include file="/views/common/header.jsp" %>
    <!-- 헤더 영역 종료 -->
    
    <!-- css -->
    <style>
        body{
            height: calc(100vh - 4.625rem);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        div.swiper, div.swiper-wrapper,div.swiper div.swiper-slide,div.swiper img {
        	z-index:1 !important;
        }
    </style>
    <!-- /css -->
    
    <section>
        <!-- Slider main container -->
        <div class="swiper">
            <!-- Additional required wrapper -->
            <div class="swiper-wrapper">
                <!-- Slides -->
                <div class="swiper-slide"><img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/02.jpg"></div>
                <div class="swiper-slide">
                    <div id="booking-complete">
                        <div id="booking-image">
                            <h3>닉네임님의 예약현황</h3>
                            <br><br>
                            <img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/03.jpg" width="600" height="400">
                        </div>
                        <div id="booking-info">
                            <table>
                                <tbody>
                                    <tr class="resize">
                                        <th>예약자&nbsp;</th>
                                        <td>김나나<br>성인 2명/유아 0명</td>
                                    </tr>
                                    <tr class="resize">
                                        <th>예약날자&nbsp;</th>
                                        <td>2023.08.22 ~ 2023.08.25<br>성인 2명/유아 0명</td>
                                    </tr>
                                    <tr class="resize">
                                        <th>추가옵션&nbsp;</th>
                                        <td>조식<br>바베큐</td>
                                    </tr>
                                    <tr class="resize">
                                        <th>예약상태&nbsp;</th>
                                        <td>결제대기중 <button type="button" class="btn btn-dark">바로결제</button></td><br>
                                    </tr>
                                </tbody>
                            </table>
                            <button type="button" class="btn btn-dark">예약변경</button>
                            <button type="button" class="btn btn-danger">예약취소</button>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div id="booking-status">
                        <img id="status-image" src="<%=request.getContextPath()%>/images/icon/house.png" alt="house-icon" width="350px">
                        <h3>최고의 휴가를 보낼 수 있도록 도와드릴게요!</h3>
                        <div>
                            <span>
                                <p>어떤 휴가를 보낼지 정했어요</p>
                                <button type="button" class="btn btn-dark">키워드로 객실찾기</button>
                            </span>
                            <span>
                                <p>아직 잘 모르겠어요... <br>어떤 객실들이 있나요?</p>
                                <button type="button" class="btn btn-dark">ROOMS 객실 둘러보기</button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div id="booking-filter">
                        <div>
                            <h3>취향저격! 키워드로 객실 찾기</h3>
                            <input class="filter-search-value" type="hidden" placeholder=" 키워드 선택하기" readonly>
                            <div id="filter-header">
                                <span id="filter-search-button"><img src="<%=request.getContextPath()%>/images/icon/search-3-24.png" alt=""></span>
                                <input class="filter-search" type="text" placeholder=" 키워드 선택하기" readonly>
                            </div>
                        </div>
                        <div id="filter-button">
                            <div>
                                <label for="filter01">#오션뷰</label>
                                <label for="filter02">#전용주차공간</label>
                                <label for="filter03">#스파</label>
                                <label for="filter04">#바베큐</label>
                            </div>
                            <div>
                                <label for="filter05">#키즈풀</label>
                                <label for="filter06">#빔프로젝터</label>
                                <label for="filter07">#불멍</label>
                            </div>
                            <div>
                                <label for="filter08">#주방</label>
                                <label for="filter09">#구스다운침대</label>
                                <label for="filter10">#선셋뷰</label>
                                <label for="filter11">#</label>
                            </div>

                            <div>
                                <input type="checkbox" id="filter01" class="filterbox" value="#오션뷰">
                                <input type="checkbox" id="filter02" class="filterbox" value="#전용주차공간">
                                <input type="checkbox" id="filter03" class="filterbox" value="#스파">
                                <input type="checkbox" id="filter04" class="filterbox" value="#바베큐">
                                <input type="checkbox" id="filter05" class="filterbox" value="#키즈풀">
                                <input type="checkbox" id="filter06" class="filterbox" value="#빔프로젝터">
                                <input type="checkbox" id="filter07" class="filterbox" value="#불멍">
                                <input type="checkbox" id="filter08" class="filterbox" value="#주방">
                                <input type="checkbox" id="filter09" class="filterbox" value="#구스다운침대">
                                <input type="checkbox" id="filter10" class="filterbox" value="#선셋뷰">
                                <input type="checkbox" id="filter11" class="filterbox" value="#">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide"><img src="<%=request.getContextPath()%>/images/rooms image/no.1 rooms/04.jpg"></div>
            </div>
            <!-- If we need pagination -->
            <div class="swiper-pagination"></div>
            
            <!-- If we need navigation buttons -->
            <div class="swiper-button-prev"><img src="<%=request.getContextPath()%>/images/icon/scroll_top_btn.png" alt=""></div>
            <div class="swiper-button-next"><img src="<%=request.getContextPath()%>/images/icon/scroll_bottom_btn.png" alt=""></div>
        </div>
        <div id="search-btn" onclick="test();">
            <img src="<%=request.getContextPath()%>/images/icon/search.png" alt=""  width="45" height="45">
            <ladel for="test07"></ladel>
        </div>
        <!-- Slider main container -->
    </section>
    
	<!-- swiper.js -->
	<script>
	const swiper = new Swiper('.swiper', {
	    // Optional parameters
	    direction: 'vertical',
	    // autoHeight : true,
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
	    // 푸터 애니매이션
	    on: {
	        slideChangeTransitionEnd: function(){
	            // console.log(this.activeIndex+1);
	            cview=this.activeIndex+1;
	            if(cview==5){
	                $("#footer").css("display","inline-block").animate({width: 1980}, 300);
	            }else{
	                $("#footer").animate({width: 0}, 300);
	                $("#footer").css("display","none");
	            };
	        },
	    },
	    });
	    //searchBtn
	    function test() {
	        swiper.slideTo(1, 500, true);
	    };
	    
	    //search-tag-button
	    let filterDB="";
	    let filterDBview="";
	    $(document).ready(function(){
	        $('.filterbox').change(function(){
	            if($(this).is(":checked")) {
	                filterDB+=this.value;
	                filterDBview+=this.value+" ";
	                $('label[for='+this.id+']').css({"background-color":"#212529","color":"white"})
	                .removeClass('change-btn').addClass('rechange-btn');
	            }else{
	                filterDB=filterDB.replace(this.value,'');
	                filterDBview=filterDBview.replace(this.value+" ",'');
	                $('label[for='+this.id+']').css({"background-color":"white","color":"#212529"})
	                .removeClass('rechange-btn').addClass('change-btn');
	            }
	            console.log(filterDBview);
	            $(".filter-search").attr("value",filterDBview);
	            $(".filter-search-value").attr("value",filterDB);
	        });
	    });
	</script>
	
    <!-- 푸터 영역 -->
    <%@ include file="/views/common/footer.jsp" %>