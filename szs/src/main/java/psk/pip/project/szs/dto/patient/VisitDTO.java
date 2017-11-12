package psk.pip.project.szs.dto.patient;

import lombok.Data;

@Data
public class VisitDTO {
	private String date;
	private Long idPatientCard;
	private Long idDoctor;
}
