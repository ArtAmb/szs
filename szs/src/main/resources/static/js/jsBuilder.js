var jsBuilder = function () {
    var jsBuilder = {}

    jsBuilder.createElement = function (tagName, id, classes) {
        return $("<" + tagName + ">").attr("id", id).addClass(classes);
    }

    jsBuilder.createInput = function (type, id, classes) {
        return $("<input>").attr("id", id).attr('type', type).addClass(classes);
    }

    jsBuilder.createTextarea = function (cols, rows, name, classes) {
        return $("<textarea>").attr("name", name).attr('cols', cols).attr('rows', rows).addClass(classes);
    }
    

    jsBuilder.createSelectOption = function (value, classes) {
        return $("<option>").attr('value', value).addClass(classes);
    }

    jsBuilder.createSelect = function (id, sourceUrl, classes, valueToSet) {
        var selectTag = $('<select>').attr('id', id).addClass(classes);
        selectTag.append(jsBuilder.createSelectOption(" ").text(" "));
        
        tools.getForObject(sourceUrl, function (response) {
            var array = tools.jsonToArray(response);
            $(array).each(function () {
                var self = this;
                selectTag.append(jsBuilder.createSelectOption(self.id).text(self.name));
            });
            
            selectTag.val(valueToSet);
        }, function () {
            alert('Blad pobrania wartosci do selecta');
        });

        return selectTag;
    }

    return jsBuilder;
}();