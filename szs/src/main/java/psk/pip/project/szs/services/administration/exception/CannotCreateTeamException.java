package psk.pip.project.szs.services.administration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotCreateTeamException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CannotCreateTeamException() {
		super();
	}

	public CannotCreateTeamException(String msg) {
		super(msg);
	}
	
	public CannotCreateTeamException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
