$(() => {
  $('#bookingTabs a').click((e) => {
    activeClassEvent(e);
  });
});

function activeClassEvent(tab) {
  $('#bookingTabs a').each(function (i, e) {
    $('#bookingTabs').find('a').removeClass('active');
  });

  $(tab.target).addClass('active text-dark');
}

function noSearchBooking() {
  let noSearchCard = '<div class="d-flex text-center">'
  noSearchCard += '<div class="col-xl-12 p-5">'
  noSearchCard += '<h2 class="p-3">예약내역이 없습니다... :(</h2>'
  noSearchCard += '</div>';
  noSearchCard += '</div>';
  return noSearchCard;
}

function resultBooking(booking) {
  const userData = booking.split(',');

  console.log(userData);

  let resultCard = '<div class="col-sm-12 p-2">';

  resultCard += '<div class="card card-booking">';

  resultCard += '<div class="card-body">';


  resultCard += '<p>결제대기</p>';
  resultCard += '<div class="d-flex text-center">';

  resultCard += '<div class="col-sm-4">';
  resultCard +=
    '<img src="<%=request.getContextPath()%>/images/01.jpg" alt="객실사진" class="img-fluid" />';
  resultCard += '<div class="mt-2 fs-5">객실이름</div>';
  resultCard += '<div class="mt-2 bg-light">유료옵션</div>';
  resultCard += '</div>';

  resultCard += '<div class="col-sm-7 ms-5">';
  
  resultCard += '<div>';
  resultCard += '<strong>예약자명:&nbsp;</strong>';
  resultCard += '<span>예약자이름</span>';
  resultCard += '</div>';

  resultCard += '<div class="mt-2">';
  resultCard += '<strong>이메일:&nbsp;</strong>';
  resultCard += '<span>예약자이메일</span>';
  resultCard += '</div>';

  resultCard += '<div class="mt-2">';
  resultCard += '<strong>전화번호:&nbsp;</strong>';
  resultCard += '<span>예약자전화번호</span>';
  resultCard += '</div>';
  
  resultCard += '<div class="mt-2">';
  resultCard += '<strong>예약기간:&nbsp;</strong>';
  resultCard += '<span>'+ userData[3] +'</span>';
  resultCard += '<span>&#126;</span>';
  resultCard += '<span>'+ userData[4] +'</span>';
  resultCard += '</div>';
  
  resultCard += '<div class="mt-2">';
  resultCard += '<strong>인원:&nbsp;</strong>';
  resultCard += '<span>성인 ' + userData[5] + '명&nbsp;</span>';
  resultCard += '<span>미성인 ' + userData[6] + '명&nbsp;</span>';
  resultCard += '<span>유아 ' + userData[7] + '명&nbsp;</span>';
  resultCard += '</div>';
  
  resultCard += '<div class="mt-2">';
  resultCard += '<strong>요청사항:&nbsp;</strong>';
  resultCard += '<span>' + userData[9] + '</span>';
  resultCard += '</div>';
  
  resultCard += '<div class="mt-2">';
  resultCard += '<table class="table">';
  resultCard += '<thead><th colpan="2" class="fs-5">결제금액</th></thead>';
  resultCard += '<tbody><td colpan="2" class="fs-5">' + userData[8] + '</td></tbody>';
  resultCard += '</table>';
  resultCard += '</div>';
  
  resultCard += '<div class="mt-2">';
  resultCard += '<button type="button" class="btn btn-danger mt-2" style="width:250px;">결제취소</button>';
  resultCard += '</div>';

  
  resultCard += '</div>';

  resultCard += '</div>';

  resultCard += '</div>';

  resultCard += '</div>';

  resultCard += '</div>';

  return resultCard;
}

// 전체 예약
function showAllBooking(url) {
  ajaxBooking(url);
}


// 예약 AJAX
function ajaxBooking(address) {
  $.ajax({
    url: address,
    data: 'get',
    dataType: 'text',
    success: (data) => {
      
      if (isEmpty(data)) {
        $('#result').html(noSearchBooking());
        return;
      }

      const bookingData = data.split('\n');

      // for (let booking of bookingData) {
      //   $('#result').html(resultBooking(booking));
      // }
      for (let i = 0; i < bookingData.length; i++){
        $("#result").append(resultBooking(bookingData[i]));
      }
    }
  })
}

// 공백, null, undefined, 빈값 처리 함수
function isEmpty(value) {
  return typeof value === "undefined" || (typeof value === "object" && !Object.keys(value).length) ||
    value === null || value === "" || value === null || value.length === 0 ? true : false;
}