package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import psk.pip.project.szs.entity.employee.Doctor;

@Entity
public class Examination {

	@Id
	private Long id;

	@ManyToOne
	private Doctor doctor;
}
