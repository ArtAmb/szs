package psk.pip.project.szs.entity.administration;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.services.administration.employee.EmployeeType;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "employee_id")
	private User user;
	private String name;
	private String surname;
	@Enumerated(EnumType.STRING)
	private EmployeeType type;
	@ManyToOne
	private EmployeeRank rank;
	private BigDecimal salary;
	@ManyToOne
	private DoctorSpecialization specialization;
}
