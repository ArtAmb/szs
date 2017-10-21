package psk.pip.project.szs.services.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2426373914050094904L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String msg) {
		super(msg);
	}

	public UserNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
