package psk.pip.project.szs.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInOutDTO {
	private Long longTermVisitId;
	private Long patientCardId;
}
