package psk.pip.project.szs.entity.patient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity

public class Referral {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long idDoctor;
	private Long idReferralType;
	private String description;
}
