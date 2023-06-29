$(function () {
  $('.repeat-buttons').click((e) => {
    const CARD_NAME = $(e.target).parent().parent().find('h5').text();
    $.ajax({
      url: PATH,
      type: 'get',
      data: { cardName: CARD_NAME },
      beforeSend: () => {
        $('html').css('cursor', 'wait');
        $(e.target).parent().parent().find('h3').html('<div> <div class="spinner-border" role="status"> <span class="visually-hidden">Loading...</span> </div> </div>');
      },
      complete: () => {
        $('html').css('cursor', 'auto');
      },
      success: (data) => {
        if (CARD_NAME === '가입회원') {
          $(e.target).parent().parent().find("h3").text(data + '명');
        } else {
          $(e.target).parent().parent().find("h3").text(data + '개');
        }
      }
    })
  });
})

// == 차트 == //
const CHART = $('#adminChart');

let roomNames = [];
let bookingCount = [];
let bookingPayment = [];

CHART_BOOKING_COUNT.forEach((e) => {
  roomNames.push(e.roomName);
  bookingCount.push(e.roomInfo * 300000);
});

CHART_BOOKING_PAYMENT.forEach((e) => {
  bookingPayment.push(e.roomInfo);
});


const CHART_DATA = {
  labels: roomNames,
  datasets: [
    {
      label: '결제금액',
      data: bookingPayment,
      backgroundColor: chartColors(),
      borderColor: chartLineColors(),
      stack: 'combined',
      type: 'bar'
    },
    {
      label: '예약횟수',
      data: bookingCount,
      backgroundColor: chartColorsTwo(),
      borderColor: chartLineColors(),
      stack: 'combined',
      type: 'line',
    }
  ]
}

new Chart(CHART, {
  type: 'line',
  data: CHART_DATA,
  options: {
    scales: {
      y: {
        stacked: true,
      },
    },
    transitions: {
      show: {
        animations: {
          x: {
            from: 0,
          },
          y: {
            from: 0,
          },
        },
      },
      hide: {
        animations: {
          x: {
            to: 0,
          },
          y: {
            to: 0,
          },
        },
      },
    },
    plugins: {
      title: {
        display: true,
        text: "객실별 매출 현황",
        font: {
          size: 28,
        },
      },
      legend: {
        labels: {
          font: {
            size: 16,
          },
        },
      },
    },
  },
});

// == 색상 배열 == //
function chartColors() {
  return ['#ade8f4', '#90e0ef', '#48cae4', '#00b4d8', '#0096c7', '#0077b6', '#023e8a'];
}

function chartColorsTwo() {
  return ['#ff8800', '#ff9500', '#ffa200', '#ffaa00', '#ffb700', '#ffc300', '#ffd000', '#ffea00', '#ff7b00'];
}

// == 경계선 색상 배열 == //
function chartLineColors() {
 return ['#caf0f8', '#ade8f4', '#48cae4', '#90e0ef', '#48cae4', '#00b4d8', '#0096c7'];
}