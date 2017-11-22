package psk.pip.project.szs.dto.patient;

import lombok.Data;

@Data
public class ReferralDTO {
	private Long idDoctor;
	private Long idReferralType;
	private String description;
}
