package psk.pip.project.szs.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.medicine.ExaminationDTO;
import psk.pip.project.szs.dto.medicine.ExaminationTypeDTO;
import psk.pip.project.szs.dto.medicine.MeasurementTypeDTO;
import psk.pip.project.szs.entity.medicine.Measurement;
import psk.pip.project.szs.entity.medicine.MeasurementRoot;
import psk.pip.project.szs.entity.medicine.MeasurementTemplateRoot;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.services.medicine.GivenDrug;
import psk.pip.project.szs.services.medicine.MedicalActionService;

@RestController
@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
public class MedicalActionControler {

	@Autowired
	private MedicalActionService medicalActionService;

	@PostMapping(value = "/medicalAction/addExaminationType")
	@Deprecated
	public void addExaminationType(@RequestBody ExaminationTypeDTO dto) {
		medicalActionService.addExaminationType(dto);
	}

	@PostMapping(value = "/medicalAction/addMeasurementType")
	@Deprecated
	public void addMeasurementType(@RequestBody MeasurementTypeDTO dto) {
		medicalActionService.addMeasurementType(dto);
	}

	@GetMapping(value = "/medicalAction/measurementType/{id}")
	@Deprecated
	public MeasurementType getMeasurementType(@PathVariable Long id) {
		return medicalActionService.getMeasurementType(id);
	}

	
	  @PostMapping(value = "/medicalAction/saveExamination") public void
	  saveExamination(@RequestBody ExaminationDTO dto) {
	  medicalActionService.saveExamination(dto); }
	/* 
	 * @GetMapping(value = "/medicalAction/Examination/{id}") public Examination
	 * getExamination(@PathVariable Long id) { return
	 * medicalActionService.getExamination(id); }
	 */
	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@PostMapping(value = "/medicalAction/measurement")
	public void addMeasurement(@RequestBody MeasurementRoot root, Principal principal) {
		medicalActionService.saveMeasurement(principal.getName(), root);
	}

	@PostMapping(value = "/medicalAction/givenDrug")
	public void addGivenDrug(@RequestBody GivenDrug givenDrug, Principal principal) {
		medicalActionService.saveGivenDrug(principal.getName(), givenDrug);
	}

	@PostMapping(value = "/medicalAction/measurement/template")
	public void addMeasurementTemplate(@RequestBody MeasurementTemplateRoot root) {
		medicalActionService.addMeasurementTemplate(root);
	}

	@GetMapping(value = "/medicalAction/measurement/templates/user/{login}")
	public Collection<MeasurementTemplateRoot> getMeasurementTemplates(@PathVariable String login) {
		return medicalActionService.getMeasurementTemplates(login);
	}

	@DeleteMapping(value = "/medicalAction/measurement/template/{id}")
	public void deleteMeasurementTemplate(@PathVariable Long id) {
		medicalActionService.deleteMeasurementTemplate(id);
	}

	@GetMapping(value = "/medicalAction/measurement/templates/patient/{id}")
	public Collection<MeasurementRoot> getMeasurementTemplates(@PathVariable Long id) {
		return medicalActionService.getMeasurements(id);
	}

	@GetMapping(value = "/medicalAction/measurement/template/{id}")
	public Measurement getMeasurementTemplate(@PathVariable Long id) {
		return medicalActionService.getMeasurementTemplate(id).getMeasurement();
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_MEDICAL_EMPLOYEE + "')")
	@PostMapping(value = "/medicalAction/new/nurse-action")
	public void saveNurseAction(Principal principal) {

	}
}
