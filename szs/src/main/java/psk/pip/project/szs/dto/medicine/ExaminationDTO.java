package psk.pip.project.szs.dto.medicine;

import lombok.Data;

@Data
public class ExaminationDTO {
	private Long patientCardId;
	private String examinationType;
	private String examinationDescription;
	private String startDate;
	private String startTime;
	
	
	private ReciptDTO reciptDTO;
	//private RefferalDTO refferalDTO;
	
	
}