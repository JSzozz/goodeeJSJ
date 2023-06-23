<%@page import="com.btc.rooms.model.vo.Room"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- 상준css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sj/style.css"/>
<%
   List<Room> rooms=(List<Room>)session.getAttribute("rooms");
%>
<%--       
      <%if(rooms.isEmpty()) {%>
         <h1>조회된 방이 없습니다.</h1>
      <%} else{
         for(Room r:rooms){%>
         <%=r.getRoomName() %>
         <%=r.getRoomPrice() %>
         <br>
      <%   }
      }%>    
--%>
      
<%-- <script src="<%=request.getContextPath()%>/js/reserve.js"></script> --%>
<%@ include file="/views/common/header.jsp"%>
   
      <!-- 카테고리별 이미지 -->
      <div class="category-image">
         <img src="<%=request.getContextPath() %>/images/reserve/reservation.png" width="100%" height="200px">
         <div>RESERVATION</div>
      </div>
      
   <br> 
   
   <!-- <h3>COSA64 펜션 객실 예약</h3> -->
   
   <br>
   
<section class="p-2 m-0 border-0 bd-example">

<!-- 0. 필터 버튼  --> <!-- 아이콘 출처 : https://tabler-icons.io/  -->
   <span class="btn-group dropend offset-xl-8 offset-md-1 col-md-1">
      <button class="btn btn-outline-dark dropdown-toggle " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
         <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-funnel-fill" viewBox="0 0 16 16">
           <path d="M1.5 1.5A.5.5 0 0 1 2 1h12a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-.128.334L10 8.692V13.5a.5.5 0 0 1-.342.474l-3 1A.5.5 0 0 1 6 14.5V8.692L1.628 3.834A.5.5 0 0 1 1.5 3.5v-2z"/>
         </svg>
          객실 조회하기
      </button>
     <!-- modal -->
      <ul class="dropdown-menu" role="menu">
         <li class="checkbox keep-open"><label><input type="checkbox">오션 뷰</label></li>
          <li class="checkbox keep-open"><label><input type="checkbox">시티 뷰</label></li>
          <li class="checkbox keep-open"><label><input type="checkbox">프리미엄</label></li>
          <hr>
          <li class="checkbox keep-open"><label><input type="checkbox">프라이빗 풀</label></li>
          <li class="checkbox keep-open"><label><input type="checkbox">뮤직룸</label></li>
          <li class="checkbox keep-open"><label><input type="checkbox">프로젝터</label></li>
          <hr>
          <li class="checkbox keep-open"><label><input type="checkbox">커피 머신</label></li>
          <li class="checkbox keep-open"><label><input type="checkbox">구스다운 베드</label></li>
          <li class="checkbox keep-open"><label><input type="checkbox">웰컴 드링크</label></li>
          <li><a class="btn btn btn-outline-secondary btn-sm">조회하기</a></li>
      </ul>
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
                        <option value="1" price="a" consprice="A">1박</option>
                        <option value="2" price="b" consprice="B">2박</option>
                        <option value="3" price="c" consprice="C">3박</option>
                        <option value="4" price="d" consprice="D">4박</option>
                        <option value="5" price="e" consprice="E">5박</option>
                        <option value="6" price="f" consprice="F">6박</option>
                        <option value="7" price="g" consprice="G">7박</option>
                    </select>
                </div> 
            </div>    
        </div>

        <div class="roundbox extra-person-wrapper">
            <div class="header">
                <h3>인원</h3>
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
                    <li>                 
                        <input type="checkbox"  id="OPTN1"  data-cnt="30000"  class="optCode op_name" value="101" data-tooltip >
                        <lable class="op_la">온수옵션(30,000원)</lable>
                        <button type="button" class="btn btn btn-outline-dark btn-sm" data-bs-toggle="tooltip" data-bs-placement="right" title="예약시간 준수 및 사용시간 2시간 제한 있습니다. 이용 금액 : n0,000원">
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
                    </li>               
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
                                <td><span id="optnPrice">0</span> 원&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(연박 할인가능)</td>
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
       <div class="buttons"  >
           <p>체크아웃 날짜와 인원(성인, 영유아)을 선택하시면 다음 단계로 넘어가실 수 있습니다.</p>
           <button class="button-1 btn btn btn-outline-dark btn-lg" id="goNextBtn"
           onclick="location.href='<%=request.getContextPath() %>/views/reserve/reserveList2.jsp' "
           >다음단계</button>
       </div>
    </div>
</section>

<script>

     /* ["오션 테라스 101","오션 테라스 201","오션 테라스 301",
        "썬셋 테라스 101","썬셋 테라스 201","썬셋 테라스 301",
          "프리미엄스파 101","프리미엄스파 201"]; */
      // 객실 목록(roomName)
          
   /* const roomNo=[]; */
   const roomName=[];
   const roomPrce=[];
   /* const roomSize=[]; */
   const roomCap=[];
   const roomMaxCap=[];
   const bookable=[];
   /* const roomImage=[]; */
   /* const dateCreated=[]; */
   /* const dateModified=[]; */
   const roomDescription=[];
   
   <%for(Room room:rooms) {%>
         roomName.push("<%=room.getRoomName()%>");
         roomPrce.push("<%=room.getRoomPrice()%>");
         roomCap.push("<%=room.getRoomCap()%>");
         roomMaxCap.push("<%=room.getRoomMaxCap()%>");
         bookable.push("<%=room.getBookable()%>");
         roomDescription.push("<%=room.getRoomDescription()%>");
   <%}%>
   
      console.log(roomName);
      console.log(roomPrce);
      console.log(roomCap);
      console.log(roomMaxCap);
      console.log(bookable);
      console.log(roomDescription); 
   
/*    $("#searchType").change(e=>{
       const type=$(e.target).val();
       $(e.target).parent().find("div").css("display","none");
       $("#search-"+type).css("display","inline-block");
   }); */
   
   $('.keep-open').click(function(e) {
        if (/input|label/i.test(e.target.tagName)){
          var parent = $(e.target).parent();
          if(parent.hasClass('checkbox')){
            var checkbox = parent.find('input[type=checkbox]');
            checkbox.prop("checked", !checkbox.prop("checked"));
            return false;
          }
        }
      });
</script>

<script>
   document.addEventListener("DOMContentLoaded", function() {
       buildCalendar();
       document.getElementById("btnPrevCalendar").addEventListener("click", function(event) {
           prevCalendar();
       });
       document.getElementById("nextNextCalendar").addEventListener("click", function(event) {
           nextCalendar();
       });
   });
   
   var toDay = new Date(); // @param 전역 변수, 오늘 날짜 / 내 컴퓨터 로컬을 기준으로 toDay에 Date 객체를 넣어줌
   var nowDate = new Date();  // @param 전역 변수, 실제 오늘날짜 고정값
   
   /**
    * @brief   이전달 버튼 클릭시
    */
   function prevCalendar() {
       this.toDay = new Date(toDay.getFullYear(), toDay.getMonth() - 1, toDay.getDate());
       buildCalendar();    // @param 전월 캘린더 출력 요청
   }
   
   /**
    * @brief   다음달 버튼 클릭시
    */
   function nextCalendar() {
       this.toDay = new Date(toDay.getFullYear(), toDay.getMonth() + 1, toDay.getDate());
       buildCalendar();    // @param 명월 캘린더 출력 요청
   }
   
   /**
    * @brief   캘린더 오픈
    * @details 날짜 값을 받아 캘린더 폼을 생성하고, 날짜값을 채워넣는다.
    */
   function buildCalendar() {
   
       let doMonth = new Date(toDay.getFullYear(), toDay.getMonth(), 1);
       let lastDate = new Date(toDay.getFullYear(), toDay.getMonth() + 1, 0);
   
       let tbCalendar = document.querySelector(".scriptCalendar > tbody");
   
       document.getElementById("calYear").innerText = toDay.getFullYear();                       // @param YYYY월
       document.getElementById("calMonth").innerText = autoLeftPad((toDay.getMonth() + 1), 2);   // @param MM월
       
   
       // @details 이전 캘린더의 출력결과가 남아있다면, 이전 캘린더를 삭제한다.
       while(tbCalendar.rows.length > 0) {
           tbCalendar.deleteRow(tbCalendar.rows.length - 1);
       }
   
       // @param 첫번째 개행
       let row = tbCalendar.insertRow();
   
       // @param 날짜가 표기될 열의 증가값
       let dom = 1;
   
       // @details 시작일의 요일값( doMonth.getDay() ) + 해당월의 전체일( lastDate.getDate())을  더해준 값에서
       //               7로 나눈값을 올림( Math.ceil() )하고 다시 시작일의 요일값( doMonth.getDay() )을 빼준다.
       let daysLength = (Math.ceil((doMonth.getDay() + lastDate.getDate()) / 7) * 7) - doMonth.getDay();
   
       // @param 달력 출력
       // @details 시작값은 1일을 직접 지정하고 요일값( doMonth.getDay() )를 빼서 마이너스( - )로 for문을 시작한다.
       for(let day = 1 - doMonth.getDay(); daysLength >= day; day++) {
   
           let column = row.insertCell();
           
           // @param 평일( 전월일과 익월일의 데이터 제외 )
           if(Math.sign(day) == 1 && lastDate.getDate() >= day) {
/* 일자 */   
               // @param 평일 날짜 데이터 삽입 & <hr>
               const divNode = document.createElement("div");
               const aNode = document.createElement("a");
               aNode.textContent = autoLeftPad(day, 2);
               divNode.appendChild(aNode);
               column.appendChild(divNode);
              
               const hrNode=document.createElement("hr");
               column.append(hrNode);
   
               // @param 일요일인 경우
               if(dom % 7 == 1) {
                   column.style.color = "#FF4D4D";
               }
   
               // @param 토요일인 경우
               if(dom % 7 == 0) {
                   column.style.color = "#4D4DFF";
                   row = tbCalendar.insertRow();   // @param 토요일이 지나면 다시 가로 행을 한줄 추가한다.
               }
               
               // 객실 리스트 입력
               for(let i=0;i<roomName.length;i++){
                   const divNode = document.createElement("div");
                   divNode.textContent = roomName[i];
                   column.appendChild(divNode);
                   divNode.setAttribute("class",toDay.getFullYear()+"-"+autoLeftPad((toDay.getMonth() + 1), 2)+"-"+autoLeftPad(day, 2));
                   divNode.setAttribute("bookable","available");
                   divNode.setAttribute("price",roomPrce[i]);
               }
              
                  column.style.margin="1px 1px 1px 1px";
                    column.style.padding="1px 1px 1px 1px";
           }
   
           // @param 평일 전월일과 익월일의 데이터 날짜변경
           else {
               let exceptDay = new Date(doMonth.getFullYear(), doMonth.getMonth(), day);
               column.innerText = autoLeftPad(exceptDay.getDate(), 2);
               column.style.color = "#A9A9A9";
           }
   
           // @brief   전월, 명월 음영처리
           // @details 현재년과 선택 년도가 같은경우
           if(toDay.getFullYear() == nowDate.getFullYear()) {
   
               // @details 현재월과 선택월이 같은경우
               if(toDay.getMonth() == nowDate.getMonth()) {
   
                   // @details 현재일보다 이전인 경우이면서 현재월에 포함되는 일인경우
                   if(nowDate.getDate() > day && Math.sign(day) == 1) {
                       column.style.backgroundColor = "#E5E5E5";
                       
                  /* (Tip).children : 자식노드를 배열 형태로 반환 */
                  for(let i=1;i<column.children.length;i++){
                     column.children[i].setAttribute("bookable","unavailable");
                     column.children[i].style.color="gray";
                     column.children[i].style.fontSize="0.95rem";

                  }
                   }   
   
                   // @details 현재일보다 이후이면서 현재월에 포함되는 일인경우
                   else if(nowDate.getDate() < day && lastDate.getDate() >= day) {
                       column.style.backgroundColor = "#FFFFFF";
                       column.style.cursor = "pointer";
                       column.onclick = function(){ calendarChoiceDay(this); }
                   }
   
                   // @details 현재일인 경우
                   else if(nowDate.getDate() == day) {
                       column.style.backgroundColor = "#FFFFE6";
                       column.style.cursor = "pointer";
                       column.onclick = function(){ calendarChoiceDay(this); }
                   }
   
               // @details 현재월보다 이전인경우
               } else if(toDay.getMonth() < nowDate.getMonth()) {
                   if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                       column.style.backgroundColor = "#E5E5E5";
                   }
               }
   
               // @details 현재월보다 이후인경우
               else {
                   if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                       column.style.backgroundColor = "#FFFFFF";
                       column.style.cursor = "pointer";
                       column.onclick = function(){ calendarChoiceDay(this); }
                   }
               }
           }
   
           // @details 선택한년도가 현재년도보다 작은경우
           else if(toDay.getFullYear() < nowDate.getFullYear()) {
               if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                   column.style.backgroundColor = "#E5E5E5";
               }
           }
   
           // @details 선택한년도가 현재년도보다 큰경우
           else {
               if(Math.sign(day) == 1 && day <= lastDate.getDate()) {
                   column.style.backgroundColor = "#FFFFFF";
                   column.style.cursor = "pointer";
                   column.onclick = function(){ calendarChoiceDay(this); }
               }
           }
           dom++;
       }
   }
   
   /**
    * @brief   날짜 선택
    * @details 사용자가 선택한 날짜에 체크표시를 남긴다.
    */
   function calendarChoiceDay(column) {
   
       // @param 기존 선택일이 존재하는 경우 기존 선택일의 표시형식을 초기화 한다.
       if(document.getElementsByClassName("choiceDay")[0]) {
           
           // @see 금일인 경우
           if(document.getElementById("calMonth").innerText == autoLeftPad((nowDate.getMonth() + 1), 2) && document.getElementsByClassName("choiceDay")[0].innerText == autoLeftPad(toDay.getDate(), 2)) {
               document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFE6";  
           }
           
           // @see 금일이 아닌 경우
           else {
               document.getElementsByClassName("choiceDay")[0].style.backgroundColor = "#FFFFFF";
           }
           document.getElementsByClassName("choiceDay")[0].classList.remove("choiceDay");
       }
   
       // @param 선택일 체크 표시
       column.style.backgroundColor = "#FF9999";
   
       // @param 선택일 클래스명 변경
       column.classList.add("choiceDay");
   }
   
   /**
    * @brief   숫자 두자릿수( 00 ) 변경
    * @details 자릿수가 한자리인 ( 1, 2, 3등 )의 값을 10, 11, 12등과 같은 두자리수 형식으로 맞추기위해 0을 붙인다.
    * @param   num     앞에 0을 붙일 숫자 값
    * @param   digit   글자의 자릿수를 지정 ( 2자릿수인 경우 00, 3자릿수인 경우 000 … )
    */
   function autoLeftPad(num, digit) {
       if(String(num).length < digit) {
           num = new Array(digit - String(num).length + 1).join("0") + num;
       }
       return num;
   }
</script>


<script>

/* 달력 객실 선택 효과 */
   $(function() {
       $(document).on("mouseover","div[bookable=available]",e=> {
           $(e.target).css("font-weight","bold");
        });
       $(document).on("mouseout","div[bookable=available]",e=> {
           $(e.target).css("font-weight","normal");
        });   
   });

/* 달력에서 객실 클릭 */
<<<<<<< HEAD
	let totalBookPrice=0;
	let totalRoomPrice=0;
	$(function() {
		let checkInDt="";//(String)
		let roomPrice=0;
=======
   let totalBookPrice=0;
   let totalRoomPrice=0;
   $(function() {
      let checkInDt="";//(String)
      let roomPrice=0;
>>>>>>> branch 'main' of https://github.com/Muggung/btc-casa64-project.git

       $(document).on("click","div[bookable=available]",e=> {
             $(".reserve_step2").show();
/*              console.dir($(e.target));
             console.log($(e.target).text());
             console.log($(e.target).parent().attr("class"));
             console.log($(e.target).attr("class"));
             console.dir($(e.target).attr("price")); */
             $(e.target).css("text-decoration","underline");

             $("#typeNm").html($(e.target).text());
             $("#checkinDt").html($(e.target).attr("class"));
             
             checkInDt=$(e.target).attr("class");

             roomPrice = Number($(e.target).attr("price"));// /* 1. 기간선택& 4. 금액 */과 이어짐
             console.log(roomPrice);
             
       });
       
/* 이용가능한 객실 선택 _ 예약 정보 받기 */
      $(function() {
         $(document).on("change","select[class=availableDays]",e=>{
/* 1. 기간선택& 4. 금액 */            
            const stringToDate=new Date(checkInDt);//(Date)
            /* console.log(checkOut+Number($("select[class=availableDays] option:selected").val())); */
            stringToDate.setDate(stringToDate.getDate() + Number($("select[class=availableDays] option:selected").val()));

<<<<<<< HEAD
				const year = stringToDate.getFullYear();
				const month = ('0' + (stringToDate.getMonth() + 1)).slice(-2);
				const day = ('0' + stringToDate.getDate()).slice(-2);
		        const checkoutDt = year+"-"+month+"-"+day;//(String)
		        // 어떤 날짜여도 'YYYY-DD-YY'형식으로 변환!
		        
		        $("#checkoutDt").html(checkoutDt);
/*  			console.log($("select[class=availableDays] option:selected").val());
				console.log($("select[class=availableDays] option:selected").attr('price'));
				console.log($("select[class=availableDays] option:selected").attr('consprice'));
				console.log($("select[class=availableDays] option:selected").text()); */ 
				
		        /* totalRoomPrice */
		        let period = Number($("select[class=availableDays] option:selected").val());
		        totalRoomPrice=period*roomPrice;
		        $("#roomPrice").html(totalRoomPrice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
		          
			});
		});
	 });
=======
            const year = stringToDate.getFullYear();
            const month = ('0' + (stringToDate.getMonth() + 1)).slice(-2);
            const day = ('0' + stringToDate.getDate()).slice(-2);
              const checkoutDt = year+"-"+month+"-"+day;//(String)
              // 어떤 날짜여도 'YYYY-DD-YY'형식으로 변환!
              
              $("#checkoutDt").html(checkoutDt);
/*           console.log($("select[class=availableDays] option:selected").val());
            console.log($("select[class=availableDays] option:selected").attr('price'));
            console.log($("select[class=availableDays] option:selected").attr('consprice'));
            console.log($("select[class=availableDays] option:selected").text()); */ 
            
              /* totalRoomPrice */
              let period = Number($("select[class=availableDays] option:selected").val());
              totalRoomPrice=period*roomPrice;
              $("#roomPrice").html(totalRoomPrice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
                
         });
      });
    });
>>>>>>> branch 'main' of https://github.com/Muggung/btc-casa64-project.git
/* 2. 인원 */
<<<<<<< HEAD
	let adultPers, kidsPers, infPers, totalPerson;
	let personPrce=0;
	$(function() {
		$(document).on("change","select[class=adultPers]",e=>{
			adultPers = Number($("select[class=adultPers] option:selected").attr('value'));
			pers=adultPers+kidsPers;
			/* console.log(pers); */
			switch(pers){
				case 0 : personPrce=0; color="black"; break;
				case 1 : personPrce=0; color="black"; break;
				case 2 : personPrce=0; color="black"; break;
				case 3 : personPrce=0; color="black"; break;
				case 4 : personPrce=0; color="black"; break;
				case 5 : personPrce=20000; color="black"; break;
				case 6 : personPrce=40000; color="black"; break;
				case 7 : personPrce=60000; color="black"; break;
				case 8 : personPrce=80000; color="black"; break;
				default: personPrce="'성인', '아동/유아' 인원 수를 선택해주세요"; color="red"; break;
			};
			$("#persePrice").html(personPrce).css("color",color);
			if(typeof personPrce=="string"){
				totalBookPrice="";		
			}else{
	        	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
			}
	        $("#totPrice").html(totalBookPrice);
		});
	});	
	$(function() {
		$(document).on("change","select[class=kidsPers]",e=>{
			kidsPers = Number($("select[class=kidsPers] option:selected").attr('value'));
			pers=adultPers+kidsPers;
			console.log(pers);
			switch(pers){
				case 0 : personPrce=0; color="black"; break;
				case 1 : personPrce=0; color="black"; break;
				case 2 : personPrce=0; color="black"; break;
				case 3 : personPrce=0; color="black"; break;
				case 4 : personPrce=0; color="black"; break;
				case 5 : personPrce=20000; color="black"; break;
				case 6 : personPrce=40000; color="black"; break;
				case 7 : personPrce=60000; color="black"; break;
				case 8 : personPrce=80000; color="black"; break;
				default: personPrce="'성인', '아동/유아' 인원 수를 선택해주세요"; color="red"; break;
			};
			$("#persePrice").html(personPrce).css("color",color);
			if(typeof personPrce=="string"){
				totalBookPrice="";		
			}else{
	        	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
			}
			$("#totPrice").html(totalBookPrice);
		});
	});	
	$(function() {
		$(document).on("change","select[class=infPers]",e=>{
			infPers = Number($("select[class=infPers] option:selected").attr('value'));
			pers=adultPers+kidsPers;
			console.log(pers);
		});
	});	
=======
   let adultPers, kidsPers, infPers, totalPerson;
   let personPrce=0;
   $(function() {
      $(document).on("change","select[class=adultPers]",e=>{
         adultPers = Number($("select[class=adultPers] option:selected").attr('value'));
         pers=adultPers+kidsPers;
         /* console.log(pers); */
         switch(pers){
            case 0 : personPrce=0; color="black"; break;
            case 1 : personPrce=0; color="black"; break;
            case 2 : personPrce=0; color="black"; break;
            case 3 : personPrce=0; color="black"; break;
            case 4 : personPrce=0; color="black"; break;
            case 5 : personPrce=20000; color="black"; break;
            case 6 : personPrce=40000; color="black"; break;
            case 7 : personPrce=60000; color="black"; break;
            case 8 : personPrce=80000; color="black"; break;
            default: personPrce="'성인', '아동/유아' 인원 수를 선택해주세요"; color="red"; break;
         };
         $("#persePrice").html(personPrce).css("color",color);
           totalBookPrice=totalRoomPrice+personPrce+OPTprice;
           $("#totPrice").html(totalBookPrice);
      });
   });   
   $(function() {
      $(document).on("change","select[class=kidsPers]",e=>{
         kidsPers = Number($("select[class=kidsPers] option:selected").attr('value'));
         pers=adultPers+kidsPers;
         console.log(pers);
         switch(pers){
            case 0 : personPrce=0; color="black"; break;
            case 1 : personPrce=0; color="black"; break;
            case 2 : personPrce=0; color="black"; break;
            case 3 : personPrce=0; color="black"; break;
            case 4 : personPrce=0; color="black"; break;
            case 5 : personPrce=20000; color="black"; break;
            case 6 : personPrce=40000; color="black"; break;
            case 7 : personPrce=60000; color="black"; break;
            case 8 : personPrce=80000; color="black"; break;
            default: personPrce="'성인', '아동/유아' 인원 수를 선택해주세요"; color="red"; break;
         };
         $("#persePrice").html(personPrce).css("color",color);
           totalBookPrice=totalRoomPrice+personPrce+OPTprice;
           $("#totPrice").html(totalBookPrice);
      });
   });   
   $(function() {
      $(document).on("change","select[class=infPers]",e=>{
         infPers = Number($("select[class=infPers] option:selected").attr('value'));
         pers=adultPers+kidsPers;
         console.log(pers);
      });
   });   
>>>>>>> branch 'main' of https://github.com/Muggung/btc-casa64-project.git

/* 3. 옵션 */
<<<<<<< HEAD
	let OPTprice=0;
	$(function() {
		$(document).on("change","input[id=OPTN1]",e=>{
			if($(e.target).is(":checked")){
				OPTprice += Number($("#OPTN1").attr("data-cnt"));
				/* console.log(OPTprice); */
				$("#optnPrice").html(OPTprice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
			}
			else {
				OPTprice -= Number($("#OPTN1").attr("data-cnt"));
				/* console.log(OPTprice); */
				$("#optnPrice").html(OPTprice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
			}
		});
	});		
	$(function() {
		$(document).on("change","input[id=OPTN2]",e=>{
			if($(e.target).is(":checked")){
				OPTprice += Number($("#OPTN2").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
			}
			else {
				OPTprice -= Number($("#OPTN2").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
			}
		});
	});		
	$(function() {
		$(document).on("change","input[id=OPTN3]",e=>{
			if($(e.target).is(":checked")){
				OPTprice += Number($("#OPTN3").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
			}
			else {
				OPTprice -= Number($("#OPTN3").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		        totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		        $("#totPrice").html(totalBookPrice);
			}
		});
	});		
	
=======
   let OPTprice=0;
   $(function() {
      $(document).on("change","input[id=OPTN1]",e=>{
         if($(e.target).is(":checked")){
            OPTprice += Number($("#OPTN1").attr("data-cnt"));
            /* console.log(OPTprice); */
            $("#optnPrice").html(OPTprice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
         }
         else {
            OPTprice -= Number($("#OPTN1").attr("data-cnt"));
            /* console.log(OPTprice); */
            $("#optnPrice").html(OPTprice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
         }
      });
   });      
   $(function() {
      $(document).on("change","input[id=OPTN2]",e=>{
         if($(e.target).is(":checked")){
            OPTprice += Number($("#OPTN2").attr("data-cnt"));
            $("#optnPrice").html(OPTprice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
         }
         else {
            OPTprice -= Number($("#OPTN2").attr("data-cnt"));
            $("#optnPrice").html(OPTprice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
         }
      });
   });      
   $(function() {
      $(document).on("change","input[id=OPTN3]",e=>{
         if($(e.target).is(":checked")){
            OPTprice += Number($("#OPTN3").attr("data-cnt"));
            $("#optnPrice").html(OPTprice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
         }
         else {
            OPTprice -= Number($("#OPTN3").attr("data-cnt"));
            $("#optnPrice").html(OPTprice);
              totalBookPrice=totalRoomPrice+personPrce+OPTprice;
              $("#totPrice").html(totalBookPrice);
         }
      });
   });      
   
>>>>>>> branch 'main' of https://github.com/Muggung/btc-casa64-project.git
/* 4. 금액 */
<<<<<<< HEAD

=======
   $(function() {
      $(document).on("change","input[id=OPTN3]",e=>{
         console.log("여기도 나오나??"+OPTprice);
      });
   });   
>>>>>>> branch 'main' of https://github.com/Muggung/btc-casa64-project.git
</script>

<!-- 결제기능 구현 -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script src="<%=request.getContextPath()%>/js/dj/payment.js"></script>
      <!-- 푸터 영역 -->
<%@ include file="/views/common/footer.jsp"%>