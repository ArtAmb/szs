package psk.pip.project.szs.services.medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.medicine.ExaminationTypeDTO;
import psk.pip.project.szs.dto.medicine.MeasurementTypeDTO;
import psk.pip.project.szs.entity.medicine.ExaminationType;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.repository.medicine.ExaminationTypeRepository;
import psk.pip.project.szs.repository.medicine.MeasurementTypeRepository;
import psk.pip.project.szs.services.medicine.exception.CannotGetMeasurementType;



@Service
public class MedicalActionService {

@Autowired 
private ExaminationTypeRepository examinationTypeRepo;
@Autowired 
private MeasurementTypeRepository measurementTypeRepo;
//private ExaminationRepository examinationRepo;

public void addExaminationType(ExaminationTypeDTO dto){
	
	ExaminationType examinationType = new ExaminationType();
	examinationType.setExaminationType(dto.getExaminationType());

	examinationTypeRepo.save(examinationType);
		
}

public void addMeasurementType(MeasurementTypeDTO dto){
	
	MeasurementType measurementType = new MeasurementType();
	measurementType.setMeasurementType(dto.getMeasurementType());

	measurementTypeRepo.save(measurementType);
		
}

public MeasurementType getMeasurementType(Long id) {
	MeasurementType measurementType = measurementTypeRepo.findOne(id);
	if (measurementType == null)
		throw new CannotGetMeasurementType("Nie znaleziono pomiaru o ID = " + id);
	
	return measurementType;
}

/*public void saveExamination(ExaminationDTO dto) {

	Examination examination = new Examination();
		
	
	Examination.setStartDate(dto.getStartDate());
	Examination.setEndDate(dto.getEndDate());
	Examination.setTypeOfExamination(dto.getTypeOfExamination());
	Examination.setEndDate(dto.getEndDate());
	
	examinationRepo.save(examination);


}*/

}
