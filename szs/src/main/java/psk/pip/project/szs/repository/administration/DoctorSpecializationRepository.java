package psk.pip.project.szs.repository.administration;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.administration.DoctorSpecialization;

public interface DoctorSpecializationRepository extends CrudRepository<DoctorSpecialization, Long> {
	Collection<DoctorSpecialization> findAll();
}
