package psk.pip.project.szs.services.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotAddVisit extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CannotAddVisit (){
		super();
	}

	public CannotAddVisit(String msg) {
		super(msg);
	}

	public CannotAddVisit(String msg, Throwable cause) {
		super(msg, cause);
	}
}
