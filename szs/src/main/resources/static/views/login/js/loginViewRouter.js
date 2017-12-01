var loginViewRouter = function () {
	var router = {}
	router._options = {}

	router.chooseProperView = function (options) {
		var view;

		router._options.containerName="login-form-container";
		switch (options.formType) {
			case "sign-in":
			view = signinView;
				break;
			case "sign-up":
				break;

			default:
			view = signinView;
				break;
		}

		view.init(router._options);
		view.render();

	}

	return router;
}();