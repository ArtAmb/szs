package psk.pip.project.szs.repository.medicine;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.medicine.MeasurementTemplateRoot;
import psk.pip.project.szs.entity.registration.User;

public interface MeasurementTemplateRootRepository extends CrudRepository<MeasurementTemplateRoot, Long> {
	Collection<MeasurementTemplateRoot> findByUser(User user);
}
