package psk.pip.project.szs.dto.medicine;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import psk.pip.project.szs.entity.medicine.Measurement;
import psk.pip.project.szs.entity.patient.PatientCard;

@Data
public class MeasurementRootDTO {
	private Long id;
	private String title;
	private String description;
	private Timestamp date;
	private PatientCard patient;
	private Measurement measurement;
}