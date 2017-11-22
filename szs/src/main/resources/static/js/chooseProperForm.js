function chooseProperForm(form){
	var htmlForm;
	
	
	switch(form){
		case "doctor-form":
		htmlForm = $("<div></div>").load("../doctor-form.html");
		break;
		case "nurse-form":
		break;
		default:
		//ERROR
		break;
	}
	
	$("#container").html(htmlForm);
	
}