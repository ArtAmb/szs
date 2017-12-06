package psk.pip.project.szs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigFrontController {
	public static String templateDirRoot = "config/";

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/config")
	public String patientView(Model model) {
		return getTemplateDir("config");
	}

}
