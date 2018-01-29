package psk.pip.project.szs.repository.administration;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.administration.DoctorTeam;

public interface DoctorTeamRepository extends CrudRepository<DoctorTeam, Long> {
	Collection<DoctorTeam> findAll();

	Collection<DoctorTeam> findByLeader_surnameContaining(String query);
}
