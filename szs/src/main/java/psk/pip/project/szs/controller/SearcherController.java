package psk.pip.project.szs.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.searcher.SearcherMapper;
import psk.pip.project.szs.dto.searcher.SearcherParams;
import psk.pip.project.szs.dto.searcher.SearcherResponse;
import psk.pip.project.szs.repository.medicine.DrugRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.HospitalService;
import psk.pip.project.szs.services.administration.TeamService;
import psk.pip.project.szs.services.administration.employee.EmployeeService;
import psk.pip.project.szs.services.patient.PatientService;
import psk.pip.project.szs.services.rooms.StorageService;

@RestController
public class SearcherController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DrugRepository drugRepo;

	@Autowired
	private StorageService storageService;

	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private HospitalService hospitalRoomService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private TeamService teamService;

	@PostMapping("/searcher/doctor/query")
	public Collection<SearcherResponse> findDoctorsByQueryString(@RequestBody SearcherParams params)
			throws InstantiationException, IllegalAccessException {
		return employeeService.findDoctorsByQueryStr(params.getQueryString());
	}

	@PostMapping("/searcher/drug/query")
	public Collection<SearcherResponse> findDrugsInStorageByQueryString(@RequestBody SearcherParams params)
			throws InstantiationException, IllegalAccessException {
		return storageService.getStorage().getDrugs().stream()
				.filter(d -> d.getName().getName().contains(params.getQueryString())).map(d -> SearcherMapper.map(d))
				.collect(Collectors.toList());
		// return
		// drugRepo.findByName_NameContaining(params.getQueryString()).stream().map(d
		// -> SearcherMapper.map(d))
		// .collect(Collectors.toList());
	}

	@PostMapping("/searcher/drug/in-room/{roomId}/query")
	public Collection<SearcherResponse> findDrugsInRoomByQueryString(@RequestBody SearcherParams params,
			@PathVariable Long roomId) throws InstantiationException, IllegalAccessException {
		return roomRepo.findOne(roomId).getDrugs().stream().map(d -> SearcherMapper.map(d))
				.collect(Collectors.toList());
	}

	@PostMapping("/searcher/config-drug/query")
	public Collection<SearcherResponse> findConfigDrugsByQueryString(@RequestBody SearcherParams params,
			@PathVariable Long roomId) throws InstantiationException, IllegalAccessException {
		return drugRepo.findByName_NameContaining(params.getQueryString()).stream().map(d -> SearcherMapper.map(d))
				.collect(Collectors.toList());
	}

	@PostMapping("/searcher/room/query")
	public Collection<SearcherResponse> findRoomsByQueryString(@RequestBody SearcherParams params)
			throws InstantiationException, IllegalAccessException {
		return hospitalRoomService.findByQuery(params.getQueryString()).stream().map(d -> SearcherMapper.map(d))
				.collect(Collectors.toList());
	}

	@PostMapping("/searcher/doctor/query/filter/inTeam/{isInTeam}")
	public Collection<SearcherResponse> findDoctorsFilterInTeamByQueryString(@RequestBody SearcherParams params,
			@PathVariable Boolean isInTeam) throws InstantiationException, IllegalAccessException {
		return employeeService.findDoctorsByQueryStrWithFilerOnInTeam(params.getQueryString(), isInTeam);
	};

	@PostMapping("/searcher/nurse/query")
	public Collection<SearcherResponse> findNursesByQueryString(@RequestBody SearcherParams params)
			throws InstantiationException, IllegalAccessException {
		return employeeService.findNursesByQueryStr(params.getQueryString());
	}

	@PostMapping("/searcher/nurse/query/filter/inTeam/{isInTeam}")
	public Collection<SearcherResponse> findNursesFilterInTeamByQueryString(@RequestBody SearcherParams params,
			@PathVariable Boolean isInTeam) throws InstantiationException, IllegalAccessException {
		return employeeService.findNursesByQueryStrWithFilerOnInTeam(params.getQueryString(), isInTeam);
	}

	@PostMapping("/searcher/patient/query")
	public Collection<SearcherResponse> findPatientsByQueryString(@RequestBody SearcherParams params)
			throws InstantiationException, IllegalAccessException {
		return patientService.findPatientsByQueryStr(params.getQueryString());
	}

	@PostMapping("/searcher/team/doctor/query/filter/inWard/{isInTeam}")
	public Collection<SearcherResponse> findTeamNursesFilterInWardByQueryString(@RequestBody SearcherParams params,
			@PathVariable Boolean isInTeam) throws InstantiationException, IllegalAccessException {
		return teamService.findTeamDoctorsByQueryStrWithFilerOnInWard(params.getQueryString(), isInTeam).stream()
				.map(t -> SearcherMapper.map(t)).collect(Collectors.toList());
	}

	@PostMapping("/searcher/team/nurse/query/filter/inWard/{isInTeam}")
	public Collection<SearcherResponse> findDoctorTeamsFilterInWardByQueryString(@RequestBody SearcherParams params,
			@PathVariable Boolean isInTeam) throws InstantiationException, IllegalAccessException {
		return teamService.findTeamNursesQueryStrWithFilerOnInWard(params.getQueryString(), isInTeam).stream()
				.map(t -> SearcherMapper.map(t)).collect(Collectors.toList());
	}

}