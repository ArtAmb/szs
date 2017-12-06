package psk.pip.project.szs.controller.front;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
	public static String templateDirRoot = "";

	@GetMapping("/")
	public String startView(Map<String, Object> model) {
		return getTemplateDir("mainMenu");
	}

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

}
