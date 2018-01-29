package psk.pip.project.szs.dto.medicine;

import lombok.Data;

@Data
public class ExaminationDTO {
	private Long patientCardId;
	
	private String examinationDescription;
	private String startDate;
	private String startTime;
	
	
	private ExaminationTypeDTO examinationTypeDTO;
	private ReciptDTO reciptDTO;
	private RefferalDTO refferalDTO;
	
	
	
}