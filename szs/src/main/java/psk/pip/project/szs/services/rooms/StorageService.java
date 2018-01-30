package psk.pip.project.szs.services.rooms;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.medicine.DrugDTO;
import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.medicine.DrugRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;

@Service
public class StorageService {
	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private DrugRepository drugRepo;

	public final static Long STORAGE_ID = 1l;

	public HospitalRoom getStorage() {
		return roomRepo.findOne(STORAGE_ID);
	}

	public void addDrugs(Collection<Drug> drugs) {
		drugs.forEach(d -> addDrug(d));
	}

	public void addDrug(Drug drug) {
		drug.setId(null);
		HospitalRoom storage = roomRepo.findOne(STORAGE_ID);
		Drug drugTmp = roomRepo.findOne(STORAGE_ID).getDrugs().stream().filter(
				d -> d.getDosage().equals(drug.getDosage()) && d.getName().getId().equals(drug.getName().getId()))
				.findFirst().orElse(drug);

		if (drugTmp.getId() == null) {
			storage.getDrugs().add(drugTmp);
		} else {
			drugTmp.setAmount(drugTmp.getAmount() + drug.getAmount());
		}

		roomRepo.save(storage);
	}

	public boolean checkIfEnough(Drug drug) {
		HospitalRoom storage = roomRepo.findOne(STORAGE_ID);

		for (Drug storageDrug : storage.getDrugs()) {
			if (storageDrug.equals(drug)) {
				return storageDrug.getAmount() >= drug.getAmount();
			}
		}
		return true;
	}

	public void addNewDrug(DrugDTO dto) {
		Drug drug = drugRepo.findOne(dto.getDrugId());

		Drug newDrug = drug.getCopyWithoutIdAndAmount();
		newDrug.setAmount(dto.getAmount());

		HospitalRoom storage = getStorage();
		storage.addDrug(newDrug);

		roomRepo.save(storage);
	}

}
