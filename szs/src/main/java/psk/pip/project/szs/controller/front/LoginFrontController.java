package psk.pip.project.szs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginFrontController {
	public static String templateDirRoot = "login/";

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/login/sign-in")
	public String login(Model model) {
		return getTemplateDir("sign-in");
	}

	@GetMapping("/view/login/sign-up")
	public String signUp(Model model) {
		return getTemplateDir("sign-up");
	}
}
