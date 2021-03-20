var COOKIE_TOKEN = "ADMIN_TOKEN";


var cookie = {};
cookie.setToken = function (value) {
    $.cookie(COOKIE_TOKEN, value, {expires: 7, path: '/'});
}

cookie.getToken = function () {
    return $.cookie(COOKIE_TOKEN);
}


cookie.clear = function () {
    $.cookie(COOKIE_TOKEN, null, {expires: 0, path: '/'});
}