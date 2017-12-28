package psk.pip.project.szs.entity.medicine;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import psk.pip.project.szs.entity.registration.User;

@Entity
@Data
public class MeasurementTemplateRoot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;
	private String description;

	@ManyToOne
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	private Measurement measurement;
}