package psk.pip.project.szs.services.patient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SignInOutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SignInOutException() {
		super();
	}

	public SignInOutException(String msg) {
		super(msg);
	}

	public SignInOutException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
