package psk.pip.project.szs.services.administration.employee;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.Employee;

public interface EmployeeCreator {

	Employee createEmployee(EmployeeDTO dto);

}
