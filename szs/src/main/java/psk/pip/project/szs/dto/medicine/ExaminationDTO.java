package psk.pip.project.szs.dto.medicine;

import lombok.Data;
import psk.pip.project.szs.entity.medicine.ExaminationType;

@Data
public class ExaminationDTO {
	private Long patientCardId;

	private String examinationDescription;
	private String startDate;
	private String startTime;

	private ExaminationType examinationType;
	private String reciptDescription;
	private String refferalType;
	private String refferalDescription;

}