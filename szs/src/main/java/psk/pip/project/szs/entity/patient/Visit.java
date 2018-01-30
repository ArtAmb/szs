package psk.pip.project.szs.entity.patient;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.medicine.Examination;

@Data
@Entity
public class Visit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Timestamp date;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Employee doctor;

	private Date startDate;
	private Time startTime;
	private Boolean isEnd = false;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "examination_id", unique = true)
	private Examination examination;
}
