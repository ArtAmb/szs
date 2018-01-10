package psk.pip.project.szs.dto.patient;

import lombok.Builder;
import lombok.Data;
import psk.pip.project.szs.entity.administration.Employee;

@Data
@Builder
public class VisitDTO {
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private Boolean isLongTermVisit;
	private Employee doctor;

	private Long patientCardId;
	private Long doctorId;
}
