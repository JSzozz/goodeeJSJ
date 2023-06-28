<%@page import="com.btc.booking.model.vo.Booking"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 상준css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sj/style.css"/>
      <!-- 헤더 영역 시작 -->
<%@ include file="/views/common/header.jsp"%>
      <!-- 헤더 영역 종료 -->
<%Booking booking=(Booking)session.getAttribute("booking");%>


      <!-- 카테고리별 이미지 -->
      <div class="category-image">
         <img src="<%=request.getContextPath() %>/images/booking/reservation.png" width="100%" height="200px">
         <div>RESERVATION</div>
      </div>
      
<br><br><br>
      
   <!-- 컨텐츠/내용 시작 -->
   <center>
      <h1><b>결제가 완료되었습니다.</b></h1><br><br>
      
      
      <button class="btn btn-dark btn-lg" onclick ="location.href='<%=request.getContextPath()%>/myPage/myPageReservation'">
      결제 내역 확인하기
      </button>
   </center>
   <!-- 내용 종료 -->

   </div>
   
</section>

      <!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>