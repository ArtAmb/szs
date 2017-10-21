package psk.pip.project.szs;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import psk.pip.project.szs.entity.registration.Role;
import psk.pip.project.szs.entity.registration.Roles;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.RoleRepository;
import psk.pip.project.szs.repository.UserRepository;

@Component
public class Initializer {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@PostConstruct
	void init() {
		for (Role role : Roles.toRoleValues()) {
			roleRepo.save(role);
		}

		List<Role> col = new LinkedList<>();
		col.add(Roles.ROLE_ADMIN.toRole());
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		userRepo.save(new User(1l, "user1", encoder.encode("test"), col, true));

		col = new LinkedList<>();
		col.add(Roles.ROLE_DOCTOR.toRole());
		col.add(Roles.ROLE_NURSE.toRole());
		userRepo.save(new User(2l, "user2", encoder.encode("test"), col, true));

		col = new LinkedList<>();
		col.add(Roles.ROLE_NURSE.toRole());
		userRepo.save(new User(3l, "user3", encoder.encode("test"), col, true));

	}

}
