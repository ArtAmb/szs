package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.DrugName;

public interface DrugNameRepository extends CrudRepository<DrugName, Long> {
	Collection<DrugName> findAll();
}
