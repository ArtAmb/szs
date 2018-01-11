package psk.pip.project.szs.entity.medicine;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.services.medicine.NurseActionType;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NurseAction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Timestamp date;
	private String description;

	@Enumerated(EnumType.STRING)
	private NurseActionType type;

	@ManyToOne(cascade = CascadeType.ALL)
	private MedicalItem medicineItem;

	@ManyToOne(cascade = CascadeType.ALL)
	private Drug drug;

	@ManyToOne(cascade = CascadeType.ALL)
	private MeasurementRoot measurementRoot;

	@ManyToOne
	private Employee medicalEmployee;
}
