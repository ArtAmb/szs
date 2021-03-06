package psk.pip.project.szs.repository.administration;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.administration.NurseTeam;

public interface NurseTeamRepository extends CrudRepository<NurseTeam, Long> {
	Collection<NurseTeam> findAll();
	Collection<NurseTeam> findByLeader_surnameContaining(String query);
}
