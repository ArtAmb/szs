package psk.pip.project.szs.services.administration.employee;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.registration.Role;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;

public class NurseCreator extends EmployeeCreator {

	@Override
	public Employee createEmployee(EmployeeDTO dto) {
		List<Role> col = new LinkedList<>();
		col.add(Roles.ROLE_NURSE.toRole());
		col.add(Roles.ROLE_MEDICAL_EMPLOYEE.toRole());

		User user = User.builder()
				.login(dto.getLogin())
				.password(new BCryptPasswordEncoder().encode(dto.getPass()))
				.active(true)
				.roles(col)
				.build();

		Employee result = Employee.builder()
				.user(user)
				.name(dto.getName())
				.surname(dto.getSurname())
				.specialization(dto.getSpecialization())
				.rank(dto.getRank())
				.type(EmployeeType.NURSE)
				.build();

		return result;
	}

}
