var doctorViewRouter = function () {
	var router = {}
	router._options = {}

	router.chooseProperView = function (options) {
		var view;

		router._options.containerName="doctor-form-container";
		switch (options.formType) {
			case "search":
				break;
			case "add":
				break;

			default:
				break;
		}

		view.init(router._options);
		view.render();

	}

	return router;
}();