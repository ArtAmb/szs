package psk.pip.project.szs.repository.systemUser;

import org.springframework.data.repository.CrudRepository;

import psk.pip.project.szs.entity.registration.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	public Role findByName(String name);
}
