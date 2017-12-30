package psk.pip.project.szs.services.patient.mapper;

import java.sql.Date;
import java.sql.Time;

import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.medicine.LongTermVisit;

public class LongTermVisitMapper {
	public static LongTermVisit map(VisitDTO dto) {
		return LongTermVisit
				.builder()
				.startDate(Date.valueOf(dto.getStartDate()))
				.endDate(Date.valueOf(dto.getStartDate()))
				.startTime(Time.valueOf(dto.getStartTime()+":00"))
				.endTime(Time.valueOf(dto.getEndTime()+":00"))
				.build();
	}

}
