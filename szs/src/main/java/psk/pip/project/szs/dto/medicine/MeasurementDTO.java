package psk.pip.project.szs.dto.medicine;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import psk.pip.project.szs.entity.medicine.MeasurementType;


@Data
public class MeasurementDTO {
	private Long id;
	private String name;
	private String value;
	private MeasurementType unit;
	private Collection<MeasurementDTO> elements;
}
