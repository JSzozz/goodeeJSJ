/**
 * about, board, review 탭 active
 */
function board_tab(){
	const current_url = window.location.pathname;
	$('.category-tab-a').removeClass('active');
	if (current_url.indexOf('notice') > -1) { // 현재 url에 notice 가 포함 되어 있을 경우 active
		$('.category-tab-notice').addClass('active');
	} else if (current_url.indexOf('qna') > -1) { // 현재 url에 qna 가 포함 되어 있을 경우 active
		$('.category-tab-qna').addClass('active');
	} else if (current_url.indexOf('review') > -1){
		$('.category-tab-review').addClass('active');
	} else if (current_url.indexOf('travel') > -1) {
		$('.category-tab-travel').addClass('active');
	} else if (current_url.indexOf('teamBTC') > -1) {
		$('.category-tab-teamBTC').addClass('active');
	} else {
		$('.category-tab-about').addClass('active');
	}
}