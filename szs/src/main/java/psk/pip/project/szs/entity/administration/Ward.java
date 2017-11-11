package psk.pip.project.szs.entity.administration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import psk.pip.project.szs.entity.employee.Doctor;

@Data
@Entity
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nameWard;
	@ManyToOne
	private Doctor idLeader;
	@ManyToOne
	private DoctorTeam idDoctorTeam;
	@ManyToOne
	private NurseTeam idNurseTeam;
}
