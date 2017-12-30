package psk.pip.project.szs.services.administration.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.repository.administration.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmpolyee(EmployeeDTO dto) throws InstantiationException, IllegalAccessException {
		Employee employee = dto.getType().createEmployee(dto);
		return employeeRepository.save(employee);
	}

}
