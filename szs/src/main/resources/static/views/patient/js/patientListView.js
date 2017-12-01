var patientListView = function () {
	var view = {}
	view._options = {
		containerName: "",
		formType: "",

	}

	view.init = function (options) {
		view._options = options;

	}
	view.render = function () {
		view.getPatients();

		//$("#" + view._options.containerName).html(table);
	}

	view.createTableRow = function (value) {
		var td = $('<td>');
		td.html(value);
		return td;
	}

	view.createPatientHtml = function (patient) {
		var editButton = $('<button>');
		editButton.addClass("edit_button");
		editButton.addClass("simplebutton");
		editButton.text("Edytuj");
		editButton.on('click', function () {
			alert("edit button!");
		});

		var medicalActionButton = $('<button>');
		medicalActionButton.addClass("simplebutton");
		medicalActionButton.addClass("medical_action_button");
		medicalActionButton.text("Akcja");
		medicalActionButton.on('click', function () {
			alert("medical action button!");
		});

		var patientDiv = $('<div>');
		patientDiv.addClass("");
		var patientInfoDiv = $('<div>');
		patientInfoDiv.html(patient.name + " " + patient.surname);

		patientDiv.append(view.createTableRow(patientInfoDiv));
		patientDiv.append(view.createTableRow(editButton));
		patientDiv.append(view.createTableRow(medicalActionButton));

		return patientDiv;

	}

	view.getPatients = function () {
		var table = $("<table>").html("");
		table.attr("id", "patient-form-table");
		table.addClass("table");
		var url = "/patient/patientCards"
		$.ajax({
			url: url,
			type: 'GET',
			dataType: "json",
			success: function (patients) {
				for (var i = 0; i < patients.length; ++i) {
					var div = view.createPatientHtml(patients[i]);
					table.append($("<tr>").html(div));
				}

				$("#" + view._options.containerName).html(table);
			}
		});

		//return table;
	}

	return view;
}();
