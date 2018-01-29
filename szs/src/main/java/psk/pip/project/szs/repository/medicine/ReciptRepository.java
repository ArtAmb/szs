package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;


import psk.pip.project.szs.entity.medicine.Recipt;

public interface ReciptRepository extends CrudRepository<Recipt, Long> {
	Collection<Recipt> findAll();
}
