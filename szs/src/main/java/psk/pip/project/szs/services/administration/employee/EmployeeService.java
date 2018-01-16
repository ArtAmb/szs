package psk.pip.project.szs.services.administration.employee;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.dto.searcher.SearcherMapper;
import psk.pip.project.szs.dto.searcher.SearcherResponse;
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

	public Collection<SearcherResponse> findDoctorsByQueryStr(String query) {
		return employeeRepository.findByQuery(query).stream().map(e -> SearcherMapper.map(e))
				.collect(Collectors.toList());

	}
	
	public Collection<Employee> findAllDoctors(){
		return employeeRepository.findAllDoctors();
	}
	
	public Collection<Employee> findAllNurses(){
		return employeeRepository.findAllNurses();
	}
	

}