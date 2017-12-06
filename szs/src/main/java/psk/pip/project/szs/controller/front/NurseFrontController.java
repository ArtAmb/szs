package psk.pip.project.szs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NurseFrontController {
	public static String templateDirRoot = "nurse/";

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/nurse")
	public String nurseView(Model model) {
		return getTemplateDir("nurse");
	}
}
