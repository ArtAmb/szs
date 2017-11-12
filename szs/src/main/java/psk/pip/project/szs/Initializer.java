package psk.pip.project.szs;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import psk.pip.project.szs.entity.employee.Doctor;
import psk.pip.project.szs.entity.employee.Nurse;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.registration.Role;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.employee.DoctorRepository;
import psk.pip.project.szs.repository.employee.NurseRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.systemUser.RoleRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;

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

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostConstruct
	void init() {
		addSystemUsers();
		addDoctors();
		addNurses();
		addPatientCard();
	}

	void addDoctors() {
		doctorRepo.save(new Doctor());
		doctorRepo.save(new Doctor());
		doctorRepo.save(new Doctor());
		doctorRepo.save(new Doctor());
		doctorRepo.save(new Doctor());
	}

	void addNurses() {
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
		nurseRepo.save(new Nurse());
	}

	void addPatientCard() {
		patientCardRepo.save(new PatientCard());
		patientCardRepo.save(new PatientCard());
		patientCardRepo.save(new PatientCard());
		patientCardRepo.save(new PatientCard());
		patientCardRepo.save(new PatientCard());
	}
	
	void addSystemUsers() {
		for (Role role : Roles.toRoleValues()) {
			roleRepo.save(role);
		}

		List<Role> col = new LinkedList<>();
		col.add(Roles.ROLE_ADMIN.toRole());
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		userRepo.save(new User(1l, "user1", encoder.encode("test"), col, true));

		col = new LinkedList<>();
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		userRepo.save(new User(2l, "user2", encoder.encode("test"), col, true));

		col = new LinkedList<>();
		col.add(Roles.ROLE_NURSE.toRole());
		userRepo.save(new User(3l, "user3", encoder.encode("test"), col, true));
	}
}
