package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import psk.pip.project.szs.services.administration.employee.EmployeeService;

@Controller
public class DoctorFrontController {
	public static String templateDirRoot = "doctor/";
	
	@Autowired
	private  EmployeeService employeeService;
	
	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/doctor")
	public String doctorView(Model model) {
		model.addAttribute("doctors", employeeService.findAllDoctors());
		return getTemplateDir("doctor");
	}
}
