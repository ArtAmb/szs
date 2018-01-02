package psk.pip.project.szs.services.rooms;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.storage.RoomRepository;

@Service
public class StorageService {
	@Autowired
	private RoomRepository roomRepo;
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

}
