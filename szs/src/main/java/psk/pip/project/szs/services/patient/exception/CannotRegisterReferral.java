package psk.pip.project.szs.services.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotRegisterReferral extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CannotRegisterReferral(){
		super();
	}

	public CannotRegisterReferral(String msg) {
		super(msg);
	}

	public CannotRegisterReferral(String msg, Throwable cause) {
		super(msg, cause);
	}
}
