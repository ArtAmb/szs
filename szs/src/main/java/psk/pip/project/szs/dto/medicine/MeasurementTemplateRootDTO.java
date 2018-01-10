package psk.pip.project.szs.dto.medicine;

import lombok.Data;
import psk.pip.project.szs.entity.medicine.Measurement;
import psk.pip.project.szs.entity.registration.User;

@Data
public class MeasurementTemplateRootDTO {
	private Long id;
	private String title;
	private String description;
	private User user;
	private Measurement measurement;
}