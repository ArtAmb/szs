package psk.pip.project.szs.services.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.administration.WardDTO;
import psk.pip.project.szs.entity.administration.DoctorTeam;
import psk.pip.project.szs.entity.administration.NurseTeam;
import psk.pip.project.szs.entity.administration.Ward;
import psk.pip.project.szs.repository.administration.DoctorTeamRepository;
import psk.pip.project.szs.repository.administration.NurseTeamRepository;
import psk.pip.project.szs.repository.administration.WardRepository;
import psk.pip.project.szs.services.administration.exception.CannotCreateWardException;

@Service
public class WardService {

	@Autowired
	private WardRepository wardRepo;

	@Autowired
	private NurseTeamRepository nurseTeamRepo;

	@Autowired
	private DoctorTeamRepository doctorTeamRepo;

	public void createWard(WardDTO dto) {
		Ward ward = new Ward();

		DoctorTeam doctorTeam = doctorTeamRepo.findOne(dto.getIdDoctorTeam());
		if (doctorTeam == null)
			throw new CannotCreateWardException("Nie znaleziono DoctorTeam o ID = " + dto.getIdDoctorTeam());

		NurseTeam nurseTeam = nurseTeamRepo.findOne(dto.getIdNurseTeam());
		if (nurseTeam == null)
			throw new CannotCreateWardException("Nie znaleziono DoctorTeam o ID = " + dto.getIdNurseTeam());
		ward.setIdDoctorTeam(doctorTeam);
		ward.setIdNurseTeam(nurseTeam);
		ward.setNameWard(dto.getNameWard());
		ward.setLeader(doctorTeam.getLeader());

		wardRepo.save(ward);

	}

	public void deleteWard(Long id) {
		Ward ward = wardRepo.findOne(id);
		if (ward == null)
			throw new RuntimeException("Oddział o id:" + id + "nie istnieje");

		ward.getIdDoctorTeam().setInWard(false);
		ward.getIdNurseTeam().setInWard(false);
		wardRepo.save(ward);

		// TODO zrobić kiedyś tranzakcję
		wardRepo.delete(id);

	}

	public Ward getWard(Long id) {
		Ward ward = wardRepo.findOne(id);
		return ward;
	}
}
