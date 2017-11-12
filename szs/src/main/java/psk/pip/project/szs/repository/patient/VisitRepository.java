package psk.pip.project.szs.repository.patient;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.patient.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
