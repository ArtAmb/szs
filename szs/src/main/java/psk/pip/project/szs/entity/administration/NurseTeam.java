package psk.pip.project.szs.entity.administration;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "nurse_team")
public class NurseTeam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@ManyToOne
	protected Employee leader;
	@OneToMany
	@JoinColumn(name = "nurse_team")
	protected Collection<Employee> members;

	private boolean inWard = false;
}
