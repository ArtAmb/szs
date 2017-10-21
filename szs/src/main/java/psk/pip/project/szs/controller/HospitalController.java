package psk.pip.project.szs.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.entity.registration.Roles;

@RestController
public class HospitalController {

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

}
