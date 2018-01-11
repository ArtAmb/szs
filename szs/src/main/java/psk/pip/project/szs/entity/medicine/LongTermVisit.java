package psk.pip.project.szs.entity.medicine;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private Time startTime;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	@JsonFormat(pattern = "HH:mm:ss")
	private Time endTime;
	@Builder.Default
	private Boolean isEnd = false;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "long_term_visit")
	private Collection<NurseAction> actions;

}
