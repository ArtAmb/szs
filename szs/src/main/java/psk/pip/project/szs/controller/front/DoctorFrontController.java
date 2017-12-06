package psk.pip.project.szs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorFrontController {
	public static String templateDirRoot = "doctor/";

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/doctor")
	public String doctorView(Model model) {
		return getTemplateDir("doctor");
	}
}
