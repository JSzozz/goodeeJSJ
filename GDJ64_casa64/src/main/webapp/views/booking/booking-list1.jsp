<%@page import="com.btc.booking.model.vo.OptionXtra"%>
<%@page import="com.btc.booking.model.vo.SeasonalPrice"%>
<%@page import="com.btc.booking.model.vo.Booking"%>
<%@page import="com.btc.rooms.model.vo.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 상준css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sj/style.css"/>
<% List<Room> rooms=(List<Room>)session.getAttribute("rooms");%>
<% List<Booking> bookings=(List<Booking>)session.getAttribute("bookings");%>
<% List<SeasonalPrice> seasons=(List<SeasonalPrice>)session.getAttribute("seasons");%>
<% List<OptionXtra> xtraOptions=(List<OptionXtra>)session.getAttribute("xtraOptions");%>
 
<%--           <%if(rooms.isEmpty()) {%>
         <h1>조회된 예약목록이 없습니다.</h1>
      <%} else{
         for(Room r:rooms){%>
         <%=r.getRoomNo() %>
         <%=r.getRoomName() %>
         <br>
      <%   }
      }%>  
      <br>
          <%if(bookings.isEmpty()) {%>
         <h1>조회된 예약목록이 없습니다.</h1>
      <%} else{
         for(Booking b:bookings){%>
         <%=b.getRoom().getRoomNo() %>
         <%=b.getCheckIn() %>
         <%=b.getCheckOut() %>
         <br>
      <%   }
      }%>  
      <br>  
          <%if(xtraOptions.isEmpty()) {%>
         <h1>조회된 예약목록이 없습니다.</h1>
      <%} else{
         for(OptionXtra o:xtraOptions){%>
         <%=o.getXtraName() %>
         <%=o.getXtraPrice() %>
         <%=o.getXtraExplanation() %>
         <br>
      <%   }
      }%> 
      <br> 
         <%if(seasons.isEmpty()) {%>
         <h1>조회된 예약목록이 없습니다.</h1>
      <%} else{
         for(SeasonalPrice s:seasons){%>
         <%=s.getSeason() %>
         <%=s.getStartDate() %>
         <%=s.getEndDate() %>
         <%=s.getWeekdayRate() %>
         <%=s.getWeekendRate() %>
         <br>
      <%   }
      }%> 
       <br> --%>

<%@ include file="/views/common/header.jsp"%>
<%-- <% if(loginMember!=null) {%>
	<%=loginMember %>
	<%=loginMember.getMemberName() %>   
	<%=loginMember.getMemberNo() %>
<%} %> --%>   
      <!-- 카테고리별 이미지 -->
      <div class="category-image">
         <img src="<%=request.getContextPath() %>/images/booking/reservation.png" width="100%" height="200px">
         <div>RESERVATION</div>
      </div>
   <br> 
   <!-- <h3>COSA64 펜션 객실 예약</h3> -->
   <br>
<section class="p-2 m-0 border-0 bd-example">
<!-- 0. 필터 버튼  --> <!-- 아이콘 출처 : https://tabler-icons.io/  -->
   <span class="btn-group dropend offset-xl-9 offset-md-1 col-md-1">
      <button class="btn btn-outline-dark dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
         <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-funnel-fill" viewBox="0 0 16 16">
           <path d="M1.5 1.5A.5.5 0 0 1 2 1h12a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.128.334L10 8.692V13.5a.5.5 0 0 1-.342.474l-3 1A.5.5 0 0 1 6 14.5V8.692L1.628 3.834A.5.5 0 0 1 1.5 3.5v-2z"/>
         </svg>
          객실 조회하기
      </button>
     <!-- modal -->
      <form action="<%=request.getContextPath()%>/booking/roomFilterServlet.do" method="post">
	      <ul class="dropdown-menu fixContainer" role="menu">
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="7">오션뷰</label></li>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="8">선셋뷰</label></li>
	          <hr>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="10">자쿠지</label></li>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="11">테라스</label></li>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="9">프라이빗 풀</label></li>
			  <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="13">노래방</label></li>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="12">빔 프로젝터</label></li>
	          <hr>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="5">커피머신</label></li>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="4">웰컴드링크</label></li>
	          <li class="checkbox keep-open"><label><input name="option" type="checkbox" value="1">구스다운침구</label></li>
				<center><button onClick="return chkSum2();" class="btn btn btn-outline-secondary btn-sm" id="selectBtn">조회하기</button></center>
	      </ul>
      </form>
   </span>
	<br><br>
<!-- 1. 객실 선택 -->
   <div class="reserve_step1 ">
      <table class="scriptCalendar table w-auto p-3">
         <thead style="background-color: #f3f3da;">
            <tr>
               <td class="calendarBtn" id="btnPrevCalendar">&#60;&#60;</td>
               <td colspan="5">
                  <span id="calYear">YYYY</span>년 
                  <span id="calMonth">MM</span>월
               </td>
               <td class="calendarBtn" id="nextNextCalendar">&#62;&#62;</td>
            </tr>
            <tr style="background-color: #f3f3da;">
               <td>일</td>
               <td>월</td>
               <td>화</td>
               <td>수</td>
               <td>목</td>
               <td>금</td>
               <td>토</td>
            </tr>
         </thead>
         <tbody>
         <!-- <tr>
               <td>
               날짜
               </td>
               <hr>
               <td>
                  <div>
                     <a>객실1</a>
                     <a>객실2</a>
                  </div>
               </td>
            </tr> -->
         </tbody>
      </table>
   </div>
   
<!-- 2. 객실 예약정보 입력-->
   <div class="reserve_step2 offset-md-2 col-md-8" id="reserveInput">
      <hr>
      <div class="header" id="tempHeader">
            <span>
                선택한 객실
                <em id="typeNm" >#typeNm</em>
            </span>
      </div>
        <div class="roundbox perio-wrapper" >
            <div class="header">
                <h3>기간선택</h3>
            </div>
            <div class="content" style="display: flex;">
                <div class="checkin"> 
                    <p>CHEKC IN</p>
                    <span id="checkinDt">#checkinDt</span>
                </div>
                <div>
                    <div style="color:white">.</div>
                    <div>~</div>
                </div>
                <div class="checkout on"> 
                    <p>CHEKC OUT</p>
                    <span id="checkoutDt">#checkoutDt</span>
                </div>   
                <div>
                    <select class="availableDays">
                        <option value>머무실 기간 선택</option>
                        <!-- <option value="1" price="a" consprice="A">1박</option>
                        <option value="2" price="b" consprice="B">2박</option>
                        <option value="3" price="c" consprice="C">3박</option>
                        <option value="4" price="d" consprice="D">4박</option>
                        <option value="5" price="e" consprice="E">5박</option>
                        <option value="6" price="f" consprice="F">6박</option>
                        <option value="7" price="g" consprice="G">7박</option> -->
                    </select>
                </div> 
            </div>    
        </div>
        <div class="roundbox extra-person-wrapper">
            <div class="header">
                <h3 >인원</h3>
                <h5 class="capCheck"></h5>
            </div>
            <div class="content" id="countPerson">
                <ul>
                    <li>
                        <label id="adtNm">성인</label>
                        <select class="adultPers" id="adultPers">  
                            <option value=".">선택</option>
                            <option value="1" price="">1명</option>
                            <option value="2" price="">2명</option>
                            <option value="3" price="">3명</option>
                            <option value="4" price="">4명</option>
                            <option value="5" price="20000">5명</option>
                            <option value="6" price="20000">6명</option>
                        </select>
                    </li>
                    <li>
                        <lable id="kidNm">아동/유아</lable>
                        <select class="kidsPers" id="kidsPers">  
                            <option value=".">선택</option>
                            <option value="0">없음</option>
                            <option value="1" price="20000">1명</option>
                            <option value="2" price="20000">2명</option>
                            <option value="3" price="20000">3명</option>
                            <option value="4" price="20000">4명</option>
                        </select>
                    </li>
                    <li>
                        <lable id="infNm">영아(24개월 미만)</lable>
                        <select class="infPers" id="infPers">  
                            <option value="0">없음</option>
                            <option value="1" price="20000">1명</option>
                            <option value="" price="20000">2명</option>
                            <option value="3" price="20000">3명</option>
                            <option value="4" price="20000">4명</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>
        <div class="roundbox options-wrapper">
            <div class="header">
                <h3>옵션선택</h3>
            </div>
            <div class="content">
                <ul id="optnList">
                   <!-- <li>                 
                        <input type="checkbox" id="OPTN1" OptionPrice="30000" value="101" >
                        <lable>온수옵션(30,000원)</lable>
                        <button type="button" class="btn btn-outline-dark btn-sm" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용 금액 : n0,000원">
                            상세설명
                        </button>
                    </li>
                    <li>
                        <input type="checkbox"  id="OPTN2"  data-cnt="30000"  class="optCode op_name" value="102" data-tooltip >
                        <lable class="op_la">물놀이용 수영장(30,000원)</lable>
                        <button type="button" class="btn btn btn-outline-dark btn-sm" data-bs-toggle="tooltip" data-bs-placement="right" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용 금액 : n0,000원">
                            상세설명
                        </button>
                    </li>                
                    <li>
                        <input type="checkbox"  id="OPTN3"  data-cnt="40000"  class="optCode op_name" value="103" data-tooltip >
                        <lable class="op_la">바베큐-4인기준(40,000원)</lable>
                        <button type="button" class="btn btn btn btn-outline-dark btn-sm" data-bs-toggle="tooltip" data-bs-placement="right" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용  금액 : n0,000원">
                            상세설명
                        </button>
                    </li>   -->           
                </ul>
            </div>
        </div>
        <div class="roundbox summary-wrapper">
            <div class="header">
                <h3>금액</h3>
            </div>
            <div class="content-amt content">
                <div>
                    <table>
                        <tbody>
                            <tr>
                                <th>객 실 금 액 &nbsp;&nbsp;&nbsp;: </th>
                                <td><span id="roomPrice">0</span> 원</td>
                            </tr>
                            <tr>
                                <th>인원추가금액 : </th>
                                <td><span id="persePrice">0</span> 원</td>
                            </tr>
                            <tr>
                                <th>옵 션 금 액  &nbsp;&nbsp;&nbsp;: </th>
                                <td><span id="optnPrice">0</span> 원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!-- (연박 할인가능) --></td>
                            </tr>
                            <tr id="consApply" style="display:none">
                                <th>연 박 할 인 &nbsp;&nbsp;&nbsp;: </th>
                                <td><span id="consApplyPrice">0</span>원</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <hr>
                <div>
                    <p>
                        총 금액
                        <br>
                        <em id="totPrice"><b>0</b></em>원
                    </p> 
                </div>
            </div>
        </div>
		<hr>
        <!-- </form> -->
        <div class="buttons">
        	<form name="bookFrm" action="<%=request.getContextPath()%>/booking/bookingList1ToList2.do" method="post">
           		<p>체크아웃 날짜와 인원(성인, 영유아)을 선택하시면 다음 단계로 넘어가실 수 있습니다.</p>
           		<button onClick="return chkSum();" class="button-1 btn btn btn-outline-dark btn-lg" id="goNextBtn" <%-- onclick="location.href='<%=request.getContextPath() %>/views/booking/booking-list2.jsp' " --%>>
					다음단계
           		</button>
				<input type="hidden" name="roomNm" value="">
				<input type="hidden" name="checkin" value="">
				<input type="hidden" name="checkout" value="">
				<input type="hidden" name="guestAdult" value="">
				<input type="hidden" name="guestChild" value="">
				<input type="hidden" name="guestInfant" value="">
				<input type="hidden" name="bookingPrice" value="">
				<input type="hidden" name="optionList" value="">
				
				<input type="hidden" name=roomPrice value="">
				<input type="hidden" name="persePrice" value="">
				<input type="hidden" name="optnPrice" value="">
			</form>
		</div>
    </div>
</section>
<script>
   /* const roomNo=[]; *//* const roomSize=[]; */
   /* const roomImage=[]; *//* const dateCreated=[]; *//* const dateModified=[]; */
   
   /* ROOM 정보 받기 */
   const roomNo=[]; const roomName=[];const roomPrce=[];const roomCap=[];
   const roomMaxCap=[];const bookable=[];const roomDescription=[];
   <%for(Room room:rooms) {%>
   		roomNo.push("<%=room.getRoomNo()%>");
        roomName.push("<%=room.getRoomName()%>");
        roomPrce.push("<%=room.getRoomPrice()%>");
        roomCap.push("<%=room.getRoomCap()%>");
        roomMaxCap.push("<%=room.getRoomMaxCap()%>");
        bookable.push("<%=room.getBookable()%>");
        roomDescription.push("<%=room.getRoomDescription()%>");
   <%}%>
   /* 예약된 BOOKING 정보 받기 */
   	const bookingRoomNo=[]; const checkIn=[]; const checkOut=[];
	<%for(Booking b:bookings){%>
		bookingRoomNo.push("<%=b.getRoom().getRoomNo()%>");
		checkIn.push("<%=b.getCheckIn()%>");
		checkOut.push("<%=b.getCheckOut()%>");
	<%} %>
	
/*    $("#searchType").change(e=>{
       const type=$(e.target).val();
       $(e.target).parent().find("div").css("display","none");
       $("#search-"+type).css("display","inline-block");
   }); */
   
   /* 유료 옵션 정보 받기 */
  	const XtraName=[]; const XtraPrice=[]; const XtraExplanation=[];
	<%for(OptionXtra o:xtraOptions){%>
		XtraName.push("<%=o.getXtraName() %>");
		XtraPrice.push("<%=o.getXtraPrice() %>");
		XtraExplanation.push(" <%=o.getXtraExplanation() %>");
	<%} %>
   
	
	/* 성수기 시즌 정보 받기 */
	let $seasonStart; let $seasonEnd;
    <%for(SeasonalPrice s:seasons){%>
		seasonStart= Date("<%=s.getStartDate() %>");
		seasonEnd = Date("<%=s.getEndDate() %>");
		console.log(seasonEnd);
	<%}%>
	
   $('.keep-open').click(function(e) {
        if (/input|label/i.test(e.target.tagName)){
          var parent = $(e.target).parent();
          if(parent.hasClass('checkbox')){
            var checkbox = parent.find('input[type=checkbox]');
            checkbox.prop("checked", !checkbox.prop("checked"));
            return false;
          };
        };
	});
   
   let checkedValues = [];
   console.log(checkedValues.length==0);
   $(document).ready(function() {
		$('#selectBtn').on('click', function() {
			$('input[type="checkbox"]').each(function() {
				if ($(this).is(':checked')) {
					checkedValues.push($(this).val());
					console.log($(this).is(':checked'))
				};
				console.log(checkedValues);
			});
	   });
   });
   
   //필터 체크 안되는 경우 체크
	$(function chkSum2() {
/* 		const valCk1 = $("select[class=availableDays] option:selected").val());//<option value>머무실 기간 선택</option>  */
 		$(document).on("click","button[id=selectBtn]",()=>{
 			if(checkedValues.length==0){
				alert("'필터 조건'을 선택해주세요.");
				return false;
			};
			return true;
		});
	});		
   
</script>
<script src="<%=request.getContextPath()%>/js/sj/list1-clickevent.js"></script>
<script src="<%=request.getContextPath()%>/js/sj/list1-claendar.js"></script>
<script>
<%-- 	if(<%=loginMember%>==null){
	alert("객실 예약은 로그인 후 진행 가능합니다.");
	} --%>

	<%-- <%if(loginMember == null) {%>
    alert("예약은 로그인 후 이용가능합니다.");
	<%}%>  --%>
	
	$(function chkSum() {
/* 		const valCk1 = $("select[class=availableDays] option:selected").val());//<option value>머무실 기간 선택</option>  */
 		$(document).on("click","button[id=goNextBtn]",()=>{
<%--  			if(<%=loginMember%>==null){
 				alert("객실 예약은 로그인 후 진행 가능합니다.");
 				return false;
 			} --%>
 			<%if(loginMember == null) {%>
	          alert("로그인 후 이용가능");
	         location.assign("<%=request.getContextPath()%>/views/LOGIN/login.jsp");
	         return false;
	       <%}%>
 			if($("select[class=availableDays] option:selected").val()==""){
				alert("'숙박기간'을 정해주세요.");
				$('.availableDays').select().focus();
				return false;
			}else if($("select[class=adultPers] option:selected").val()=="."){
				alert("'(성인)이용인원'을 정해주세요.");
				$('.adultPers').select().focus();
				return false;
			}else if($("select[class=kidsPers] option:selected").val()=="."){
				alert("'(아동/유아)이용인원'을 정해주세요.");
				$('.kidsPers').select().focus();
				return false;
			};
			$('input[name=roomNm]').attr('value',$("#typeNm").text());
			$('input[name=checkin]').attr('value',$("#checkinDt").text());
			$('input[name=checkout]').attr('value',$("#checkoutDt").text());
			$('input[name=guestAdult]').attr('value',Number($("select[class=adultPers] option:selected").attr('value')));
			$('input[name=guestChild]').attr('value',Number($("select[class=kidsPers] option:selected").attr('value')));
			$('input[name=guestInfant]').attr('value',Number($("select[class=infPers] option:selected").attr('value')));
			$('input[name=bookingPrice]').attr('value',Number($("#totPrice").text()));
			
			$('input[name=roomPrice]').attr('value',$("#roomPrice").text());
			$('input[name=persePrice]').attr('value',$("#persePrice").text());
			$('input[name=optnPrice]').attr('value',$("#optnPrice").text());
			return true;
		});
		
	});	
		
	//로딩된 화면에서, 예약이 있는 일자의 객실 클릭 비활성화 기능
 	$(function() {
 		checkin_checkout();
 	});
 	
/* checkIn-checkOut 날짜 차이 구하기 
 (ex. let stringDate = '2022-05-19'; StringToDate(stringDate, 5); //-> 2022-05-24 )*/
	function StringToDate(date, n) {
		let yyyy = date.substring(0, 4);
		let mm = date.substring(5, 7);
		let dd = date.substring(8, 10);
		mm = Number(mm) - 1;
		let stringNewDate = new Date(yyyy, mm, dd);
		stringNewDate.setDate(stringNewDate.getDate() + n);
		return stringNewDate.getFullYear() +
			"-" + ((stringNewDate.getMonth() + 1) > 9 ? (stringNewDate.getMonth() + 1).toString() : "0" + (stringNewDate.getMonth() + 1)) +
			"-" + (stringNewDate.getDate() > 9 ? stringNewDate.getDate().toString() : "0" + stringNewDate.getDate().toString());
	};
	function checkin_checkout(){
	/* checkIn-checkOut 사이 날짜 구해서 클릭기능 해제하기*/
		for(let i=0;i<checkIn.length;i++){
			const checkRoomNo=bookingRoomNo[i];
			const startDay = new Date(checkIn[i]);
			const endDay = new Date(checkOut[i]);
			let diff = Math.abs(endDay.getTime() - startDay.getTime());
			diff = (Math.ceil(diff / (1000 * 60 * 60 * 24)));
	/*  	console.log(bookingRoomNo[i]+" :"+diff);//number
	 		console.log(typeof diff); */
	 		for(let j=0; j<diff;j++){
	/* 			console.log(bookingRoomNo[i]+":"+StringToDate(checkIn[i], j));
	 	 		console.log(typeof StringToDate(checkIn[i], j));//string */
	 			$('div[roomno='+bookingRoomNo[i]+'][class='+StringToDate(checkIn[i], j)+']')/* .css("text-decoration", "line-through") */
	 			.attr("bookable","N").css("backgroundColor","pink").css("color","gray").css("cursor","default");
			};
		};
	};
	
	function checkAmountOfLodgment(e){
    //선택한 날짜와 가장 가까운 다음 체크인 날짜와의 차이 구하기
	    const checkday=$(e.target).attr("class");//클릭한 객실의 날짜
	    const checkroom=$(e.target).attr("roomno");//클릭한 객실의 방번호
	    let availableDay=5;
	    for(i=0;i<bookingRoomNo.length;i++){
			 const oldDate = new Date(checkday);//클릭한 객실
			 const newDate = new Date(checkIn[i]);//예약된 체크인 날짜들
			 if(bookingRoomNo[i]==checkroom){
				 if(oldDate<newDate){
					 let diff = Math.abs(newDate.getTime() - oldDate.getTime());
					 diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
					 /* console.log(oldDate);
					 console.log(newDate);
					 console.log("날짜 차: "+diff); */
					 if(availableDay>diff){
					 	availableDay=diff;// 값
					 }
				 };
			 };
		 };return availableDay;
	};
	
	function insertSelectTag (e, availableDay){
		const checkRoomday=$(e.target).attr("class");
		const checkRoomNo=$(e.target).attr("roomno");
		const checkRoomPrice=$(e.target).attr("price");
 		let date = new Date(checkRoomday);
		$('select[class=availableDays]').children('option:not(:first)').remove();
 		for(i=0; i<availableDay;i++){
 			console.log(availableDay);
 			date.setDate(date.getDate() + i);
 			const $day=dateFormatChange(date);
			let $roomPrice=$('div[roomno='+checkRoomNo+'][class='+$day+']').attr("price");
 			if($roomPrice==undefined){
 	 			$roomPrice=$('div[roomno='+checkRoomNo+'][class='+StringToDate($day, -14)+']').attr("price");
 				console.log("나가리"+$roomPrice);
 			}else{
 				console.log("진땡"+$roomPrice);
 			};
 			const $option= $("<option>"+(i+1)+"박</option>").attr({"value":(i+1),"price":$roomPrice});
 			$('select[class=availableDays]').append($option);
 		}; 
	};
	
	function dateFormatChange(dateValue){
	    const year = dateValue.getFullYear();
	    const month = ('0' + (dateValue.getMonth() + 1)).slice(-2);
	    const day = ('0' + dateValue.getDate()).slice(-2);
	    const checkoutDt = year+"-"+month+"-"+day;//(String)
	    // 어떤 날짜여도 'YYYY-DD-YY'형식으로 변환!
	    return checkoutDt;
	};
	
	function checkLogin(){
		<%if(loginMember == null) {%>
	    alert("예약은 로그인 후 이용가능합니다.");
		<%}%> 
	}

	
	</script>
	
	
	<script>
      let filterDB="";
          $(document).ready(function(){
              $('input[name="xtraOption"]').change(function(){
                  if($(this).is(":checked")) {
                      filterDB+=this.value+",";
                  }else{
                      filterDB=filterDB.replace(this.value+",",'');
                  }
                     /* filterDB = filterDB.substr(0, filterDB.length-1); */
                  console.log(filterDB);
/*                      $("input[name='QnaFilter']").val(filterDB);
                     $(".filterQna").submit();
 */              });
          });
      </script>
	
	

      <!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>