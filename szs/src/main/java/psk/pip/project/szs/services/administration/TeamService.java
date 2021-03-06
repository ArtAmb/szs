package psk.pip.project.szs.services.administration;

import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.administration.TeamDTO;
import psk.pip.project.szs.entity.administration.DoctorTeam;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.administration.NurseTeam;
import psk.pip.project.szs.repository.administration.DoctorTeamRepository;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.repository.administration.NurseTeamRepository;
import psk.pip.project.szs.repository.employee.DoctorRepository;
import psk.pip.project.szs.repository.employee.NurseRepository;
import psk.pip.project.szs.services.administration.exception.CannotCreateTeamException;

@Service
public class TeamService {
	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private NurseRepository nurseRepo;

	@Autowired
	private NurseTeamRepository nurseTeamRepo;

	@Autowired
	private DoctorTeamRepository doctorTeamRepo;

	public void createTeamDoctor(TeamDTO dto) {
		Collection<Long> memberIds = dto.getMemberIds();
		int licznik;
		for (Long id : memberIds) {
			licznik = 0;
			for (Long id2 : memberIds) {
				if (id == id2)
					licznik++;
				if (licznik > 1)
					throw new RuntimeException("Dodano kilukrotnie tego samego doktora");
			}
		}
		LinkedList<Employee> doctors = new LinkedList<>();
		for (Long id : memberIds) {
			Employee doctor = employeeRepo.findDoctorById(id);
			if (doctor == null)
				throw new CannotCreateTeamException("Nie znaleziono Doktora o tym ID = " + id);
			doctor.setInTeam(true);
			doctors.add(doctor);

		}

		DoctorTeam doctorTeam = new DoctorTeam();
		doctorTeam.setMembers(doctors);
		Employee leader = doctors.getFirst();
		doctorTeam.setLeader(leader);

		doctorTeamRepo.save(doctorTeam);
	}

	public void deleteDoctorTeam(Long id) {
		DoctorTeam doctorTeam = doctorTeamRepo.findOne(id);
		if (doctorTeam == null)
			throw new RuntimeException("Drużyna lekarzy o id:" + id + "nie istnieje");
		for (Employee doctor : doctorTeam.getMembers()) {
			doctor.setInTeam(false);
			employeeRepo.save(doctor);
		}
		// TODO zrobić kiedyś tranzakcję
		doctorTeamRepo.delete(id);

	}

	public void createTeamNurse(TeamDTO dto) {
		Collection<Long> memberIds = dto.getMemberIds();
		int licznik;
		for (Long id : memberIds) {
			licznik = 0;
			for (Long id2 : memberIds) {
				if (id == id2)
					licznik++;
				if (licznik > 1)
					throw new RuntimeException("Dodano kilukrotnie tą samą pielęgniarke.");
			}
		}
		LinkedList<Employee> nurses = new LinkedList<>();
		for (Long id : memberIds) {
			Employee nurse = employeeRepo.findNurseById(id);
			if (nurse == null)
				throw new CannotCreateTeamException("Nie znaleziono Pielęgniarki o tym ID = " + id);
			nurse.setInTeam(true);
			nurses.add(nurse);
		}

		NurseTeam nurseTeam = new NurseTeam();
		nurseTeam.setMembers(nurses);
		Employee leader = nurses.getFirst();
		nurseTeam.setLeader(leader);

		nurseTeamRepo.save(nurseTeam);
	}

	public void deleteNurseTeam(Long id) {
		NurseTeam nurseTeam = nurseTeamRepo.findOne(id);
		if (nurseTeam == null)
			throw new RuntimeException("Drużyna pielęgniarek o id:" + id + "nie istnieje");
		for (Employee nurse : nurseTeam.getMembers()) {
			nurse.setInTeam(false);
			employeeRepo.save(nurse);
		}
		// TODO zrobić kiedyś tranzakcję
		nurseTeamRepo.delete(id);

	}

	public DoctorTeam getTeamDoctor(Long id) {
		DoctorTeam doctorTeam = doctorTeamRepo.findOne(id);
		return doctorTeam;
	}

	public Collection<DoctorTeam> allTeamDoctors() {
		return doctorTeamRepo.findAll();
	}

	public Collection<NurseTeam> allTeamNurses() {
		return nurseTeamRepo.findAll();
	}

	public NurseTeam getTeamNurse(Long id) {
		NurseTeam nurseTeam = nurseTeamRepo.findOne(id);
		return nurseTeam;
	}

	public Collection<DoctorTeam> findTeamDoctorsByQueryStr(String queryString) {
		return doctorTeamRepo.findByLeader_surnameContaining(queryString);
	}

	public Collection<DoctorTeam> findTeamDoctorsByQueryStrWithFilerOnInWard(String queryString, boolean inWard) {
		return findTeamDoctorsByQueryStr(queryString).stream().filter(t -> t.isInWard() == inWard)
				.collect(Collectors.toList());
	}

	public Collection<NurseTeam> findTeamNursesByQueryStr(String queryString) {
		return nurseTeamRepo.findByLeader_surnameContaining(queryString);
	}

	public Collection<NurseTeam> findTeamNursesQueryStrWithFilerOnInWard(String queryString, boolean inWard) {
		return findTeamNursesByQueryStr(queryString).stream().filter(t -> t.isInWard() == inWard)
				.collect(Collectors.toList());
	}

}
