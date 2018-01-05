var compositeTools = function () {
    var compositeTools = {};
    compositeTools.size = 0;
    compositeTools.reset = function () {
        compositeTools.size = 0;
    },
        compositeTools.addGroup = function (groupName, parentDivId, isTemplate) {
            if (isTemplate == undefined) isTemplate = false;

            groupName = groupName + compositeTools.size;
            compositeTools.size = compositeTools.size + 1;

            var groupContainerID = groupName + '-group-container';
            var parentGroup = $('#' + parentDivId);
            var buttonDiv = $('<span>')
                .attr('id', groupName + '-buttons');

            var addGroupB = $("<button>")
                .attr('id', groupName + '-button-group-add')
                .click(function () {
                    compositeTools.addGroup("pomiar", groupContainerID, isTemplate);
                })
                .html("Dodaj grupe");

            var addValueB = $("<button>")
                .attr('id', groupName + '-button-group-add-value')
                .click(function () {
                    compositeTools.addValue(groupName + "-leaf", groupContainerID, isTemplate);
                })
                .html("Dodaj wartosc");

            var delGroupB = $("<button>")
                .attr('id', groupName + '-button-group-del')
                .html("Usun grupe");

            var hideGroupB = $("<button>")
                .attr('id', groupName + '-button-group-hide')
                .attr('ishide', false)
                .html("-")
                .click(function () {
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

            var groupContainer = $('<ul>')
                .attr('id', groupContainerID)
                .addClass('tree-element-container')
                .attr(consts.REQUIRED_ATTR, true);

            buttonDiv.append(addGroupB);
            buttonDiv.append(addValueB);
            buttonDiv.append(delGroupB);
            buttonDiv.append(hideGroupB);

            var group = $('<li>')
                .attr('id', groupName + 'ID')
                .attr('treepart', 'node');

            var inputName = $('<input>')
                .attr('id', groupName + '-name')
                .attr('type', 'text')
                .attr(consts.REQUIRED_ATTR, true);


            var header = $('<span>').attr('id', groupName + '-header');

            header.append(inputName)
            header.append(buttonDiv);

            group.append(header);
            group.append(groupContainer);

            parentGroup.append(group);


            delGroupB.click(function () {
                group.remove();
            });

            return groupName;
        }

    compositeTools.addValue = function (valueName, parentDivId, isTemplate) {
        if (isTemplate == undefined) isTemplate = false;
        var parentGroup = $('#' + parentDivId);
        var buttonDiv = $('<span>');
        buttonDiv.attr('id', valueName + '-buttons');

        var delValueB = $("<button>");
        delValueB.attr('id', valueName + '-button-value-del');
        delValueB.html("Usun wartosc");


        var inputDiv = $("<span>");
        inputDiv.attr('id', valueName + '-value');

        var inputValue = $("<input>")
            .attr('id', valueName + '-value-input')
            .attr('type', "text")
            .prop('disabled', isTemplate)
            .attr(consts.REQUIRED_ATTR, !isTemplate);

        var inputName = $("<input>")
            .attr('id', valueName + '-name-input')
            .attr('type', "text")
            .attr(consts.REQUIRED_ATTR, true);



        var unitSelect = jsBuilder.createSelect(valueName + '-unit-select', consts.URLS.getMeasurementUnits)
        .attr(consts.REQUIRED_ATTR, true);
        
        inputDiv.append(inputName);
        inputDiv.append(inputValue);
        inputDiv.append(unitSelect);

        buttonDiv.append(delValueB);

        var leaf = $("<li>");
        leaf.attr('id', valueName + 'ID');
        leaf.addClass('tree-element-container');

        leaf.append(inputDiv);
        leaf.append(buttonDiv);
        leaf.attr('treepart', 'leaf');

        parentGroup.append(leaf);

        delValueB.click(function () {
            leaf.remove();
        });

        return leaf;
    },

        compositeTools.compositeToObject = function (element) {
            var treePart = element.attr('treepart');
            switch (treePart) {
                case 'leaf':
                    var inputs = element.find('input');
                    inputs = inputs.toArray();
                    var measurement = {};
                    measurement.name = $(inputs[0]).val();
                    measurement.value = $(inputs[1]).val();
                    measurement.unit = { 
                    					 id: element.find('select').val(),
                    					 name: element.find('select').text()
                    					};
 
                    return measurement;

                case 'node':
                    var measurement = {};
                    var inputName = element.children().find('input');
                    measurement.name = inputName.val();
                    measurement.elements = [];
                    var children = element.children('.tree-element-container').children();
                    children.each(function () {
                        var treeEl = $(this).attr('treepart');
                        var id = $(this).attr('id');
                        if (treeEl !== undefined)
                            measurement.elements.push(compositeTools.compositeToObject($(this)));
                    });
                    return measurement;

                default:
                    alert('ERROR: NIEZNANY TYP - PROBLEM Z ZAPISEM ' + treePart);
            }
        },


        compositeTools.showCompositeTemplate = function (element, groupName, parentDivId, readOnlyValue) {
            var isLeaf = (element.elements == undefined || element.elements == null || element.elements.length == 0);

            if (isLeaf) {
                var valueName = $('#' + parentDivId).parent().attr('id') + "-detail-leaf";
                var leaf = compositeTools.addValue(valueName, parentDivId);
                leaf.find('#' + valueName + '-value-input').prop('disabled', readOnlyValue).val(element.value);
                leaf.find('#' + valueName + '-name-input').val(element.name);
                leaf.find('#' + valueName + '-unit-input').val(element.unit);
                return;
            }
            else {
                var newGroupName = compositeTools.addGroup(groupName, parentDivId);
                $(element.elements).each(function () {
                    var groupContainerID = newGroupName + '-group-container';
                    var inputNameId = newGroupName + '-name';
                    $('#' + inputNameId).val(element.name);
                    compositeTools.showCompositeTemplate(this, groupName, groupContainerID, readOnlyValue);
                });
            }
        };

    return compositeTools;
}();