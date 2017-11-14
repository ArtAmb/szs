package psk.pip.project.szs.services.medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import psk.pip.project.szs.dto.medicine.TypeOfExaminationDTO;
import psk.pip.project.szs.entity.medicine.TypeOfExamination;
import psk.pip.project.szs.repository.medicine.TypeOfExaminationRepository;



@Service
public class MedicalActionService {

	@Autowired 
	private TypeOfExaminationRepository typeOfExaminationRepo;
//private ExaminationRepository examinationRepo;

	public void addTypeOfExamination(TypeOfExaminationDTO dto)
	{
		TypeOfExamination typeOfExamination = new TypeOfExamination();
		typeOfExamination.setTypeOfExamination(dto.getTypeOfExamination());

		typeOfExaminationRepo.save(typeOfExamination);
		
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
