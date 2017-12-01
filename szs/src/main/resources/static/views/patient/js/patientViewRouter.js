var patientViewRouter = function () {
	var router = {}
	router._options = {}

	router.chooseProperView = function (options) {
		var view;

		router._options.containerName="patient-form-container";
		switch (options.formType) {
			case "search":
				break;
			case "add":
				//htmlForm = $("<div></div>").load("../patient-form.html");
				break;

			default:
				view = patientListView;
				break;
		}

		view.init(router._options);
		view.render();

	}

	return router;
}();