package psk.pip.project.szs.controller.front;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.rooms.StorageService;

@Controller
public class AdministrationFrontController {
	public static String templateDirRoot = "administration/";

	@Autowired
	private RoomRepository roomRepository;

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
	public String View(Model model) {
		return getTemplateDir("storage-room");
	}

}
