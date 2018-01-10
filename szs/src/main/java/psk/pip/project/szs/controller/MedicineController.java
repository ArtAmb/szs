package psk.pip.project.szs.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.medicine.DrugName;
import psk.pip.project.szs.entity.medicine.Unit;
import psk.pip.project.szs.services.HospitalService;
import psk.pip.project.szs.services.medicine.ConfigService;
import psk.pip.project.szs.services.rooms.StorageService;

@RestController
public class MedicineController {

	@Autowired
	private ConfigService medicineService;

	@Autowired
	private StorageService storageService;

	@Autowired
	private HospitalService hospitalService;

	@GetMapping("/medicine/drug/units")
	public Collection<Unit> getAllDrugsUnits() {
		return null;
	}

	@PostMapping("/medicine/drug/unit")
	public void addNewUnit(@RequestBody Unit unit) {

	}

	@PostMapping("/medicine/new/drug")
	public void addNewDrug(@RequestBody DrugName name) {

	}

	@PostMapping("/medicine/drug/to/storage")
	public void addDrugsToStorage(@RequestBody Drug name) {

	}

	@PostMapping("/medicine/drug/to/room/{roomId}")
	public void addDrugsToHospotalRoom(@RequestBody Drug drug, @PathVariable Long roomId) {

	}

}
