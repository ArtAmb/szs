package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.MeasurementType;

public interface MeasurementTypeRepository extends CrudRepository<MeasurementType, Long> {
	Collection<MeasurementType> findAll();
}
