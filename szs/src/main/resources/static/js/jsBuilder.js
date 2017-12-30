var jsBuilder = function(){
    var jsBuilder = {}
    
    jsBuilder.createElement = function(tagName, id, classes){
        return $("<" + tagName + ">").attr("id", id).addClass(classes);
    }

    jsBuilder.createInput= function(type, id, classes){
        return $("<input>").attr("id", id).attr('type', type).addClass(classes);
    }

    jsBuilder.createSelectOption= function(value, classes){
        return $("<option>").attr('value', value).addClass(classes);
    }

    return jsBuilder;
}();