package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.Examination;

public interface ExaminationRepository extends CrudRepository<Examination, Long> {
	Collection<Examination> findAll();
}
