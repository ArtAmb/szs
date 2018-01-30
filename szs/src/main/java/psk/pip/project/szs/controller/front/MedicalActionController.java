package psk.pip.project.szs.controller.front;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.medicine.ExaminationRepository;
import psk.pip.project.szs.repository.medicine.NurseActionRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.VisitRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.medicine.MedicalActionService;
import psk.pip.project.szs.services.patient.Patient;

@Controller
@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
public class MedicalActionController {

	public static String templateDirRoot = "medicalAction/";

	@Autowired
	private PatientCardRepository patientCardRepository;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MedicalActionService medicalActionService;
	@Autowired
	private NurseActionRepository nurseActionRepo;
	@Autowired
	private VisitRepository visitRepo;
	@Autowired
	private ExaminationRepository examinationRepo;
	@Autowired
	private PatientCardRepository patientRepo;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/medical/action/patient/{patientId}")
	public String medicalActionsView(@PathVariable Long patientId, Model model) {
		PatientCard patient = patientCardRepository.findOne(patientId);
		model.addAttribute("patient", patient);
		return getTemplateDir("medicalActions");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_DOCTOR + "')")
	@GetMapping("/view/medical/action/patient/{patientId}/examination/visit/{visitId}")
	public String examinationView(@PathVariable Long patientId, @PathVariable Long visitId, Model model) {
		PatientCard patient = patientCardRepository.findOne(patientId);

		model.addAttribute("patient", patient.toPatient());
		model.addAttribute("visit", visitRepo.findOne(visitId));

		return getTemplateDir("examination");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_DOCTOR + "')")
	@GetMapping("/view/medical/action/patient/{patientId}/operation")
	public String operationView(@PathVariable Long patientId, Model model) {
		PatientCard patient = patientCardRepository.findOne(patientId);
		model.addAttribute("patient", patient);
		return getTemplateDir("operation");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/medical/action/patient/{patientId}/measurement")
	public String mesurementView(@PathVariable Long patientId, Model model, Principal principal) {
		Patient patient = patientCardRepository.findPatientById(patientId);
		model.addAttribute("templates", medicalActionService.getTemplates(principal.getName()));
		model.addAttribute("patient", patient);
		return getTemplateDir("measurement");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/medical/action/patient/{patientId}/giveDrug")
	public String giveDrugView(@PathVariable Long patientId, Model model) {
		PatientCard patient = patientCardRepository.findOne(patientId);
		model.addAttribute("patient", patient.toPatient());
		model.addAttribute("room", patient.getRoom());
		return getTemplateDir("giveDrug");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/medical/action/templates")
	public String medicalActionsView(Model model, Principal principal) {
		return getTemplateDir("template");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/medical/action/template/add/to/myprofile")
	public String addNewMeasurementTemplateView(Model model, Principal principal) {
		String login = principal.getName();
		User user = userRepo.findByLogin(login);
		model.addAttribute("user", user);
		return getTemplateDir("measurement-template");
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@GetMapping("/view/medical/action/{id}/patient/{patientId}/visit/{visitId}")
	public String nurseActionDetailsView(@PathVariable Long id, @PathVariable Long patientId,
			@PathVariable Long visitId, Model model) {
		model.addAttribute("visitId", visitId);
		model.addAttribute("patient", patientCardRepository.findOne(patientId).toPatient());
		model.addAttribute("action", nurseActionRepo.findOne(id));
		return getTemplateDir("nurse-action-detail");
	}

	@GetMapping("/view/patient/{patientId}/examination/{examinationId}/detail")
	public String getExaminationDetailView(@PathVariable Long patientId, @PathVariable Long examinationId,
			Model model) {
		model.addAttribute("examination", examinationRepo.findOne(examinationId));
		PatientCard patientCard = patientRepo.findOne(patientId);
		model.addAttribute("patient", patientCard.toPatient());

		return getTemplateDir("examination-detail");
	}

}
