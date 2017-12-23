var compositeTools = function () {
    var compositeTools = {};
    compositeTools.size = 0;
    compositeTools.addGroup = function (groupName, parentDivId) {
        groupName = groupName + compositeTools.size;
        compositeTools.size = compositeTools.size + 1;

        var groupContainerID = groupName + '-group-container';
        var parentGroup = $('#' + parentDivId);
        var buttonDiv = $('<div>');
        buttonDiv.attr('id', groupName + '-buttons');

        var addGroupB = $("<button>");
        addGroupB.attr('id', groupName + '-button-group-add');
        addGroupB.click(function () {
            compositeTools.addGroup("pomiar", groupContainerID);
        });
        addGroupB.html("Dodaj grupe");

        var addValueB = $("<button>");
        addValueB.attr('id', groupName + '-button-group-add-value');
        addValueB.click(function () {
            compositeTools.addValue(groupName + "-leaf", groupContainerID);
        });
        addValueB.html("Dodaj wartosc");

        var delGroupB = $("<button>");
        delGroupB.attr('id', groupName + '-button-group-del');
        delGroupB.html("Usun wartosc");

        var hideGroupB = $("<button>");
        hideGroupB.attr('id', groupName + '-button-group-hide');
        hideGroupB.attr('ishide', false);
        hideGroupB.html("-");
        hideGroupB.click(function () {
            if (hideGroupB.attr('ishide') == false || hideGroupB.attr('ishide') == "false") {
                hideGroupB.attr('ishide', true);
                hideGroupB.html("+");
                $('#' + groupContainerID).css("display", 'none');
            }
            else {
                hideGroupB.attr('ishide', false);
                hideGroupB.html("-");
                $('#' + groupContainerID).css("display", 'block');
            }

        });

        var groupContainer = $('<div>');
        groupContainer.attr('id', groupContainerID);


        buttonDiv.append(addGroupB);
        buttonDiv.append(addValueB);
        buttonDiv.append(delGroupB);
        buttonDiv.append(hideGroupB);

        var group = $('<div>');
        group.attr('id', groupName + 'ID');

        group.append(buttonDiv);
        group.append(groupContainer);

        parentGroup.append(group);


        delGroupB.click(function () {
            group.replaceWith("");
        });
    }

    compositeTools.addValue = function (valueName, parentDivId) {
        var parentGroup = $('#' + parentDivId);
        var buttonDiv = $('<span>');
        buttonDiv.attr('id', valueName + '-buttons');

        var delValueB = $("<button>");
        delValueB.attr('id', valueName + '-button-value-del');
        delValueB.html("Usun wartosc");


        var inputDiv = $("<span>");
        inputDiv.attr('id', valueName + '-value');

        var inputValue = $("<input>");
        inputValue.attr('id', valueName + '-value-input');
        inputValue.attr('type', "text");

        var inputName = $("<input>");
        inputName.attr('id', valueName + '-name-input');
        inputName.attr('type', "text");

        var inputUnit = $("<input>");
        inputUnit.attr('id', valueName + '-unit-input');
        inputUnit.attr('type', "text");

        inputDiv.append(inputName);
        inputDiv.append(inputValue);
        inputDiv.append(inputUnit);

        buttonDiv.append(delValueB);

        var leaf = $("<div>");
        leaf.attr('id', valueName + 'ID');

        leaf.append(inputDiv);
        leaf.append(buttonDiv);

        parentGroup.append(leaf);

        delValueB.click(function () {
            leaf.replaceWith("");
        });
    }


    return compositeTools;
}();