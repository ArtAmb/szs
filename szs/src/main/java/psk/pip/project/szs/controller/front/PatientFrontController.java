package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.services.patient.PatientService;

@Controller
public class PatientFrontController {
	public static String templateDirRoot = "patient/";

	@Autowired
	private PatientService patientService;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/patients")
	public String patientView(Model model) {
		model.addAttribute("patients", patientService.getPatientCards());
		return getTemplateDir("patient");
	}

	@GetMapping("/view/patient/{id}/edit")
	public String editPatientView(@PathVariable Integer patientId, Model model) {
		model.addAttribute("patients", patientService.getPatientCards());
		return getTemplateDir("edit");
	}

	@GetMapping("/view/patient/search")
	public String searchPatientView(Model model) {
		model.addAttribute("patients", patientService.getPatientCards());
		return getTemplateDir("search");
	}

	@GetMapping("/view/patient/add")
	public String addPatientView(Model model) {
		model.addAttribute("patients", patientService.getPatientCards());
		return getTemplateDir("add");
	}

	@GetMapping("/view/patient/{patientId}/detail")
	public String getPatientDetailView(@PathVariable Long patientId, Model model) {
		model.addAttribute("patient", patientService.getPatientCard(patientId));
		return getTemplateDir("patient-detail");
	}

	@GetMapping("/view/patient/{patientId}/new/visit")
	public String getPatientnewVisitView(@PathVariable Long patientId, Model model) {
		model.addAttribute("patient", patientService.findPatientByCardId(patientId));
		return getTemplateDir("new-visit");
	}
}
