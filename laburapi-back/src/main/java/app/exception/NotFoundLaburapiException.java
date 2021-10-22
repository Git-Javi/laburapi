package app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No se ha encontrado el recurso solicitado")
public class NotFoundLaburapiException extends MainLaburapiException {

	private static final long serialVersionUID = 1L;

	public NotFoundLaburapiException(String msg) {
		super(msg);
	}

}
