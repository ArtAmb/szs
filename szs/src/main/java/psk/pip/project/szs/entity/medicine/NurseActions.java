package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import psk.pip.project.szs.entity.administration.Nurse;

@Entity
public class NurseActions {

	@Id
	private Long id;

	@ManyToOne
	private LongTermVisit longTermVisit;

	@ManyToOne
	private Nurse nurse;

	// @OneToMany
	// private Collection<NurseAction> nurseActions;

}
