package psk.pip.project.szs.services.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
	private Long id;
	private String name;
	private String surname;

}
