package psk.pip.project.szs.services.patient;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import psk.pip.project.szs.dto.patient.PersonalDataDTO;
import psk.pip.project.szs.dto.patient.ReferralDTO;
import psk.pip.project.szs.dto.patient.ReferralTypeDTO;
import psk.pip.project.szs.dto.patient.SignInOutDTO;
import psk.pip.project.szs.dto.patient.VisitDTO;
import psk.pip.project.szs.dto.searcher.SearcherMapper;
import psk.pip.project.szs.dto.searcher.SearcherResponse;
import psk.pip.project.szs.entity.administration.Employee;
import psk.pip.project.szs.entity.employee.Doctor;
import psk.pip.project.szs.entity.medicine.LongTermVisit;
import psk.pip.project.szs.entity.patient.PatientCard;
import psk.pip.project.szs.entity.patient.Referral;
import psk.pip.project.szs.entity.patient.ReferralType;
import psk.pip.project.szs.entity.patient.Visit;
import psk.pip.project.szs.entity.storage.HospitalRoom;
import psk.pip.project.szs.repository.administration.EmployeeRepository;
import psk.pip.project.szs.repository.employee.DoctorRepository;
import psk.pip.project.szs.repository.patient.LongTermVisitRepository;
import psk.pip.project.szs.repository.patient.PatientCardRepository;
import psk.pip.project.szs.repository.patient.ReferralRepository;
import psk.pip.project.szs.repository.patient.ReferralTypeRepository;
import psk.pip.project.szs.repository.patient.VisitRepository;
import psk.pip.project.szs.repository.storage.RoomRepository;
import psk.pip.project.szs.services.patient.exception.CannotAddVisitException;
import psk.pip.project.szs.services.patient.exception.CannotDeleteVisit;
import psk.pip.project.szs.services.patient.exception.CannotGetPatientCard;
import psk.pip.project.szs.services.patient.exception.CannotRegisterReferral;
import psk.pip.project.szs.services.patient.exception.SignInOutException;
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

	@Autowired
	private RoomRepository hospitalRoomRepo;

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

	public Collection<LongTermVisit> getLongTermVisits(Long id) {
		return patientCardRepo.findOne(id).getLongTermVisits().stream().collect(Collectors.toList());
	}

	public Collection<LongTermVisit> getLongTermVisits(Long id, Boolean isVisitEnd) {
		return patientCardRepo.findOne(id).getLongTermVisits().stream().filter(ltv -> ltv.getIsEnd().equals(isVisitEnd))
				.collect(Collectors.toList());
	}

	public Collection<Visit> getVisits(Long id) {
		return patientCardRepo.findOne(id).getVisits().stream().collect(Collectors.toList());
	}

	public Collection<Visit> getVisits(Long id, Boolean isVisitEnd) {
		return patientCardRepo.findOne(id).getVisits().stream().filter(v -> v.getIsEnd().equals(isVisitEnd))
				.collect(Collectors.toList());
	}

	public void addVisit(VisitDTO dto) {
		Employee doctor = employeeRepository.findDoctorById(dto.getDoctorId());
		PatientCard patientCard = patientCardRepo.findOne(dto.getPatientCardId());

		if (doctor == null)
			throw new CannotAddVisitException("Nie znaleziono doktora o ID = " + dto.getDoctorId());
		if (patientCard == null)
			throw new CannotAddVisitException("Nie znaleziono karty pacjenta o ID = " + dto.getPatientCardId());

		dto.setDoctor(doctor);

		patientCard.getVisits().add(VisitMapper.map(dto));

		patientCardRepo.save(patientCard);
	}

	public void endVisit(Long id) {
		Visit v = visitRepo.findOne(id);
		v.setIsEnd(true);
		visitRepo.save(v);
	}

	public void addLongTermVisit(VisitDTO dto) {
		PatientCard patientCard = patientCardRepo.findOne(dto.getPatientCardId());

		if (patientCard == null)
			throw new CannotAddVisitException("Nie znaleziono karty pacjenta o ID = " + dto.getPatientCardId());

		LongTermVisit currentVisit = LongTermVisitMapper.map(dto);
		patientCard.getLongTermVisits().add(currentVisit);

		patientCardRepo.save(patientCard);
	}

	public void signInToHospital(SignInOutDTO dto) {
		LongTermVisit longTermVisit = longTermVisitRepo.findOne(dto.getLongTermVisitId());
		PatientCard card = patientCardRepo.findOne(dto.getPatientCardId());

		if (card.getCurrentVisit() != null) {
			throw new SignInOutException("Prosze zakonczyc obecny pobyt");
		}

		if (longTermVisit.getIsEnd() == true) {
			throw new SignInOutException("Ta wizyta zostala juz zakonczona");
		}

		card.setCurrentVisit(longTermVisit);
		patientCardRepo.save(card);
	}

	public void signOutFromHospital(SignInOutDTO dto) {
		LongTermVisit longTermVisit = longTermVisitRepo.findOne(dto.getLongTermVisitId());
		PatientCard card = patientCardRepo.findOne(dto.getPatientCardId());

		if (card.getCurrentVisit() == null) {
			throw new SignInOutException("Pacject obecnie nie przebywa w szpitalu");
		}

		if (longTermVisit.getIsEnd() == true) {
			throw new SignInOutException("Ta wizyta zostala juz zakonczona");
		}

		card.setCurrentVisit(null);
		card.setRoom(null);
		longTermVisit.setIsEnd(true);
		longTermVisitRepo.save(longTermVisit);
		patientCardRepo.save(card);
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

	public void setPatientInRoom(Long patientId, Long roomId) {
		PatientCard card = patientCardRepo.findOne(patientId);
		HospitalRoom room = hospitalRoomRepo.findOne(roomId);
		if (room == null)
			throw new RuntimeException("Sala o id == " + roomId + " nie istnieje");
		card.setRoom(room);
		patientCardRepo.save(card);
	}

	public Collection<SearcherResponse> findPatientsByQueryStr(String query) {
		return patientCardRepo.findPatientsByQuery(query).stream().map(e -> SearcherMapper.map(e))
				.collect(Collectors.toList());
	}

	// public Collection<Patient> findMyPatients(Long doctorId){
	// Colletion<Visit> visits = visitRepo.findByDoctorId(doctorId);
	// patientCardRepo.findBy
	// }

}
