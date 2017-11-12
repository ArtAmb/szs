package psk.pip.project.szs.services.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.patient.PersonalDataDTO;
import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.patient.Visit;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.VisitRepository;

@Service

public class PatientService {
	
	@Autowired
	private PatientCardRepository patientCardRepo;
	@Autowired
	private VisitRepository visitRepo;

	public void addPatientCard(PersonalDataDTO patient) {
		PatientCard patientCard = new PatientCard();
		patientCard.setImie(patient.getImie());
		patientCard.setNazwisko(patient.getNazwisko());
		patientCardRepo.save(patientCard);
	}
	
	public PatientCard getPatientCard(Long id) {
		PatientCard patientCard = patientCardRepo.findOne(id);
		return patientCard;
	}
	
	public void addVisit(VisitDTO dto) {
		Visit visit = new Visit();
		visit.setDate(dto.getDate());
		visit.setIdDoctor(dto.getIdDoctor());
		visit.setIdPatientCard(dto.getIdPatientCard());
		visitRepo.save(visit);
	}
}
