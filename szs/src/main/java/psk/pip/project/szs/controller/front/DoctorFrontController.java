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
public class DoctorFrontController {
	public static String templateDirRoot = "doctor/";

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepo;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/doctor")
	public String doctorView(Model model) {
		model.addAttribute("doctors", employeeService.findAllDoctors());
		return getTemplateDir("doctor");
	}

	@GetMapping("/view/doctor/{id}")
	public String hospitalDoctorView(@PathVariable Long id, Model model) {
		Employee doctor = employeeRepo.findOne(id);
		model.addAttribute("employee", doctor);
		return getTemplateDir("doctor-detail");
	}
	@GetMapping("/view/doctor/search")
	public String searchDoctorView(Model model) {
		model.addAttribute("doctors", employeeService.findAllDoctors());
		return getTemplateDir("doctor-search");
	}
}
