let self;

$(function () {

    window.addEventListener('scroll', function() {
        var t = $('body, html').scrollTop();
        if (t > 310) {
            $('#toTop').addClass('toTop-active');
        } else {
            $('#toTop').removeClass('toTop-active');
        }
    });


    $("#toTop").click(function(){
        $('html,body').animate({scrollTop: 300}, 500);
    });


    $('.mobileButton').click(function(){
        $(".navItem").toggleClass("mobileHidden");
        $("#searchItem").toggleClass("searchItemHidden");
    });

});