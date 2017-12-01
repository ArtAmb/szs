var loginView = function () {
	var view = {}
	view._options = {
		containerName: "",
		formType: "",

	}

	view.init = function(options){
      view._options =options;
	}
	view.render = function(){
		var htmlForm = $("<div></div>").load("../../views/login/login-form.html");
		$("#" + view._options.containerName).html(htmlForm);

		loginViewRouter.chooseProperView(view._options);

	}

	return view;
}();
