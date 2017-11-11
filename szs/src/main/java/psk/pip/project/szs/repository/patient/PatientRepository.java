package psk.pip.project.szs.repository.patient;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.patient.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
