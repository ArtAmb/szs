package psk.pip.project.szs.entity.registration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Roles {

	ROLE_ADMIN(Consts.ROLE_ADMIN), ROLE_NURSE(Consts.ROLE_NURSE), ROLE_DOCTOR(Consts.ROLE_DOCTOR);
	public static final String ROLE_ADMIN2 = "ROLE_ADMIN";
	private final String name;
	private Role role;

	public final String getRoleName() {
		return name;
	}

	private Roles(String name) {
		this.name = name;
		role = new Role(name);
	}

	public Role toRole() {
		return role;
	}

	public static List<Role> toRoleValues() {
		return Arrays.stream(values()).map(r -> {
			return r.toRole();
		}).collect(Collectors.toList());
	}

	public class Consts {
		public static final String ROLE_ADMIN = "ROLE_ADMIN";
		public static final String ROLE_NURSE = "ROLE_NURSE";
		public static final String ROLE_DOCTOR = "ROLE_DOCTOR";

	}
}
