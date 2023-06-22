// index
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
// /index

// email-search
/*$('a[class="login__button"]').click((e)=>{
    // const p = $("<p>");
    let msg,color;
    if($("#email-search").val()==''){
        msg='*가입된 이메일이 아닙니다.';
        color='red';
    }else{
        msg='*이미 가입된 계정입니다.';
        color='green';
    }
    $(".text-alert").html("");
    $(".text-alert").append($("<p>").addClass('fs-6').text(msg).css("color",color));
});*/
// /email-search

// login
$("#login-submit-button").on("mousedown",(e)=>{
    let flag = $("#id-null-check").val();
    if(typeof flag == "undefined" || flag == ""){
        flag = null;
        alert("아이디 혹은 비밀번호를 다시 입력해주세요.");
    };
});
// /login

// pw-create
$(".fn-password-check").keyup(e=>{
    const i = document.getElementsByClassName("login__input")[0].value;
    const j = document.getElementsByClassName("login__input")[1].value;
    let msg,color;
    if(i.length==j.length&&i==j){
        msg='*비밀번호가 일치합니다.';
        color='green';
    }else{
        msg='*비밀번호가 일치하지 않습니다.';
        color='red';
    }
    $(".text-alert").html("");
    $(".text-alert").append($("<p>").text(msg).css("color",color));
});
// /pw-create

// pw-search
$('.check-num>button[class="btn btn-dark"]').click((e)=>{
    // const p = $("<p>");
    let msg,color;
    if($('.check-num>input[class="login__input"]').val()==''){
        msg='*올바르지않은 입력값입니다.';
        color='red';
    }else{
        msg='*인증완료 되었습니다.';
        color='green';
    }
    $(".text-alert").html("");
    $(".text-alert").append($("<p>").text(msg).css("color",color));
});
// /pw-search

 //signup01-agreement
 const swiper = new Swiper('.swiper', {
     // Optional parameters
     direction: 'horizontal',
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
     });
// /signup01-agreement

// signup02-input
// pw-check
$(".fn-password-check").keyup(()=>{
    const i = $("#ck-pw1").val();
    const j = $("#ck-pw2").val();
    let msg,color;
    if(i!=0&&j!=0){
        if(i.length==j.length&&i==j){
            msg='*비밀번호가 일치합니다.';
            color='green';
        }else{
            msg='*비밀번호가 일치하지 않습니다.';
            color='red';
        }
    }
    $(".pwcheck").html("");
    $(".pwcheck").append($("<p>").text(msg).css("color",color));
});

//double-check
/*$('#check-pw>button[class="btn btn-dark"]').click((e)=>{
    // const p = $("<p>");
    let msg,color;
    if($('#check-pw>input[class="login__input"]').val()==''){
        msg='*올바르지않은 입력값입니다.';
        color='red';
    }else{
        msg='*인증완료 되었습니다.';
        color='green';
    }
    $(".double-check").html("");
    $(".double-check").append($("<p>").text(msg).css("color",color));
});*/
// /signup02-input

