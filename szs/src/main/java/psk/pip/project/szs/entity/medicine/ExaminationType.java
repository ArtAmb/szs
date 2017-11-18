package psk.pip.project.szs.entity.medicine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class ExaminationType {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	
	private Long id;
	private String examinationType;
	

}