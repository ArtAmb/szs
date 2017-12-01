var mainViewRouter = function () {
	var router = {}
	router._options = {
		containerName: "",
		formType: "",

	}

	router.chooseProperView = function (options) {
		var view;
		
		options.containerName = "container";
		router._options = options;


		switch (options.formType) {
			case "doctor-form":
				view = doctorView;
				break;
			case "nurse-form":
				break;
			case "patient-form":
					view = patientView;
				break;
			case "login-form":
					view = loginView;
				break;

			default:
				//ERROR
				break;
		}
		view.init(router._options);
		view.render();
	}

	return router;
}();