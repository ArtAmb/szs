package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.administration.TeamDTO;
import psk.pip.project.szs.dto.administration.WardDTO;
import psk.pip.project.szs.entity.administration.DoctorTeam;
import psk.pip.project.szs.entity.administration.NurseTeam;
import psk.pip.project.szs.entity.administration.Ward;
import psk.pip.project.szs.services.administration.TeamService;
import psk.pip.project.szs.services.administration.WardService;

@RestController
public class AdministrationController {

	@Autowired
	private TeamService teamService;

	@Autowired
	private WardService wardService;

	@PostMapping(value = "/administration/team/doctor")
	public void createDoctorTeam(@RequestBody TeamDTO dto) {
		teamService.createTeamDoctor(dto);
	}

	@PostMapping(value = "/administration/team/nurse")
	public void createNurseTeam(@RequestBody TeamDTO dto) {
		teamService.createTeamNurse(dto);
	}

	@PostMapping(value = "/administration/ward")
	public void createWard(@RequestBody WardDTO dto) {
		wardService.createWard(dto);
	}

	@GetMapping(value = "/administration/team/doctor/{id}")
	public DoctorTeam getDoctorTeam(@PathVariable Long id) {
		return teamService.getTeamDoctor(id);
	}

	@GetMapping(value = "/administration/team/nurse/{id}")
	public NurseTeam getNurseTeam(@PathVariable Long id) {
		return teamService.getTeamNurse(id);
	}

	@GetMapping(value = "/administration/ward/{id}")
	public Ward getWard(@PathVariable Long id) {
		return wardService.getWard(id);
	}

}
