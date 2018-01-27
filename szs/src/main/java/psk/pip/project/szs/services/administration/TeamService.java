package psk.pip.project.szs.services.administration;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.administration.TeamDTO;
import psk.pip.project.szs.entity.administration.DoctorTeam;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.administration.NurseTeam;
import psk.pip.project.szs.entity.employee.Nurse;
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
		LinkedList<Employee> doctors = new LinkedList<>();
		for (Long id : memberIds) {
			Employee doctor = employeeRepo.findDoctorById(id);
			if (doctor == null)
				throw new CannotCreateTeamException("Nie znaleziono Doktora o tym ID = " + id);
			doctors.add(doctor);
		}

		DoctorTeam doctorTeam = new DoctorTeam();
		doctorTeam.setMembers(doctors);
		Employee leader = doctors.getFirst();
		doctorTeam.setLeader(leader);

		doctorTeamRepo.save(doctorTeam);
	}

	public void createTeamNurse(TeamDTO dto) {
		Collection<Long> memberIds = dto.getMemberIds();
		LinkedList<Nurse> nurses = new LinkedList<>();
		for (Long id : memberIds) {
			Nurse nurse = nurseRepo.findOne(id);
			if (nurse == null)
				throw new CannotCreateTeamException("Nie znaleziono Pielęgniarki o tym ID = " + id);
			nurses.add(nurse);
		}

		NurseTeam nurseTeam = new NurseTeam();
		nurseTeam.setMembers(nurses);
		Nurse leader = nurses.getFirst();
		nurseTeam.setLeader(leader);

		nurseTeamRepo.save(nurseTeam);
	}

	public DoctorTeam getTeamDoctor(Long id) {
		DoctorTeam doctorTeam = doctorTeamRepo.findOne(id);
		return doctorTeam;
	}

	public Collection<DoctorTeam> allTeamDoctors() {
		return doctorTeamRepo.findAll();
	}

	public NurseTeam getTeamNurse(Long id) {
		NurseTeam nurseTeam = nurseTeamRepo.findOne(id);
		return nurseTeam;
	}

}
