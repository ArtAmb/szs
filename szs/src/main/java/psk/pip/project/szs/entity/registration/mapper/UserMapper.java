package psk.pip.project.szs.entity.registration.mapper;

import psk.pip.project.szs.dto.registration.UserDTO;
import psk.pip.project.szs.entity.registration.User;

public class UserMapper {

	public static User map(UserDTO userDTO) {
		User user = new User();
		user.setLogin(userDTO.getLogin());
		user.setPassword(userDTO.getPassword());
		return user;
	}

}
