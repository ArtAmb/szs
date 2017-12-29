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
            success: function (response) {
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

    tools.validation = function (tagId) {
        tools.cleanValidation(tagId);
        tools.markRequiredTags(tagId);
        return tools.isValidationOK(tagId);
    }

    tools.markRequiredTags = function (tagId) {
        var tag = $('#' + (tagId));
        var func = function (self) {
            var val = self.val();
            var isContainer = (self.children().toArray().length != 0);
            if (isContainer) {
                if (!self.html() || self.html().trim() == "") {
                    self.addClass("required");
                }
            } else {
                if (val == undefined || val.trim() == "") {
                    self.addClass("required");
                }
            }
        }

        $(tag.find("[" + consts.REQUIRED_ATTR + "='true']")).each(function () {
            func($(this));
        });
        $(tag.find("[" + consts.REQUIRED_ATTR + "='" + consts.REQUIRED_ATTR + "']")).each(function () { // w js jesli ustawiasz attr na true to przyjmuje wartosci swojej nazwy
            func($(this));
        });
    }

    tools.isValidationOK = function (tagId) {
        var tag = $('#' + (tagId));
        if (tag.find(".required").toArray().length != 0)
            return false;

        return true;
    }

    tools.cleanValidation = function (tagId) {
        var tag = $('#' + (tagId));
        tag.find(".required").each(function () {
            $(this).removeClass("required");
        });
    }

    return tools;
}();