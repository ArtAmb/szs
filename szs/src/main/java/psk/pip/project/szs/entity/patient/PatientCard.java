package psk.pip.project.szs.entity.patient;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import psk.pip.project.szs.entity.medicine.LongTermVisit;

@Data
@Entity
public class PatientCard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String surname;

	@ManyToOne
	private LongTermVisit curentVisit;

	@OneToMany
	@JoinColumn(name = "patient_card")
	private Collection<LongTermVisit> longTermVisitHistory;

	@OneToMany
	@JoinColumn(name = "patient_card")
	private Collection<Visit> visitHistory;

	public PatientCard() {

	}

	public PatientCard(String name, String surname) {
		this.name = name;
		this.surname = surname;

	}
}
