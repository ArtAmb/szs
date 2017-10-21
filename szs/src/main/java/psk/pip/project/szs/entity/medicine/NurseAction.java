package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import psk.pip.project.szs.entity.administration.Nurse;

@Entity
@Data
public class NurseAction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description;

	@ManyToOne
	private MedicineItem medicineItem;

	@ManyToOne
	private Nurse nurse;
}
