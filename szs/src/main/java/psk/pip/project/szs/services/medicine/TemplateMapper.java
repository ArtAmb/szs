package psk.pip.project.szs.services.medicine;

import psk.pip.project.szs.dto.medicine.TemplateDTO;
import psk.pip.project.szs.entity.medicine.MeasurementTemplateRoot;

public class TemplateMapper {

	public static TemplateDTO map(MeasurementTemplateRoot root) {
		return TemplateDTO.builder().id(root.getId()).title(root.getTitle()).build();
	}
}
