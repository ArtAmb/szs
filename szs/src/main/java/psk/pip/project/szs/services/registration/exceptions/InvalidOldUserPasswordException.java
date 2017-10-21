package psk.pip.project.szs.services.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED) // do zastanowenia TODO
public class InvalidOldUserPasswordException extends RuntimeException {
	private static final long serialVersionUID = 2426373914050094904L;

	public InvalidOldUserPasswordException() {
		super();
	}

	public InvalidOldUserPasswordException(String msg) {
		super(msg);
	}

	public InvalidOldUserPasswordException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
