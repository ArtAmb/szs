package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.medicine.ExaminationTypeDTO;
import psk.pip.project.szs.dto.medicine.MeasurementTypeDTO;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.services.medicine.MedicalActionService;

@RestController
public class MedicalActionControler {
	
	@Autowired 
private MedicalActionService medicalActionService;

@PostMapping(value = "/medicalAction/addExaminationType") 
public void addExaminationType(@RequestBody ExaminationTypeDTO dto) { 
medicalActionService.addExaminationType(dto);
}

@PostMapping(value = "/medicalAction/addMeasurementType") 
public void addMeasurementType(@RequestBody MeasurementTypeDTO dto) { 
medicalActionService.addMeasurementType(dto);
}	

@GetMapping(value = "/medicalAction/getMeasurementType/{id}")
public MeasurementType getMeasurementType(@PathVariable Long id) { 
return medicalActionService.getMeasurementType(id);
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
