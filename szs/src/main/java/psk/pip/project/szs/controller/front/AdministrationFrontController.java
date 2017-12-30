package psk.pip.project.szs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrationFrontController {
	public static String templateDirRoot = "administration/";

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/administration")
	public String patientView(Model model) {
		return getTemplateDir("administration");
	}

	@GetMapping("/view/administration/new/employee")
	public String addEmployeeView(Model model) {
		return getTemplateDir("add-employee");
	}
}
