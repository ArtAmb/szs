package psk.pip.project.szs.repository.administration;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.registration.User;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "select e from Employee e where e.type='DOCTOR' and (e.name like %?1% or e.surname like %?1% or e.id like %?1%)")
	Collection<Employee> findDoctorsByQuery(String query);

	@Query(value = "select e from Employee e where e.type='NURSE' and (e.name like %?1% or e.surname like %?1% or e.id like %?1%)")
	Collection<Employee> findNursesByQuery(String query);

	@Query(value = "select e from Employee e where e.type='DOCTOR' and e.id=?1")
	Employee findDoctorById(Long id);
	// Collection<Employee> findByNameOrSurnameOrIdContaining(String query);

	@Query(value = "select e from Employee e where e.type='NURSE' and e.id=?1")
	Employee findNurseById(Long id);

	@Query(value = "select e from Employee e where e.type='DOCTOR'")
	Collection<Employee> findAllDoctors();

	@Query(value = "select e from Employee e where e.type='NURSE'")
	Collection<Employee> findAllNurses();

	Employee findByUser(User user);
}
