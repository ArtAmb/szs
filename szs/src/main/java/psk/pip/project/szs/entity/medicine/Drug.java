package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drug {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "drug_name")
	private DrugName name;
	private Integer dosage;
	private Integer amount;
	@ManyToOne
	@JoinColumn(name = "drug_unit")
	private Unit unit;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Drug))
			return false;
		Drug drug = (Drug) obj;
		return drug.getName().getName().equals(name.getName()) && drug.getDosage().equals(dosage)
				&& drug.getUnit().getName().equals(unit.getName());
	}

	@Override
	public String toString() {
		return name.getName() + " " + dosage + unit.getName();
	}

	public Drug getCopyWithoutIdAndAmount() {
		return new Drug.DrugBuilder().name(name).dosage(dosage).build();
	}

}
