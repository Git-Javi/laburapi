package app.controller.exception;

import java.io.IOException;

import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public void handleConstraintViolationException(ConstraintViolationException cve, ServletWebRequest webRequest) throws IOException {
		webRequest.getResponse().
		sendError(HttpStatus.BAD_REQUEST.value(), cve.getMessage());
	}

}
