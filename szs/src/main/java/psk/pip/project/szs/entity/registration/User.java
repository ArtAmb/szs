package psk.pip.project.szs.entity.registration;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, nullable = true)
	private String login;

	@Column(nullable = true)
	private String password;

	@Enumerated(EnumType.STRING)
	@ManyToMany
	private List<Role> roles = new LinkedList<>();

	private boolean active = true;

	public boolean hasRole(String roleName) {
		for (Role role : roles) {
			if (role.equals(roleName))
				return true;
		}
		return false;
	}
}
