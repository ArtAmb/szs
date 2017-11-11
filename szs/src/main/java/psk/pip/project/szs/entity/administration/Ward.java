package psk.pip.project.szs.entity.administration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import psk.pip.project.szs.entity.employee.Doctor;

@Data
@Entity
public class Ward {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nameWard;
	private Doctor idLeader;
	private DoctorTeam idDoctorTeam;
	private NurseTeam idNurseTeam;
}
