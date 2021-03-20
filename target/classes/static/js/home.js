var self;

function jumpPage(pageNumber) {
    if (pageNumber==self.data.currentPage){
        return;
    }
    self.listNewArticle(pageNumber,self.data.pageSize);
}

$(function(){

    var data4Vue = {
        listArticleUri:"/list/article",
        hotArticleUri:"/article/recommend",
        newVideoUri:"/video/recommend",
        authorRecommendArticleUri:"/author/article/recommend",
        authorRecommendVideoUri:"/author/video/recommend",
        commentWallUri:"/commentWall/recommend",
        backgroundUri:"/background",
        searchArticleUri:"/searchArticle",
        keyword:"",
        websiteUri:"/website",
        websiteInfo:{},
        background:{},
        newArticles:[],
        hotArticles:[],
        authorRecommendArticles:[],
        authorRecommendVideos:[],
        bigVideo:{id:""},
        newVideos:[],
        commentWalls:[],
        data:{},

    }






    window.addEventListener('scroll', function() {
        var t = $('body, html').scrollTop();
        if (t > 715) {
            $('#navMenu').addClass('navDiv-active');
            $('#toTop').addClass('toTop-active');
            $(".aplayer").removeClass('aplayer-hide');

        } else {
            $('#navMenu').removeClass('navDiv-active');
            $('#toTop').removeClass('toTop-active');
            $(".aplayer").addClass('aplayer-hide');
        }
    });



    $(".guidance i").click(function(){
        $('html,body').animate({scrollTop: 720}, 500);
    });

    $("#toTop").click(function(){
        $('html,body').animate({scrollTop: 715}, 500);
    });

    $('.mobileButton').click(function(){
        $(".navItem").toggleClass("mobileHidden");
        $("#searchItem").toggleClass("searchItemHidden");
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