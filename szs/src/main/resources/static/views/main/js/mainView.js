function createPatientView(patients) {

}

function createTableRow(value){
	var td = $('<td>');
	td.html(value);
	return td;
}

function createPatientHtml(patient) {
	var editButton = $('<button>');
	editButton.addClass("edit_button");
	editButton.addClass("simplebutton");
	editButton.text("Edytuj");
	editButton.on('click', function(){
		alert("edit button!");
	});

	var medicalActionButton = $('<button>');
	medicalActionButton.addClass("simplebutton");
	medicalActionButton.addClass("medical_action_button");
	medicalActionButton.text("Akcja");
	medicalActionButton.on('click', function(){
		alert("medical action button!");
	});

	var patientDiv = $('<div>');
	patientDiv.addClass("");
	var patientInfoDiv = $('<div>');
	patientInfoDiv.html(patient.name +" "+ patient.surname);

	patientDiv.append(createTableRow(patientInfoDiv));
	patientDiv.append(createTableRow(editButton));
	patientDiv.append(createTableRow(medicalActionButton));

	return patientDiv;
	
} 

function getPatients() {
	$("#patient-form-table").html("");
	var url = "/patient/patientCards" 
	$.ajax({
		url: url,
		type: 'GET',
		dataType: "json",
		success: function (patients) {
			for(var i =0; i < patients.length; ++i){
				var div = createPatientHtml(patients[i]);
				$("#patient-form-table").append($("<tr>").html(div));
			}
		}
	});
} 

