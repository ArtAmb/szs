package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.patient.PatientDTO;
import psk.pip.project.szs.services.patient.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	private PatientService PatientService;
	
	@PostMapping(value = "/patient/addPatient")
	public void addPatient(@RequestBody PatientDTO dto) {
		PatientService.addPatient(dto);
	}
}