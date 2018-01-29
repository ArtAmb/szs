package psk.pip.project.szs.services.medicine;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.medicine.ExaminationDTO;
import psk.pip.project.szs.dto.medicine.ExaminationTypeDTO;
import psk.pip.project.szs.dto.medicine.MeasurementTypeDTO;
import psk.pip.project.szs.dto.medicine.TemplateDTO;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.medicine.Examination;
import psk.pip.project.szs.entity.medicine.ExaminationType;
import psk.pip.project.szs.entity.medicine.MeasurementRoot;
import psk.pip.project.szs.entity.medicine.MeasurementTemplateRoot;
import psk.pip.project.szs.entity.medicine.MeasurementType;
import psk.pip.project.szs.entity.medicine.Recipt;
import psk.pip.project.szs.entity.medicine.Refferal;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.registration.User;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.repository.medicine.ExaminationRepository;
import psk.pip.project.szs.repository.medicine.ExaminationTypeRepository;
import psk.pip.project.szs.repository.medicine.MeasurementRootRepository;
import psk.pip.project.szs.repository.medicine.MeasurementTemplateRootRepository;
import psk.pip.project.szs.repository.medicine.MeasurementTypeRepository;
import psk.pip.project.szs.repository.medicine.ReciptRepository;
import psk.pip.project.szs.repository.medicine.RefferalRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.systemUser.UserRepository;
import psk.pip.project.szs.services.medicine.exception.CannotAddMedicalActionException;
import psk.pip.project.szs.services.medicine.exception.CannotGetMeasurementType;
import psk.pip.project.szs.services.medicine.strategy.GivenDrugSaver;
import psk.pip.project.szs.services.medicine.strategy.RootMeasurementSaver;

@Service
public class MedicalActionService {

	@Autowired
	private ExaminationTypeRepository examinationTypeRepo;
	@Autowired
	private MeasurementTypeRepository measurementTypeRepo;

	@Autowired
	private ExaminationRepository examinationRepo;
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private ReciptRepository reciptRepo;
	@Autowired
	private RefferalRepository refferalRepo;
	@Autowired
	private MeasurementRootRepository measurementRootRepo;
	@Autowired
	private MeasurementTemplateRootRepository measurementTemplateRootRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PatientCardRepository patientRepo;
	@Autowired
	private RootMeasurementSaver rootMeasurementSaver;
	@Autowired
	private GivenDrugSaver givenDrugSaver;

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

	public void saveMeasurement(String login, MeasurementRoot root) {
		root.setDate(Timestamp.valueOf(LocalDateTime.now()).getTime());
		root.setPatient(patientRepo.findOne(root.getPatient().getId()));
		if (root.getPatient().getCurrentVisit() == null)
			throw new CannotAddMedicalActionException("Pacjent musi byc zapisany do szpitala");

		rootMeasurementSaver.saveNurseAction(login, root);
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

	public void saveGivenDrug(String login, GivenDrug givenDrug) {
		givenDrugSaver.saveNurseAction(login, givenDrug);
	}

	public void saveExamination(String login, ExaminationDTO dto) {
		Employee medicalEmployee;
		User user = userRepo.findByLogin(login);
		medicalEmployee = employeeRepo.findByUser(user);

		Examination examination = new Examination();

		examination.setEmployee(medicalEmployee);
		examination.setStartDate(dto.getStartDate());
		examination.setStartTime(dto.getStartTime());
		examination.setExaminationDescription(dto.getExaminationDescription());

		examination.setExaminationType(dto.getExaminationType());

		Recipt recipt = new Recipt();
		recipt.setReciptDescription(dto.getReciptDescription());
		examination.setRecipt(recipt);

		Refferal refferal = new Refferal();
		refferal.setRefferalType(dto.getRefferalType());
		refferal.setRefferalDescription(dto.getRefferalDescription());
		examination.setRefferal(refferal);

		reciptRepo.save(recipt);
		refferalRepo.save(refferal);
		examinationRepo.save(examination);

	}
}
