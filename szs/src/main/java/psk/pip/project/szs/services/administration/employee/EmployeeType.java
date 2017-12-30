package psk.pip.project.szs.services.administration.employee;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.Employee;

public enum EmployeeType {
	DOCTOR(DoctorCreator.class), NURSE(DoctorCreator.class), RECEPCIONIST(DoctorCreator.class);
	private Class<DoctorCreator> creatorType;

	private EmployeeType(Class<DoctorCreator> creatorType) {
		this.creatorType = creatorType;
	}

	public Employee createEmployee(EmployeeDTO dto) throws InstantiationException, IllegalAccessException {
		return creatorType.newInstance().createEmployee(dto);
	}

}
