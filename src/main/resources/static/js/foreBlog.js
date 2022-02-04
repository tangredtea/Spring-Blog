function formatDate(value,formatString){
    if(value==null){
        return "";
    }
    formatString = formatString || "YYYY-MM-DD  hh:mm:ss";
    return moment(value).format(formatString);
}


function parseUrl(){
    var data =[];
    var params = location.search;
    params = params.substring(params.indexOf('?')+1,params.length);
    var keyValues = params.split("&");
    for (var i in keyValues){
        var array = new Array();
        var keyValue = keyValues[i].split("=");
        for (var j in keyValue){
            array.push(keyValue[j]);
        }
        data.push(array);
    }
    return data;
}

function getParamValue(name,dataArray){
    for( var i in dataArray){
        var keyValue = dataArray[i];
        if(keyValue[0]==name){
            return keyValue[1];
        }
    }
}

function isEmpty(object,name){
    if (object==null){
        alert(name+": 不能为空");
        return true
    }
    if(object.length==0){
        alert(name+": 不能为空");
        return true;
    }
    return false;
}

function isEmail(value) {
    var reg = new RegExp("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    //校验
    if (value==null){
        alert("邮箱地址不能为空");
        return false;
    }
    if(!reg.test(value)){
        alert("请输入有效的邮箱地址");
        return false;
    }
    return  true;
}


function isNumber(value,text) {
    if(value==null){
        alert(text+" 不能为空");
        return false;
    }
    if(value.length==0){
        alert(text+" 不能为空");
        return false;
    }
    if(isNaN(value)){
        alert(text+" 不是数字");
        return false;
    }
    return true;
}


function isInt(value,text) {
    if(value==null){
        alert(text+" 不能为空");
        return false;
    }
    if(value.length==0){
        alert(text+" 不能为空");
        return false;
    }
    //parseInt(123a)==123
    if(parseInt(value)!=value){
        alert(text+" 不是整数");
        return false;
    }
    return true;
}


function errorShowAndJump(message,address){
    $(".ui.tiny.modal.errorModal .content").text(message);
    $(".ui.tiny.modal.errorModal").modal("show");
    $("#closeButton").click(function () {
        $(".ui.tiny.modal.errorModal").modal("hide");
        location.href=address;
    });
}

function errorShow(message){
    $(".ui.tiny.modal.errorModal .content").text(message);
    $(".ui.tiny.modal.errorModal").modal("show");
    $("#closeButton").click(function () {
        $(".ui.tiny.modal.errorModal").modal("hide");
    });
}


function tipShow(message){
    $(".ui.tiny.modal.tipModal .content").text(message);
    $(".ui.tiny.modal.tipModal").modal("show");
    $("#tipCloseButton").click(function () {
        $(".ui.tiny.modal.tipModal").modal("hide");
    });
}


function showModal(modalName) {
    $(".ui.tiny.modal."+modalName).modal("show");
}

function closeModal(modalName) {
    $(".ui.tiny.modal."+modalName).modal("hide");
}

function isMP4(value,name) {
    if(value==null){
        alert(name+": 不能为空");
        return false;
    }
    if(value.length<=0){
        alert(name+": 不能为空");
        return false;
    }
    if(value.type=="video/mp4"){
        return true;
    }
    alert(name+": 不是mp4格式");
    return false;
}

function getTotalCommentNumber(vue){
    for (var i  in vue.beans){
        vue.totalCommentNumber++;
        if(vue.beans[i].replies  == null){
            continue;
        }
        if(vue.beans[i].replies.length>0){
            vue.totalCommentNumber+=vue.beans[i].replies.length;
        }
    }
}

function elementEnableLazyLoadOfRequest(className, callback) {
    $('.' + className)
        .visibility({
            once: true,
            observeChanges: true,
            onBottomVisible: function () {
                callback();
            }
        })
    ;
}


// 元素动画部分
function multiScaleAnimation(fatherClassName, subClassName) {
    $('.' + fatherClassName)
        .transition({
            animation: 'scale',
            duration: '0s',
            onComplete: function () {
                $('.' + fatherClassName + ' .' + subClassName).transition({
                    animation: 'scale',
                    duration: '1s',
                    reverse: 'auto', // default setting
                    interval: 200
                });
            }
        })
    ;
}

function simpleScaleAnimation(className) {
    $('.' + className)
        .transition({
            animation: 'scale',
            duration: '0s',
            onComplete: function () {
                $('.' + className).transition({
                    animation: 'scale',
                    duration: '1s',
                });
            }
        })
    ;
}




function simpleAnimationOfId(fatherClass, id, animation, duration) {
    $('.' + fatherClass + ' #' + id)
        .transition({
            animation: animation,
            duration: '0s',
            onComplete: function () {
                $('#' + id).transition({
                    animation: animation,
                    duration: duration,
                });
            }
        })
    ;
}

function simpleAnimation(className, animation) {
    $('.' + className)
        .transition({
            animation: animation,
            duration: '0s',
            onComplete: function () {
                $('.' + className).transition({
                    animation: animation,
                    duration: '1s',
                });
            }
        })
    ;
}

function secondToDate(second) {
    if (!second) {
        return 0;
    }
    var time = new Array(0, 0, 0, 0, 0);
    if (second >= 365 * 24 * 3600) {
        time[0] = parseInt(second / (365 * 24 * 3600));
        second %= 365 * 24 * 3600;
    }
    if (second >= 24 * 3600) {
        time[1] = parseInt(second / (24 * 3600));
        second %= 24 * 3600;
    }
    if (second >= 3600) {
        time[2] = parseInt(second / 3600);
        second %= 3600;
    }
    if (second >= 60) {
        time[3] = parseInt(second / 60);
        second %= 60;
    }
    if (second > 0) {
        time[4] = second;
    }
    return time;
}
function setTime() {
    var create_time = Math.round(new Date(Date.UTC(2022, 1, 1, 0, 0, 0)).getTime() / 1000);
    var timestamp = Math.round((new Date().getTime() + 8 * 60 * 60 * 1000) / 1000);
    currentTime = secondToDate((timestamp - create_time));
    currentTimeHtml = currentTime[0] + '年' + currentTime[1] + '天'
        + currentTime[2] + '时' + currentTime[3] + '分' + currentTime[4]
        + '秒';
    document.getElementById("webRuntime").innerHTML = currentTimeHtml;
}


function isSatisfactoryForKeyword(keyword){
    if(isEmpty(keyword)){
        return false;
    }
    return true;
}


//分页js
function setPagination(totalPage,currentPage){

    var ulTag = $(".pagination ul");

    var liTag = "";
    var beforePage = currentPage-1; //前一页
    var afterPage = currentPage+1;	//后一页
    var activeLi = "";
    //显示头
    if(currentPage>1){
        liTag += '<li class="btn prev" onclick="jumpPage('+beforePage+')"><span><i class="left chevron icon"></i></span></li>';
    }
    if(currentPage>2){
        liTag+='<li class="numb" onclick="jumpPage('+1+')"><span>1</span></li>';
        // if(currentPage>3){
        // 	liTag+='<li class="dots"><span>...</span></li>';
        // }
    }
    for (var pageLength = beforePage ; pageLength <= afterPage; pageLength++) {
        if(pageLength>totalPage){
            continue;
        }
        //显示1
        if(pageLength==0){
            pageLength = pageLength+1;
        }
        //当前页显示
        if(pageLength==currentPage){
            activeLi = "active";
        }else{
            activeLi="";
        }
        liTag+='<li class="numb '+activeLi+' " onclick="jumpPage('+pageLength+')"><span>'+pageLength+'</span></li>';

    }
    if(currentPage<totalPage-1){    //5<19
        if(currentPage< totalPage-2){  //5<18
            liTag+='<li class="dots"><span>...</span></li>';
        }
        liTag+='<li class="numb"  onclick="jumpPage('+totalPage+')"><span>'+totalPage+'</span></li>';
    }
    //显示尾
    if(currentPage<totalPage){
        liTag += '<li class="btn next" onclick="jumpPage('+afterPage+')"><span><i class="right chevron icon"></i></span></li>';
    }
    ulTag.html(liTag);
}