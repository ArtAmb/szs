package psk.pip.project.szs.services.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.patient.PatientDTO;
import psk.pip.project.szs.entity.patient.Patient;
import psk.pip.project.szs.repository.patient.PatientRepository;

@Service

public class PatientService {
	
	@Autowired
	private PatientRepository patientRepo;

	public void addPatient(PatientDTO dto) {
		Patient patient = new Patient();
		patient.setImie(dto.getImie());
		patient.setNazwisko(dto.getNazwisko());
		patientRepo.save(patient);
	}
	
	public Patient getPatient(Long id) {
		Patient patient = patientRepo.findOne(id);
		return patient;
	}
}
