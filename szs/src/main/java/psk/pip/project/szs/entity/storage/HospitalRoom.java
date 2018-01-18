package psk.pip.project.szs.entity.storage;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.pip.project.szs.entity.medicine.Drug;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String roomName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "hospital_room")
	private Collection<Drug> drugs;

	public void addDrug(Drug drug) {
		drug.setId(null);
		Drug drugTmp = getDrugs().stream().filter(
				d -> d.getDosage().equals(drug.getDosage()) && d.getName().getId().equals(drug.getName().getId()))
				.findFirst().orElse(drug);

		if (drugTmp.getId() == null) {
			getDrugs().add(drugTmp);
		} else {
			drugTmp.setAmount(drugTmp.getAmount() + drug.getAmount());
		}
	}

}
