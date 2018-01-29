package psk.pip.project.szs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import psk.pip.project.szs.dto.administration.EmployeeDTO;
import psk.pip.project.szs.entity.administration.DoctorSpecialization;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.services.administration.employee.EmployeeType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewEmployeeTest {
	DoctorSpecialization spec = DoctorSpecialization.builder().name("test").build();
	EmployeeDTO dto = EmployeeDTO.builder().login("test").specialization(spec).pass("test").name("test").surname("test")
			.build();

	@Test
	public void createDoctor() {
		try {
			Employee doctor = EmployeeType.DOCTOR.createEmployee(dto);

			assertThat(doctor.getType().name()).isEqualTo(EmployeeType.DOCTOR.name());
			assertThat(doctor.getUser().getRoles()).contains(Roles.ROLE_DOCTOR.toRole(),
					Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
			assertThat(doctor.getSpecialization()).isNotNull();

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void createNurse() {
		try {
			Employee doctor = EmployeeType.NURSE.createEmployee(dto);

			assertThat(doctor.getType().name()).isEqualTo(EmployeeType.NURSE.name());
			assertThat(doctor.getUser().getRoles()).contains(Roles.ROLE_NURSE.toRole(),
					Roles.ROLE_MEDICAL_EMPLOYEE.toRole());
			assertThat(doctor.getSpecialization()).isNotNull();

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}
