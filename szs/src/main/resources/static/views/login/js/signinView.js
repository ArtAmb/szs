var signinView = function () {
	var view = {}
	view._options = {
		containerName: "",
		formType: "",

	}

	view.init = function (options) {
		view._options = options;
	}
	view.render = function () {
		var htmlForm = $("<div></div>").load("../../../views/login/template/sign-in.html", function () {
			$("#" + view._options.containerName).html(htmlForm);
		});


	}


	view.tryToLogin = function () {

		var url = "/login?" + "login=" + $("#loginInput").val() + "&" + "password=" + $("#passInput").val();
		$.ajax({
			url: url,
			type: 'POST',
			success: function (data) {
				alert("Gratuluje. Proces autoryzacji zakonczony sukcesem");
			}
		});
	}
	return view;
}();
