package psk.pip.project.szs.services.administration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotCreateWardException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CannotCreateWardException() {
		super();
	}

	public CannotCreateWardException(String msg) {
		super(msg);
	}

	public CannotCreateWardException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
