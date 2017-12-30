package psk.pip.project.szs.services.administration.employee;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.Employee;

public enum EmployeeType {
	DOCTOR(DoctorCreator.class), NURSE(NurseCreator.class), RECEPCIONIST(RecepcionistCreator.class);
	private Class<? extends EmployeeCreator> creatorType;

	private EmployeeType(Class<? extends EmployeeCreator> creatorType) {
		this.creatorType = creatorType;
	}

	public Employee createEmployee(EmployeeDTO dto) throws InstantiationException, IllegalAccessException {
		return creatorType.newInstance().createEmployee(dto);
	}
}
