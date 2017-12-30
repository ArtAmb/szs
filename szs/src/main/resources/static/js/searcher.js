var searcher = function(){
    var searcher = {};

    searcher.buildSearcher = function(tag, searcherName, sourceURL, buildOptionDataFunc) {
        var resultDiv = jsBuilder.createElement('div', searcherName);
        
        var inputQuery = jsBuilder.createInput('text', searcherName+"-input-query");
        var selectResult = jsBuilder.createElement('select', searcherName+"-select-result");
        var findButton = jsBuilder.createElement('button', searcherName+"-button-find").text('szukaj');
        
        var emptySelectOption = jsBuilder.createSelectOption(" ").text(' ');
        selectResult.html(selectResult);


        findButton.click(function(){
            var queryString = inputQuery.val().trim();
            selectResult.html(emptySelectOption);
            selectResult.removeAttr('title');

            tools.postForObject(sourceURL, {queryString : queryString}, function(res){
                var array = tools.jsonToArray();
                if(array.length == 0){
                    selectResult.attr('title', 'Nie udalo sie znalezc frazy "' + queryString +'".');
                }
                $(array).each(function(){
                    buildOptionDataFunc(jsBuilder.createSelectOption(this.id).text(this.text).appendTo(selectResult));
                });
                
            },
            function(){}    
            )
        });

        resultDiv.append(inputQuery);
        resultDiv.append(selectResult);
        resultDiv.append(findButton);
        tag.append(resultDiv);
    }


    return searcher;
}();