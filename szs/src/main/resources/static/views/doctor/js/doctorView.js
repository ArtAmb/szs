var doctorView = function () {
	var view = {}
	view._options = {
		containerName: "",
		formType: "",

	}

	view.init = function(options){
      view._options =options;
	}
	view.render = function(){
		var htmlForm = $("<div></div>").load("../views/doctor/doctor-form.html");
		$("#" + view._options.containerName).html(htmlForm);

		doctorViewRouter.chooseProperView(view._options);

	}

	return view;
}();
