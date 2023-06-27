/* 달력 객실 선택 효과 */
   $(function() {
	   let roomName;
       $(document).on("mouseover","div[bookable=Y]",e=> {
		   roomName=$(e.target).text();
           $(e.target).css("font-weight","bold");
           $(e.target).text("객실 가격 : "+$(e.target).attr("price")+"원");
           
        });
       $(document).on("mouseout","div[bookable=Y]",e=> {
           $(e.target).css("font-weight","normal");
           $(e.target).text(roomName);
        });   
   });
/* 달력에서 객실 클릭 */
   let totalRoomPrice=0;
   let totalBookPrice=0;
   
   $(function() {
      let checkInDt="";//(String)
      let roomPrice=0;
       $(document).on("click","div[bookable=Y]",e=> {
             $(".reserve_step2").show();
             $(e.target).css("text-decoration","underline");

             $("#typeNm").html($(e.target).attr("roomName"));
             $("#checkinDt").html($(e.target).attr("class"));
             
             checkInDt=$(e.target).attr("class");

             roomPrice = Number($(e.target).attr("price"));// /* 1. 기간선택& 4. 금액 */과 이어짐
             
			 const $checkAmountOfLodgment=checkAmountOfLodgment(e);//number
			 insertSelectTag(e,$checkAmountOfLodgment);
       });
/* 이용가능한 객실 선택 _ 예약 정보 받기 */
      $(function() {
         $(document).on("change","select[class=availableDays]",e=>{
/* 1. 기간선택& 4. 금액 */            
            const stringToDate=new Date(checkInDt);//(Date)
            /* console.log(checkOut+Number($("select[class=availableDays] option:selected").val())); */
            stringToDate.setDate(stringToDate.getDate() + Number($("select[class=availableDays] option:selected").val()));

            const year = stringToDate.getFullYear();
            const month = ('0' + (stringToDate.getMonth() + 1)).slice(-2);
            const day = ('0' + stringToDate.getDate()).slice(-2);
            const checkoutDt = year+"-"+month+"-"+day;//(String)
            // 어떤 날짜여도 'YYYY-DD-YY'형식으로 변환!
            
              $("#checkoutDt").html(checkoutDt);

              /* totalRoomPrice */
              let period = Number($("select[class=availableDays] option:selected").val());
              totalRoomPrice=period*roomPrice;
              $("#roomPrice").html(totalRoomPrice);
              if(typeof personPrce=="string"){
             	 totalBookPrice=totalRoomPrice+OPTprice;
                 $("#totPrice").html(totalBookPrice);
              }else{
                 totalBookPrice=totalRoomPrice+personPrce+OPTprice;
               	 $("#totPrice").html(totalBookPrice);
              };
         });
      });
    });
/* 2. 인원 */
	let adultPers, kidsPers, infPers, totalPerson;
   	let personPrce=0;
   	$(function() {
      	$(document).on("change","select[class=adultPers]",e=>{
         	adultPers = Number($("select[class=adultPers] option:selected").attr('value'));
         	pers=adultPers+kidsPers;
         	switch(pers){
            	case 0 : personPrce=0; color="gray"; break;
            	case 1 : personPrce=0; color="gray"; break;
            	case 2 : personPrce=0; color="gray"; break;
	            case 3 : personPrce=0; color="gray"; break;
	            case 4 : personPrce=0; color="gray"; break;
	            case 5 : personPrce=20000; color="black"; break;
	            case 6 : personPrce=40000; color="black"; break;
	            case 7 : personPrce=60000; color="black"; break;
	            case 8 : personPrce=80000; color="black"; break;
	            default: personPrce="'성인', '아동/유아' 인원 수를 선택해주세요"; color="red"; break;
         	};
         	$("#persePrice").html(personPrce).css("color",color);
         	if(typeof personPrce=="string"){
        	 	totalBookPrice=totalRoomPrice+OPTprice;
             	$("#totPrice").html(totalBookPrice);
         	}else{
           	 	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
          	 	$("#totPrice").html(totalBookPrice);
         	};
      	});
   	});   
   	$(function() {
      	$(document).on("change","select[class=kidsPers]",e=>{
         	kidsPers = Number($("select[class=kidsPers] option:selected").attr('value'));
         	pers=adultPers+kidsPers;
         	switch(pers){
         		case 0 : personPrce=0; color="gray"; break;
         		case 1 : personPrce=0; color="gray"; break;
         		case 2 : personPrce=0; color="gray"; break;
         		case 3 : personPrce=0; color="gray"; break;
         		case 4 : personPrce=0; color="gray"; break;
         		case 5 : personPrce=20000; color="black"; break;
         		case 6 : personPrce=40000; color="black"; break;
         		case 7 : personPrce=60000; color="black"; break;
         		case 8 : personPrce=80000; color="black"; break;
         		default: personPrce="'성인', '아동/유아' 인원 수를 선택해주세요"; color="red"; break;
      		};
      		$("#persePrice").html(personPrce).css("color",color);
      		if(typeof personPrce=="string"){
     	  		totalBookPrice=totalRoomPrice+OPTprice;
          		$("#totPrice").html(totalBookPrice);
      		}else{
       	  		totalBookPrice=totalRoomPrice+personPrce+OPTprice;
       	  		$("#totPrice").html(totalBookPrice);
      		};
      	});
   	});   
   	$(function() {
      	$(document).on("change","select[class=infPers]",e=>{
         	infPers = Number($("select[class=infPers] option:selected").attr('value'));
         	pers=adultPers+kidsPers;
         	console.log(pers);
      	});
   	});   
/* 3. 옵션 */
	let OPTprice=0;
	$(function() {
		$(document).on("change","input[id=OPTN1]",e=>{
			if($(e.target).is(":checked")){
				OPTprice += Number($("#OPTN1").attr("data-cnt"));
				/* console.log(OPTprice); */
				$("#optnPrice").html(OPTprice);
		      	if(typeof personPrce=="string"){
		     	  	totalBookPrice=totalRoomPrice+OPTprice;
		          	$("#totPrice").html(totalBookPrice);
		      	}else{
		       	  	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		       	  	$("#totPrice").html(totalBookPrice);
		      	};
			}else{
				OPTprice -= Number($("#OPTN1").attr("data-cnt"));
				/* console.log(OPTprice); */
				$("#optnPrice").html(OPTprice);
		      	if(typeof personPrce=="string"){
		     	  	totalBookPrice=totalRoomPrice+OPTprice;
		          	$("#totPrice").html(totalBookPrice);
		      	}else{
		       	  	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		       	  	$("#totPrice").html(totalBookPrice);
		      	};
			};
		});
	});		
	$(function() {
		$(document).on("change","input[id=OPTN2]",e=>{
			if($(e.target).is(":checked")){
				OPTprice += Number($("#OPTN2").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		      	if(typeof personPrce=="string"){
		     	  	totalBookPrice=totalRoomPrice+OPTprice;
		          	$("#totPrice").html(totalBookPrice);
		      	}else{
		       	  	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		       	  	$("#totPrice").html(totalBookPrice);
		      	};
			}else{
				OPTprice -= Number($("#OPTN2").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		      	if(typeof personPrce=="string"){
		     	  	totalBookPrice=totalRoomPrice+OPTprice;
		          	$("#totPrice").html(totalBookPrice);
		      	}else{
		       	  	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		       	  	$("#totPrice").html(totalBookPrice);
		      	};
			};
		});
	});		
	$(function() {
		$(document).on("change","input[id=OPTN3]",e=>{
			if($(e.target).is(":checked")){
				OPTprice += Number($("#OPTN3").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		      	if(typeof personPrce=="string"){
		     	  	totalBookPrice=totalRoomPrice+OPTprice;
		          	$("#totPrice").html(totalBookPrice);
		      	}else{
		       	  	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		       	  	$("#totPrice").html(totalBookPrice);
		      	};
			}else{
				OPTprice -= Number($("#OPTN3").attr("data-cnt"));
				$("#optnPrice").html(OPTprice);
		      	if(typeof personPrce=="string"){
		     	  	totalBookPrice=totalRoomPrice+OPTprice;
		          	$("#totPrice").html(totalBookPrice);
		      	}else{
		       	  	totalBookPrice=totalRoomPrice+personPrce+OPTprice;
		       	  	$("#totPrice").html(totalBookPrice);
		      	};
			};
		});
	});