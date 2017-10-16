package psk.pip.project.szs.entity.medicine;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LongTermVisit {

	@Id
	private Long id;

	private Date start;
	private Date stop;

}
