package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.ExaminationType;

public interface ExaminationTypeRepository extends CrudRepository<ExaminationType, Long> {
	Collection<ExaminationType> findAll();
}
