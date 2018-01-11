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
            success: function (response) {
                onSuccess(response)
            },
            error: function (e) {
                onFailed(e);
            }
        })

    }

    tools.postForObject = function (url, dto, onSuccess, onFailed) {
        var data = JSON.stringify(dto);

        $.ajax({
            url: url,
            data: data,
            contentType: "application/json",
            type: 'POST',
            success: function (response) {
                onSuccess(response)
            },
            error: function (e) {
                onFailed(e);
            }
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
            error: function (e) {
                onFailed(e);
            }
        })

    }

    tools.putForObject = function (url, dto, onSuccess, onFailed) {
        var data = JSON.stringify(dto);

        $.ajax({
            url: url,
            data: data,
            contentType: "application/json",
            type: 'PUT',
            success: function (response) {
                onSuccess(response);
            },
            error: function (e) {
                onFailed(e);
            }
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
                if (tools.isTagSelect(self) && tools.isBlank(val)) {
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
    
    tools.selectToJSON = function(selectId){
       var select = $("#" + selectId);
       var selVal = select.find("[value='"+select.val()+"']").text();
       if(select.val() == null)
        return null;
       
        return { 
        id: select.val(),
        name: tools.isBlank(selVal) ? null : selVal
       };
    }

    tools.tagInputsToDTO = function (tagName) {
        var tag = $('#' + tagName);
        var dto = {};

        var func = function (el) {
            var self = $(el);
            if (self.attr('type') == 'checkbox') {
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

    tools.openDialog = function () {
        $('<div>').attr('id', 'add-or-update-dialog').dialog({
            autoOpen: false,
            resizable: false,
            title: "",
            height: "auto",
            width: 400,
            modal: true,
            buttons: [{
                id: 'save-dialog-button',
                text: "Zapisz",
                click: function () {
                    $(this).dialog("close");
                },
            },
            {
                text: "Anuluj",
                click: function () {
                    $(this).dialog("close");
                }
            }],
            close: function () {
                $(this).html("");
            },
            open: function () {
                var content = jsBuilder.createElement('div');
                jsBuilder.createElement('label').text('Nazwa:').appendTo(content);
                content.append('  ');
                jsBuilder.createInput('text', 'dialog-input').attr('required', true).appendTo(content);
                $(this).html(content);
            }
        });
    }

    return tools;
}();