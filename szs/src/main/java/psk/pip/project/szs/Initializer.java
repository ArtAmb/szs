package psk.pip.project.szs;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.employee.Nurse;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.patient.ReferralType;
import psk.pip.project.szs.entity.registration.Role;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.employee.DoctorRepository;
import psk.pip.project.szs.repository.employee.NurseRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.ReferralTypeRepository;
import psk.pip.project.szs.repository.systemUser.RoleRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.administration.employee.EmployeeService;
import psk.pip.project.szs.services.administration.employee.EmployeeType;

@Component
public class Initializer {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	DoctorRepository doctorRepo;

	@Autowired
	NurseRepository nurseRepo;

	@Autowired
	PatientCardRepository patientCardRepo;

	@Autowired
	ReferralTypeRepository referralTypeRepo;

	@Autowired
	EmployeeService employeeService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostConstruct
	void init() {
		addSystemUsers();
		addDoctors();
		addNurses();
		addPatientCard();
		addReferralType();
	}

	void addDoctors() {
		try {
			EmployeeDTO dto = EmployeeDTO.builder().name("Piotr").surname("Piotrowski").type(EmployeeType.DOCTOR)
					.login("doctor").pass("test").build();
			employeeService.saveEmpolyee(dto);
			dto = EmployeeDTO.builder().name("Klaudia").surname("Klaudiowska").type(EmployeeType.DOCTOR)
					.login("doctor1").pass("test").build();
			employeeService.saveEmpolyee(dto);
			dto = EmployeeDTO.builder().name("Monika").surname("Monikowska").type(EmployeeType.DOCTOR).login("doctor2")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);
			dto = EmployeeDTO.builder().name("Klaudia").surname("Klaudiowska").type(EmployeeType.DOCTOR)
					.login("doctor3").pass("test").build();
			employeeService.saveEmpolyee(dto);
			dto = EmployeeDTO.builder().name("Robert").surname("Robertowski").type(EmployeeType.DOCTOR).login("doctor4")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);
			dto = EmployeeDTO.builder().name("Rob").surname("Robrowy").type(EmployeeType.DOCTOR).login("doctor5")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);
			dto = EmployeeDTO.builder().name("Dominika").surname("Dominikowska").type(EmployeeType.DOCTOR)
					.login("doctor6").pass("test").build();
			employeeService.saveEmpolyee(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void addNurses() {
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
	}

	void addPatientCard() {
		patientCardRepo.save(new PatientCard("Franek", "Francesco"));
		patientCardRepo.save(new PatientCard("Truman", "Trauma"));
		patientCardRepo.save(new PatientCard("Boleslaw", "Kedzierzawy"));
		patientCardRepo.save(new PatientCard("Rumcajs", "Brodaty"));
		patientCardRepo.save(new PatientCard("Rademenes", "Faraonski"));
	}

	void addReferralType() {
		referralTypeRepo.save(new ReferralType());
		referralTypeRepo.save(new ReferralType());
		referralTypeRepo.save(new ReferralType());
		referralTypeRepo.save(new ReferralType());
		referralTypeRepo.save(new ReferralType());
	}

	void addSystemUsers() {
		for (Role role : Roles.toRoleValues()) {
			roleRepo.save(role);
		}

		List<Role> col = new LinkedList<>();
		col.add(Roles.ROLE_ADMIN.toRole());
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
		userRepo.save(new User(1l, "user1", encoder.encode("test"), col, true));

		col = new LinkedList<>();
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
		userRepo.save(new User(2l, "user2", encoder.encode("test"), col, true));

		col = new LinkedList<>();
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
		userRepo.save(new User(3l, "user3", encoder.encode("test"), col, true));
	}
}
