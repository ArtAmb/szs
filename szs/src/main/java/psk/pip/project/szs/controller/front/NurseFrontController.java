package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.services.administration.employee.EmployeeService;

@Controller
public class NurseFrontController {
	public static String templateDirRoot = "nurse/";

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepo;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/nurse")
	public String nurseView(Model model) {
		model.addAttribute("nurses", employeeService.findAllNurses());
		return getTemplateDir("nurse");
	}

	@GetMapping("/view/nurse/{id}")
	public String hospitalNurseView(@PathVariable Long id, Model model) {
		Employee nurse = employeeRepo.findOne(id);
		model.addAttribute("employee", nurse);
		return getTemplateDir("nurse-detail");
	}
	
	@GetMapping("/view/nurse/search")
	public String searchNurseView(Model model) {
		model.addAttribute("nurses", employeeService.findAllNurses());
		return getTemplateDir("search-nurse");
	}
}
