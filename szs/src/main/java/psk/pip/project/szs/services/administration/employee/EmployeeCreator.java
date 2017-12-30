package psk.pip.project.szs.services.administration.employee;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.Employee;

public abstract class EmployeeCreator {

	public abstract Employee createEmployee(EmployeeDTO dto);

}
