package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import psk.pip.project.szs.services.patient.PatientService;

@Controller
public class PatientFrontController {
	public static String templateDirRoot = "patient/";

	@Autowired
	PatientService patientService;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/patient")
	public String patientView(Model model) {
		model.addAttribute("patients", patientService.getPatientCards());
		return getTemplateDir("patient");
	}
}
