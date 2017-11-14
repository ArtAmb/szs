package psk.pip.project.szs.services.medicine.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CannotGetMeasurementType extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CannotGetMeasurementType(){
		super();
	}

	public CannotGetMeasurementType(String msg) {
		super(msg);
	}

	public CannotGetMeasurementType(String msg, Throwable cause) {
		super(msg, cause);
	}
}