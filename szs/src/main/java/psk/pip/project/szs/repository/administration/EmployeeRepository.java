package psk.pip.project.szs.repository.administration;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.administration.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
