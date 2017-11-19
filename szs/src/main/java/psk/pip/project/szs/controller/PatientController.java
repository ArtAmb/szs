package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.patient.PersonalDataDTO;
import psk.pip.project.szs.dto.patient.ReferralDTO;
import psk.pip.project.szs.dto.patient.ReferralTypeDTO;
import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.services.patient.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@PostMapping(value = "/patient/addPatientCard")
	public void addPatientCard(@RequestBody PersonalDataDTO dto) {
		patientService.addPatientCard(dto);
	}
	
	@GetMapping(value = "/patient/cardPatient/{id}")
	public PatientCard getPatientCard(@PathVariable Long id) {
		return patientService.getPatientCard(id);
	}
	
	@PostMapping(value = "/patient/addVisit")
	public void addVisit(@RequestBody VisitDTO dto) {
		patientService.addVisit(dto);
	}
	
	@DeleteMapping(value = "/patient/visit/{id}")
	public void deleteVisit(@PathVariable Long id){
		patientService.deleteVisit(id);
	}
	
	@PostMapping(value = "/patient/addReferralType")
	public void addReferralType(@RequestBody ReferralTypeDTO dto) {
		patientService.addReferralType(dto);
	}
	
	@PostMapping(value = "/patient/registerReferral")
	public void registerReferral(@RequestBody ReferralDTO dto) {
		patientService.registerReferral(dto);
	}
}