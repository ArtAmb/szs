package psk.pip.project.szs.dto.administration;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import psk.pip.project.szs.entity.administration.DoctorSpecialization;
import psk.pip.project.szs.entity.administration.EmployeeRank;
import psk.pip.project.szs.services.administration.employee.EmployeeType;

@Data
@Builder
public class EmployeeDTO {

	private String login;
	private String pass;
	private String name;
	private String surname;
	private EmployeeType type;
	private EmployeeRank rank;
	private BigDecimal salary;
	private DoctorSpecialization specialization;

}
