package psk.pip.project.szs.entity.storage;

import java.util.Collection;
import java.util.LinkedList;

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
		if (getDrugs() == null)
			drugs = new LinkedList<>();

		Drug tmpDrug = getDrugs().stream().filter(
				d -> d.equals(drug))
				.findFirst().orElse(null);

		if (tmpDrug == null) {
			getDrugs().add(drug);
		} else {
			tmpDrug.setAmount(tmpDrug.getAmount() + drug.getAmount());
		}
	}

	public void deleteDrugs(Drug drug) {
		Drug drugInRoom = drugs.stream().filter(d -> d.equals(drug)).findFirst().orElse(null);
		if (drugInRoom == null)
			throw new RuntimeException("Lek " + drug.toString() + " nie jest obecny na sali");

		Integer newAmount = drugInRoom.getAmount() - drug.getAmount();
		if (newAmount < 0)
			throw new RuntimeException(
					"Ilosc leku " + drug.toString() + " nie jest wystarczajaca! Prosze zlozyc zamowienie do magazynu.");

		drugInRoom.setAmount(newAmount);

	}

}
