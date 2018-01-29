package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import psk.pip.project.szs.entity.employee.Doctor;

@Data
@Entity
public class Examination {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;	
	private Long patientCardId;
	private String examinationType;
	private String examinationDescription;
	private String startDate;
	private String startTime;
	
	@OneToOne
	private Recipt recipt;
	
	/*
	@OneToOne
	private Refferal refferal;
	*/

	@ManyToOne
	private Doctor doctor;

}
