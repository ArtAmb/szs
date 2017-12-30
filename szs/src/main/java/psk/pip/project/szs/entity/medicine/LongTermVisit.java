package psk.pip.project.szs.entity.medicine;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "long_term_visit")
@AllArgsConstructor
@NoArgsConstructor
public class LongTermVisit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date startDate;
	private Time startTime;
	private Date endDate;
	private Time endTime;

	@OneToMany
	@JoinColumn(name = "long_term_visit")
	private Collection<NurseAction> actions;
}
