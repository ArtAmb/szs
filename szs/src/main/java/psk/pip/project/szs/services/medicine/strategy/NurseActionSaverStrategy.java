package psk.pip.project.szs.services.medicine.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.medicine.NurseAction;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;

@Service
abstract public class NurseActionSaverStrategy<T> {
	@Autowired
	protected UserRepository userRepo;
	@Autowired
	protected EmployeeRepository employeeRepo;
	@Autowired
	protected PatientCardRepository patientCardRepository;

	protected Employee medicalEmployee;
	protected PatientCard patientCard;
	protected NurseAction nurseAction;

	protected void findMedicalEmployee(String login) {
		User user = userRepo.findByLogin(login);
		medicalEmployee = employeeRepo.findByUser(user);
	}

	protected abstract void createNurseAction(T object);

	protected void saveNurseAction() {
		patientCard.getCurrentVisit().getActions().add(nurseAction);
		patientCardRepository.save(patientCard);
	}

	protected abstract boolean validateObjectAndSetAdditionalData(T object);

	public void saveNurseAction(String login, T object) {
		findMedicalEmployee(login);
		validateObjectAndSetAdditionalData(object);
		createNurseAction(object);
		saveNurseAction();
	}

}
