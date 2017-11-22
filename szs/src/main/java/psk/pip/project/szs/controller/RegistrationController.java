package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.registration.LoginDTO;
import psk.pip.project.szs.dto.registration.ModifyUserDTO;
import psk.pip.project.szs.dto.registration.RolesDTO;
import psk.pip.project.szs.dto.registration.UserDTO;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.entity.registration.mapper.UserMapper;
import psk.pip.project.szs.services.registration.RegistrationService;

@RestController
@RequestMapping("/user/")
public class RegistrationController {

	@Autowired
	private RegistrationService service;

	@PostMapping("new")
	@ResponseStatus(value = HttpStatus.CREATED)
	void createNewUser(@RequestBody UserDTO userDTO) {
		service.addUser(UserMapper.map(userDTO));
	}

	@DeleteMapping("{id}")
	void deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
	}

	@PostMapping("{id}")
	void changeUserPassword(@PathVariable Long id, ModifyUserDTO dto, Authentication auth) {
		String login = auth.getName();
		service.changeUserPassword(login, dto);
	}

	@GetMapping("{id}")
	User getUser(@PathVariable Long id, Authentication auth) {
		String login = auth.getName();

		return service.tryToGetUser(id, login);
	}

	@PreAuthorize("hasRole('" + Roles.Consts.ROLE_ADMIN + "')")
	@PostMapping("{id}/roles")
	void modifyRoles(@PathVariable Long id, @RequestBody RolesDTO roles) {
		service.changeRoles(id, roles);
	}

	@PostMapping("/login")
	void login(@RequestBody LoginDTO loginDTO) {
		service.login(loginDTO);
	}

}
