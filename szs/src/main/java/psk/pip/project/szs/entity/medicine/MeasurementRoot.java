package psk.pip.project.szs.entity.medicine;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import psk.pip.project.szs.entity.patient.PatientCard;

@Entity
@Data
public class MeasurementRoot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String description;
	private Long date;

	@ManyToOne
	private PatientCard patient;

	@ManyToOne(cascade = CascadeType.ALL)
	private Measurement measurement;
}