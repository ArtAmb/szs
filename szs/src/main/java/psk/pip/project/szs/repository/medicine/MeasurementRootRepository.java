package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.MeasurementRoot;
import psk.pip.project.szs.entity.patient.PatientCard;

public interface MeasurementRootRepository extends CrudRepository<MeasurementRoot, Long> {
	Collection<MeasurementRoot> findByPatient(PatientCard patient);
}
