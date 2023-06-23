$(() => {
    $("#reservationTabs span").click((e) => {
      activeClassEvent(e);
    });
});

function activeClassEvent(tag) {
    $("#reservationTabs span").each((i, e) => {
      $("#reservationTabs").find("span").removeClass("active");
    });

    $(tag.target).addClass('active');
}

function completeUse(address) {
    $.ajax({
        url: address,
        type: 'GET',
        success: (data) => {
            $("#cardBody").html(data);
        }
    })
}


