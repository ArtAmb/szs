package psk.pip.project.szs.services.medicine.strategy;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.medicine.NurseAction;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.medicine.DrugRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.medicine.GivenDrug;
import psk.pip.project.szs.services.medicine.NurseActionType;

@Component
public class GivenDrugSaver extends NurseActionSaverStrategy<GivenDrug> {

	@Autowired
	private PatientCardRepository patientRepo;

	@Autowired
	private RoomRepository roomRepo;
	
	@Autowired
	private DrugRepository drugRepo;

	@Override
	protected void createNurseAction(GivenDrug obj) {
		nurseAction = NurseAction.builder()
				.description(obj.getDescription())
				.medicalEmployee(medicalEmployee)
				.drugs(obj.getDrugs())
				.type(NurseActionType.DRUG_GIVEN)
				.date(new Timestamp(System.currentTimeMillis()))
				.build();
	}

	@Override
	protected boolean validateObjectAndSetAdditionalData(GivenDrug object) {
		patientCard = patientRepo.findOne(object.getPatientId());

		HospitalRoom room = patientCard.getRoom();
		if (room == null)
			throw new RuntimeException("Pacjent nie jest przypisany do sali! Brak informacji o zrodle leku!");

		Collection<Drug> drugs = object.getDrugs();
		if (drugs.isEmpty())
			throw new RuntimeException("Brak informacji o ilosci lekow");

		for (Drug drug : drugs)
			room.deleteDrugs(drug);

		roomRepo.save(room);
		
		
		Collection<Drug> givenDrugsInfoCol = new LinkedList<>();
		for (Drug drug : drugs){
			Drug realDrug = drugRepo.findOne(drug.getId());
			Drug givenDrugInfo = realDrug.getCopyWithoutIdAndAmount();
			givenDrugInfo.setAmount(drug.getAmount());
			givenDrugsInfoCol.add(givenDrugInfo);
		}
		object.setDrugs(givenDrugsInfoCol);
		
		
		return true;
	}

}
