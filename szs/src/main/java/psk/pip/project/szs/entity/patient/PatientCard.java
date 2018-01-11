package psk.pip.project.szs.entity.patient;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import psk.pip.project.szs.entity.medicine.LongTermVisit;
import psk.pip.project.szs.services.patient.Patient;

@Data
@Entity
@Table(name = "patient_card")
public class PatientCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;

	@ManyToOne
	private LongTermVisit currentVisit;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_card_id")
	private Collection<LongTermVisit> longTermVisits;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_card_id")
	private Collection<Visit> visits;

	public PatientCard() {

	}

	public PatientCard(String name, String surname) {
		this.name = name;
		this.surname = surname;

	}

	public Patient toPatient() {
		return Patient.builder().id(id).name(name).surname(surname).build();
	}
}
