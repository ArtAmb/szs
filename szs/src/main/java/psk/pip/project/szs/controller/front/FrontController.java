package psk.pip.project.szs.controller.front;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {
	public static String templateDirRoot = "";

	@GetMapping("/")
	public String startView(Model model, Principal principal) {
		model.addAttribute("isPrincipal", false);
		if (principal != null) {
			model.addAttribute("isPrincipal", true);
		}

		return getTemplateDir("mainMenu");
	}

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

}
