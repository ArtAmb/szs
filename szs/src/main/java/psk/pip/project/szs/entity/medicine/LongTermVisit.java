package psk.pip.project.szs.entity.medicine;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "long_term_visit")
public class LongTermVisit {

	@Id
	private Long id;

	private Date start;
	private Date stop;

	@OneToMany
	@JoinColumn(name = "long_term_visit")
	private Collection<NurseAction> actions;
}
