package psk.pip.project.szs.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.entity.administration.DoctorSpecialization;
import psk.pip.project.szs.entity.medicine.DrugName;
import psk.pip.project.szs.entity.medicine.ExaminationType;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.entity.medicine.Unit;
import psk.pip.project.szs.services.medicine.ConfigService;

@RestController
public class ConfigController {
	@Autowired
	private ConfigService service;

	@PostMapping("/config/doctor-specialization/create/or/update")
	public void addDoctorSpecialization(@RequestBody DoctorSpecialization dto) {
		service.addDoctorSpecialization(dto);
	}

	@GetMapping("/config/doctor-specialization/all")
	public Collection<DoctorSpecialization> findAllDoctorSpecialization() {
		return service.findAllDoctorSpecialization();
	}

	@PostMapping("/config/drug-unit/create/or/update")
	public void addDrugUnitRepo(@RequestBody Unit dto) {
		service.addDrugUnitRepo(dto);
	}

	@GetMapping("/config/drug-unit/all")
	public Collection<Unit> findAllDrugUnit() {
		return service.findAllDrugUnit();
	}

	@PostMapping("/config/drug-name/create/or/update")
	public void addDrugName(@RequestBody DrugName dto) {
		service.addDrugName(dto);
	}

	@GetMapping("/config/drug-name/all")
	public Collection<DrugName> findAllDrugName() {
		return service.findAllDrugName();
	}

	@PostMapping("/config/examination-type/create/or/update")
	public void addExaminationType(@RequestBody ExaminationType dto) {
		service.addExaminationType(dto);
	}

	@GetMapping("/config/examination-type/all")
	public Collection<ExaminationType> findAllExaminationType() {
		return service.findAllExaminationType();
	}

	@PostMapping("/config/measurement-type/create/or/update")
	public void addMeasurementType(@RequestBody MeasurementType dto) {
		service.addMeasurementType(dto);
	}

	@GetMapping("/config/measurement-type/all")
	public Collection<MeasurementType> getAllMeasurementType() {
		return service.findAllMeasurementType();
	}
}
