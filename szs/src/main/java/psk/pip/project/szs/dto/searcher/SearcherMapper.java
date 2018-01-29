package psk.pip.project.szs.dto.searcher;

import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.storage.HospitalRoom;

public class SearcherMapper {

	public static SearcherResponse map(Employee employee) {
		String text = String.format("Id: %d, login: %s dr. %s %s", employee.getId(), employee.getUser().getLogin(),
				employee.getName(), employee.getSurname());
		return SearcherResponse.builder().entityId(employee.getId()).text(text).build();
	}

	public static SearcherResponse map(Drug drug) {
		String text = String.format("%s %s %s %s", drug.getName().getName(), drug.getDosage(), drug.getUnit().getName(),
				drug.getAmount());
		return SearcherResponse.builder().entityId(drug.getId()).text(text).build();
	}

	public static SearcherResponse map(HospitalRoom room) {
		String text = String.format("%s", room.getRoomName());
		return SearcherResponse.builder().entityId(room.getId()).text(text).build();
	}
	
	public static SearcherResponse map(PatientCard patientCard) {
		String text = String.format("Id: %d, %s %s", patientCard.getId(),
				patientCard.getName(), patientCard.getSurname());
		return SearcherResponse.builder().entityId(patientCard.getId()).text(text).build();
	}
}
