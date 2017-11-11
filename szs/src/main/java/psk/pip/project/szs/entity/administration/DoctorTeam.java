package psk.pip.project.szs.entity.administration;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import psk.pip.project.szs.entity.employee.Doctor;



@Entity 
@Data
@Table(name = "doctor_team")
public class DoctorTeam{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@ManyToOne
	protected Doctor leader;
	@OneToMany
	@JoinColumn(name = "doctor_team")
	protected Collection<Doctor> members;
}
