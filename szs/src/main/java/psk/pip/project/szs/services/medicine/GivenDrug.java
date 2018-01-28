package psk.pip.project.szs.services.medicine;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import psk.pip.project.szs.entity.medicine.Drug;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GivenDrug {
	private Long patientId;
	private Collection<Drug> drugs;
	private String description;
}
