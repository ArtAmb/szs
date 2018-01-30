package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.medicine.DrugName;
import psk.pip.project.szs.entity.medicine.Unit;

public interface DrugRepository extends CrudRepository<Drug, Long> {
	Collection<Drug> findAll();

	Collection<Drug> findByName_NameContaining(String query);

	Collection<Drug> findByAmount(Integer amount);

	Collection<Drug> findByNameAndUnitAndDosageAndAmountIsNull(DrugName name, Unit unit, Integer dosage);
}
