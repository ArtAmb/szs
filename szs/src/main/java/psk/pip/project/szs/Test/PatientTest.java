package psk.pip.project.szs.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import psk.pip.project.szs.dto.patient.PersonalDataDTO;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.services.patient.PatientService;

public class PatientTest {

	@Autowired
	PatientCardRepository patientCardRepo;
	
	@Test
	public void test() {
		PatientService test = new PatientService();
		patientCardRepo.save(new PatientCard("Franek", "Francesco"));
		
	}

}
