package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.administration.DrugTransferDTO;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.HospitalService;

@RestController
public class HospitalController {

	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private HospitalService hospitalService;

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_ADMIN + "')")
	@GetMapping("/test/admin")
	public String testForAdmin() {
		return "admin: dzialam!!";
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_DOCTOR + "')")
	@GetMapping("/test/doctor")
	public String testForDoctor() {
		return "doctor: dzialam!!";
	}

	@PostMapping("/hospital/new/room")
	public void addNewRoom(HospitalRoom dto) {
		roomRepo.save(dto);
	}

	@PostMapping("/hospital/room/drug/transfer")
	public void transferDrugsFromWarehouseToHospitalRoom(DrugTransferDTO dto) {
		hospitalService.transferDurgsFromStorageToRoom(dto);
	}

}
