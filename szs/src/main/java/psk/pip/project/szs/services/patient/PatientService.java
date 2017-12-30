package psk.pip.project.szs.services.patient;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.patient.PersonalDataDTO;
import psk.pip.project.szs.dto.patient.ReferralDTO;
import psk.pip.project.szs.dto.patient.ReferralTypeDTO;
import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.employee.Doctor;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.patient.Referral;
import psk.pip.project.szs.entity.patient.ReferralType;
import psk.pip.project.szs.entity.patient.Visit;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.repository.employee.DoctorRepository;
import psk.pip.project.szs.repository.patient.LongTermVisitRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.ReferralRepository;
import psk.pip.project.szs.repository.patient.ReferralTypeRepository;
import psk.pip.project.szs.repository.patient.VisitRepository;
import psk.pip.project.szs.services.patient.exception.CannotAddVisitException;
import psk.pip.project.szs.services.patient.exception.CannotDeleteVisit;
import psk.pip.project.szs.services.patient.exception.CannotGetPatientCard;
import psk.pip.project.szs.services.patient.exception.CannotRegisterReferral;
import psk.pip.project.szs.services.patient.mapper.LongTermVisitMapper;
import psk.pip.project.szs.services.patient.mapper.VisitMapper;

@Service
public class PatientService {

	@Autowired
	private PatientCardRepository patientCardRepo;

	@Autowired
	private VisitRepository visitRepo;

	@Autowired
	private LongTermVisitRepository longTermVisitRepo;

	@Autowired
	private DoctorRepository doctorRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ReferralTypeRepository referralTypeRepo;

	@Autowired
	private ReferralRepository referralRepo;

	public void addPatientCard(PersonalDataDTO patient) {
		PatientCard patientCard = new PatientCard();
		patientCard.setName(patient.getName());
		patientCard.setSurname(patient.getSurname());
		patientCardRepo.save(patientCard);
	}

	public PatientCard getPatientCard(Long id) {
		PatientCard patientCard = patientCardRepo.findOne(id);
		if (patientCard == null)
			throw new CannotGetPatientCard("Nie znaleziono karty pacjenta o ID = " + id);

		return patientCard;
	}

	public Collection<PatientCard> getPatientCards() {
		return patientCardRepo.findAll();
	}

	public void addVisit(VisitDTO dto) {
		Employee doctor = employeeRepository.findDoctorById(dto.getDoctorId());
		if (doctor == null)
			throw new CannotAddVisitException("Nie znaleziono doktora o ID = " + dto.getDoctorId());
		dto.setDoctor(doctor);

		PatientCard patientCard = patientCardRepo.findOne(dto.getPatientCardId());
		if (patientCard == null)
			throw new CannotAddVisitException("Nie znaleziono karty pacjenta o ID = " + dto.getPatientCardId());

		if (dto.getIsLongTermVisit()) {
			patientCard.getLongTermVisits().add(LongTermVisitMapper.map(dto));
			patientCardRepo.save(patientCard);
		}
		patientCard.getVisits().add(VisitMapper.map(dto));
		patientCardRepo.save(patientCard);
	}

	public void addLongTermVisit(VisitDTO dto) {

	}

	public void deleteVisit(Long id) {
		Visit visit = visitRepo.findOne(id);

		if (visit == null)
			throw new CannotDeleteVisit("Nie znaleziono wizyty o ID = " + id);

		visitRepo.delete(visit);
	}

	public void addReferralType(ReferralTypeDTO dto) {
		ReferralType referralType = new ReferralType();
		referralType.setType(dto.getType());
		referralTypeRepo.save(referralType);
	}

	public void registerReferral(ReferralDTO dto) {
		Referral referral = new Referral();

		if (dto.getIdDoctor() != null) {
			Doctor doctor = doctorRepo.findOne(dto.getIdDoctor());
			if (doctor == null)
				throw new CannotRegisterReferral("Nie znaleziono doktora o ID = " + dto.getIdDoctor());
		}

		ReferralType referralType = referralTypeRepo.findOne(dto.getIdReferralType());
		if (referralType == null)
			throw new CannotRegisterReferral("Nie znaleziono typu skierowania o ID = " + dto.getIdReferralType());

		referral.setIdDoctor(dto.getIdDoctor());
		referral.setIdReferralType(dto.getIdReferralType());
		referral.setDescription(dto.getDescription());
		referralRepo.save(referral);
	}

	public Patient findPatientByCardId(Long id) {
		return patientCardRepo.findPatientById(id);
	}

}
