package psk.pip.project.szs.controller.front;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.rooms.StorageService;

@Controller
public class AdministrationFrontController {
	public static String templateDirRoot = "administration/";

	@Autowired
	private RoomRepository roomRepository;

	/*
	 * @Autowired private WardRepository wardRepository;
	 */

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

	@GetMapping("/view/administration/wards")
	public String wardsView(Model model) {
		model.addAttribute("wards");
		return getTemplateDir("hospital-wards");
	}

	@GetMapping("/view/administration/teamDoctors")
	public String teamDoctorsView(Model model) {
		model.addAttribute("teamDoctors");
		return getTemplateDir("hospital-teamDoctors");
	}

	@GetMapping("/view/administration/teamNurses")
	public String teamNursesView(Model model) {
		model.addAttribute("teamNurses");
		return getTemplateDir("hospital-teamNurses");
	}

	@GetMapping("/view/administration/new/teamDoctors")
	public String addteamDoctorsView(Model model) {
		return getTemplateDir("add-teamDoctors");
	}

	@GetMapping("/view/administration/new/teamNurses")
	public String addteamNursesView(Model model) {
		return getTemplateDir("add-teamNurses");
	}

	@GetMapping("/view/administration/rooms")
	public String roomsView(Model model) {
		model.addAttribute("rooms", roomRepository.findAll().stream()
				.filter(r -> !r.getId().equals(StorageService.STORAGE_ID)).collect(Collectors.toList()));
		return getTemplateDir("hospital-rooms");
	}

	@GetMapping("/view/administration/room/storage")
	public String storageView(Model model) {
		return getTemplateDir("storage-room");
	}

	@GetMapping("/view/hospital/room/{id}")
	public String hospitalRoomView(@PathVariable Long id, Model model) {
		model.addAttribute("room", roomRepository.findOne(id));
		return getTemplateDir("room-detail");
	}
}
