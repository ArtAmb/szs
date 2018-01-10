package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import psk.pip.project.szs.services.administration.employee.EmployeeService;

@Controller
public class NurseFrontController {
	public static String templateDirRoot = "nurse/";
	
	@Autowired
	private EmployeeService employeeService;
	
	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/nurse")
	public String nurseView(Model model) {
		model.addAttribute("nurses", employeeService.findAllNurses());
		return getTemplateDir("nurse");
	}
}
