package psk.pip.project.szs.services.medicine;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.medicine.ExaminationTypeDTO;
import psk.pip.project.szs.dto.medicine.MeasurementTypeDTO;
import psk.pip.project.szs.dto.medicine.TemplateDTO;
import psk.pip.project.szs.entity.medicine.ExaminationType;
import psk.pip.project.szs.entity.medicine.MeasurementRoot;
import psk.pip.project.szs.entity.medicine.MeasurementTemplateRoot;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.medicine.ExaminationTypeRepository;
import psk.pip.project.szs.repository.medicine.MeasurementRootRepository;
import psk.pip.project.szs.repository.medicine.MeasurementTemplateRootRepository;
import psk.pip.project.szs.repository.medicine.MeasurementTypeRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.medicine.exception.CannotGetMeasurementType;

@Service
public class MedicalActionService {

	@Autowired
	private ExaminationTypeRepository examinationTypeRepo;
	@Autowired
	private MeasurementTypeRepository measurementTypeRepo;
	// private ExaminationRepository examinationRepo;
	@Autowired
	private MeasurementRootRepository measurementRootRepo;
	@Autowired
	private MeasurementTemplateRootRepository measurementTemplateRootRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PatientCardRepository patientRepo;

	public void addExaminationType(ExaminationTypeDTO dto) {

		ExaminationType examinationType = new ExaminationType();
		examinationType.setName(dto.getExaminationType());

		examinationTypeRepo.save(examinationType);

	}

	public void addMeasurementType(MeasurementTypeDTO dto) {

		MeasurementType measurementType = new MeasurementType();
		measurementType.setName(dto.getMeasurementType());

		measurementTypeRepo.save(measurementType);

	}

	public MeasurementType getMeasurementType(Long id) {
		MeasurementType measurementType = measurementTypeRepo.findOne(id);
		if (measurementType == null)
			throw new CannotGetMeasurementType("Nie znaleziono pomiaru o ID = " + id);

		return measurementType;
	}

	/*
	 * public void saveExamination(ExaminationDTO dto) {
	 * 
	 * Examination examination = new Examination();
	 * 
	 * 
	 * Examination.setStartDate(dto.getStartDate());
	 * Examination.setEndDate(dto.getEndDate());
	 * Examination.setTypeOfExamination(dto.getTypeOfExamination());
	 * Examination.setEndDate(dto.getEndDate());
	 * 
	 * examinationRepo.save(examination);
	 * 
	 * 
	 * }
	 */

	public void saveMeasurement(MeasurementRoot root) {
		root.setDate(Timestamp.valueOf(LocalDateTime.now()));
		measurementRootRepo.save(root);
	}

	public Collection<MeasurementTemplateRoot> getMeasurementTemplates(String login) {
		User user = userRepo.findByLogin(login);
		return measurementTemplateRootRepo.findByUser(user);
	}

	public Collection<MeasurementRoot> getMeasurements(Long patientID) {
		PatientCard patient = patientRepo.findOne(patientID);
		return measurementRootRepo.findByPatient(patient);
	}

	public void deleteMeasurementTemplate(Long measurementTemplateID) {
		measurementTemplateRootRepo.delete(measurementTemplateID);
	}

	public void addMeasurementTemplate(MeasurementTemplateRoot root) {
		measurementTemplateRootRepo.save(root);
	}

	public MeasurementTemplateRoot getMeasurementTemplate(Long id) {
		return measurementTemplateRootRepo.findOne(id);
	}

	public Collection<TemplateDTO> getTemplates(String login) {
		return getMeasurementTemplates(login).stream().map(root -> TemplateMapper.map(root))
				.collect(Collectors.toList());
	}
}
