package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Recipt {
	@Id
	private Long id;	
	private String reciptDescription;
}
