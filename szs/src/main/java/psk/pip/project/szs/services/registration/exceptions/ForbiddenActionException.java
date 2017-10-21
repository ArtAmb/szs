package psk.pip.project.szs.services.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN) // czy unauthorized ?
public class ForbiddenActionException extends RuntimeException {
	private static final long serialVersionUID = 2426373914050094904L;

	public ForbiddenActionException() {
		super();
	}

	public ForbiddenActionException(String msg) {
		super(msg);
	}

	public ForbiddenActionException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
