package psk.pip.project.szs.repository.patient;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.patient.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {

	Visit findByExaminationId(Long id);

	Collection<Visit> findByDoctorId(Long id);
}
