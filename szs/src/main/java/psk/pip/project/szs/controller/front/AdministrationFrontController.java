package psk.pip.project.szs.controller.front;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import psk.pip.project.szs.entity.administration.DoctorTeam;
import psk.pip.project.szs.entity.administration.NurseTeam;
import psk.pip.project.szs.entity.administration.Ward;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.administration.DoctorTeamRepository;
import psk.pip.project.szs.repository.administration.NurseTeamRepository;
import psk.pip.project.szs.repository.administration.WardRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.administration.TeamService;
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

	@Autowired
	private WardRepository wardRepository;

	@Autowired
	private TeamService teamService;

	@Autowired
	private DoctorTeamRepository doctorTeamRepo;

	@Autowired
	private NurseTeamRepository nurseTeamRepo;

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
		model.addAttribute("wards", wardRepository.findAll());
		return getTemplateDir("hospital-wards");
	}

	@GetMapping("/view/administration/teamDoctors")
	public String teamDoctorsView(Model model) {
		model.addAttribute("teamDoctors", teamService.allTeamDoctors());
		return getTemplateDir("hospital-teamDoctors");
	}

	@GetMapping("/view/administration/teamNurses")
	public String teamNursesView(Model model) {
		model.addAttribute("teamNurses", teamService.allTeamNurses());
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

	@GetMapping("/view/administration/new/ward")
	public String addWardView(Model model) {
		return getTemplateDir("add-ward");
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

	@GetMapping("/view/hospital/teamDoctor/{id}")
	public String hospitalTeamDoctorView(@PathVariable Long id, Model model) {
		DoctorTeam teamDoctor = doctorTeamRepo.findOne(id);
		model.addAttribute("team", teamDoctor);
		return getTemplateDir("teamDoctor-detail");
	}

	@GetMapping("/view/hospital/teamNurse/{id}")
	public String hospitalTeamNurseView(@PathVariable Long id, Model model) {
		NurseTeam teamNurse = nurseTeamRepo.findOne(id);
		model.addAttribute("team", teamNurse);
		return getTemplateDir("teamNurse-detail");
	}

	@GetMapping("/view/hospital/ward/{id}")
	public String hospitalWardView(@PathVariable Long id, Model model) {
		Ward ward = wardRepository.findOne(id);
		model.addAttribute("ward", ward);
		return getTemplateDir("ward-detail");
	}
}
