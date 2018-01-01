package psk.pip.project.szs.services.patient.mapper;

import java.sql.Date;
import java.sql.Time;

import org.codehaus.groovy.util.StringUtil;
import org.h2.util.StringUtils;

import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.medicine.LongTermVisit;

public class LongTermVisitMapper {
	public static LongTermVisit map(VisitDTO dto) {
		return LongTermVisit
				.builder()
				.startDate(Date.valueOf(dto.getStartDate()))
				.endDate(StringUtils.isNullOrEmpty(dto.getEndDate())? null : Date.valueOf(dto.getEndDate()))
				.startTime(Time.valueOf(dto.getStartTime()+":00"))
				.endTime(StringUtils.isNullOrEmpty(dto.getEndTime()) ? null : Time.valueOf(dto.getEndTime()+":00"))
				.build();
	}

}
