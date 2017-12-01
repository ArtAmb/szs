var patientView = function () {
	var view = {}
	view._options = {
		containerName: "",
		formType: "",

	}

	view.init = function(options){
      view._options =options;
	}
	view.render = function(){
		var htmlForm = $("<div></div>").load("../views/patient/patient-form.html");
		$("#" + view._options.containerName).html(htmlForm);

		patientViewRouter.chooseProperView(view._options);

	}

	return view;
}();
