package psk.pip.project.szs.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.searcher.SearcherParams;
import psk.pip.project.szs.dto.searcher.SearcherResponse;
import psk.pip.project.szs.services.administration.employee.EmployeeService;

@RestController
public class SearcherController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/searcher/doctor/query")
	public Collection<SearcherResponse> findDoctorsByQueryString(@RequestBody SearcherParams params)
			throws InstantiationException, IllegalAccessException {
		return employeeService.findDoctorsByQueryStr(params.getQueryString());
	}

}
