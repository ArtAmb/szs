package psk.pip.project.szs;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.medicine.DrugName;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.entity.medicine.Unit;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.patient.ReferralType;
import psk.pip.project.szs.entity.registration.Role;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.repository.employee.DoctorRepository;
import psk.pip.project.szs.repository.employee.NurseRepository;
import psk.pip.project.szs.repository.medicine.DrugNameRepository;
import psk.pip.project.szs.repository.medicine.DrugRepository;
import psk.pip.project.szs.repository.medicine.DrugUnitRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.ReferralTypeRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.repository.systemUser.RoleRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.administration.employee.EmployeeService;
import psk.pip.project.szs.services.administration.employee.EmployeeType;
import psk.pip.project.szs.services.medicine.ConfigService;
import psk.pip.project.szs.services.medicine.MedicalActionService;
import psk.pip.project.szs.services.patient.PatientService;
import psk.pip.project.szs.services.rooms.StorageService;

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
	DrugNameRepository drugNameRepo;

	@Autowired
	DrugUnitRepository drugUnitRepo;

	@Autowired
	DrugRepository drugRepo;

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	ReferralTypeRepository referralTypeRepo;

	@Autowired
	StorageService storageService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	MedicalActionService medicalActionService;

	@Autowired
	PatientService patientService;

	@Autowired
	ConfigService configService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostConstruct
	void init() {
		addSystemUsers();
		addDoctors();
		addNurses();
		addPatientCard();
		addReferralType();
		addStorage();
		addDrugs();
		// addVisit();
		addConfigTypes();
	}

	void addStorage() {
		roomRepo.save(HospitalRoom.builder().roomName("MAGAZYN").build());

		roomRepo.save(HospitalRoom.builder().roomName("Sala 1").build());
		roomRepo.save(HospitalRoom.builder().roomName("Sala 11a / 3").build());
	}

	void addDrugs() {
		Unit ml = drugUnitRepo.save(Unit.builder().name("ml").build());
		Unit mgr = drugUnitRepo.save(Unit.builder().name("mgr").build());
		Unit sztuk = drugUnitRepo.save(Unit.builder().name("sztuk").build());

		DrugName holinex = drugNameRepo.save(DrugName.builder().name("Holinex").build());
		DrugName morfina = drugNameRepo.save(DrugName.builder().name("Morfina").build());
		DrugName mucosolvan = drugNameRepo.save(DrugName.builder().name("Mucosolvan").build());

		drugRepo.save(Drug.builder().dosage(10).name(holinex).unit(mgr).amount(null).build());
		drugRepo.save(Drug.builder().dosage(20).name(morfina).unit(sztuk).amount(null).build());
		drugRepo.save(Drug.builder().dosage(30).name(mucosolvan).unit(ml).amount(null).build());

		storageService.addDrug(Drug.builder().dosage(10).name(holinex).unit(mgr).amount(1000).build());
		storageService.addDrug(Drug.builder().dosage(20).name(morfina).unit(sztuk).amount(850).build());
		storageService.addDrug(Drug.builder().dosage(30).name(mucosolvan).unit(ml).amount(750).build());
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

		try {
			EmployeeDTO dto = EmployeeDTO.builder().name("Anna").surname("Piotrowska").type(EmployeeType.NURSE)
					.login("nurse").pass("test").build();
			employeeService.saveEmpolyee(dto);

			dto = EmployeeDTO.builder().name("Marta").surname("Mała").type(EmployeeType.NURSE).login("nurse1")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);

			dto = EmployeeDTO.builder().name("Sandra").surname("Chodakowska").type(EmployeeType.NURSE).login("nurse2")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);

			dto = EmployeeDTO.builder().name("Natalia").surname("Duża").type(EmployeeType.NURSE).login("nurse3")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);

			dto = EmployeeDTO.builder().name("Dominika").surname("Pawlik").type(EmployeeType.NURSE).login("nurse4")
					.pass("test").build();
			employeeService.saveEmpolyee(dto);

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

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

	@Transactional
	void addSystemUsers() {
		for (Role role : Roles.toRoleValues()) {
			roleRepo.save(role);
		}

		List<Role> col = new LinkedList<>();
		col.add(Roles.ROLE_ADMIN.toRole());
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
		User user = User.builder().login("user1").password(encoder.encode("test")).roles(col).active(true).build();
		employeeRepository.save(
				Employee.builder().user(user).name("Admin").surname("Adminowski").type(EmployeeType.DOCTOR).build());

		col = new LinkedList<>();
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
		User user2 = User.builder().login("user22").password(encoder.encode("test")).roles(col).active(true).build();
		employeeRepository.save(
				Employee.builder().user(user2).name("Doctor").surname("Doctorowski").type(EmployeeType.DOCTOR).build());

		col = new LinkedList<>();
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
		User user3 = User.builder().login("user3").password(encoder.encode("test")).roles(col).active(true).build();
		employeeRepository.save(Employee.builder().user(user3).name("Pielegniarka").surname("Pielegniarska")
				.type(EmployeeType.NURSE).build());
	}

	void addVisit() {
		patientService.addLongTermVisit(VisitDTO.builder().isLongTermVisit(true).patientCardId(1l)
				.startDate("2018-01-15").startTime("12:00").build());
	}

	void addConfigTypes() {
		configService.addMeasurementType(MeasurementType.builder().name("%").build());
	}
}
