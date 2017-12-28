package psk.pip.project.szs.controller.front;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.entity.medicine.MeasurementTemplateRoot;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.medicine.MedicalActionService;

@Controller
public class LoginFrontController {
	public static String templateDirRoot = "login/";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MedicalActionService medicalActionService;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/login/sign-in")
	public String login(Model model, Principal principal) {
		if (principal != null) {
			User user = userRepository.findByLogin(principal.getName());
			model.addAttribute("user", user);
			model.addAttribute("roles", user.getRoles());
			return getTemplateDir("my-profile");
		}

		return getTemplateDir("sign-in");
	}

	@GetMapping("/view/login/sign-up")
	public String signUp(Model model) {
		return getTemplateDir("sign-up");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/login/my-profile")
	public String showMyProfileView() {
		return getTemplateDir("my-profile");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/login/my-profile/templates")
	public String showMyProfileMeasurementTemplatesView(Model model, Principal principal) {
		String login = principal.getName();
		model.addAttribute("templates", medicalActionService.getMeasurementTemplates(login));
		model.addAttribute("login", login);
		return getTemplateDir("myprofile/my-templates");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/login/my-profile/template/{templateId}/edit")
	public String editMyProfileMeasurementTemplateView(@PathVariable Long templateId, Model model,
			Principal principal) {
		String login = principal.getName();
		MeasurementTemplateRoot template = medicalActionService.getMeasurementTemplate(templateId);

		if (template.getUser() == null || !template.getUser().getLogin().equals(login)) {
			model.addAttribute("message", "uzytkownik nie ma dostepu do tego szablonu");
			return "error";
		}
		model.addAttribute("login", login);
		model.addAttribute("template", template);
		return getTemplateDir("myprofile/edit-template");
	}

}
