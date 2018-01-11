package psk.pip.project.szs.services.medicine.strategy;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import psk.pip.project.szs.entity.medicine.MeasurementRoot;
import psk.pip.project.szs.entity.medicine.NurseAction;

@Service
public class RootMeasurementSaver extends NurseActionSaverStrategy<MeasurementRoot> {

	@Override
	protected void createNurseAction(MeasurementRoot obj) {
		nurseAction = NurseAction.builder().description(obj.getDescription()).medicalEmployee(medicalEmployee)
				.measurementRoot(obj).date(new Timestamp(obj.getDate())).build();
	}

	@Override
	protected boolean validateObjectAndSetAdditionalData(MeasurementRoot object) {
		patientCard = object.getPatient();
		return true;
	}

}
