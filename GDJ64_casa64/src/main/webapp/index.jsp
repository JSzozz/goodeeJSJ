<%@page import="com.btc.booking.model.vo.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <!-- 헤더 영역 시작 -->
    <%@ include file="/views/common/header.jsp" %>
    <!-- 헤더 영역 종료 -->
<%
   Booking recentBooking=(Booking)session.getAttribute("recentBooking");
%>   
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
                <div class="swiper-slide"><img src="<%=request.getContextPath()%>/images/room/02.jpg"></div>
                <%if(loginMember!=null&&recentBooking!=null) {%>
                   <div class="swiper-slide">
                       <div id="booking-complete">
                           <div id="booking-image">
                               <h3><%=loginMember.getMemberName() %>님의 예약현황</h3>
                               <br><br>
                               <img src="<%=request.getContextPath()%>/images/room/01.jpg" width="600" height="400">
                           </div>
                           <div id="booking-info">
                               <table>
                                   <tbody>
                                   <%if(recentBooking!=null){ %>
                                       <tr class="resize">
                                       
                                           <th>예약자&nbsp;</th>
                                           <td><%=loginMember.getMemberName() %><br>성인 <%=recentBooking.getGuestAdult() %>명, 아동/유아 <%=recentBooking.getGuestChild() %>명, 영아(24개월 미만) <%=recentBooking.getGuestInfant() %>명</td>
                                       </tr>
                                       <tr class="resize">
                                           <th>객실 이용일&nbsp;</th>
                                           <td><%=recentBooking.getCheckIn() %> ~ <%=recentBooking.getCheckOut() %></td>
                                       </tr>
                                       <tr class="resize">
                                           <th>결제금액&nbsp;</th>
                                           <td><%=recentBooking.getBookingPrice() %>원</td>
                                       </tr>
                                       <tr class="resize">
                                           <th>예약상태&nbsp;</th>
                                           <td><%=recentBooking.getBookingState() %>(결제일 : <%=recentBooking.getPaymentDate() %>) </td><br>
                                       </tr>
                                       <tr class="resize">
                                           <th>요청사항&nbsp;</th>
                                           <td><%=recentBooking.getBookingComment() %></td><br>
                                       </tr>
                                       <%} %>
                                   </tbody>
                               </table>
                               <button type="button" class="btn btn-dark"
                               onclick = "location.href = '<%=request.getContextPath()%>/myPage/myPageReservation' "
                               >예약내역</button>
                           </div>
                       </div>
                   </div>
                <%} %>
                <div class="swiper-slide">
                    <div id="booking-status">
                        <img id="status-image" src="<%=request.getContextPath()%>/images/icon/house.png" alt="house-icon" width="350px">
                        <h3>최고의 휴가를 보낼 수 있도록 도와드릴게요!</h3>
                        <div>
                            <!-- <span>
                                <p>어떤 휴가를 보낼지 정했어요</p>
                                <button href="#KeywordSection" type="button" class="btn btn-dark">키워드로 객실찾기</button>
                            </span> -->
                            <span>
                                <p>아직 잘 모르겠어요... <br>어떤 객실들이 있나요?</p>
                                <button onclick = "location.href = '<%=request.getContextPath()%>/RoomListServlet.do' " type="button" class="btn btn-dark">ROOMS 객실 둘러보기</button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide">
                    <div id="booking-filter">
                        <div>
                            <h3 id="KeywordSection">취향저격! 키워드로 객실 찾기</h3>
                           <form action="<%=request.getContextPath()%>/booking/roomFilterByMainServlet.do" method="post">
                           <!-- BookingRoomFilterFromMainpageServlet -->
                               <input name="options" class="filter-search-value" type="hidden" placeholder=" 키워드 선택하기" readonly>
                               <div id="filter-header">
                                   <span id="filter-search-button"  >
                                      <button onClick="return chkSum2();"  id="selectBtn" style="background-color:#212529;">
                                         <img src="<%=request.getContextPath()%>/images/icon/search-3-24.png" alt="">
                                      </button>
                                   </span>
                                   <input class="filter-search" type="text" placeholder=" 키워드 선택하기" readonly>
                               </div>
                           </form>
                        </div>
                        <div id="filter-button">
                        
                            <div>
                                <label for="filter01">#오션뷰</label>
                                <label for="filter02">#선셋뷰</label>
                                <label for="filter03">#자쿠지</label>
                                <label for="filter04">#테라스</label>
                            </div>
                            <div>
                                <label for="filter05">#프라이빗 풀</label>
                                <label for="filter06">#노래방</label>
                                <label for="filter07">#빔 프로젝터</label>
                            </div>
                            <div>
                                <label for="filter08">#커피머신</label>
                                <label for="filter09">#웰컴드링크</label>
                                <label for="filter10">#구스다운침구</label>
                                <label for="filter11">#</label>
                            </div>

                            <div>
                                <input name="option" type="checkbox" id="filter01" class="filterbox" value="#오션뷰">
                                <input name="option" type="checkbox" id="filter02" class="filterbox" value="#선셋뷰">
                                <input name="option" type="checkbox" id="filter03" class="filterbox" value="#자쿠지">
                                <input name="option" type="checkbox" id="filter04" class="filterbox" value="#테라스">
                                <input name="option" type="checkbox" id="filter05" class="filterbox" value="#프라이빗 풀">
                                <input name="option" type="checkbox" id="filter06" class="filterbox" value="#노래방">
                                <input name="option" type="checkbox" id="filter07" class="filterbox" value="#빔 프로젝터">
                                <input name="option" type="checkbox" id="filter08" class="filterbox" value="#커피머신">
                                <input name="option" type="checkbox" id="filter09" class="filterbox" value="#웰컴드링크">
                                <input name="option" type="checkbox" id="filter10" class="filterbox" value="#구스다운침구">
                                <input name="option" type="checkbox" id="filter11" class="filterbox" value="#">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="swiper-slide"><img src="<%=request.getContextPath()%>/images/room/04.jpg"></div>
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
       
       //필터 체크 안되는 경우 체크
      $(function chkSum2() {
   /*       const valCk1 = $("select[class=availableDays] option:selected").val());//<option value>머무실 기간 선택</option>  */
          $(document).on("click","button[id=selectBtn]",()=>{
             if(filterDB==""){
               alert("'키워드'를 선택해주세요.");
               return false;
            };
            return true;
         });
      });   
      
   </script>
   
    <!-- 푸터 영역 -->
    <%@ include file="/views/common/footer.jsp" %>