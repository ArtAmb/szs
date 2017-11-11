package psk.pip.project.szs.repository.systemUser;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.registration.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByLogin(String login);
}
