package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import psk.pip.project.szs.dto.medicine.TypeOfExaminationDTO;
import psk.pip.project.szs.services.medicine.MedicalActionService;

@RestController
public class MedicalActionControler {
	
	@Autowired 
private MedicalActionService medicalActionService;

@PostMapping(value = "/medicalAction/addTypeOfExamination") 
public void addTypeOfExamination(@RequestBody TypeOfExaminationDTO dto) { 
medicalActionService.addTypeOfExamination(dto);
}	

/*@PostMapping(value = "/medicalAction/saveMedicalExamination") 
public void saveExamination(@RequestBody ExaminationDTO dto) { 
	medicalActionService.saveExamination(dto);
}

@GetMapping(value = "/medicalAction/Examination/{id}")
public Examination getExamination(@PathVariable Long id) { 
return medicalActionService.getExamination(id);
}*/

}
