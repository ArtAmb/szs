package psk.pip.project.szs.controller;

import java.util.Collection;

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
import psk.pip.project.szs.dto.patient.SignInOutDTO;
import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.medicine.LongTermVisit;
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

	@GetMapping(value = "/patient/patientCard/{id}")
	public PatientCard getPatientCard(@PathVariable Long id) {
		return patientService.getPatientCard(id);
	}

	@GetMapping(value = "/patient/patientCards")
	public Collection<PatientCard> getPatientCards() {
		return patientService.getPatientCards();
	}

	@GetMapping(value = "/patient/{patientId}/long-term-visits/filter/isEnd/{isEnd}")
	public Collection<LongTermVisit> getPatientLongTermVisits(@PathVariable Long patientId,
			@PathVariable Boolean isEnd) {
		return patientService.getLongTermVisits(patientId, isEnd);
	}

	@GetMapping(value = "/patient/{patientId}/long-term-visits")
	public Collection<LongTermVisit> getPatientLongTermVisits(@PathVariable Long patientId) {
		return patientService.getLongTermVisits(patientId);
	}

	@PostMapping(value = "/patient/sign-in")
	public void signInToHospital(@RequestBody SignInOutDTO dto) {
		patientService.signInToHospital(dto);
	}

	@PostMapping(value = "/patient/sign-out")
	public void signOutFromHospital(@RequestBody SignInOutDTO dto) {
		patientService.signOutFromHospital(dto);
	}

	@PostMapping(value = "/patient/addVisit")
	public void addVisit(@RequestBody VisitDTO dto) {
		if (dto.getIsLongTermVisit())
			patientService.addLongTermVisit(dto);
		else
			patientService.addVisit(dto);
	}

	@DeleteMapping(value = "/patient/visit/{id}")
	public void deleteVisit(@PathVariable Long id) {
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