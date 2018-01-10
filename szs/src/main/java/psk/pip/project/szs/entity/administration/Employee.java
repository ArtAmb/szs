package psk.pip.project.szs.entity.administration;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import psk.pip.project.szs.entity.registration.User;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	private String name;
	private String surname;
	private String type;
	@ManyToOne
	private EmployeeRank rank;
	private BigDecimal salary;
	@ManyToOne
	private DoctorSpecialization specialization;
}
