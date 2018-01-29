package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;



import psk.pip.project.szs.entity.medicine.Refferal;

public interface RefferalRepository extends CrudRepository<Refferal, Long> {
	Collection<Refferal> findAll();
}
