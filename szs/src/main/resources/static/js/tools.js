var tools = function () {
    var tools = {

    }

    tools.isBlank = function (data) {
        return data === undefined || data === null || data.trim() === "";
    }

    tools.deleteObject = function (url, onSuccess, onFailed) {
        $.ajax({
            url: url,
            contentType: "application/json",
            type: 'DELETE',
            success: onSuccess,
            error: onFailed
        })

    }

    tools.postForObject = function (url, dto, onSuccess, onFailed) {
        var data = JSON.stringify(dto);

        $.ajax({
            url: url,
            data: data,
            contentType: "application/json",
            type: 'POST',
            success: onSuccess,
            error: onFailed
        })

    }

     tools.getForObject = function (url, onSuccess, onFailed) {
        $.ajax({
            url: url,
            contentType: "application/json",
            type: 'GET',
            success: function(response){
                onSuccess(response);
            },
            error: onFailed
        })

    }

     tools.putForObject = function (url, dto, onSuccess, onFailed) {
        var data = JSON.stringify(dto);

        $.ajax({
            url: url,
            data: data,
            contentType: "application/json",
            type: 'PUT',
            success: onSuccess,
            error: onFailed
        })

    }

    return tools;
}();