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

    tools.isTagSelect = function (tag) {
        var tagName = tag.prop('tagName');
        return tagName == 'SELECT' || tagName == 'select' || tagName == 'Select';
    }

    tools.markRequiredTags = function (tagId) {
        var tag = $('#' + (tagId));
        var func = function (self) {
            var val = self.val();
            var isContainer = (self.children().toArray().length != 0);
            if (isContainer) {
                if (tools.isTagSelect(self) && val.trim() == "") {
                    self.addClass("required");
                }
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
    tools.jsonToArray = function (obj) {
        return $.map(obj, function (el) { return el });
    }
    tools.inputToJSON = function (inputId) {
        return $("#" + inputId).val().trim() == "" ? null : $("#" + inputId).val().trim();
    }

    tools.tagInputsToDTO = function (tagName) {
        var tag = $('#' + tagName);
        var dto = {};

        var func = function (el) {
            var self = $(el);
            if(self.attr('type') == 'checkbox'){
                dto[self.attr('name')] = self.prop('checked');
                return;
            }

            dto[self.attr('name')] = self.val();
        }

        tag.find("[" + consts.DTO_VALUE_ATTR + "='true']").each(function () {
            func(this);
        });
        tag.find("[" + consts.DTO_VALUE_ATTR + "='" + consts.DTO_VALUE_ATTR + "']").each(function () {
            func(this);
        });

        return dto;
    }
    return tools;
}();