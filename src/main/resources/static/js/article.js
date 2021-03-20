
var self;

function jumpPage(pageNumber) {
    if (pageNumber==self.data.currentPage){
        return;
    }
    self.listComments(pageNumber,self.data.pageSize);
}

$(function () {

    var aid;

    var data4Vue={
        articleUri:"/article",
        articleAppreciateUri:"/article/appreciate",
        listCommentUri:"/list/comment",
        commentUri:"/comment",
        replyUri:"/reply",
        searchArticleUri:"/searchArticle",
        keyword:"",
        websiteUri:"/website",
        websiteInfo:{},
        mottoUri:"/motto",
        motto:{},
        beans:[],
        comment:{},
        article:{category:{},blogImage:{id:''}},
        data:{},
        totalCommentNumber:"",
        articleLink:0,
        bean:{user:{}},
    }





    function isSatisfactory(bean){
        if(isEmpty(bean.username,"名字")){
            return false;
        }
        if(!isEmail(bean.email)){
            return false;
        }
        if(isEmpty(bean.content,"内容")){
            return false;
        }
        return true;
    }


    function fillFormData(bean){
        var formData =  new FormData();
        formData.append("username",bean.username);
        formData.append("content",bean.content);
        formData.append("email",bean.email);
        return formData;
    }


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