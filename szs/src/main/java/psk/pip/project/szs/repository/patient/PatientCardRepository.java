package psk.pip.project.szs.repository.patient;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.services.patient.Patient;

public interface PatientCardRepository extends JpaRepository<PatientCard, Long> {
	List<PatientCard> findAll();

	@Query("select new psk.pip.project.szs.services.patient.Patient(pc.id, pc.name, pc.surname) from PatientCard pc where pc.id = ?1")
	Patient findPatientById(Long id);
	
	@Query(value = "select e from PatientCard e where (e.name like %?1% or e.surname like %?1% or e.id like %?1%)")
	Collection<PatientCard> findPatientsByQuery(String query);

	Collection<PatientCard> findLongTermVisitsByIdAndLongTermVisits_isEndTrue(Long id);

	Collection<PatientCard> findByRoom(HospitalRoom room);
}
