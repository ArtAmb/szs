package psk.pip.project.szs.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Builder;
import lombok.Data;
import psk.pip.project.szs.services.medicine.ConfigService;

@Data
@Builder
class Urls {
	private String add;
	private String delete;
	private String edit;
}

@Controller
public class ConfigFrontController {

	@Autowired
	private ConfigService service;

	public static String templateDirRoot = "config/";

	private String getTemplateDir(String templateName) {
		return templateDirRoot + templateName;
	}

	@GetMapping("/view/config")
	public String patientView(Model model) {
		return getTemplateDir("config");
	}

	@GetMapping("/view/config/drug-name")
	public String drugNameView(Model model) {
		Urls urls = Urls.builder().add("/config/drug-name/create/or/update").edit("/config/drug-name/create/or/update")
				.delete("").build();

		model.addAttribute("title", "Nazwy lekow");
		model.addAttribute("values", service.findAllDrugName());
		model.addAttribute("url", urls);
		return getTemplateDir("config-template");
	}

	@GetMapping("/view/config/drug-unit")
	public String drugUnitView(Model model) {
		Urls urls = Urls.builder().add("/config/drug-unit/create/or/update").edit("/config/drug-unit/create/or/update")
				.delete("").build();

		model.addAttribute("title", "Jednostki lekow");
		model.addAttribute("values", service.findAllDrugUnit());
		model.addAttribute("url", urls);
		return getTemplateDir("config-template");
	}

	@GetMapping("/view/config/doctor-specialization")
	public String docSpecView(Model model) {
		Urls urls = Urls.builder().add("/config/doctor-specialization/create/or/update")
				.edit("/config/doctor-specialization/create/or/update").delete("").build();

		model.addAttribute("title", "Specjalizacje lekarzy");
		model.addAttribute("values", service.findAllDoctorSpecialization());
		model.addAttribute("url", urls);
		return getTemplateDir("config-template");
	}

	@GetMapping("/view/config/examination-type")
	public String examinTypeView(Model model) {
		Urls urls = Urls.builder().add("/config/examination-type/create/or/update")
				.edit("/config/examination-type/create/or/update").delete("").build();

		model.addAttribute("title", "Typy badan");
		model.addAttribute("values", service.findAllExaminationType());
		model.addAttribute("url", urls);
		return getTemplateDir("config-template");
	}

	@GetMapping("/view/config/measurement-type")
	public String measureTypeView(Model model) {
		Urls urls = Urls.builder().add("/config/measurement-type/create/or/update")
				.edit("/config/measurement-type/create/or/update").delete("").build();

		model.addAttribute("title", "Typ pomiaru");
		model.addAttribute("values", service.findAllMeasurementType());
		model.addAttribute("url", urls);
		return getTemplateDir("config-template");
	}

}
