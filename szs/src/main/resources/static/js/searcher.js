var searcher = function () {
    var searcher = {};

    /*
    options = {
        isRequired: boolean,
        buildOptionDataFunc: function(optionTag, dto),
        isDTOValue: boolean,
        name: "string"
    }
    */
    searcher.buildSearcher = function (tag, searcherName, sourceURL, options) {
        var resultDiv = jsBuilder.createElement('span', searcherName);

        var inputQuery = jsBuilder.createInput('text', searcherName + "-input-query");
        var selectResult = jsBuilder.createElement('select', searcherName + "-select-result")
            .attr(consts.REQUIRED_ATTR, options.isRequired)
            .attr(consts.DTO_VALUE_ATTR, options.isDTOValue)
            .attr('name', options.name)
            .attr(consts.SEARCHER_ATTR, true);
        var findButton = jsBuilder.createElement('button', searcherName + "-button-find").text('szukaj');

        var emptySelectOption = jsBuilder.createSelectOption(" ").text(' ');
        selectResult.html(selectResult);

        findButton.click(function () {
            var queryString = inputQuery.val().trim();
            selectResult.html(emptySelectOption);
            selectResult.removeAttr('title');

            tools.postForObject(sourceURL, { queryString: queryString }, function (res) {
                var array = tools.jsonToArray(res);
                if (array.length == 0) {
                    selectResult.attr('title', 'Nie udalo sie znalezc frazy "' + queryString + '".');
                }
                $(array).each(function () {
                    if (options.buildOptionDataFunc != undefined)
                        options.buildOptionDataFunc(jsBuilder.createSelectOption(this.entityId).text(this.text).appendTo(selectResult), this);
                    else
                        jsBuilder.createSelectOption(this.entityId).text(this.text).appendTo(selectResult);
                });

            },
                function () { }
            )
        });

        resultDiv.append(inputQuery);
        resultDiv.append(selectResult);
        resultDiv.append(findButton);
        tag.append(resultDiv);
    }


    return searcher;
}();