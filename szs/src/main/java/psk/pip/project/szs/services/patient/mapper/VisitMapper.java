package psk.pip.project.szs.services.patient.mapper;

import java.sql.Date;
import java.sql.Time;

import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.patient.Visit;

public class VisitMapper {
	static public Visit map(VisitDTO dto) {
		Visit visit = new Visit();
		visit.setStartDate(Date.valueOf(dto.getStartDate()));
		visit.setStartTime(Time.valueOf(dto.getStartTime() + ":00"));
		visit.setDoctor(dto.getDoctor());
		return visit;
	}

}
