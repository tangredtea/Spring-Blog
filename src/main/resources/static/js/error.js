$(function(){

    $('.mobileButton').click(function(){
        $(".navItem").toggleClass("mobileHidden");
    });

    $('#wechat')
        .popup({
            popup: $('#wechatPic'),
            on: 'hover',
            position: 'bottom center'
        });

    $('#QQ')
        .popup({
            popup:$('#QQPic'),
            on:"hover",
            position:"bottom center"
        });

});