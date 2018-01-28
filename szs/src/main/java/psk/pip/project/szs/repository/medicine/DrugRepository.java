package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.Drug;

public interface DrugRepository extends CrudRepository<Drug, Long> {
	Collection<Drug> findAll();

	Collection<Drug> findByName_NameContaining(String query);
}
