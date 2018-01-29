package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.repository.patient.LongTermVisitRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.VisitRepository;
import psk.pip.project.szs.services.patient.PatientService;

@Controller
public class PatientFrontController {
	public static String templateDirRoot = "patient/";

	@Autowired
	private PatientService patientService;
	@Autowired
	private LongTermVisitRepository longTermVisitRepo;
	@Autowired
	private VisitRepository VisitRepo;
	@Autowired
	private PatientCardRepository patientRepo;

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
		return getTemplateDir("add-patient");
	}

	@GetMapping("/view/patient/{patientId}/detail")
	public String getPatientDetailView(@PathVariable Long patientId, Model model) {
		model.addAttribute("patient", patientService.getPatientCard(patientId));
		return getTemplateDir("patient-detail");
	}

	@GetMapping("/view/patient/{patientId}/new/visit")
	public String getPatientNewVisitView(@PathVariable Long patientId, Model model) {
		model.addAttribute("patient", patientService.findPatientByCardId(patientId));
		return getTemplateDir("new-visit");
	}

	@GetMapping("/view/patient/{patientId}/long-term-visits")
	public String getPatientLongTermVisitsView(@PathVariable Long patientId, Model model) {
		PatientCard patientCard = patientRepo.findOne(patientId);
		model.addAttribute("patient", patientCard.toPatient());
		model.addAttribute("isCurrVisit", patientCard.getCurrentVisit() != null);
		model.addAttribute("visits", patientService.getLongTermVisits(patientId));

		return getTemplateDir("long-term-visits");
	}

	@GetMapping("/view/patient/{patientId}/long-term-visits/filter/{isEnd}")
	public String getPatientLongTermVisitsView(@PathVariable Long patientId, @PathVariable Boolean isEnd, Model model) {
		PatientCard patientCard = patientRepo.findOne(patientId);
		model.addAttribute("patient", patientCard.toPatient());
		model.addAttribute("isCurrVisit", patientCard.getCurrentVisit() != null);
		model.addAttribute("visits", patientService.getLongTermVisits(patientId, isEnd));

		return getTemplateDir("long-term-visits");
	}

	@GetMapping("/view/patient/{patientId}/visits")
	public String getPatientVisitsView(@PathVariable Long patientId, Model model) {
		model.addAttribute("patient", patientRepo.findPatientById(patientId));
		model.addAttribute("visits", patientService.getVisits(patientId));
		return getTemplateDir("visits");
	}

	@GetMapping("/view/patient/{patientId}/visits/filter/{isEnd}")
	public String getPatientVisitsView(@PathVariable Long patientId, @PathVariable Boolean isEnd, Model model) {
		model.addAttribute("patient", patientRepo.findPatientById(patientId));
		model.addAttribute("visits", patientService.getVisits(patientId, isEnd));

		return getTemplateDir("visits");
	}

	@GetMapping("/view/patient/{patientId}/long-term-visit/{visitId}/detail")
	public String getLongTermVisitDetailView(@PathVariable Long patientId, @PathVariable Long visitId, Model model) {
		model.addAttribute("visit", longTermVisitRepo.findOne(visitId));
		PatientCard patientCard = patientRepo.findOne(patientId);
		model.addAttribute("patient", patientCard.toPatient());
		model.addAttribute("isCurrVisit", patientCard.getCurrentVisit() != null);

		return getTemplateDir("long-term-visit-detail");
	}
	
	@GetMapping("/view/patient/{patientId}/visit/{visitId}/detail")
	public String getVisitDetailView(@PathVariable Long patientId, @PathVariable Long visitId, Model model) {
		model.addAttribute("visit", VisitRepo.findOne(visitId));
		PatientCard patientCard = patientRepo.findOne(patientId);
		model.addAttribute("patient", patientCard.toPatient());
		return getTemplateDir("visit-detail");
	}
	
	@GetMapping("/view/patient/delete/{patientId}")
	public String deletePatientCard(@PathVariable Long patientId, Model model) {
		PatientCard patientCard = patientRepo.findOne(patientId);
		model.addAttribute("patient", patientCard.toPatient());
		return getTemplateDir("delete-patient-card");
	}
}
