package psk.pip.project.szs.services.patient.mapper;

import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.services.patient.Patient;

public class PatientCardMapper {

	public static Patient map(PatientCard card) {
		return Patient.builder().id(card.getId()).name(card.getName()).surname(card.getSurname()).build();
	}
}
