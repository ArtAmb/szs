package psk.pip.project.szs.services.medicine;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import psk.pip.project.szs.entity.administration.DoctorSpecialization;
import psk.pip.project.szs.entity.medicine.Drug;
import psk.pip.project.szs.entity.medicine.DrugName;
import psk.pip.project.szs.entity.medicine.ExaminationType;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.entity.medicine.Unit;
import psk.pip.project.szs.repository.administration.DoctorSpecializationRepository;
import psk.pip.project.szs.repository.medicine.DrugNameRepository;
import psk.pip.project.szs.repository.medicine.DrugRepository;
import psk.pip.project.szs.repository.medicine.DrugUnitRepository;
import psk.pip.project.szs.repository.medicine.ExaminationTypeRepository;
import psk.pip.project.szs.repository.medicine.MeasurementTypeRepository;

@Service
public class ConfigService {

	@Autowired
	private DrugNameRepository drugNameRepo;

	@Autowired
	private DrugUnitRepository drugUnitRepo;

	@Autowired
	private DrugRepository drugRepo;

	@Autowired
	private DoctorSpecializationRepository specRepo;

	@Autowired
	private MeasurementTypeRepository measurementTypeRepo;

	@Autowired
	private MedicalActionService medicalActionService;

	@Autowired
	private ExaminationTypeRepository examinationTypeRepository;

	public void addDoctorSpecialization(DoctorSpecialization dto) {
		specRepo.save(dto);
	}

	public Collection<DoctorSpecialization> findAllDoctorSpecialization() {
		return specRepo.findAll();
	}

	public void addDrugUnitRepo(Unit dto) {
		drugUnitRepo.save(dto);
	}

	public Collection<Unit> findAllDrugUnit() {
		return drugUnitRepo.findAll();
	}

	public void addDrugName(DrugName dto) {
		drugNameRepo.save(dto);
	}

	public Collection<DrugName> findAllDrugName() {
		return drugNameRepo.findAll();
	}

	public void addExaminationType(@RequestBody ExaminationType dto) {
		examinationTypeRepository.save(dto);
	}

	public Collection<ExaminationType> findAllExaminationType() {
		return examinationTypeRepository.findAll();
	}

	public void addMeasurementType(@RequestBody MeasurementType dto) {
		measurementTypeRepo.save(dto);
	}

	public Collection<MeasurementType> findAllMeasurementType() {
		return measurementTypeRepo.findAll();
	}

	public void addDrug(Drug dto) {
		dto.setId(null);
		dto.setAmount(null);
		if (dto.getDosage() < 0)
			throw new RuntimeException("Dawka leku nie moze byc ujemna.");
		Collection<Drug> col = drugRepo.findByNameAndUnitAndDosageAndAmountIsNull(dto.getName(), dto.getUnit(),
				dto.getDosage());
		if (!col.isEmpty())
			throw new RuntimeException("Lek " + dto.toString() + " zostal juz skonfigurowany.");
		drugRepo.save(dto);
	}

	public Collection<Drug> getAllConfigDrugs() {
		return drugRepo.findByAmount(null);
	}
}
