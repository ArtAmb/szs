package psk.pip.project.szs.entity.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
@Deprecated
public class Nurse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

}
