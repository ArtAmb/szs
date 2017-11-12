package psk.pip.project.szs.repository.patient;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.patient.PatientCard;

public interface PatientCardRepository extends CrudRepository<PatientCard, Long> {
}
