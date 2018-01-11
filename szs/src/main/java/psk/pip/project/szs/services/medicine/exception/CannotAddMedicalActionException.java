package psk.pip.project.szs.services.medicine.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotAddMedicalActionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CannotAddMedicalActionException(){
		super();
	}

	public CannotAddMedicalActionException(String msg) {
		super(msg);
	}

	public CannotAddMedicalActionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}