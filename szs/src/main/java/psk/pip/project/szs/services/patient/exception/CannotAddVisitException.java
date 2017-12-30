package psk.pip.project.szs.services.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotAddVisitException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CannotAddVisitException (){
		super();
	}

	public CannotAddVisitException(String msg) {
		super(msg);
	}

	public CannotAddVisitException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
