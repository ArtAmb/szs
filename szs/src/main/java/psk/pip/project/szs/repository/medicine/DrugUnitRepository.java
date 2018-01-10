package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.Unit;

public interface DrugUnitRepository extends CrudRepository<Unit, Long> {
	Collection<Unit> findAll();
}
