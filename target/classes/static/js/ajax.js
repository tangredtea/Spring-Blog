var ajax = {};

ajax.get = function (url, data, callback) {
    $.ajax({
        type: 'GET',
        url: url,
        data: data,
        beforeSend: function (request) {
            request.setRequestHeader("authorize", cookie.getToken());
        },
        success: function (data, status) {
            if (data.code == 999) {
                console.log("Login....");
//                top.location.href = "/";
                $(window).attr('location', '/');
                return;
            }
            callback(data, status);
        },
        dataType: "json"
    });
}

ajax.post = function (url, data, callback) {
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        beforeSend: function (request) {
            request.setRequestHeader("authorize", cookie.getToken());
        },
        success: function (data, status) {
            if (data.code == 999) {
                console.log("Login....");
                $(window).attr('location', '/');
                return;
            }
            callback(data, status);
        },
        dataType: "json"
    });
}

ajax.put = function (url, data, callback) {
    $.ajax({
        type: 'PUT',
        url: url,
        data: data,
        beforeSend: function (request) {
            request.setRequestHeader("authorize", cookie.getToken());
        },
        success: function (data, status) {
            if (data.code == 999) {
                console.log("Login....");
                $(window).attr('location', '/');
                return;
            }
            callback(data, status);
        },
        dataType: "json"
    });
}

ajax.delete = function (url, data, callback) {
    $.ajax({
        type: 'DELETE',
        url: url,
        data: data,
        beforeSend: function (request) {
            request.setRequestHeader("authorize", cookie.getToken());
        },
        success: function (data, status) {
            if (data.code == 999) {
                console.log("Login....");
                $(window).attr('location', '/');
                return;
            }
            callback(data, status);
        },
        dataType: "json"
    });
}

