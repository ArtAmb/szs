package psk.pip.project.szs.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.administration.DrugTransferDTO;
import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.medicine.DrugRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.rooms.StorageService;

@Service
public class HospitalService {

	@Autowired
	private StorageService storageService;

	@Autowired
	private DrugRepository drugRepo;

	@Autowired
	private RoomRepository roomRepo;

	public void transferDurgsFromStorageToRoom(DrugTransferDTO dto) {
		Drug drug = drugRepo.findOne(dto.getDrugId());
		if (drug.getAmount() < dto.getDrugsAmountToMove())
			throw new RuntimeException("W magazynie nie ma wystarczajacej liczby lekow");

		drug.setAmount(drug.getAmount() - dto.getDrugsAmountToMove());
		drugRepo.save(drug); // TODO potrzebna transakacja...

		HospitalRoom room = roomRepo.findOne(dto.getRoomId());
		room.addDrug(Drug.builder().name(drug.getName()).dosage(drug.getDosage()).amount(dto.getDrugsAmountToMove())
				.unit(drug.getUnit()).build());
		roomRepo.save(room);

	}

	public Collection<HospitalRoom> findByQuery(String query) {
		return roomRepo.findByQuery(query);
	}

}
