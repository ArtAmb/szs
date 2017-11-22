package psk.pip.project.szs.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/")
	public String startView(Map<String, Object> model) {
		return "mainMenu";
	}

	@GetMapping("/front/login/sign-in")
	public String login(Map<String, Object> model) {
		return "login/sign-in";
	}

	@GetMapping("/view/doctor")
	public String doctorView(Model model) {
		model.addAttribute("formName", "doctor-form");
		return "mainMenu";
	}

	@GetMapping("/view/nurse")
	public String nurseView(Map<String, Object> model) {
		return "mainMenu";
	}

	@GetMapping("/view/administration")
	public String administrationView(Map<String, Object> model) {
		return "mainMenu";
	}

	@GetMapping("/view/config")
	public String configView(Map<String, Object> model) {
		return "mainMenu";
	}

}
