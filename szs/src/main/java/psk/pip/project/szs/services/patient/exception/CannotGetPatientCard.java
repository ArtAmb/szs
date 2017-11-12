package psk.pip.project.szs.services.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotGetPatientCard extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CannotGetPatientCard(){
		super();
	}

	public CannotGetPatientCard(String msg) {
		super(msg);
	}

	public CannotGetPatientCard(String msg, Throwable cause) {
		super(msg, cause);
	}
}
