package psk.pip.project.szs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psk.pip.project.szs.dto.medicine.DrugDTO;
import psk.pip.project.szs.services.rooms.StorageService;

@RestController
public class StorageController {

	@Autowired
	private StorageService storageService;

	@PostMapping("/storage/add/new/drug")
	public void addNewDrug(@RequestBody DrugDTO dto) {
		storageService.addNewDrug(dto);
	}

}
