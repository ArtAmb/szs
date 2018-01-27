package psk.pip.project.szs.controller.front;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.rooms.StorageService;

@Controller
public class AdministrationFrontController {
	public static String templateDirRoot = "administration/";

	@Autowired
	private PatientCardRepository patientCardRepo;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private StorageService storageService;

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/administration")
	public String patientView(Model model) {
		return getTemplateDir("administration");
	}

	@GetMapping("/view/administration/new/employee")
	public String addEmployeeView(Model model) {
		return getTemplateDir("add-employee");
	}

	@GetMapping("/view/administration/rooms")
	public String roomsView(Model model) {
		model.addAttribute("rooms", roomRepository.findAll().stream()
				.filter(r -> !r.getId().equals(StorageService.STORAGE_ID)).collect(Collectors.toList()));
		return getTemplateDir("hospital-rooms");
	}

	@GetMapping("/view/administration/room/storage")
	public String storageView(Model model) {
		model.addAttribute("room", storageService.getStorage());
		return getTemplateDir("storage-room");
	}

	@GetMapping("/view/hospital/room/{id}")
	public String hospitalRoomView(@PathVariable Long id, Model model) {
		HospitalRoom room = roomRepository.findOne(id);
		model.addAttribute("room", room);
		model.addAttribute("patients",
				patientCardRepo.findByRoom(room).stream().map(p -> p.toPatient()).collect(Collectors.toList()));
		return getTemplateDir("room-detail");
	}
}
