package psk.pip.project.szs.services.registration;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import psk.pip.project.szs.dto.registration.LoginDTO;
import psk.pip.project.szs.dto.registration.ModifyUserDTO;
import psk.pip.project.szs.dto.registration.RolesDTO;
import psk.pip.project.szs.entity.registration.Role;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.systemUser.RoleRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.registration.exceptions.ForbiddenActionException;
import psk.pip.project.szs.services.registration.exceptions.InvalidOldUserPasswordException;
import psk.pip.project.szs.services.registration.exceptions.UserNotFoundException;

@Service
public class RegistrationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public void deleteUser(Long id) {
		if (userRepository.exists(id) == false)
			throw new UserNotFoundException();
		userRepository.delete(id);
	}

	public void addUser(User user) {
		saveUser(user);
	}

	public void changeUserPassword(String login, ModifyUserDTO dto) {
		User user = userRepository.findByLogin(login);
		if (encoder.matches(dto.getOldPassword(), user.getPassword()) == false)
			throw new InvalidOldUserPasswordException();

		user.setPassword(encoder.encode(dto.getNewPassword()));
		userRepository.save(user);
	}

	public void updateUser(User user) {
		if (userRepository.exists(user.getId()) == false)
			throw new UserNotFoundException();
		saveUser(user);
	}

	private void saveUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public User tryToGetUser(Long id, String login) {
		User userFromId = userRepository.findOne(id);
		if (userFromId == null)
			throw new UserNotFoundException();
		User userFromLogin = userRepository.findByLogin(login);

		if (userFromId.getId().equals(userFromLogin.getId()) || userFromLogin.hasRole(Roles.ROLE_ADMIN.getRoleName()))
			return userFromId;

		throw new ForbiddenActionException();
	}

	public void changeRoles(Long id, RolesDTO dto) {
		User user = userRepository.findOne(id);
		if (user == null)
			throw new UserNotFoundException();

		List<Role> roleList = dto.getRoleNames().stream().map(roleName -> {
			return roleRepository.findByName(roleName);
		}).collect(Collectors.toList());

		user.setRoles(roleList);
		userRepository.save(user);
	}

	public void login(LoginDTO dto) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuffer url = new StringBuffer("http://localhost:8080/login?login=");
		url.append(dto.getLogin());
		url.append("&password=");
		url.append(dto.getPassword());

		restTemplate.postForEntity(url.toString(), null, null);
	}

}
