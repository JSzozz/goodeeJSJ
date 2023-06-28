$(function () {
  $('.repeat-buttons').click((e) => {
    const CARD_NAME = $(e.target).parent().parent().find('h5').text();
    console.log(CARD_NAME);
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